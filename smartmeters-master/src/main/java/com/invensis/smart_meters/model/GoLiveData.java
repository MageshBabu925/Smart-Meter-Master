package com.invensis.smart_meters.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GoLiveData {

	@JsonProperty("GOLIVE_DT")
	private String GOLIVE_DT;

	@JsonProperty("DEVICE_ID")
	private String DEVICE_ID;

}
