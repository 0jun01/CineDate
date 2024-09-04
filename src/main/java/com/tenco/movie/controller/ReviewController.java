package com.tenco.movie.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.model.MovieDetail;
import com.tenco.movie.repository.model.Review;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.service.MovieService;
import com.tenco.movie.service.ReviewService;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
public class ReviewController {
	
	@Autowired
    private final ReviewService reviewService;
	
	@Autowired
    private final MovieService movieService;

   

	/**
	 * 관람평 작성 기능 요청
	 * @return 영화 상세보기 페이지
	 * @author 김가령
	 */
    @PostMapping("/review")
    public String submitReview(
            @RequestParam(name ="movieId")int movieId,
            @RequestParam(name ="reviewText")String reviewText,
            @RequestParam(name ="rating")int rating,
            @SessionAttribute(name="principal")User principal,
            Model model) throws UnsupportedEncodingException {
    	
    	int userId = principal.getId(); // principal에서 userId를 가져옴
    		
    	 // 이미 작성된 리뷰가 있는지 확인
        boolean hasReviewed = reviewService.hasUserReviewed(movieId, userId);
    	
        if(hasReviewed) {
        	throw new DataDeliveryException(Define.DUPLICATION_REVIEW, HttpStatus.BAD_REQUEST);
        }
    	
    	MovieDetail selectedMovie = movieService.readMovieAllofData(movieId);
    	String movieTitle = selectedMovie.getTitle();
    	
    	String encodedTitle = URLEncoder.encode(movieTitle, "UTF-8");
    	
        Review review = Review.builder()
                .movieId(movieId)
                .userId(userId) // String 타입으로 설정
                .reviewText(reviewText)
                .rating(rating)
                .reviewDate(Timestamp.from(Instant.now()))
                .build();

        reviewService.saveReview(review);

        return "redirect:/movie/detail?title=" + encodedTitle;
    }
}
