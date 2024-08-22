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
public class SuperList {
	private int id; // megaphone_tb
	private int profileId; // 보낸 유저 id
	private Timestamp createdAt; // 시작시간
	private Timestamp endDate; // 나중에 created_at + 30분 할 값
}
