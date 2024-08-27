package com.tenco.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.interfaces.MovieRepository;
import com.tenco.movie.repository.model.Actors;
<<<<<<< HEAD
import com.tenco.movie.repository.model.MovieDetail;
=======
>>>>>>> 528fecc (영화 디테일 1차완료)
import com.tenco.movie.repository.model.Movies;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
	
	@Autowired
	private final MovieRepository movieRepository;
<<<<<<< HEAD

	/**
	 * 무비 엔터티를 movieController에 보내기
	 * @param title
	 * @return movieEntity
	 */
=======
	
>>>>>>> 528fecc (영화 디테일 1차완료)
	public Movies readMovieByTitle(String title) {
		Movies movieEntity = null;
		
		try {
<<<<<<< HEAD
			// 타이틀로 무비엔터티 만들기
=======
>>>>>>> 528fecc (영화 디테일 1차완료)
			movieEntity = movieRepository.findByTitle(title);
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return movieEntity;
	}
<<<<<<< HEAD
	
	/**
	 * movieId를 받아서 그 영화에 출연한 배우들 출력하기
	 * @param movieId
	 * @return actorsEntity
	 */
=======

>>>>>>> 528fecc (영화 디테일 1차완료)
	public List<Actors> readActorsByMovieId(int movieId) {
		List<Actors > actorsEntity = null;
		
		try {
<<<<<<< HEAD
			// movieId로 select 때려서 배우 리스트 받아 오기
=======
>>>>>>> 528fecc (영화 디테일 1차완료)
			actorsEntity = movieRepository.findByMovieId(movieId);
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return actorsEntity;
	}

<<<<<<< HEAD
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

=======
>>>>>>> 528fecc (영화 디테일 1차완료)
}
