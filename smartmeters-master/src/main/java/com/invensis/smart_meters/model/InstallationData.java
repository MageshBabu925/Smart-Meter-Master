package com.invensis.smart_meters.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InstallationData {
	@JsonProperty("SECTION_NAME")
	private String SECTION_NAME;

	@JsonProperty("AREA")
	private String AREA;

	@JsonProperty("FEEDER_CODE")
	private String FEEDER_CODE;

	@JsonProperty("SERVICE_QR")
	private String SERVICE_QR;

	@JsonProperty("DTR_STRUCTURE_CODE")
	private String DTR_STRUCTURE_CODE;

	@JsonProperty("UKSCNO")
	private String UKSCNO;

	@JsonProperty("INST_DT")
	private String INST_DT;

	@JsonProperty("COMM_DT")
	private String COMM_DT;

	@JsonProperty("MTR_MAKE")
	private String MTR_MAKE;

	@JsonProperty("MTR_DEVICE_ID")
	private String MTR_DEVICE_ID;

	@JsonProperty("DEVICE_ID")
	private String DEVICE_ID;

	@JsonProperty("MTR_CAP")
	private String MTR_CAP;

	@JsonProperty("MTR_MF")
	private String MTR_MF;

	@JsonProperty("MTR_INITIAL_RDG_KWH")
	private String MTR_INITIAL_RDG_KWH;

	@JsonProperty("MTR_INITIAL_RDG_KWH_EXPORT")
	private String MTR_INITIAL_RDG_KWH_EXPORT;

	@JsonProperty("MTR_INITIAL_RDG_KVAH")
	private String MTR_INITIAL_RDG_KVAH;

	@JsonProperty("MTR_INITIAL_RDG_KVAH_EXPORT")
	private String MTR_INITIAL_RDG_KVAH_EXPORT;

	@JsonProperty("MTR_SEAL1")
	private String MTR_SEAL1;

	@JsonProperty("MTR_SEAL2")
	private String MTR_SEAL2;

	@JsonProperty("MTR_TYPE")
	private String MTR_TYPE;

	@JsonProperty("OPERATOR_TYPE")
	private String OPERATOR_TYPE;

	@JsonProperty("SIM_IMEINO")
	private String SIM_IMEINO;

	@JsonProperty("SIM_NO")
	private String SIM_NO;

	@JsonProperty("LATITUDE")
	private String LATITUDE;

	@JsonProperty("LONGITUDE")
	private String LONGITUDE;

	@JsonProperty("AMISP")
	private String AMISP;

	@JsonProperty("OLD_MTR_MAKE")
	private String OLD_MTR_MAKE;

	@JsonProperty("OLD_MTR_DEVICE_ID")
	private String OLD_MTR_DEVICE_ID;

	@JsonProperty("OLD_MTR_FN_KWH")
	private String OLD_MTR_FN_KWH;

	@JsonProperty("OLD_MTR_FN_KVAH")
	private String OLD_MTR_FN_KVAH;

	@JsonProperty("GO_LIVE_DATE")
	private String GO_LIVE_DATE;

	@JsonProperty("OLD_MTR_FN_KWH_EXPORT")
	private String OLD_MTR_FN_KWH_EXPORT;

	@JsonProperty("OLD_MTR_FN_KVAH_EXPORT")
	private String OLD_MTR_FN_KVAH_EXPORT;
	
	@JsonProperty("TRANDT")
	private String TRANDT;
	
	@JsonProperty("REMARKS")
	private String REMARKS;


}
