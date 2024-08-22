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
public class Dating {

	private int id;
	private int profileId; // Profile - pk 유저 프로필
	private Timestamp createdAt; // 작성 시간
	
}
