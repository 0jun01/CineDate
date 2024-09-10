package com.tenco.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.repository.interfaces.DateManagerRepocitory;
import com.tenco.movie.repository.model.DateProfile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DateManagerService {
	
	@Autowired
	private final DateManagerRepocitory dateManagerRepocitory;
	
	
	@Transactional
	public int movieSuggest(int userId, int partNerId) {
		int result = 0;
		
		result = dateManagerRepocitory.movieSuggest(userId, partNerId);
		
		return result;
	}
	
	
	public List<DateProfile> matchingList(int principalId){
		
		List<DateProfile> list =  dateManagerRepocitory.matchingList(principalId);
		
		return list;
	}
	
}
