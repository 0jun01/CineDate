package com.tenco.movie.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDBDTO {
	private int page;
	private List<TMDBMovies> results;
	private int totalPages;
	private int totalResults;

	@Data
	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class TMDBMovies {
		private boolean adult;
		private String backdropPath;
		private List<Integer> genreIds;
		private int id;
		private String originalLanguage;
		private String originalTitle;
		private String overview;
		private double popularity;
		private String posterPath;
		private String releaseDate;
		private String title;
		private boolean video;
		private double voteAverage;
		private int voteCount;
	}
}