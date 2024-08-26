package com.tenco.movie.repository.model;

import java.sql.Date;

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
public class Movies {

	private int id;
	private String title; // 영화 제목
	private String genre; // 영화 장르
	private String director; // 영화 감독
	private String movieImg; // 무비 URL주소
	private String movieDesc; // 영화 상세 설명
	private String releaseDate; // 개봉일자
	private Date endDate; // 종영일자
	
}
