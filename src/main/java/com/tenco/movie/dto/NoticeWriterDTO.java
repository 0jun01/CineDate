package com.tenco.movie.dto;

import com.tenco.movie.repository.model.Notice;

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
public class NoticeWriterDTO {
	private int id;
	private String category;
	private String title;
	private String content;
	
	public Notice toWrite() {
		return Notice.builder()
				.adminId(1)
				.category(this.category)
				.title(this.title)
				.content(this.content)
				.build();
	}
	
	public Notice reWrite(int id) {
		return Notice.builder()
				.id(id)
				.adminId(1)
				.category(this.category)
				.title(this.title)
				.content(this.content)
				.build();
	}
	
}
