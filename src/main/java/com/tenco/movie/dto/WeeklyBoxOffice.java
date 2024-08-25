package com.tenco.movie.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeeklyBoxOffice {
	// JsonIgnoreProperties로 json 값을 안 받으려고 무시 해줌
	// 저 어노테이션 설정 안해주면 오류뜸!
	@JsonProperty("boxOfficeResult")
	private BoxOfficeResult boxOfficeResult;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class BoxOfficeResult {
		@JsonProperty("boxofficeType")
		private String boxofficeType;

		@JsonProperty("showRange")
		private String showRange;

		@JsonProperty("yearWeekTime")
		private String yearWeekTime;

		@JsonProperty("weeklyBoxOfficeList")
		private List<Movie> weeklyBoxOfficeList;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Movie {
		@JsonProperty("rnum")
		private String rnum;

		@JsonProperty("rank")
		private String rank;

		@JsonProperty("rankInten")
		private String rankInten;

		@JsonProperty("rankOldAndNew")
		private String rankOldAndNew;

		@JsonProperty("movieCd")
		private String movieCd;

		@JsonProperty("movieNm")
		private String movieNm;

		@JsonProperty("openDt")
		private String openDt;

		@JsonProperty("salesAmt")
		private String salesAmt;

		@JsonProperty("salesShare")
		private String salesShare;

		@JsonProperty("salesInten")
		private String salesInten;

		@JsonProperty("salesChange")
		private String salesChange;

		@JsonProperty("salesAcc")
		private String salesAcc;

		@JsonProperty("audiCnt")
		private String audiCnt;

		@JsonProperty("audiInten")
		private String audiInten;

		@JsonProperty("audiChange")
		private String audiChange;

		@JsonProperty("audiAcc")
		private String audiAcc;

		@JsonProperty("scrnCnt")
		private String scrnCnt;

		@JsonProperty("showCnt")
		private String showCnt;
	}

}