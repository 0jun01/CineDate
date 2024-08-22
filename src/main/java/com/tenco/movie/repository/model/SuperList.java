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
	private int id; 
	private int profileId; // 보낸 유저 id
	private Timestamp createdAt; // 시작시간
	private Timestamp endDate; // 종료시간 (추후 created_at + 30분 할 값)
}
