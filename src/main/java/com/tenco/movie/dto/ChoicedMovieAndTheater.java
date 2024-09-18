package com.tenco.movie.dto;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoicedMovieAndTheater {

	private int showTimeId;
	private String movieTitle;
	private String theaterName;
	private Date showDate;
	private Time showTime;
}
