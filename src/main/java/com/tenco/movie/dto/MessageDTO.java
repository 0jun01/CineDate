package com.tenco.movie.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
	private Integer recipientId;
    private String message;
    private Integer senderId;
    private Timestamp timestamp;
    private String position;
    
    
    public String timestampToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(timestamp);
	}
	
    
}


