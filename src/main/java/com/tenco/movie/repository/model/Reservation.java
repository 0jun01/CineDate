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
	private int id; 
	private int userId; // User - pk
	private int movieId; // Movies - pk
	private String sitNum; // 좌석 번호
}
