package com.tenco.movie.dto;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoicedMovie {

	private Date showDate;
	private int theaterId;
	private String theaterName;
	private int screenId;
	private String screenName;
	private Time showTime;
}
