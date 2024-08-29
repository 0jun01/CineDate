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
public class Actors {

	private int id; // 액터 pk
	private String name; // 배우 이름
	private String role; // 직업
	private String actorFaceFile; // 출연진 사진

}
