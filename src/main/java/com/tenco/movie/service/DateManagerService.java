package com.tenco.movie.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.dto.MessageDTO;
import com.tenco.movie.repository.interfaces.DateManagerRepocitory;
import com.tenco.movie.repository.model.DateProfile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DateManagerService {

    private final DateManagerRepocitory dateManagerRepocitory;

    @Transactional
    public int movieSuggest(int userId, int partNerId) {
        return dateManagerRepocitory.movieSuggest(userId, partNerId);
    }

    public List<DateProfile> matchingList(int principalId) {
        return dateManagerRepocitory.matchingList(principalId);
    }

    public List<MessageDTO> chatHistory(int principalId, int partnerId) {
        List<MessageDTO> list = dateManagerRepocitory.chatHistory(principalId, partnerId);

        for (MessageDTO messageDTO : list) {
            if (messageDTO.getSenderId().equals(principalId)) {
                messageDTO.setPosition("나");
            } else {
                messageDTO.setPosition("상대");
            }
        }
        return list;
    }
    
    public int chatHitory(String message, Integer senderId, Integer recipientId) {
        MessageDTO dto = MessageDTO.builder()
                .message(message)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();
        return dateManagerRepocitory.insrtChat(dto);
    }
}
