package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tenco.movie.repository.model.Review;

@Mapper
public interface ReviewRepository {

	List<Review> findByMovieId(int movieId);

}
