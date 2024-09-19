package com.tenco.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionCountDTO {
	
	private String regionName;
	private int theaterCount;
}
