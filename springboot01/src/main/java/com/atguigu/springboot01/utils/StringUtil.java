package com.atguigu.springboot01.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class StringUtil {
	
	
	/**我要把那新房子，刷的真漂亮
	 * @description 将NULL转化为空字符串
	 * @param object
	 * @return String
	 */
	public static String removeNull(Object object) {
		return removeNull(object, "");
	}

	/**
	 * @description 将NULL转化为指定为字符串
	 * @param object
	 * @param string
	 * @return String
	 */
	public static String removeNull(Object object, String string) {
		if (object == null) {
			return string;
		}
		return object.toString().trim();
	}

	public static String getString(Object object){
		String s = removeNull(object, "");
		if (s.equals("")){
			throw new NullPointerException();
		}
		return s;
	}

	public static boolean isEmptyString(String str) {
		return str == null || str.trim().equals("") || "NULL".equalsIgnoreCase(str);
	}

	public static Date convertStringToDate(String strDate) {
		if (strDate == null || "".equals(strDate)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date d = null;
		try {
			d = sdf.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date date = new Date(d.getTime());
		return date;
	}
}
