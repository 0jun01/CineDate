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
	private int id; // screen_tb
	private int locationId; // location_tb
	private int movieId; // moives_tb
	private Timestamp screenStart; // 상영 시작 시간
}
