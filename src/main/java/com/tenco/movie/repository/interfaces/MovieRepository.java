package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.Actors;
import com.tenco.movie.repository.model.Movies;

@Mapper
public interface MovieRepository {
	
	public Movies findByTitle(@Param("title") String title);
	public List<Actors> findByMovieId(@Param("id") int id);
}
