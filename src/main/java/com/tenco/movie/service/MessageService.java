package com.tenco.movie.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.dto.MessageDTO;
import com.tenco.movie.repository.interfaces.MessageRepository;
import com.tenco.movie.repository.model.Message;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
 
    @Transactional
    public void sendMessage(MessageDTO messagedto) throws SQLException {
        try {
            messageRepository.save(messagedto);
        } catch (DataAccessException e) {
            // Spring DataAccessException 처리, 트랜잭션 롤백
            System.err.println("메시지 전송 중 오류 발생: " + e.getMessage());
            throw e; // 예외를 다시 던져서 트랜잭션 롤백을 유도
        }
    }

    @Transactional
    public void save(MessageDTO messagedto) throws SQLException {
        try {
            System.out.println("여기는 서비스메세지 들어오나: " + messagedto); // 로그 추가
            messageRepository.save(messagedto);
        } catch (DataAccessException e) {
            // Spring DataAccessException 처리, 트랜잭션 롤백
            System.err.println("메시지 저장 중 오류 발생: " + e.getMessage());
            throw e; // 예외를 다시 던져서 트랜잭션 롤백을 유도
        }
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> getConversation(String senderId, String recipientId) {
        return messageRepository.findConversation(senderId, recipientId);
    }
}
