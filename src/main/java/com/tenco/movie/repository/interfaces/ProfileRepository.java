package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.DateProfile;

@Mapper
public interface ProfileRepository {
	
	
	public DateProfile searchProfile(@Param("userId") int principalId);
	public DateProfile searchNickName(@Param("nickname") String nickname);
	public int createdProfile(DateProfile profile);
	void updateProfile(DateProfile profile);
	
	public List<DateProfile> searchPartner(@Param("userId")int userId,@Param("gender")String gender);
	
}
