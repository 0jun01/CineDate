package com.tenco.movie.repository.model;

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
	private String movieDesc; // 영화 상세 설명
	private String movieImg; // 무비 URL주소
	private String releaseDate; // 개봉일자
	
}
