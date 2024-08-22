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
public class DateMessage {

	private int id;
	private int sUserId; // 쪽지 발신자 id
	private int rUserId; // 쪽지 수신자 id
	private String message; // 발신한 메세지 내용
	
}
