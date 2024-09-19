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
    private String senderId; 
    private String recipientId;
    private String message;
    private LocalDateTime timestamp;

    public Message() {
        // Default constructor
    }

}
