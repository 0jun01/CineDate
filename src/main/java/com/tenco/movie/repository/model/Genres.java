package com.tenco.movie.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genres {
	
	
	private int id; // id
	private String genreName; // 장르 이름 
}
