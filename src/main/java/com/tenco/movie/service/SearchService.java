package com.tenco.movie.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.handler.exception.RedirectException;
import com.tenco.movie.repository.interfaces.SearchRepository;
import com.tenco.movie.repository.model.Search;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SearchService {
	
	@Autowired
	private final SearchRepository searchRepository;

	/**
	 * 메인 검색 기능
	 * @param title
	 * @return
	 * @author 형정
	 */
	public List<Search> searchMoiveTitle(String title) {
			
			List<Search> searchListEntity = null;
			
			try {
				searchListEntity = searchRepository.searchMoiveTitle(title);
			} catch (DataAccessException e) {
				throw new DataDeliveryException(Define.INVALID_INPUT, HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception e) {
				throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
			}
			
			return searchListEntity != null ? searchListEntity : Collections.emptyList();
		}
}
