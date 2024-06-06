package com.invensis.smart_meters.Response;

import com.invensis.smart_meters.Util.StaticUtils;

public class ResponseHandler {

	public static StandardResponse validResponse(Object data, String message) {
		String currentdate = StaticUtils.getCurrentDate();
		return new StandardResponse(data, true, message, currentdate);
	}

	public static StandardResponse validResponse(Object data) {
		String currentdate = StaticUtils.getCurrentDate();
		return new StandardResponse(data, true, null, currentdate);
	}

	public static StandardResponse failedResponse(String message) {
		String currentdate = StaticUtils.getCurrentDate();
		return new StandardResponse(null, false, message, currentdate);
	}

	public static StandardResponse failedResponse(Object data, String message) {
		String currentdate = StaticUtils.getCurrentDate();
		return new StandardResponse(data, false, message, currentdate);
	}
}
