package com.tenco.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.interfaces.MovieRepository;
import com.tenco.movie.repository.model.Actors;
import com.tenco.movie.repository.model.MovieDetail;
import com.tenco.movie.repository.model.Movies;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

	@Autowired
	private final MovieRepository movieRepository;

	/**
	 * 무비 엔터티를 movieController에 보내기
	 * 
	 * @param title
	 * @return movieEntity
	 */
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

	/**
	 * movieId를 받아서 그 영화에 출연한 배우들 출력하기
	 * 
	 * @param movieId
	 * @return actorsEntity
	 */
	public List<Actors> readActorsByMovieId(int movieId) {
		List<Actors> actorsEntity = null;

		try {
			// movieId로 select 때려서 배우 리스트 받아 오기
			actorsEntity = movieRepository.findByMovieId(movieId);
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return actorsEntity;
	}

	public MovieDetail readMovieAllofData(int movieId) {
		MovieDetail detailEntity = null;
		try {
			detailEntity = movieRepository.findDetailByMovieId(movieId);
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return detailEntity;
	}

	 public List<Movies> readAllMovies() {
	        try {
	            return movieRepository.findAllMovies();
	        } catch (DataDeliveryException e) {
	            // 로깅 추가
	            System.err.println("DataDeliveryException occurred: " + e.getMessage());
	            throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.INTERNAL_SERVER_ERROR);
	        } catch (Exception e) {
	            // 로깅 추가
	            System.err.println("Exception occurred: " + e.getMessage());
	            throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
