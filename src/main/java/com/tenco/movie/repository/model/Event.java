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
public class Event {

	private int id;
	private String title; // 이벤트명
	private Timestamp createdAt; 
	private Timestamp releaseDate; // 이벤트 시작일자
	private Timestamp endDate; // 이벤트 종료일자
	private String originFileName; // 이벤트 이미지 파일명
	private String uploadFileName; // 이벤트 이미지 업로드 파일명
	// 시간 포맷
    public String timestampToString() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(createdAt);
    }
}
