package com.invensis.smart_meters.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invensis.smart_meters.Response.ResponseHandler;
import com.invensis.smart_meters.Response.StandardResponse;
import com.invensis.smart_meters.model.GoLiveData;
import com.invensis.smart_meters.model.InstallationData;
import com.invensis.smart_meters.model.MeterData;
import com.invensis.smart_meters.model.SurveyData;
import com.invensis.smart_meters.service.MeterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MeterController {
	@Autowired
	private MeterService meterService;

	@PostMapping("/getInsertedBillingMeterData")
	public Map<String, Object> getInsertedBillingMeterData(@RequestBody List<MeterData> meterDataList) {
		log.info("Received meterDataList: {}", meterDataList);

		Map<String, Object> response = new HashMap<>();
		try {
			response = meterService.getInsertedBillingMeterData(meterDataList);
		} catch (Exception e) {
			log.error("An error occurred ", e);
			response.put("httpStatus", 500);
			response.put("message", "An error occurred during data processing");
		}
		return response;
	}

	@PostMapping("/getInsertedSurveyMeterData")
	public Map<String, Object> getInsertedSurveyMeterData(@RequestBody List<SurveyData> surveyDataList) {
		log.info("Received meterDataList: {}", surveyDataList);

		Map<String, Object> response = new HashMap<>();
		try {
			response = meterService.getInsertedSurveyMeterData(surveyDataList);
		} catch (Exception e) {
			log.error("An error occurred ", e);
			response.put("httpStatus", 500);
			response.put("message", "An error occurred during data processing");
		}
		return response;
	}

	@PostMapping("/getInstallationMeterData")
	public Map<String, Object> getInstallationMeterData(@RequestBody List<InstallationData> insDataList) {
		log.info("Received meterDataList: {}", insDataList);

		Map<String, Object> response = new HashMap<>();
		try {
			response = meterService.getInstallationMeterData(insDataList);
		} catch (Exception e) {
			log.error("An error occurred ", e);
			response.put("httpStatus", 500);
			response.put("message", "An error occurred during data processing");
		}
		return response;
	}

	@PostMapping("/getGoLiveMeterData")
	public Map<String, Object> getGoLiveMeterData(@RequestBody List<GoLiveData> goLiveDataList) {
		log.info("Received meterDataList: {}", goLiveDataList);

		Map<String, Object> response = new HashMap<>();
		try {
			response = meterService.getGoLiveMeterData(goLiveDataList);
		} catch (Exception e) {
			log.error("An error occurred ", e);
			response.put("httpStatus", 500);
			response.put("message", "An error occurred during data processing");
		}
		return response;
	}

	@GetMapping("/getInstalledMeterData")
	public StandardResponse getInstalledMeterData(@RequestParam String section_name,
			@RequestParam String installed_date) {
		StandardResponse standardResponse = null;
		String response = meterService.getInstalledMeterData(section_name, installed_date);
		try {
			if (response != null) {
				standardResponse = ResponseHandler.validResponse(response);
			} else {
				standardResponse = ResponseHandler.failedResponse("No data found");
			}
		} catch (Exception e) {
			standardResponse = ResponseHandler.failedResponse("An error occurred");
		}
		return standardResponse;
	}

	@GetMapping("/getBillingMeterData")
	public StandardResponse getBillingMeterData(@RequestParam String section_name, @RequestParam String reading_date) {
		StandardResponse standardResponse = null;
		String response = meterService.getBillingMeterData(section_name, reading_date);
		try {
			if (response != null) {
				standardResponse = ResponseHandler.validResponse(response);
			} else {
				standardResponse = ResponseHandler.failedResponse("No data found");
			}
		} catch (Exception e) {
			standardResponse = ResponseHandler.failedResponse("An error occurred");
		}
		return standardResponse;
	}

	//NIU
	@PostMapping("/getInsertedMeterData")
	public StandardResponse getInsertedMeterData(@RequestBody List<MeterData> meterDataList) {
		log.info("Received meterDataList: {}", meterDataList);
		StandardResponse standardResponse = null;
		String response = meterService.getInsertedMeterData(meterDataList);
		try {
			if (response != null && !response.isEmpty()) {
				standardResponse = ResponseHandler.validResponse(response);
			} else {
				standardResponse = ResponseHandler.failedResponse("No data found");
			}
		} catch (Exception e) {
			standardResponse = ResponseHandler.failedResponse("An error occurred");
		}
		return standardResponse;
	}
}