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

	private int id; 
	private String name; // 배우 이름
	private int age; // 배우 나이
	private String actorFaceFile; // 주연 사진
	
}
