package com.tenco.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterCountDTO {
	private int regionId;
	private int subId;
	private String regionName;
	private String name;
	private int movieCount;
}
