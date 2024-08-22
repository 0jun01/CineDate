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
public class ConHistory {

	private int id; 
	private int userId; // User pk
	private int wConCount; // 사용한 콘 개수
	private int dConCount; // 충전한 콘 개수
	private int conAmount; // 총전 또는 사용 후 콘 개수
	private String itemName; // 아이템 사용 이름
	
}
