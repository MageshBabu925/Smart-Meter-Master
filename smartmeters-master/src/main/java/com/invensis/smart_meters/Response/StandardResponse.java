package com.invensis.smart_meters.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse {
	public Object data;
	public boolean status;
	public String message;
	public String currentdate;
}
