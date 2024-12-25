package com.javaweb.Util;

public class NumberUtils {

	public static boolean isNumbber(String s) {
		try {
			
			Long number=Long.parseLong(s);
			
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
