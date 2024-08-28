package com.tenco.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.model.Actors;
import com.tenco.movie.repository.model.Movies;
import com.tenco.movie.repository.model.Review;
import com.tenco.movie.service.MovieService;
import com.tenco.movie.service.ReviewService;
import com.tenco.movie.utils.Define;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	private final MovieService movieService;
	private final ReviewService reviewService;
	
	@Autowired
	public MovieController(MovieService movieService, ReviewService reviewService) {
		this.movieService = movieService;
		this.reviewService = reviewService;
	}
	
	
	
	/**
	 * 영화 페이지 요청
	 * @author 김가령
	 */
	@GetMapping("/movies")
	public String getMoviesPage() {
		return "/movie/moviePage";
	}
	
	/**
	 * @param title 박스오피스 영화 제목
	 * @return 무비디테일 페이지
	 * @author 변영준
	 */
	@GetMapping("/detail")
	public String moviePage(Model model,@RequestParam("title") String title) {
		
		if(title == null || title.isEmpty()) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE,HttpStatus.BAD_REQUEST);
		}
		
		Movies selectedMovies = movieService.readMovieByTitle(title);
		
		if(selectedMovies == null) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		}
		
		int movieId = selectedMovies.getId();
		if(movieId == 0) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		}
		System.out.println("--------------");
		System.out.println(movieId);
		System.out.println("--------------");
		List<Actors> movieActors = movieService.readActorsByMovieId(movieId);
		for (Actors actors : movieActors) {
			System.out.println(actors);
		}
		List<Review> movieReviews = reviewService.getReviewsByMovieId(movieId);
		double averageRating = reviewService.getAverageRatingByMovieId(movieId);
		
		model.addAttribute("actors",movieActors);
		model.addAttribute("movie",selectedMovies);
		model.addAttribute("reviews", movieReviews);
		model.addAttribute("averageRating", averageRating);
		
		return "/movie/movieDetail";
	}
	
}
