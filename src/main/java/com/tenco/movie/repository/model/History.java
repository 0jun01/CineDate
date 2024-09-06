package com.tenco.movie.repository.model;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class History {

	Integer id;
	String paymentKey;
	int userId;
	String orderId;
	String orderName;
	String amount;
	String method;
	String requestedAt;
	String approvedAt;	
	
	
	public String dateToTime() {
		
		TimeZone tz = TimeZone.getTimeZone("UTC");
		
		DateFormat changeDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		changeDate.setTimeZone(tz);
		Date date = new Date();
		try {
			date = changeDate.parse(approvedAt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("전환됨" + date);
		
		SimpleDateFormat changeTime = new SimpleDateFormat("HH:mm");
		
		System.out.println("전환됨" + changeTime.format(date));
		
		return changeTime.format(date);
		
	
	}
	
	public String dateToDate() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat changeDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		
		changeDate.setTimeZone(tz);
		Date date = new Date();
		try {
			date = changeDate.parse(approvedAt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("전환됨" + date);
		
		SimpleDateFormat changeTime = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println("전환됨" + changeTime.format(date));
		
		return changeTime.format(date);
		
	}
	
}
