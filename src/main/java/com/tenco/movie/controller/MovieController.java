package com.tenco.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie")
public class MovieController {

	/**
	 * 영화 페이지 요청
	 * @author 김가령
	 */
	@GetMapping("/movies")
	public String getMoviesPage() {
		return "/movie/moviePage";
	}
	
}
