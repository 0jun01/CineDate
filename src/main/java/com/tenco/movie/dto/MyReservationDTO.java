package com.tenco.movie.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MyReservationDTO {
    private int bookingId; // 예약 ID
    private Timestamp bookingTime; // 예약 시간
    private int quantity; // 예약한 티켓 수
    private Date showDate; // 상영 날짜
    private Time showTime; // 상영 시간
    private String movieTitle; // 영화 제목
    private String reservedSeat; // 예약된 좌석 목록
}
