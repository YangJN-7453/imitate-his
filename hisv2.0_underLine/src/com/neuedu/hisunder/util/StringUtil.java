package com.neuedu.hisunder.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	/*判断字符串是否为空值或空串*/
	public static boolean isBlank(String str) {
		if(null == str || str.trim().equals("")){
			return true;
		} else {
			return false;
		}
	}
	
	/*字符串转换成日期*/
	public static Date strToDate(String str) {
		Date date = null;
		if (isBlank(str)) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date;
		}
	}
	
	/*日期转换成字符串*/
	public static String dateToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/*提取字符串中数字，并转换成int类型*/
	public static int strToInt(String str) {
		String s = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >=48 && str.charAt(i) <= 57) {
				s += str.charAt(i);
			}
		}
		return Integer.parseInt(s);
	}
}
