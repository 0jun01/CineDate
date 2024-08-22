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
public class Matching {

	private int id;
	private int sUserId; // 매칭 요청 발신자 id
	private int rUserId; // 매칭 요청 수신자 id
	private int status; // 상태 ( 0 - 대기중, 1 - 수락, 2 - 거절 )
	
}
