package com.tenco.movie.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.repository.interfaces.MessageRepository;
import com.tenco.movie.repository.model.Message;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

	 @Autowired
	    private MessageRepository messageRepository;
	 
	 @Transactional
	 public void sendMessage(Message message) {
	            try {
					messageRepository.save(message);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	 @Transactional
	 public void save(Message message) {
		   try {
			   System.out.println("여기는 서비스메세지 들어오나: " + message); // 로그 추가
			messageRepository.save(message);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	    }

