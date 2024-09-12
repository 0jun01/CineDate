package com.tenco.movie.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
	private int userId;
	private int showTimeId;
	private int quantity;
	private List<Integer> selectedSeatsId;
}
