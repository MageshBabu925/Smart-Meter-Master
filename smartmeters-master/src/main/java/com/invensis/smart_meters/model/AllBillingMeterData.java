package com.invensis.smart_meters.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllBillingMeterData {

	@JsonProperty("page")
	private String page;

	@JsonProperty("per_page")
	private String per_page;

	@JsonProperty("data")
	private List<BillingMeterData> data;
}
