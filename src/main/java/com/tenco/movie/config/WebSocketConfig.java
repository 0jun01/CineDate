package com.tenco.movie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.tenco.movie.handler.ChatWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		System.out.println("세션 생성 1");
        registry.addHandler(chatWebSocketHandler(), "/ws/chat")
                .setAllowedOrigins("*")  // Allow all origins, adjust as necessary
                .addInterceptors(new HttpSessionHandshakeInterceptor()); // If session support is needed
    }

    public WebSocketHandler chatWebSocketHandler() {
    	System.out.println("세션 생성 2");
        return new ChatWebSocketHandler();
    }
}