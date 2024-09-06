package com.tenco.movie.repository.interfaces;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.Review;

@Mapper
public interface ReviewRepository {

	List<Review> findByMovieId(int movieId);
	void saveReview(Review review);
	boolean existsByMovieIdAndUserId(@Param("movieId") int movieId, @Param("userId") int userId); // 중복 리뷰 체크
	void deleteReviewById(@Param("id") int id);
	Review findById(@Param("id") int id);
	void updateReview(Review review);
	List<Review> findByMovieIdWithPagination(@Param("movieId") int movieId, @Param("offset") int offset, @Param("size") int size);
}
