package com.tenco.movie.repository.model;

import java.sql.Timestamp;

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
public class Bookings {
	private int id;
	private int userId;
	private int showtimeId;
	private int quantity;
	private Timestamp bookingTime;
	private String status;
}
