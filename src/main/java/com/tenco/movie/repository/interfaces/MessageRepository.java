package com.tenco.movie.repository.interfaces;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.dto.MessageDTO;
import com.tenco.movie.repository.model.Message;

@Mapper
public interface MessageRepository {
	
	 	public void save(MessageDTO messagedto);
	 	
	 	 List<MessageDTO> findConversation(@Param("senderId") String senderId, @Param("recipientId") String recipientId);

	}