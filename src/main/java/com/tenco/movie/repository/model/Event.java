package com.tenco.movie.repository.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Event {

	private int id;
	private String title; // 이벤트명
	private Timestamp createdAt; 

	private Timestamp releaseDate; // 이벤트 시작일자

	private Timestamp endDate; // 이벤트 종료일자
	
	private String originFileName; // 이벤트 이미지 파일명
	private String uploadFileName; // 이벤트 이미지 업로드 파일명
	// 시간 포맷
    public String timestartToString() {

        SimpleDateFormat startTime = new SimpleDateFormat("yyyy-MM-dd");

        return startTime.format(releaseDate);
    }
    
    public String timeendToString() {
    	SimpleDateFormat endTime = new SimpleDateFormat("yyyy-MM-dd");
    	
    	return endTime.format(endDate);
    }
   
    public String getStatus() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endDateTime = endDate.toLocalDateTime();

        if (now.isBefore(endDateTime)) {
            return "진행중";
        } else {
            return "종료됨";
        }
    }

    

}
