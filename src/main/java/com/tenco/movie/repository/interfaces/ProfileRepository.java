package com.tenco.movie.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.DateProfile;

@Mapper
public interface ProfileRepository {
	
	
	public DateProfile searchProfile(@Param("userId")int principalId);
	public int createdProfile(DateProfile profile);
	
}
