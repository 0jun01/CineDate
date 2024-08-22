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
public class BlackList {

	private int id;
	private int userId; // 유저 id pk
	private int blackUserId; // 블랙 당한 유저 id
	
}
