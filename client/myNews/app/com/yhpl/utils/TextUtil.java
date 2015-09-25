package com.yhpl.utils;

public class TextUtil {

	public static boolean isEmpty(String txt) {
		if (txt == null || txt.length() == 0) {
			return true;
		}
		return false;
	}
}
