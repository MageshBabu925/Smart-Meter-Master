package com.invensis.smart_meters.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.invensis.smart_meters.model.BillingMeterData;
import com.invensis.smart_meters.model.GoLiveData;
import com.invensis.smart_meters.model.InstallationData;
import com.invensis.smart_meters.model.InstalledMeterData;
import com.invensis.smart_meters.model.MeterData;
import com.invensis.smart_meters.model.SurveyData;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MeterDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<MeterData> getMeterData(String USCNO, String DEVICE_ID, String BILLING_DATE) {
		String sql = "SELECT * FROM DBT_SCS_READS WHERE USCNO=? AND DEVICE_ID=? "
				+ "AND TO_CHAR(BILLING_DATE,'DD-MM-YYYY HH24:MI:SS')=?";

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MeterData.class),
				new Object[] { USCNO, DEVICE_ID, BILLING_DATE });
	}

	// BILLING
	public boolean insertMeterData(MeterData meterData) {
		try {

			String sql = "INSERT INTO REPORTS.DBT_SCS_READS (TRANSACTION_ID, USCNO, DEVICE_ID, BILLING_DATE, "
					+ "POWER_FACTOR, KWH, KWH_TZ1, KWH_TZ2, KWH_TZ3, KWH_TZ4, KWH_TZ5, KWH_TZ6, KWH_TZ7, KWH_TZ8, "
					+ "KVAH, KVAH_TZ1, KVAH_TZ2, KVAH_TZ3, KVAH_TZ4, KVAH_TZ5, KVAH_TZ6, KVAH_TZ7, KVAH_TZ8, MD_KW, "
					+ "MD_TZ1, MD_TZ2, MD_TZ3, MD_TZ4, MD_TZ5, MD_TZ6, MD_TZ7, MD_TZ8, MD_KV, MD_KV_TZ1, MD_KV_TZ2, "
					+ "MD_KV_TZ3, MD_KV_TZ4, MD_KV_TZ5, MD_KV_TZ6, MD_KV_TZ7, MD_KV_TZ8, POWER_ON_DURATION, KWH_E, "
					+ "KVAH_E, KVARH_Q1, KVARH_Q2, KVARH_Q3, KVARH_Q4, AMISP, NO_OF_BLOCKS,NO_OF_DAYS) "
					+ "VALUES (?, ?, ?, TO_DATE(?,'DD-MM-YYYY HH24:MI:SS'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			log.info("Inserting MeterData: {}", meterData);
			int status = jdbcTemplate.update(sql, 
											meterData.getTRANSACTION_ID(), 
											meterData.getUSCNO(),
											meterData.getDEVICE_ID(), 
											meterData.getBILLING_DATE(), 
											meterData.getPOWER_FACTOR(),
											meterData.getKWH(), 
											meterData.getKWH_TZ1(), 
											meterData.getKWH_TZ2(), 
											meterData.getKWH_TZ3(),
											meterData.getKWH_TZ4(), 
											meterData.getKWH_TZ5(), 
											meterData.getKWH_TZ6(), 
											meterData.getKWH_TZ7(),
											meterData.getKWH_TZ8(), 
											meterData.getKVAH(), 
											meterData.getKVAH_TZ1(), 
											meterData.getKVAH_TZ2(),
											meterData.getKVAH_TZ3(), 
											meterData.getKVAH_TZ4(), 
											meterData.getKVAH_TZ5(), 
											meterData.getKVAH_TZ6(),
											meterData.getKVAH_TZ7(), 
											meterData.getKVAH_TZ8(), 
											meterData.getMD_KW(), 
											meterData.getMD_TZ1(),
											meterData.getMD_TZ2(), 
											meterData.getMD_TZ3(), 
											meterData.getMD_TZ4(), 
											meterData.getMD_TZ5(),
											meterData.getMD_TZ6(), 
											meterData.getMD_TZ7(), 
											meterData.getMD_TZ8(), 
											meterData.getMD_KV(),
											meterData.getMD_KV_TZ1(), 
											meterData.getMD_KV_TZ2(), 
											meterData.getMD_KV_TZ3(),
											meterData.getMD_KV_TZ4(), 
											meterData.getMD_KV_TZ5(), 
											meterData.getMD_KV_TZ6(),
											meterData.getMD_KV_TZ7(), 
											meterData.getMD_KV_TZ8(), 
											meterData.getPOWER_ON_DURATION(),
											meterData.getKWH_E(), 
											meterData.getKVAH_E(), 
											meterData.getKVARH_Q1(), 
											meterData.getKVARH_Q2(),
											meterData.getKVARH_Q3(), 
											meterData.getKVARH_Q4(), 
											meterData.getAMISP(), 
											meterData.getNO_OF_BLOCKS(),
											meterData.getNO_OF_DAYS());
			return status > 0;

		} catch (Exception e) {
			log.error("Some error has occured", e);
			return false;
		}
	}

	public int recordExistOrNot(String USCNO, String DEVICE_ID, String BILLING_DATE) {
		try {
			String sql = "SELECT COUNT(1) FROM DBT_SCS_READS WHERE USCNO=? AND DEVICE_ID=? "
					+ "AND TO_CHAR(BILLING_DATE,'DD-MM-YYYY HH24:MI:SS')=?";
			return jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { USCNO, DEVICE_ID, BILLING_DATE });
		} catch (DataAccessException e) {
			log.error("NO DATA FOUND IN RECORD EXIST OR NOT ");
			return 0;
		}
	}

	public int servicenoValidOrNot(String USCNO) {
		try {
			String schema = OrauserNamesUtility.getDBSchemaDetails(USCNO.substring(0, 3));
			String sql = "SELECT COUNT(1) FROM " + schema + ".CONS WHERE CMUSCNO=?";
			return jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { USCNO });
		} catch (Exception e) {
			log.error("NO DATA FOUND IN SERVICENO VALID OR NOT ");
			return 0;
		}
	}

	// SURVEY
	public boolean insertSurveyData(SurveyData surveyData) {
		try {
			String sql = "INSERT INTO REPORTS.DBT_SURVEY (SCNO, UKSCNO, SECTION_CODE, LT_DISTRIBUTION_AREA, FEEDER_CODE, "
					+ "FEEDER_NAME, SUB_STATION_CODE, DTR_QRCODE, DTR_LATITUDE, DTR_LONGITUDE, SUB_STATION_NAME, "
					+ "SERVICE_LATITUDE, SERVICE_LONGITUDE, SERVICE_QR_CODE, OLD_MTR_MAKE, OLD_MTR_DEVICE_ID, CIRCLE, "
					+ "DIVISION, SUB_DIVISION, SECTION_NAME,TRANDT,REMARKS) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?)";

			log.info("Inserting SurveyData: {}", surveyData);
			int status = jdbcTemplate.update(sql, 
											surveyData.getSCNO(), 
											surveyData.getUKSCNO(),
											surveyData.getSECTION_CODE(), 
											surveyData.getLT_DISTRIBUTION_AREA(), 
											surveyData.getFEEDER_CODE(),
											surveyData.getFEEDER_NAME(), 
											surveyData.getSUB_STATION_CODE(), 
											surveyData.getDTR_QRCODE(),
											surveyData.getDTR_LATITUDE(), 
											surveyData.getDTR_LONGITUDE(), 
											surveyData.getSUB_STATION_NAME(),
											surveyData.getSERVICE_LATITUDE(), 
											surveyData.getSERVICE_LONGITUDE(),
											surveyData.getSERVICE_QR_CODE(), 
											surveyData.getOLD_MTR_MAKE(), 
											surveyData.getOLD_MTR_DEVICE_ID(),
											surveyData.getCIRCLE(), 
											surveyData.getDIVISION(), 
											surveyData.getSUB_DIVISION(),
											surveyData.getSECTION_NAME(), 
											surveyData.getREMARKS());
			return status > 0;

		} catch (Exception e) {
			log.error("Some error has occurred", e);
			return false;
		}
	}

	public int surveyRecordExistOrNot(String UKSCNO, String SCNO, String SERVICE_QR_CODE) {
		try {
			String sql = "SELECT COUNT(1) FROM REPORTS.DBT_SURVEY WHERE UKSCNO=? AND SCNO=? AND SERVICE_QR_CODE=?";
			return jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { UKSCNO, SCNO, SERVICE_QR_CODE });
		} catch (DataAccessException e) {
			log.error("NO DATA FOUND IN RECORD EXIST OR NOT ");
			return 0;
		}
	}

	public int surveyValidOrNot(String UKSCNO) {
		try {
			String schema = OrauserNamesUtility.getDBSchemaDetails(UKSCNO.substring(0, 3));
			String sql = "SELECT COUNT(1) FROM " + schema + ".CONS WHERE CMUSCNO=?";
			return jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { UKSCNO });
		} catch (Exception e) {
			log.error("NO DATA FOUND IN SERVICENO VALID OR NOT ");
			return 0;
		}
	}

	public int oldSurveyValidOrNot(String UKSCNO) {
		try {
			String schema = OrauserNamesUtility.getDBSchemaDetails(UKSCNO.substring(0, 3));
			String sql = "SELECT COUNT(1) FROM " + schema + ".CONS WHERE CMIHCSCNO=?";
			return jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { UKSCNO });
		} catch (Exception e) {
			log.error("NO DATA FOUND IN SERVICENO VALID OR NOT ");
			return 0;
		}
	}

	// INSTALLATION
	public boolean insertInsData(InstallationData insData) {
		try {
			String sql = "INSERT INTO REPORTS.DBT_INSTALLATION (SECTION_NAME, AREA, FEEDER_CODE, SERVICE_QR, DTR_STRUCTURE_CODE, UKSCNO, "
					+ "INST_DT, COMM_DT, MTR_MAKE, MTR_DEVICE_ID, DEVICE_ID, MTR_CAP, MTR_MF, MTR_INITIAL_RDG_KWH, "
					+ "MTR_INITIAL_RDG_KWH_EXPORT, MTR_INITIAL_RDG_KVAH, MTR_INITIAL_RDG_KVAH_EXPORT, MTR_SEAL1, "
					+ "MTR_SEAL2, MTR_TYPE, OPERATOR_TYPE, SIM_IMEINO, SIM_NO, LATITUDE, LONGITUDE, AMISP, "
					+ "OLD_MTR_MAKE, OLD_MTR_DEVICE_ID, OLD_MTR_FN_KWH, OLD_MTR_FN_KVAH, GO_LIVE_DATE, "
					+ "OLD_MTR_FN_KWH_EXPORT, OLD_MTR_FN_KVAH_EXPORT,TRANDT,REMARKS) "
					+ "VALUES (?, ?, ?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY'), TO_DATE(?,'DD-MM-YYYY'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY'), ?, ?, SYSDATE, ?)";

			int status = jdbcTemplate.update(sql, 
										insData.getSECTION_NAME(), 
										insData.getAREA(),
										insData.getFEEDER_CODE(), 
										insData.getSERVICE_QR(), 
										insData.getDTR_STRUCTURE_CODE(),
										insData.getUKSCNO(), 
										insData.getINST_DT(), 
										insData.getCOMM_DT(), 
										insData.getMTR_MAKE(),
										insData.getMTR_DEVICE_ID(), 
										insData.getDEVICE_ID(), 
										insData.getMTR_CAP(), 
										insData.getMTR_MF(),
										insData.getMTR_INITIAL_RDG_KWH(), 
										insData.getMTR_INITIAL_RDG_KWH_EXPORT(),
										insData.getMTR_INITIAL_RDG_KVAH(), 
										insData.getMTR_INITIAL_RDG_KVAH_EXPORT(), 
										insData.getMTR_SEAL1(),
										insData.getMTR_SEAL2(), 
										insData.getMTR_TYPE(), 
										insData.getOPERATOR_TYPE(), 
										insData.getSIM_IMEINO(),
										insData.getSIM_NO(), 
										insData.getLATITUDE(), 
										insData.getLONGITUDE(), 
										insData.getAMISP(),
										insData.getOLD_MTR_MAKE(), 
										insData.getOLD_MTR_DEVICE_ID(), 
										insData.getOLD_MTR_FN_KWH(),
										insData.getOLD_MTR_FN_KVAH(), 
										insData.getGO_LIVE_DATE(), 
										insData.getOLD_MTR_FN_KWH_EXPORT(),
										insData.getOLD_MTR_FN_KVAH_EXPORT(), 
										insData.getREMARKS());
			return status > 0;

		} catch (Exception e) {
			log.error("Error occurred while inserting records", e);
			return false;
		}
	}

	public int insRecordExistOrNot(String UKSCNO, String MTR_DEVICE_ID) {
		try {
			String sql = "SELECT COUNT(1) FROM REPORTS.DBT_INSTALLATION WHERE UKSCNO=? AND MTR_DEVICE_ID=? ";
			return jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { UKSCNO, MTR_DEVICE_ID });
		} catch (DataAccessException e) {
			log.error("NO DATA FOUND IN RECORD EXIST OR NOT ");
			return 0;
		}
	}

	public int insValidOrNot(String UKSCNO) {
		try {
			String schema = OrauserNamesUtility.getDBSchemaDetails(UKSCNO.substring(0, 3));
			String sql = "SELECT COUNT(1) FROM " + schema + ".CONS WHERE CMUSCNO=?";
			return jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { UKSCNO });
		} catch (Exception e) {
			log.error("NO DATA FOUND IN SERVICENO VALID OR NOT ");
			return 0;
		}
	}

	// GO-LIVE
	public void insertGoLiveData(GoLiveData goLiveData) {
		try {
			String sql = "INSERT INTO smart_meters (GOLIVE_DT, DEVICE_ID) values(?,?) ";
			jdbcTemplate.update(sql, goLiveData.getGOLIVE_DT(), goLiveData.getDEVICE_ID());
		} catch (Exception e) {
			log.error("Some error has occurred", e);
		}
	}

	public int goLiveRecordExistOrNot(String GOLIVE_DT, String DEVICE_ID) {
		try {
			String sql = "SELECT COUNT(1) FROM DBT_SCS_READS WHERE GOLIVE_DT=? AND DEVICE_ID=? "
					+ "AND DTR_STRUCTURE_CODE=?";
			return jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { GOLIVE_DT, DEVICE_ID });
		} catch (DataAccessException e) {
			log.error("NO DATA FOUND IN RECORD EXIST OR NOT ");
			return 0;
		}
	}

	public int goLiveValidOrNot(String DEVICE_ID) {
		try {
			String schema = OrauserNamesUtility.getDBSchemaDetails(DEVICE_ID.substring(0, 3));
			String sql = "SELECT COUNT(1) FROM " + schema + ".CONS WHERE CMUSCNO=?";
			return jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { DEVICE_ID });
		} catch (Exception e) {
			log.error("NO DATA FOUND IN SERVICENO VALID OR NOT ");
			return 0;
		}
	}

	// ================================================================================================================ //

	public boolean insertInstalledMeterData(InstalledMeterData installedMeterData) {
		try {
			String sql = "INSERT INTO smart_meters (SECTION, AREA, FEEDER_CODE, DTR_STRUCTURE_CODE, UKSCNO, INST_DT, "
					+ "COMM_DT, MTR_MAKE, MTR_SNO, MTR_CAP, MTR_MF, MTR_INITIAL_RDG_KWH, MTR_INITIAL_RDG_KVAH, "
					+ "MTR_SEAL1, MTR_SEAL2, MTR_TYPE, OPERATOR_TYPE, SIM_IMEINO, SIM_NO, AMISP, LATITUDE, LONGITUDE, "
					+ "OLD_MTR_MAKE, OLD_MTR_SNO, OLD_MTR_FN_RDG_KWH, OLD_MTR_FN_RDG_KVAH) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			int status = jdbcTemplate.update(sql, 
											installedMeterData.getSECTION(), 
											installedMeterData.getAREA(),
											installedMeterData.getFEEDER_CODE(), 
											installedMeterData.getDTR_STRUCTURE_CODE(),
											installedMeterData.getUKSCNO(), 
											installedMeterData.getINST_DT(), 
											installedMeterData.getCOMM_DT(),
											installedMeterData.getMTR_MAKE(), 
											installedMeterData.getMTR_SNO(), 
											installedMeterData.getMTR_CAP(),
											installedMeterData.getMTR_MF(), 
											installedMeterData.getMTR_INITIAL_RDG_KWH(),
											installedMeterData.getMTR_INITIAL_RDG_KVAH(), 
											installedMeterData.getMTR_SEAL1(),
											installedMeterData.getMTR_SEAL2(), 
											installedMeterData.getMTR_TYPE(),
											installedMeterData.getOPERATOR_TYPE(), 
											installedMeterData.getSIM_IMEINO(),
											installedMeterData.getSIM_NO(), 
											installedMeterData.getAMISP(), 
											installedMeterData.getLATITUDE(),
											installedMeterData.getLONGITUDE(), 
											installedMeterData.getOLD_MTR_MAKE(),
											installedMeterData.getOLD_MTR_SNO(), 
											installedMeterData.getOLD_MTR_FN_RDG_KWH(),
											installedMeterData.getOLD_MTR_FN_RDG_KVAH());
			return status > 0;

		} catch (Exception e) {
			log.error("Some error has occurred", e);
			return false;
		}
	}

	public boolean insertBillingMeterData(BillingMeterData billingMeterData) {
		try {
			String sql = "INSERT INTO smart_meters (TRANSACTION_ID, UKSCNO, SCNO, DEVICE_ID, BILLING_DATE, "
					+ "POWER_FACTOR, KWH, KWH_TZ1, KWH_TZ2, KWH_TZ3, KWH_TZ4, KWH_TZ5, KWH_TZ6, KWH_TZ7, KWH_TZ8, "
					+ "KVAH, KVAH_TZ1, KVAH_TZ2, KVAH_TZ3, KVAH_TZ4, KVAH_TZ5, KVAH_TZ6, KVAH_TZ7, KVAH_TZ8, "
					+ "MD_KW, MD_TZ1, MD_TZ2, MD_TZ3, MD_TZ4, MD_TZ5, MD_TZ6, MD_TZ7, MD_TZ8, "
					+ "MD_KV, MD_KV_TZ1, MD_KV_TZ2, MD_KV_TZ3, MD_KV_TZ4, MD_KV_TZ5, MD_KV_TZ6, MD_KV_TZ7, MD_KV_TZ8, "
					+ "POWER_ON_DURATION, KWH_E, KVAH_E, KVARH_Q1, KVARH_Q2, KVARH_Q3, KVARH_Q4, AMISP) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			int status = jdbcTemplate.update(sql, 
											billingMeterData.getTRANSACTION_ID(), 
											billingMeterData.getUKSCNO(),
											billingMeterData.getSCNO(), 
											billingMeterData.getDEVICE_ID(), 
											billingMeterData.getBILLING_DATE(),
											billingMeterData.getPOWER_FACTOR(), 
											billingMeterData.getKWH(), 
											billingMeterData.getKWH_TZ1(),
											billingMeterData.getKWH_TZ2(), 
											billingMeterData.getKWH_TZ3(), 
											billingMeterData.getKWH_TZ4(),
											billingMeterData.getKWH_TZ5(), 
											billingMeterData.getKWH_TZ6(), 
											billingMeterData.getKWH_TZ7(),
											billingMeterData.getKWH_TZ8(), 
											billingMeterData.getKVAH(), 
											billingMeterData.getKVAH_TZ1(),
											billingMeterData.getKVAH_TZ2(), 
											billingMeterData.getKVAH_TZ3(), 
											billingMeterData.getKVAH_TZ4(),
											billingMeterData.getKVAH_TZ5(), 
											billingMeterData.getKVAH_TZ6(), 
											billingMeterData.getKVAH_TZ7(),
											billingMeterData.getKVAH_TZ8(), 
											billingMeterData.getMD_KW(), 
											billingMeterData.getMD_TZ1(),
											billingMeterData.getMD_TZ2(), 
											billingMeterData.getMD_TZ3(), 
											billingMeterData.getMD_TZ4(),
											billingMeterData.getMD_TZ5(), 
											billingMeterData.getMD_TZ6(), 
											billingMeterData.getMD_TZ7(),
											billingMeterData.getMD_TZ8(), 
											billingMeterData.getMD_KV(), 
											billingMeterData.getMD_KV_TZ1(),
											billingMeterData.getMD_KV_TZ2(), 
											billingMeterData.getMD_KV_TZ3(), 
											billingMeterData.getMD_KV_TZ4(),
											billingMeterData.getMD_KV_TZ5(), 
											billingMeterData.getMD_KV_TZ6(), 
											billingMeterData.getMD_KV_TZ7(),
											billingMeterData.getMD_KV_TZ8(), 
											billingMeterData.getPOWER_ON_DURATION(),
											billingMeterData.getKWH_E(), 
											billingMeterData.getKVAH_E(), 
											billingMeterData.getKVARH_Q1(),
											billingMeterData.getKVARH_Q2(), 
											billingMeterData.getKVARH_Q3(), 
											billingMeterData.getKVARH_Q4(),
											billingMeterData.getAMISP());
			return status > 0;
		} catch (Exception e) {
			log.error("Some error has occurred", e);
			return false;
		}
	}
}
