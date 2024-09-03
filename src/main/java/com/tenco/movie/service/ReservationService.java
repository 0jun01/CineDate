package com.tenco.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenco.movie.repository.interfaces.ReservationRepository;
import com.tenco.movie.repository.model.MovieDetailTB;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	@Autowired
	private final ReservationRepository reservationRepository;

	public List<MovieDetailTB> readAllMovie() {
		List<MovieDetailTB> movieList = null;

		try {
			movieList = reservationRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
		return movieList;
	}

	public List<MovieDetailTB> readAllMoviesSortedByWatchGrade() {
		List<MovieDetailTB> movieList = null;
		
		try {
			movieList = reservationRepository.findGradeNmMovie();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieList;
	}

	public List<MovieDetailTB> readAllMoviesSortedByTitle() {
		List<MovieDetailTB> movieList = null;
		
		try {
			movieList = reservationRepository.findAscMovie();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieList;
	}
}
