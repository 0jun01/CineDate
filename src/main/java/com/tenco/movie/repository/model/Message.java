package com.tenco.movie.repository.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class Message {
    private Long id;
    private int senderId; // int로 유지
    private String recipientId; // String으로 수정
    private String message;
    private LocalDateTime timestamp;

    public Message() {
        // Default constructor
    }

    // Lombok's @Data will generate getters and setters
}
