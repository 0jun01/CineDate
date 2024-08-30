package com.tenco.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenco.movie.repository.interfaces.SearchRepository;
import com.tenco.movie.repository.model.search;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SearchService {
	
	@Autowired
	private final SearchRepository searchRepository;

	public search searchMovie() {
		return null;
	}
	
}
