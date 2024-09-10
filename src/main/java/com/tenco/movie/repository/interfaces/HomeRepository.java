package com.tenco.movie.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.Actors;
import com.tenco.movie.repository.model.Director;
import com.tenco.movie.repository.model.Genres;
import com.tenco.movie.repository.model.MovieActor;
import com.tenco.movie.repository.model.MovieDetailTB;
import com.tenco.movie.repository.model.MovieDirector;
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
	public MovieGenre checkMovieGenreExists(@Param("movieId") int movieId, @Param("genreId") int genreId);

	// movie_genre_tb 에 인설트 중간 테이블임
	public void insertMovieGenre(@Param("movieId") int movieId, @Param("genreId") int genreId);

	// movie_detail_tb insert하기
	public int insertMovieDetail(@Param("movieId") int movieId, @Param("title") String title,
			@Param("titleEn") String titleEn, @Param("showTm") String showTm, @Param("openDt") String openDt,
			@Param("prdStatNm") String prdStatNm, @Param("watchGradeNm") String watchGradeNm);

	// directors_tb에 디렉터 insert
	public int insertDirector(Director director);

	// directors_tb에 디렉터 식별 할 수 있게 디렉터 네임을 가져옴
	public Director findByDirectorName(String name);

	// movie_director_tb에 인설트 하기
	public void insertByMovieDirector(@Param("movieId") int movieId, @Param("directorId") int directorId);

	// movie_director_tb에 똑같은 id가 중복되면 못넣게 하기
	public MovieDirector findByMovieIdAndDirectorId(@Param("movieId") int movieId, @Param("directorId") int directorId);

	// actors_tb에 배우 insert
	public int insertActors(Actors actor);

	// 디비에 같은 배우가 안들어가게 하기위해 식별할 수 있는 이름을 가져옴
	public Actors findByActorName(String name);

	// DB에 movies_actors_tb 중간다리에 insert하기
	public void insertMovieActor(@Param("movieId") int movieId, @Param("actorId") int actorId);

	// movies_actors_tb에 이미 값이 있으면 못들어가게 하기
	public MovieActor findByMovieAndGenre(@Param("movieId") int movieid, @Param("actorId") int actorId);

}
