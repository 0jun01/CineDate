package com.tenco.movie.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubRegions {

	private int id; // subregions_tb의 id
	private int regionId; // regions_tb의 id
	private String name; // subregion의 지역 이름 / 예 동래, 서면
	private String regionImage;
<<<<<<< HEAD
	
=======
>>>>>>> 2d44f600c135731dcfdfe87ac816cd9d3c5133b0
}
