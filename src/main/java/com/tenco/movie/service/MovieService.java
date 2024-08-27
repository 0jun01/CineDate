package com.tenco.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.interfaces.MovieRepository;
import com.tenco.movie.repository.model.Actors;
import com.tenco.movie.repository.model.Movies;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
	
	@Autowired
	private final MovieRepository movieRepository;
	
	public Movies readMovieByTitle(String title) {
		Movies movieEntity = null;
		
		try {
			movieEntity = movieRepository.findByTitle(title);
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return movieEntity;
	}

	public List<Actors> readActorsByMovieId(int movieId) {
		List<Actors > actorsEntity = null;
		
		try {
			actorsEntity = movieRepository.findByMovieId(movieId);
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return actorsEntity;
	}

}
