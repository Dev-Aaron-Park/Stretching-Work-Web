package com.aaron.stretchingwork.home;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class DateManager {
	public static void getCurYear(HttpServletRequest req) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String curYear = sdf.format(now);
		req.setAttribute("curYear", Integer.parseInt(curYear));
	}
	
	public static void getDate(Date date, HttpServletRequest req) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		req.setAttribute("year", Integer.parseInt(sdf.format(date)));
		
		sdf = new SimpleDateFormat("MM");
		req.setAttribute("month", Integer.parseInt(sdf.format(date)));
		
		sdf = new SimpleDateFormat("dd");
		req.setAttribute("day", Integer.parseInt(sdf.format(date)));
	}
}
