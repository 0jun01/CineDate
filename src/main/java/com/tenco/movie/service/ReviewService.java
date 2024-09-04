package com.tenco.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.repository.interfaces.ReviewRepository;
import com.tenco.movie.repository.model.Review;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
/**
 * 리뷰
 * @author 가령
 */
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	public List<Review> getReviewsByMovieId(int movieId) {
		return reviewRepository.findByMovieId(movieId);
	}
	
	// 리뷰 평균 계산
	public double getAverageRatingByMovieId(int movieId) {
        List<Review> reviews = getReviewsByMovieId(movieId);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        double sum = reviews.stream()
                            .mapToDouble(Review::getRating)
                            .sum();
        return sum / reviews.size();
    }
	
	
	
	// 관람평 추가
	@Transactional
	public void saveReview(Review review) {
        reviewRepository.saveReview(review);
    }
	
	// 중복 리뷰 체크
    public boolean hasUserReviewed(int movieId, int userId) {
        return reviewRepository.existsByMovieIdAndUserId(movieId, userId);
    }
	
}
