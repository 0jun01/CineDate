package com.tenco.movie.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HolidayResponse {

	@JsonProperty("response")
	private Response response;

	@Data
	@NoArgsConstructor
	public static class Response {
		@JsonProperty("header")
		private Header header;

		@JsonProperty("body")
		private Body body;
	}

	@Data
	@NoArgsConstructor
	public static class Header {
		@JsonProperty("resultCode")
		private String resultCode;

		@JsonProperty("resultMsg")
		private String resultMsg;
	}

	@Data
	@NoArgsConstructor
	public static class Body {
		@JsonProperty("items")
		private Items items;

		@JsonProperty("numOfRows")
		private int numOfRows;

		@JsonProperty("pageNo")
		private int pageNo;

		@JsonProperty("totalCount")
		private int totalCount;
	}

	@Data
	@NoArgsConstructor
	public static class Items {
		@JsonProperty("item")
		private List<Item> item;
	}

	@Data
	@NoArgsConstructor
	public static class Item {
		@JsonProperty("dateKind")
		private String dateKind;

		@JsonProperty("dateName")
		private String dateName;

		@JsonProperty("isHoliday")
		private String isHoliday;

		@JsonProperty("locdate")
		private int locdate;

		@JsonProperty("seq")
		private int seq;
	}
}