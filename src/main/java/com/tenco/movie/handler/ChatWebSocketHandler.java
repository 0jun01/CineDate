package com.tenco.movie.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenco.movie.dto.MessageDTO;
import com.tenco.movie.service.DateManagerService;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DateManagerService managerService;

    public ChatWebSocketHandler(DateManagerService managerService) {
        this.managerService = managerService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        MessageDTO dto = objectMapper.readValue(payload, MessageDTO.class);

        managerService.chatHitory(dto.getMessage(), dto.getSenderId(), dto.getRecipientId());

        for (WebSocketSession webSocketSession : sessions.values()) {
            webSocketSession.sendMessage(message);
        }
    }
}
