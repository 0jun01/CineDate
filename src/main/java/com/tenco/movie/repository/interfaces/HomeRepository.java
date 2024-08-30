package com.tenco.movie.repository.interfaces;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
public interface HomeRepository {

>>>>>>> 32a4fcf (영화 검색 기능 중)
=======
public interface HomeRepository {

=======
=======
>>>>>>> b04a172 (충돌해결중)
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> e6ede81 (영화 API DB로 자동 연결구현)
<<<<<<< HEAD
>>>>>>> 8b40cc4 (영화 API DB로 자동 연결구현)
=======
=======
	
=======
=======
>>>>>>> b04a172 (충돌해결중)

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
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> dac25ec (3차 자동 인설트 완성)
<<<<<<< HEAD
>>>>>>> a3aafde (3차 자동 인설트 완성)
=======
=======
=======
>>>>>>> b04a172 (충돌해결중)

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
<<<<<<< HEAD
>>>>>>> 679302a (영화API파싱 거의 95완료)
>>>>>>> 96e61be (영화API파싱 거의 95완료)
=======
>>>>>>> b04a172 (충돌해결중)
}
