package com.sxgis.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {

	public static void main(String[] args) {
		Date myDate = new Date();
//		System.out.println(myDate);

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("SSS");
//		System.out.println(sdf.format(myDate));
//		System.out.println(sdf2.format(myDate));
		
		Timestamp myTime = new Timestamp(System.currentTimeMillis());
		int nanos = myTime.getNanos();
		System.out.println(myTime);
		System.out.println(nanos);

	}

}
