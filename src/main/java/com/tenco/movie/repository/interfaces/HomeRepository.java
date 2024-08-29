package com.tenco.movie.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.Director;
import com.tenco.movie.repository.model.Genres;
import com.tenco.movie.repository.model.MovieDetailTB;
import com.tenco.movie.repository.model.MovieGenre;
import com.tenco.movie.repository.model.Movies;

@Mapper
public interface HomeRepository {
	
	// movies_tb 인서트
	public int insertMovie(Movies movies);

	// movies_tb title로 찾기
	public Movies findByTitle(String title);
	
	// movies_detail_tb id로 찾기
	public MovieDetailTB findById(int id);
	
	// genres_tb의 장르 ID 추출
	public Genres findIdByGenreName(String genre);
	
	
	// genres_tb에 insert 장르 추가
	public void insertGenre(String genre);
	
	// genres_genre_tb에 만약 무비Id가 있으면 추가되지 못하게 select
	public MovieGenre checkMovieGenreExists(@Param("movieId") int movieId,@Param("genreId") int genreId);
	
	// movie_genre_tb 에 인설트 중간 테이블임
	public void insertMovieGenre(@Param("movieId") int movieId ,@Param("genreId") int genreId);
	
	// movie_detail_tb insert하기
	public int insertMovieDetail(@Param("movieId") int movieId, @Param("title") String title,
			@Param("titleEn") String titleEn, @Param("showTm") String showTm, @Param("openDt") String openDt,
			@Param("prdStatNm") String prdStatNm, @Param("watchGradeNm") String watchGradeNm);
	
	public int insertDirector(Director director);
}
