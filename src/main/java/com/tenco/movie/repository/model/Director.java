package com.tenco.movie.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Director {
	
	private int id;
	private String name;
	private String birth;
	private String role;
	private String nationality;
	private String directorFaceFile;
}
