package com.dodam.hotel.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateFormat {

	public static String dateFormat(Timestamp date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String formatDate = sdf.format(date);
		
		return formatDate;
	}
	
}
