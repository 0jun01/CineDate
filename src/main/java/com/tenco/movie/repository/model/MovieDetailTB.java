package com.tenco.movie.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieDetailTB {

	private int id; // movies_detail_tb의 pk
	private int movieId; // 영화 id
	private String title; // 영화 제목
	private String titleEn; // 영화 영어 제목
	private String showTm; // 상영 시간
	private String openDt; // 개봉일
	private String prdStatNm; // 개봉여부
	private String watchGradeNm; // 몇세 관람가
}
