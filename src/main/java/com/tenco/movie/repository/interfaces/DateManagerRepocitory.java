package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.DateProfile;

@Mapper
public interface DateManagerRepocitory {
	
	int movieSuggest(@Param("userId")int userId, @Param("partNerId")int partNerId);
	
	List<DateProfile> matchingList(@Param("principalId")int principalId);
	
	
	
}
