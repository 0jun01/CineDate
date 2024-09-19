package com.tenco.movie.dto;

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
public class detailCountDTO {
	
	private int id;
	private int userId;
	private int profileId;
	private int originCount;
	private int purchaseCount;
	
}
