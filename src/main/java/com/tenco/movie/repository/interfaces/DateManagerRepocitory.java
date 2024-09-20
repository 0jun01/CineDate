package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.dto.MessageDTO;
import com.tenco.movie.dto.detailCountDTO;
import com.tenco.movie.dto.matchingDTO;
import com.tenco.movie.repository.model.User;

@Mapper
public interface DateManagerRepocitory {
	
	int movieSuggest(@Param("userId")int userId, @Param("partNerId")int partNerId);
	
	int retryDate(@Param("userId")int userId, @Param("partNerId")int partNerId);
	int isOkDate(@Param("userId")int userId, @Param("partNerId")int partNerId);
	int isRefuseDate(@Param("userId")int userId, @Param("partNerId")int partNerId);
	
	List<matchingDTO> matchingList(@Param("principalId")int principalId);
	
	
	// ===  detailCount ===
	
	detailCountDTO datailCount(@Param("userId")int id);
	
	int updateIpurchaseCount(@Param("userId")int id, @Param("count")int count);
	
	int updateOriginCount(@Param("userId")int id,@Param("count")int count);
	
	
	User searchUserById(int id); 
	
	// ===  END detailCount ===
	
	
	// == chatdto ===
	List<MessageDTO> chatHistory(@Param("senderId")int principalId,@Param("recipientId")int partnerId);
	
	int insrtChat();

	int insrtChat(MessageDTO dto);

	void resetAllDrtailCount();



	
	
	
}
