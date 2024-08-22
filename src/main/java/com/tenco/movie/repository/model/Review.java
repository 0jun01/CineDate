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
public class Review {
	private int id; // review_tb
	private int movieId; // movies_tb
	private int userId; // user_tb
	private String reviewText; // 리뷰 내용
	private double rating; // 평점
	private Timestamp reviewDate; // 작성 시간
}
