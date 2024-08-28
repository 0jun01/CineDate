package com.tenco.movie.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetail {
	private String title; // 영화 제목
	private String titleEn; // 영화 영어 제목
	private String movieDesc; // 영화 설명
	private String movieImg; // 영화 이미지
	private String releaseDate; // 개봉일
	private String director; // 디렉터 이름
	private String showTm; // 상영 시간
	private String prdStatNm; // 개봉여부
	private String watchGradeNm; // 몇세 관람가인지
	private String genre; // 장르
}
