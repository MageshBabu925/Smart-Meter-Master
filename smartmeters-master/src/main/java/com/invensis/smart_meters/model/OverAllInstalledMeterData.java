package com.invensis.smart_meters.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OverAllInstalledMeterData {
	private boolean status;
    private String message;
    private AllInstalledMeterData data;
    private int code;

}
