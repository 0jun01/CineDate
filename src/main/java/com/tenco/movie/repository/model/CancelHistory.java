package com.tenco.movie.repository.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class CancelHistory {

	Integer id;
	String paymentKey;
	int userId;
	String orderId;
	String orderName;
	String amount;
	String method;
	String requestedAt;
	String approvedAt;
	String cancelAt;

	public String dateToTime() {

		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println("받은것" + cancelAt);

		// 출력할 형식 정의 (HH:mm 형식)
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
        
        String formattedDate = null;

        try {
            // 문자열을 Date로 변환
            Date date = 
            		
            		inputFormat.parse(cancelAt);
            

            // 원하는 HH:mm 형식으로 변환
            formattedDate = outputFormat.format(date);
            System.out.println("전환됨2" + formattedDate);

            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
	}

	public String dateToDate() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat changeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		changeDate.setTimeZone(tz);
		Date date = new Date();
		try {
			date = changeDate.parse(cancelAt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("전환됨3" + date);

		SimpleDateFormat changeTime = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println("전환됨4" + changeTime.format(date));

		return changeTime.format(date);

	}

}
