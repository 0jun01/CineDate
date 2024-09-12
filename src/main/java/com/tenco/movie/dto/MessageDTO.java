package com.tenco.movie.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MessageDTO {
	private Long id;
	private String recipientId;
    private String message;
    private String senderId;
    private LocalDateTime timestamp;
}
