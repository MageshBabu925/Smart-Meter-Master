package com.invensis.smart_meters.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstalledMeterData {

	@JsonProperty("SECTION")
	private String SECTION;

	@JsonProperty("AREA")
	private String AREA;

	@JsonProperty("FEEDER_CODE")
	private String FEEDER_CODE;

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

	@JsonProperty("MTR_SNO")
	private String MTR_SNO;

	@JsonProperty("MTR_CAP")
	private String MTR_CAP;

	@JsonProperty("MTR_MF")
	private String MTR_MF;

	@JsonProperty("MTR_INITIAL_RDG_KWH")
	private String MTR_INITIAL_RDG_KWH;

	@JsonProperty("MTR_INITIAL_RDG_KVAH")
	private String MTR_INITIAL_RDG_KVAH;

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

	@JsonProperty("AMISP")
	private String AMISP;

	@JsonProperty("LATITUDE")
	private String LATITUDE;

	@JsonProperty("LONGITUDE")
	private String LONGITUDE;

	@JsonProperty("OLD_MTR_MAKE")
	private String OLD_MTR_MAKE;

	@JsonProperty("OLD_MTR_SNO")
	private String OLD_MTR_SNO;

	@JsonProperty("OLD_MTR_FN_RDG_KWH")
	private String OLD_MTR_FN_RDG_KWH;

	@JsonProperty("OLD_MTR_FN_RDG_KVAH")
	private String OLD_MTR_FN_RDG_KVAH;

}
