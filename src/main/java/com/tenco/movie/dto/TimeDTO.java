package com.tenco.movie.dto;

import java.sql.Date;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeDTO {

	private int showTimeId;
	private LocalTime showTime;
	private int capacity;
	private String name;
}
