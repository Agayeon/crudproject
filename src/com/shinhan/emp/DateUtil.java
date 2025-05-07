package com.shinhan.emp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class DateUtil {

    // 문자열을 java.sql.Date로 변환
    public static Date convertToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date parsedDate = sdf.parse(dateString);
            return new Date(parsedDate.getTime());  // java.sql.Date로 변환
        } catch (ParseException e) {
            e.printStackTrace();
            return null;  // 변환 실패 시 null 반환
        }
    }

    public static Date convertToDate(Date date) {
        return date;
    }

	public static Date convertToSQLDate(Date convertToDate) {
		// TODO Auto-generated method stub
		return null;
	}

 
}
