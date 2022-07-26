package com.example.vehiclesharing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class dateTime {
	public static String convertDate(String date_time) throws ParseException
	{
		date_time = date_time.replace("T", " ");
        SimpleDateFormat sformat = new SimpleDateFormat("MMMM dd, HH:mm");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(date_time);
		return  sformat.format(date);
	}
    
    public static String getCurrentTime() {
		long ms = System.currentTimeMillis();
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date resultdate = new Date(ms);
		sformat.setTimeZone(TimeZone.getTimeZone("America/Halifax"));
		return sformat.format(resultdate);
	}
}
