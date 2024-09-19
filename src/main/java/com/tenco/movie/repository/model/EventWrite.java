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
public class EventWrite {

	private int id;
	private String title; // 이벤트명
	private Timestamp createdAt;

	private String releaseDate; // 이벤트 시작일자

	private String endDate; // 이벤트 종료일자

	private String originFileName; // 이벤트 이미지 파일명
	private String uploadFileName; // 이벤트 이미지 업로드 파일명

}
