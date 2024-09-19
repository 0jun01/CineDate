package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.dto.profileDetailDTO;
import com.tenco.movie.repository.model.DateProfile;

@Mapper
public interface ProfileRepository {
	
	public DateProfile searchProfile(@Param("userId") int principalId);
	public DateProfile searchNickName(@Param("nickname") String nickname);
	public int createdProfile(DateProfile profile);
	public int createdProfileDetail(profileDetailDTO detailDTO);
	void updateProfile(DateProfile profile);
	
	public List<DateProfile> searchPartner(@Param("userId")int userId,@Param("gender")String gender);
	
	// 슈퍼 파트너 리스트
	public List<DateProfile> superPartner(@Param("userId")int userId,@Param("gender")String gender);
	
	public DateProfile detailPartner(@Param("userId")int userId, @Param("id")int id);
	public profileDetailDTO detailPartnerDetail(@Param("userid")int id);
	
}
	
