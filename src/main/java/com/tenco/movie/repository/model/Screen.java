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
public class Screen {
	private int id; 
	private int locationId; // Location - pk
	private int movieId; // Movies - pk
	private Timestamp screenStart; // 상영 시작 시간
}
