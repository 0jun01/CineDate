package com.tenco.movie.handler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.tenco.movie.repository.model.User;

public class ChatWebSocketHandler extends TextWebSocketHandler {

	private final ConcurrentMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		// 사용자 ID 추출
		String userId = extractUserId(session);

		System.out.println("userId : " + userId);
		if (userId != null) {
			sessions.put(userId, session);
			System.out.println("웹소켓 세션이 사용자 " + userId + "에 대해 설정되었습니다.");
		} else {
			System.err.println("사용자 ID가 null입니다. 세션을 추가할 수 없습니다.");
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		// 사용자 ID 추출
		String userId = extractUserId(session);
		if (userId != null) {
			sessions.remove(userId);
			System.out.println("웹소켓 세션이 사용자 " + userId + "에 대해 종료되었습니다.");
		} else {
			System.err.println("사용자 ID가 null입니다. 세션을 제거할 수 없습니다.");
		}
	}

	private String extractUserId(WebSocketSession session) {
		// 세션 속성 또는 원칙에서 사용자 ID 추출

		String userId = null;
		System.out.println("세션 활성화");
		System.out.println(session.getAttributes().get("principal"));
		User principal = (User) session.getAttributes().get("principal");
		session.getAttributes().put("userId", principal.getId()); // 속성 설정
		userId = String.valueOf(principal.getId());

		return userId;
	}
}
