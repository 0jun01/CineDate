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
public class Location {

	private int id;
	private String cinema; // 극장 이름
	private String address; // 도로명 주소
	private int capacity; // 관람 정원수
	
}
