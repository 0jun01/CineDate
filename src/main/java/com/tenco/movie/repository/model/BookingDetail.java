package com.tenco.movie.repository.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDetail {

	private int id; // 예매 id
	private Date bookingTime; // 예매한 시간
	private int quantity; // 인원
	private Date showDate; // 상영날짜
	private String showTime; // 상영시간
	private String theaterName; // 예매 극장
	private List<String> bookedSeats; // 예매 좌석
	
}
