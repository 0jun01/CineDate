package com.tenco.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class profileDetailDTO {
	
	private int userId;
	private String idealType;
	private String bloodtype;
	private String myJop;
	private String bestMovie;
	private String drinking;
	private String smoking;

}
