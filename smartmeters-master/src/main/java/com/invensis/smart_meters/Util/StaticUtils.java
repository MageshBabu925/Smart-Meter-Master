package com.invensis.smart_meters.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
public class StaticUtils {
	public static String getCurrentDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH").format(new Date());
	}
	
}
