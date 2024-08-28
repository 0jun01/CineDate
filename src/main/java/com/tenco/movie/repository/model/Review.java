package com.tenco.movie.repository.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
	private int id; 
	private int movieId; // Movies - pk
	private int userId; // User - pk
	private String reviewText; // 리뷰 내용
	private double rating; // 평점
	private Timestamp reviewDate; // 작성 시간
	
	// 시간 포맷
    public String timestampToString() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(reviewDate);
    }
}
