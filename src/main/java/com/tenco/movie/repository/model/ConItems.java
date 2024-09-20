package com.tenco.movie.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ConItems {
	private int id;
	private String name;
	private String itemDesc;
	private int price;
	private String itemImg;
}
