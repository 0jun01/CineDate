package com.tenco.movie.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import com.tenco.movie.repository.model.Movies;

@Mapper
public interface HomeRepository {
	
	public int insertMovie(Movies movies); 
	public Movies findByTitle(String title);
}
