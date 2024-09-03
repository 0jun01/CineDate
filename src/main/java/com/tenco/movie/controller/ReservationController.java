package com.tenco.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.model.MovieDetailTB;
import com.tenco.movie.service.ReservationService;
import com.tenco.movie.utils.Define;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	private final ReservationService reservationService;

	@Autowired
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	/**
	 * 예매 페이지 요청
	 * 
	 * @author 변영준
	 */
	@GetMapping("/reservation")
	public String reservationPage(Model model) {
		List<MovieDetailTB> movieList = null;

		movieList = reservationService.readAllMovie();

		if (movieList == null) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.BAD_REQUEST);
		}

		model.addAttribute("movieList", movieList);
		return "/reservation/reservationPage";
	}

	@GetMapping("/movies")
	@ResponseBody
	public List<MovieDetailTB> getFiltedMovies(@RequestParam("sortBy") String sortBy) {
		System.out.println("-----------------------------------");
		System.out.println(sortBy+sortBy+sortBy+sortBy+sortBy+sortBy);
		System.out.println("-----------------------------------");
		List<MovieDetailTB> movieList;
		if ("korean".equalsIgnoreCase(sortBy)) {
			movieList = reservationService.readAllMoviesSortedByTitle();
			System.out.println("korean");
		} else if ("age".equalsIgnoreCase(sortBy)) {
			movieList = reservationService.readAllMoviesSortedByWatchGrade();
			System.out.println("age");
		} else {
			movieList = reservationService.readAllMovie(); // 기본값
			System.out.println("default");
		}
		return movieList;
	}

}
