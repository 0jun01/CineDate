package com.tenco.movie.repository.model;

import java.sql.Timestamp;

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
public class Reservation {
	private int id; // reservation_tb pk 예약 번호
	private int userId; // user_tb
	private int movieId; // movies_tb
	private String sitNum; // 좌석 번호
}
