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
public class Notice {

	private int id;
	private int adminId; // 관리자 id
	private String category; // 분류
	private String title; // 공지사항 제목
	private String content; // 공지사항 내용
	private Timestamp createdAt;
	
}
