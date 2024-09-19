package com.tenco.movie.dto;

import lombok.Data;

@Data
public class UserItemInventory {

	private int itemId;
	private int quantity;
	private String name;
	private String itemDesc;
	private String itemImg;
}
