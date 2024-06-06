package com.invensis.smart_meters.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingMeterData {

	@JsonProperty("TRANSACTION_ID")
	private int TRANSACTION_ID;

	@JsonProperty("UKSCNO")
	private String UKSCNO;

	@JsonProperty("SCNO")
	private String SCNO;

	@JsonProperty("DEVICE_ID")
	private String DEVICE_ID;

	@JsonProperty("BILLING_DATE")
	private String BILLING_DATE;

	@JsonProperty("POWER_FACTOR")
	private double POWER_FACTOR;

	@JsonProperty("KWH")
	private double KWH;

	@JsonProperty("KWH_TZ1")
	private String KWH_TZ1;

	@JsonProperty("KWH_TZ2")
	private String KWH_TZ2;

	@JsonProperty("KWH_TZ3")
	private String KWH_TZ3;

	@JsonProperty("KWH_TZ4")
	private String KWH_TZ4;

	@JsonProperty("KWH_TZ5")
	private String KWH_TZ5;

	@JsonProperty("KWH_TZ6")
	private String KWH_TZ6;

	@JsonProperty("KWH_TZ7")
	private String KWH_TZ7;

	@JsonProperty("KWH_TZ8")
	private String KWH_TZ8;

	@JsonProperty("KVAH")
	private String KVAH;

	@JsonProperty("KVAH_TZ1")
	private String KVAH_TZ1;

	@JsonProperty("KVAH_TZ2")
	private String KVAH_TZ2;

	@JsonProperty("KVAH_TZ3")
	private String KVAH_TZ3;

	@JsonProperty("KVAH_TZ4")
	private String KVAH_TZ4;

	@JsonProperty("KVAH_TZ5")
	private String KVAH_TZ5;

	@JsonProperty("KVAH_TZ6")
	private String KVAH_TZ6;

	@JsonProperty("KVAH_TZ7")
	private String KVAH_TZ7;

	@JsonProperty("KVAH_TZ8")
	private String KVAH_TZ8;

	@JsonProperty("MD_KW")
	private String MD_KW;

	@JsonProperty("MD_TZ1")
	private String MD_TZ1;

	@JsonProperty("MD_TZ2")
	private String MD_TZ2;

	@JsonProperty("MD_TZ3")
	private String MD_TZ3;

	@JsonProperty("MD_TZ4")
	private String MD_TZ4;

	@JsonProperty("MD_TZ5")
	private String MD_TZ5;

	@JsonProperty("MD_TZ6")
	private String MD_TZ6;

	@JsonProperty("MD_TZ7")
	private String MD_TZ7;

	@JsonProperty("MD_TZ8")
	private String MD_TZ8;

	@JsonProperty("MD_KV")
	private String MD_KV;

	@JsonProperty("MD_KV_TZ1")
	private String MD_KV_TZ1;

	@JsonProperty("MD_KV_TZ2")
	private String MD_KV_TZ2;

	@JsonProperty("MD_KV_TZ3")
	private String MD_KV_TZ3;

	@JsonProperty("MD_KV_TZ4")
	private String MD_KV_TZ4;

	@JsonProperty("MD_KV_TZ5")
	private String MD_KV_TZ5;

	@JsonProperty("MD_KV_TZ6")
	private String MD_KV_TZ6;

	@JsonProperty("MD_KV_TZ7")
	private String MD_KV_TZ7;

	@JsonProperty("MD_KV_TZ8")
	private String MD_KV_TZ8;

	@JsonProperty("POWER_ON_DURATION")
	private int POWER_ON_DURATION;

	@JsonProperty("KWH_E")
	private String KWH_E;

	@JsonProperty("KVAH_E")
	private String KVAH_E;

	@JsonProperty("KVARH_Q1")
	private String KVARH_Q1;

	@JsonProperty("KVARH_Q2")
	private String KVARH_Q2;

	@JsonProperty("KVARH_Q3")
	private String KVARH_Q3;

	@JsonProperty("KVARH_Q4")
	private String KVARH_Q4;

	@JsonProperty("AMISP")
	private String AMISP;

}
