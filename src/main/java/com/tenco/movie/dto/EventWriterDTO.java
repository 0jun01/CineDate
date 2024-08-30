package com.tenco.movie.dto;

import java.sql.Timestamp;

import com.tenco.movie.repository.model.Event;

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
public class EventWriterDTO {

	private int id;
	private String title;
	private Timestamp createdAt;
	private Timestamp releaseDate;
	private Timestamp endDate;
	private String originFileName;
	private String uploadFileName;
	
	public Event toWrite() {
		return Event.builder()
				.title(this.title)
				.releaseDate(this.releaseDate)
				.endDate(this.endDate)
				.originFileName(this.originFileName)
				.uploadFileName(this.uploadFileName)
				.build();
	}
}


