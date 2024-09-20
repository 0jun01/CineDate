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
 * 리뷰 서비스 
 * @author 김가령
 */
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	
	public List<Review> getReviewsByMovieId(int movieId, int page, int size) {
        int offset = (page - 1) * size;
        return reviewRepository.findByMovieIdWithPagination(movieId, offset, size);
    }
	
	// 리뷰 평균 계산
    public double getAverageRatingByMovieId(int movieId) {
        List<Review> reviews = getReviewsByMovieId(movieId, 1, Integer.MAX_VALUE);
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

    
    /**
     * 리뷰 ID로 리뷰 삭제
     * @param id 삭제할 리뷰의 ID
     */
    @Transactional
    public void deleteReview(int id) {
        reviewRepository.deleteReviewById(id);
    }
    
    /**
     * 리뷰 ID로 리뷰 찾기
     * @param id 리뷰의 ID
     * @return 리뷰 객체
     */
    public Review findReviewById(int id) {
        return reviewRepository.findById(id);
    }
	
    
 // 리뷰 업데이트
    public void updateReview(Review review) {
        reviewRepository.updateReview(review);
    }
    
    
}
