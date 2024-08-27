package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tenco.movie.repository.model.Notice;

@Mapper
public interface NoticeRepository {
	
	public List<Notice> findAll(); // 공지사항 리스트 조회
	
	public int insert(Notice notice); // 공지사항 작성
	public int updateById(Notice notice); // 공지사항 수정
	public int deleteById(int id); // 공지사항 삭제
	
	public Notice findById(int id); // 게시글 id로 찾기
}

