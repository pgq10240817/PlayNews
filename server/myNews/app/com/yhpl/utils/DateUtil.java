package com.yhpl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static SimpleDateFormat DATE_FMT_NORMAL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Date getDateFromString(String timeStr) {
		return getDateFromString(DATE_FMT_NORMAL, timeStr);
	}

	public static Date getDateFromString(SimpleDateFormat sdf, String timeStr) {
		try {
			return sdf.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return new Date(0);
	}
}
