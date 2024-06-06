package com.invensis.smart_meters.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllInstalledMeterData {

	@JsonProperty("rec_count")
	private int rec_count;

	@JsonProperty("data")
	private List<InstalledMeterData> data;

	@JsonProperty("page")
	private int page;

	@JsonProperty("per_page")
	private int per_page;

}
