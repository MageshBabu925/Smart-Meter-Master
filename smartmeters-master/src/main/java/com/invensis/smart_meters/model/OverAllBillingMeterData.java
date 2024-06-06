package com.invensis.smart_meters.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OverAllBillingMeterData {
	private boolean status;
	private String message;
	private AllBillingMeterData data;
	private int code;

}
