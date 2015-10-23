package com.sxgis.hibernate.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GenHql {
	private String hql = "";

	public static void main(String args[]) {
		GenHql genHql = new GenHql();
		Timestamp startTime = Timestamp.valueOf("2010-03-12 19:11:40");
		Timestamp endTime = Timestamp.valueOf("2010-03-16 19:11:40");
		genHql.setHqlByTimestamp(startTime);
		String hql1 = "select * from Test t where 1=1 " + genHql.getHql();
		genHql.setHqlByTimestamp(startTime, endTime);
		String hql2 = "select * from Test t where 1=1 " + genHql.getHql();
		System.out.println("开始时间到现在" + hql1);
		System.out.println("开始时间到结束时间" + hql2);
	}

	// 根据开始时间和结束时间生成hql语句
	public void setHqlByTimestamp(Timestamp startTime, Timestamp endTime) {
		String beginDate = "";
		String endDate = "";
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (startTime != null && startTime.toString() != "") {
			// startTime = Timestamp.valueOf("2010-03-12 19:11:40");
			Calendar cal = Calendar.getInstance();
			cal.setTime(startTime);
			Date begin = cal.getTime();
			beginDate = f.format(begin);
			hql += " and t.timestamp>=to_date('" + beginDate
					+ "','YYYY-MM-DD HH24:MI:SS')";
		}

		if (endTime == null || endTime.toString().equals("")) {
			Date date = new Date();
			endDate = f.format(date);
			hql += " and t.timestamp<=to_date('" + endDate
					+ "','YYYY-MM-DD HH24:MI:SS')";
		} else {
			// startTime = Timestamp.valueOf("2010-03-12 19:11:40");
			Calendar cal = Calendar.getInstance();
			cal.setTime(endTime);
			Date end = cal.getTime();
			endDate = f.format(end);
			hql += " and t.timestamp<=to_date('" + endDate
					+ "','YYYY-MM-DD HH24:MI:SS')";
		}
		System.out.println(hql);
	}

	// 根据开始时间生成hql语句
	public void setHqlByTimestamp(Timestamp startTime) {
		String beginDate = "";
		String endDate = "";
		hql = "";
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (startTime != null && startTime.toString() != "") {
			// startTime = Timestamp.valueOf("2010-03-12 19:11:40");
			Calendar cal = Calendar.getInstance();
			cal.setTime(startTime);
			Date begin = cal.getTime();
			beginDate = f.format(begin);
			hql = " and t.timestamp>=to_date('" + beginDate
					+ "','YYYY-MM-DD HH24:MI:SS')";
		}
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}
}