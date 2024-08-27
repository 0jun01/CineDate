package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.Actors;
<<<<<<< HEAD
import com.tenco.movie.repository.model.MovieDetail;
=======
>>>>>>> 528fecc (영화 디테일 1차완료)
import com.tenco.movie.repository.model.Movies;

@Mapper
public interface MovieRepository {
	
	public Movies findByTitle(@Param("title") String title);
	public List<Actors> findByMovieId(@Param("id") int id);
<<<<<<< HEAD
	public MovieDetail findDetailByMovieId(@Param("id") int id);
=======
>>>>>>> 528fecc (영화 디테일 1차완료)
}
