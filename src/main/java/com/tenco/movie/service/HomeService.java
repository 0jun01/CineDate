package com.tenco.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.handler.exception.RedirectException;
import com.tenco.movie.repository.interfaces.HomeRepository;
import com.tenco.movie.repository.model.Movies;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {
	
	HomeRepository homeRepository;
	
	@Autowired
	public HomeService(HomeRepository homeRepository) {
		this.homeRepository = homeRepository; 
	}

	@Transactional
	public void insertMovies(Movies movies) {
		int result = 0;
		try {
			Movies moviesEntity = homeRepository.findByTitle(movies.getTitle());
			
			if(moviesEntity == null ) {
				result = homeRepository.insertMovie(movies);
				if(result == 0) {
					System.out.println(movies.getTitle() + "은 이미 DB에 있어서 실패");
				} else {
					System.out.println(movies.getTitle() +"DB에 값 넣기 성공");
				}
			}
			
		} catch (DataAccessException e) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}
		
	}
}
