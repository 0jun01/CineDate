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
import com.tenco.movie.repository.model.MovieDetail;
import com.tenco.movie.repository.model.Movies;
import com.tenco.movie.service.MovieService;
import com.tenco.movie.utils.Define;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	private final MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	/**
	 * 영화 페이지 요청
	 * 
	 * @author 김가령, 성후
	 */
	@GetMapping("/movies")
	  public String moviesPage(Model model) {
        try {
            List<Movies> movieList = movieService.readAllMovies();
            model.addAttribute("movieList", movieList);
            return "/movie/moviePage";
        } catch (DataDeliveryException e) {
            System.err.println("들어오나");
            return "/movie/moviePage"; 
        } catch (Exception e) {
            System.err.println("여기도?");
            return "/movie/moviePage"; 
        }
    }
	/**
	 * @param title 박스오피스 영화 제목
	 * @return 무비디테일 페이지
	 * @author 변영준
	 */
	@GetMapping("/detail")
	public String detailPage(Model model, @RequestParam("title") String title) {

		// 타이틀 유효성 검사
		if (title == null || title.isEmpty()) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		}

		// 타이틀로 던져서 타이틀 이름에 맞는 무비 정보 가져오기
		Movies selectedMovies = movieService.readMovieByTitle(title);

		// 유효성 검사
		if (selectedMovies == null) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		}

		// DB에 저장되어있는 무비의 id 가져오기
		int movieId = selectedMovies.getId();

		// 유효성 검사
		if (movieId == 0) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		}

		// 그 무비에 출연한 배우,감독을 출력하는 리스트
		List<Actors> movieActors = movieService.readActorsByMovieId(movieId);
		MovieDetail movieDetail = movieService.readMovieAllofData(movieId);
		// movieDetail.jsp에 올려줌
		model.addAttribute("actors", movieActors);
		model.addAttribute("movie", selectedMovies);
		model.addAttribute("movieDetail", movieDetail);
		return "/movie/movieDetail";
	}

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
		
		model.addAttribute("actors",movieActors);
		model.addAttribute("movie",selectedMovies);
		return "/movie/movieDetail";
	}
	
}
