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
public class Evaluation {

	private int id;
	private int rUserId; // 평가받은 유저 id
	private int sUserId; // 평가한 유저 id
	private double userRationg; // 데이팅 유저 평점
	
}
