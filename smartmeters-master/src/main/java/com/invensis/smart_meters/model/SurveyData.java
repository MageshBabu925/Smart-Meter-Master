package com.invensis.smart_meters.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyData {

	@JsonProperty("SCNO")
	private String SCNO;

	@JsonProperty("UKSCNO")
	private String UKSCNO;

	@JsonProperty("SECTION_CODE")
	private String SECTION_CODE;

	@JsonProperty("LT_DISTRIBUTION_AREA")
	private String LT_DISTRIBUTION_AREA;

	@JsonProperty("FEEDER_CODE")
	private String FEEDER_CODE;

	@JsonProperty("FEEDER_NAME")
	private String FEEDER_NAME;

	@JsonProperty("SUB_STATION_CODE")
	private String SUB_STATION_CODE;

	@JsonProperty("DTR_QRCODE")
	private String DTR_QRCODE;

	@JsonProperty("DTR_LATITUDE")
	private String DTR_LATITUDE;

	@JsonProperty("DTR_LONGITUDE")
	private String DTR_LONGITUDE;

	@JsonProperty("SUB_STATION_NAME")
	private String SUB_STATION_NAME;

	@JsonProperty("SERVICE_LATITUDE")
	private String SERVICE_LATITUDE;

	@JsonProperty("SERVICE_LONGITUDE")
	private String SERVICE_LONGITUDE;

	@JsonProperty("SERVICE_QR_CODE")
	private String SERVICE_QR_CODE;

	@JsonProperty("OLD_MTR_MAKE")
	private String OLD_MTR_MAKE;

	@JsonProperty("OLD_MTR_DEVICE_ID")
	private String OLD_MTR_DEVICE_ID;

	@JsonProperty("CIRCLE")
	private String CIRCLE;

	@JsonProperty("DIVISION")
	private String DIVISION;

	@JsonProperty("SUB_DIVISION")
	private String SUB_DIVISION;

	@JsonProperty("SECTION_NAME")
	private String SECTION_NAME;

	@JsonProperty("TRANDT")
	private String TRANDT;

	@JsonProperty("REMARKS")
	private String REMARKS;
}
