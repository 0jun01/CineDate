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
		
		DateFormat changeDate = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ssXXX");
		changeDate.setTimeZone(tz);
		Date date = new Date();
		try {
			date = changeDate.parse(approvedAt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormat changeTime = new SimpleDateFormat("HH:mm");
		
		return changeTime.format(date);
		
	
	}
	
}
