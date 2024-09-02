package com.tenco.movie.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Search {

	private String title;
	private String movieImg;
	private String movieDesc;
	private String releaseDate;
	
	// 영화 디테일 테이블 추가
	private String prdStatNm;
	private String watchGradeNm;
	
}
