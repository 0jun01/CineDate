package com.tenco.movie.service;

import java.util.Calendar;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	
	public String getOderId() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);
		Random rd1 =  new Random();
		Random rd2 =  new Random();
		int rd11 = rd1.nextInt(100);
		int rd22 = rd2.nextInt(100);
		
		String yStr = Integer.toString(y);
		String mStr = Integer.toString(m);
		String dStr = Integer.toString(d);
		String rd1Str = Integer.toString(rd11);
		String rd2Str = Integer.toString(rd22);
		
		return mStr + rd1Str + yStr + rd2Str + dStr;
	}
	
	public int tossHistory() {
		int resuly = 0;
		
		return resuly;
	}
	
}
