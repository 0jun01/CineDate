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
public class Screens {
	private int id; 
	private int theaterId; // 극장 pk
	private String name; // Movies - pk
	private int capacity; // 좌석수
}
