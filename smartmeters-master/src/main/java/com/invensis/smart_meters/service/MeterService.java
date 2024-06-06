package com.invensis.smart_meters.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.invensis.smart_meters.Dao.MeterDao;
import com.invensis.smart_meters.model.BillingMeterData;
import com.invensis.smart_meters.model.GoLiveData;
import com.invensis.smart_meters.model.InstallationData;
import com.invensis.smart_meters.model.InstalledMeterData;
import com.invensis.smart_meters.model.MeterData;
import com.invensis.smart_meters.model.OverAllBillingMeterData;
import com.invensis.smart_meters.model.OverAllInstalledMeterData;
import com.invensis.smart_meters.model.SurveyData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MeterService {
	@Autowired
	private MeterDao meterDao;
	
	// BILLING
	public Map<String, Object> getInsertedBillingMeterData(List<MeterData> meterDataList) {
		log.info("Received meterDataList in service: {}", meterDataList.size());

		int insertCount = 0;
		int existCount = 0;
		int validateCount = 0;

		List<Map<String, Object>> dataList = new ArrayList<>();
		//((ArrayList<Map<String, Object>>) dataList).ensureCapacity(500);
		//List<Map<String, Object>> dataList = new LinkedList<>();
		//List<Map<String, Object>> dataList = new ArrayList<>(meterDataList.size(500));
		Map<String, Object> responseMap = new HashMap<>();

		try {
			for (MeterData meterData : meterDataList) {
				Map<String, Object> dataMap = new HashMap<>();
				if (meterData.getUSCNO() != null && meterData.getUSCNO().length() == 13) {
					if (meterData.getBILLING_DATE() != null && !meterData.getBILLING_DATE().startsWith("01")) {
						validateCount++;
						dataMap.put("STATUS", false);
						dataMap.put("ERROR", "Invalid bill date");
						dataMap.put("DEVICE_ID", meterData.getDEVICE_ID());

					} else if (meterData.getDEVICE_ID() == null || meterData.getDEVICE_ID().isEmpty()) {
						validateCount++;
						dataMap.put("STATUS", false);
						dataMap.put("ERROR", "No Device ID");
						dataMap.put("DEVICE_ID", meterData.getDEVICE_ID());
						log.info("Device id: ", meterData.getDEVICE_ID());
					} else {
						int existingData = meterDao.recordExistOrNot(meterData.getUSCNO(), meterData.getDEVICE_ID(),
								meterData.getBILLING_DATE());
						if (existingData > 0) {
							existCount++;
							dataMap.put("STATUS", false);
							dataMap.put("ERROR", "DUPLICATE");
							dataMap.put("DEVICE_ID", meterData.getDEVICE_ID());
						} else {
							int validateData = meterDao.servicenoValidOrNot(meterData.getUSCNO());
							if (validateData > 0) {
								boolean insertSuccess = meterDao.insertMeterData(meterData);
								if (insertSuccess) {
									insertCount++;
									dataMap.put("STATUS", true);
									dataMap.put("message", "Successfully Inserted");
									dataMap.put("DEVICE_ID", meterData.getDEVICE_ID());
									log.info("Inserted MeterData: {}", meterData.toString());
								} else {
									dataMap.put("STATUS", true);
									dataMap.put("message", "Failed to insert records");
									dataMap.put("DEVICE_ID", meterData.getDEVICE_ID());
								}
							} else {
								validateCount++;
								dataMap.put("STATUS", false);
								dataMap.put("ERROR", "Not a valid service request");
								dataMap.put("DEVICE_ID", meterData.getDEVICE_ID());
							}
						}
					}
					dataList.add(dataMap);
				} else {
					validateCount++;
					dataMap.put("STATUS", false);
					dataMap.put("ERROR", "Not a valid service number");
					dataMap.put("DEVICE_ID", meterData.getDEVICE_ID());
					dataList.add(dataMap);
				}
			}

			log.info(insertCount + " Records inserted," + existCount + " Records already existed," + validateCount
					+ " Records not a valid AMISP");

			responseMap.put("httpStatus", 200);
			responseMap.put("message", insertCount + " records inserted," + existCount + " Records already existed,"
					+ validateCount + " Records not a valid AMISP");
			responseMap.put("data", dataList);

		} catch (Exception e) {
			log.error("Some error has occurred", e);
			responseMap.put("httpStatus", 500);
			responseMap.put("message", "An error occurred during data processing");
		}
		return responseMap;
	}

	// SURVEY
	public Map<String, Object> getInsertedSurveyMeterData(List<SurveyData> surveyDataList) {
		log.info("Received surveyDataList in service: {}", surveyDataList.size());
		int insertCount = 0;
		int existCount = 0;
		int validateCount = 0;

		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> responseMap = new HashMap<>();

		try {
			for (SurveyData surveyData : surveyDataList) {
				Map<String, Object> dataMap = new HashMap<>();
				if (surveyData.getUKSCNO() != null && surveyData.getUKSCNO().length() == 13) {
					int existingData = meterDao.surveyRecordExistOrNot(surveyData.getUKSCNO(), surveyData.getSCNO(),
							surveyData.getSERVICE_QR_CODE());
					if (existingData > 0) {
						existCount++;
						dataMap.put("STATUS", false);
						dataMap.put("MESSAGE", "DUPLICATE");
						dataMap.put("SERVICE_QR_CODE", surveyData.getSERVICE_QR_CODE());
						dataList.add(dataMap);

					} else {
						int validateData = meterDao.surveyValidOrNot(surveyData.getUKSCNO());
						if (surveyData.getUKSCNO() != null && validateData > 0) {
							boolean insertSuccess = meterDao.insertSurveyData(surveyData);
							if (insertSuccess) {
								insertCount++;
								dataMap.put("STATUS", true);
								dataMap.put("SERVICE_QR_CODE", surveyData.getSERVICE_QR_CODE());
								dataMap.put("message", "Valid Service number");
								dataList.add(dataMap);
							} else {
								dataMap.put("STATUS", false);
								dataMap.put("SERVICE_QR_CODE", surveyData.getSERVICE_QR_CODE());
								dataMap.put("message", "Failed to insert records");
								dataList.add(dataMap);

							}

						} else if (surveyData.getUKSCNO() == null && surveyData.getSERVICE_QR_CODE() != null) {
							meterDao.insertSurveyData(surveyData);
							insertCount++;
							dataMap.put("STATUS", true);
							dataMap.put("SERVICE_QR_CODE", surveyData.getSERVICE_QR_CODE());
							dataMap.put("message", "Unidentified Service Inserted");
							dataList.add(dataMap);

						} else {
							// database query to check old service
							int validateData1 = meterDao.oldSurveyValidOrNot(surveyData.getUKSCNO());
							if (surveyData.getUKSCNO() != null && validateData1 > 0) {
								validateCount++;
								dataMap.put("STATUS", false);
								dataMap.put("SERVICE_QR_CODE", surveyData.getSERVICE_QR_CODE());
								dataMap.put("message", "USG Change: New USC NO");
								dataList.add(dataMap);

							} else if (surveyData.getUKSCNO() == null && surveyData.getSERVICE_QR_CODE() == null) {
								validateCount++;
								dataMap.put("STATUS", false);
								dataMap.put("SERVICE_QR_CODE", surveyData.getSERVICE_QR_CODE());
								dataMap.put("message", "NOT A VALID SERVICE");
								dataList.add(dataMap);

							} else {
								validateCount++;
								dataMap.put("STATUS", false);
								dataMap.put("SERVICE_QR_CODE", surveyData.getSERVICE_QR_CODE());
								dataMap.put("message", "INVALID SERVICE REQUEST");
								dataList.add(dataMap);

							}
						}
					}
				} else {
					validateCount++;
					dataMap.put("STATUS", false);
					dataMap.put("SERVICE_QR_CODE", surveyData.getSERVICE_QR_CODE());
					dataMap.put("message", "NOT A VALID SERVICE");
					dataList.add(dataMap);
				}

			}

			responseMap.put("httpStatus", 200);
			responseMap.put("message", insertCount + " records inserted," + existCount + " Records already existed,"
					+ validateCount + " Records not a valid AMISP");
			responseMap.put("data", dataList);

		} catch (Exception e) {
			responseMap.put("httpStatus", 500);
			responseMap.put("message", "An error occurred during data processing");
		}

		return responseMap;
	}

	// INSTALLATION
	public Map<String, Object> getInstallationMeterData(List<InstallationData> insDataList) {
		log.info("Received insDataList in service: {}", insDataList.size());

		int insertCount = 0;
		int existCount = 0;
		int validateCount = 0;

		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> responseMap = new HashMap<>();

		try {
			for (InstallationData insData : insDataList) {
				Map<String, Object> dataMap = new HashMap<>();
				if (insData.getUKSCNO() != null && insData.getUKSCNO().length() == 13) {
					if (insData.getDEVICE_ID() == null || insData.getDEVICE_ID().isEmpty()) {
						validateCount++;
						dataMap.put("STATUS", false);
						dataMap.put("ERROR", "No Device ID");
						dataMap.put("DEVICE_ID", insData.getDEVICE_ID());
						dataList.add(dataMap);
					} else {
						int existingData = meterDao.insRecordExistOrNot(insData.getUKSCNO(),
								insData.getMTR_DEVICE_ID());
						if (existingData > 0) {
							existCount++;
							dataMap.put("STATUS", false);
							dataMap.put("MESSAGE", "DUPLICATE");
							dataMap.put("SERVICE_QR_CODE", insData.getDTR_STRUCTURE_CODE());
							dataList.add(dataMap);

						} else {
							int validateData = meterDao.insValidOrNot(insData.getUKSCNO());
							if (insData.getUKSCNO() != null && insData.getDEVICE_ID() != null && validateData > 0) {
								boolean insertSuccess = meterDao.insertInsData(insData);
								if (insertSuccess) {
									insertCount++;
									dataMap.put("STATUS", true);
									dataMap.put("DEVICE_ID", insData.getDEVICE_ID());
									dataMap.put("message", "Valid Service number");
									dataList.add(dataMap);
								} else {
									dataMap.put("STATUS", false);
									dataMap.put("DEVICE_ID", insData.getDEVICE_ID());
									dataMap.put("message", "Failed to insert records");
									dataList.add(dataMap);
								}

							} else if (insData.getUKSCNO() == null && insData.getDEVICE_ID() != null
									&& insData.getSERVICE_QR() != null) {
								meterDao.insertInsData(insData);
								insertCount++;
								dataMap.put("STATUS", true);
								dataMap.put("DEVICE_ID", insData.getDEVICE_ID());
								dataMap.put("message", "Unidentified Service Inserted");
								dataList.add(dataMap);

							} else {
								// database query to check old service
								int validateData1 = meterDao.oldSurveyValidOrNot(insData.getUKSCNO());

								if (insData.getUKSCNO() != null && validateData1 > 0) {
									validateCount++;
									dataMap.put("STATUS", false);
									dataMap.put("DEVICE_ID", insData.getDEVICE_ID());
									dataMap.put("message", "USG Change: New USC NO");
									dataList.add(dataMap);

								} else if (insData.getUKSCNO() == null && insData.getSERVICE_QR() == null) {
									validateCount++;
									dataMap.put("STATUS", false);
									dataMap.put("DEVICE_ID", insData.getDEVICE_ID());
									dataMap.put("message", "NOT A VALID SERVICE");
									dataList.add(dataMap);

								} else {
									validateCount++;
									dataMap.put("STATUS", false);
									dataMap.put("DEVICE_ID", insData.getDEVICE_ID());
									dataMap.put("message", "INVALID SERVICE REQUEST");
									dataList.add(dataMap);

								}
							}
						}
					}
				} else {
					validateCount++;
					dataMap.put("STATUS", false);
					dataMap.put("ERROR", "Not a valid service number");
					dataMap.put("DEVICE_ID", insData.getDEVICE_ID());
					dataList.add(dataMap);
				}
			}

			responseMap.put("httpStatus", 200);
			responseMap.put("message", insertCount + " records inserted," + existCount + " Records already existed,"
					+ validateCount + " Records not a valid AMISP");
			responseMap.put("data", dataList);

		} catch (Exception e) {
			responseMap.put("httpStatus", 500);
			responseMap.put("message", "An error occurred during data processing");
		}

		return responseMap;
	}

	// GO LIVE
	public Map<String, Object> getGoLiveMeterData(List<GoLiveData> goLiveDataList) {
		log.info("Received meterDataList in service: {}", goLiveDataList.size());

		int insertCount = 0;
		int existCount = 0;
		int validateCount = 0;

		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> responseMap = new HashMap<>();

		try {
			for (GoLiveData goLiveData : goLiveDataList) {
				Map<String, Object> dataMap = new HashMap<>();
				if (goLiveData.getDEVICE_ID() == null) {
					validateCount++;
					dataMap.put("STATUS", false);
					dataMap.put("ERROR", "No Device ID");
					dataMap.put("DEVICE_ID", goLiveData.getDEVICE_ID());
				} else {
					int existingData = meterDao.goLiveRecordExistOrNot(goLiveData.getGOLIVE_DT(),
							goLiveData.getDEVICE_ID());
					if (existingData > 0) {
						existCount++;
						dataMap.put("STATUS", false);
						dataMap.put("ERROR", "DUPLICATE");
						dataMap.put("DEVICE_ID", goLiveData.getDEVICE_ID());
					} else {
						int validateData = meterDao.goLiveValidOrNot(goLiveData.getDEVICE_ID());
						if (goLiveData.getDEVICE_ID() != null && validateData > 0) {
							meterDao.insertGoLiveData(goLiveData);
							insertCount++;
							dataMap.put("STATUS", true);
							dataMap.put("message", "GO-Live Inserted");
							dataMap.put("DEVICE_ID", goLiveData.getDEVICE_ID());
							log.info("Inserted MeterData: {}", goLiveData.toString());
						} else {
							if (goLiveData.getDEVICE_ID() != null && validateData == 0) {
								validateCount++;
								dataMap.put("STATUS", false);
								dataMap.put("ERROR", "Not In Installation Master");
								dataMap.put("DEVICE_ID", goLiveData.getDEVICE_ID());

							} else {
								validateCount++;
								dataMap.put("STATUS", false);
								dataMap.put("DEVICE_ID", goLiveData.getDEVICE_ID());
								dataMap.put("message", "INVALID SERVICE REQUEST");
							}
						}
					}
				}
				dataList.add(dataMap);
			}

			log.info(insertCount + " Records inserted," + existCount + " Records already existed," + validateCount
					+ " Records not a valid AMISP");

			responseMap.put("httpStatus", 200);
			responseMap.put("message", insertCount + " records inserted," + existCount + " Records already existed,"
					+ validateCount + " Records not a valid AMISP");
			responseMap.put("data", dataList);

		} catch (Exception e) {
			log.error("Some error has occurred", e);
			responseMap.put("httpStatus", 500);
			responseMap.put("message", "An error occurred during data processing");
		}
		return responseMap;
	}

	public String getInstalledMeterData(String section_name, String installed_date) {
		try {
			String baseUrl = "https://pixelvide-smis.com/api/3rdparty/epdcl/installed_meters_data";
			int page = 1;

			RestTemplate restTemplate = new RestTemplate();
			boolean hasNextPage = true;

			while (hasNextPage) {
				String apiUrl = baseUrl + "?installed_date=" + installed_date + "&section_name=" + section_name
						+ "&page=" + page;

				ResponseEntity<OverAllInstalledMeterData> result = restTemplate.getForEntity(apiUrl,
						OverAllInstalledMeterData.class);

				if (result.getStatusCode().is2xxSuccessful()) {
					OverAllInstalledMeterData responseData = result.getBody();

					if (responseData != null && responseData.getData() != null) {
						int recCount = responseData.getData().getRec_count();
						int perPage = responseData.getData().getPer_page();

						log.info("rec_count: {}", recCount);
						log.info("per_page: {}", perPage);

						int customerCount = 0;
						List<InstalledMeterData> installedMeterDataList = responseData.getData().getData();
						for (int i = 0; i < recCount; i++) {
							InstalledMeterData installedMeterData = installedMeterDataList.get(i);
							meterDao.insertInstalledMeterData(installedMeterData);
							customerCount++;
						}

						if (recCount <= customerCount) {
							hasNextPage = false;
						} else {
							page++;
						}
					} else {
						log.error("Response data or nested data is null.");
						return "Response data or nested data is null.";
					}
				} else {
					log.error("Failed to retrieve data. Status code: {}", result.getStatusCodeValue());
					return "Failed to retrieve data. Status code: " + result.getStatusCodeValue();
				}
			}

			return "Data retrieved and inserted successfully";
		} catch (Exception e) {
			log.error("An error occurred: {}", e.getMessage());
			return "An error occurred: " + e.getMessage();
		}
	}

	public String getBillingMeterData(String section_name, String reading_date) {
		try {
			String apiUrl = "https://pixelvide-smis.com/api/3rdparty/epdcl/billing_meters_data?reading_date="
					+ reading_date + "&section_name=" + section_name;

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<OverAllBillingMeterData> result = restTemplate.getForEntity(apiUrl,
					OverAllBillingMeterData.class);

			if (result.getStatusCode().is2xxSuccessful()) {
				OverAllBillingMeterData responseData = result.getBody();

				if (responseData != null && responseData.getData() != null) {
					List<BillingMeterData> billingMeterDataList = responseData.getData().getData();
					for (BillingMeterData billingMeterData : billingMeterDataList) {
						meterDao.insertBillingMeterData(billingMeterData);
					}
					return "Data retreived and inserted successfully";
				} else {
					log.error("Response data or nested data is null.");
					return "Response data or nested data is null.";
				}
			} else {
				log.error("Failed to retrieve data. Status code: {}", result.getStatusCodeValue());
				return "Failed to retrieve data. Status code: " + result.getStatusCodeValue();
			}
		} catch (Exception e) {
			log.error("An error occurred: {}", e.getMessage());
			return "An error occurred: " + e.getMessage();
		}
	}
	
	// NIU
		public String getInsertedMeterData(List<MeterData> meterDataList) {
			log.info("Received meterDataList in service: {}", meterDataList.size());

			int insertCount = 0;
			int existCount = 0;
			int validateCount = 0;

			try {
				for (MeterData meterData : meterDataList) {
					if (meterData.getUSCNO() != null && meterData.getUSCNO().length() == 13) {
						int existingData = meterDao.recordExistOrNot(meterData.getUSCNO(), meterData.getDEVICE_ID(),
								meterData.getBILLING_DATE());
						if (existingData > 0) {
							existCount++;
						} else {
							int validateData = meterDao.servicenoValidOrNot(meterData.getUSCNO());
							if (validateData > 0) {
								meterDao.insertMeterData(meterData);
								insertCount++;
								log.info("Inserted MeterData: {}", meterData.toString());
							} else {
								validateCount++;
							}
						}
					} else {
						validateCount++;

					}
				}
				log.info(insertCount + " Records inserted," + existCount + " Records already existed," + validateCount
						+ " Records not a valid AMISP");
				return insertCount + " Records inserted," + existCount + " Records already existed," + validateCount
						+ " Records not a valid AMISP";

			} catch (Exception e) {
				log.error("Some error has occured", e);
				return "An error occurred during data processing";
			}
		}

}
