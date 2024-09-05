package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.Notice;

@Mapper
public interface NoticeRepository {
	
	public List<Notice> findAll(@Param("limit") int limit,	
			@Param("offset") int offset); // 공지사항 리스트 조회
	Notice findById(int id); // 공지사항 상세보기
	
	public int countNotice();
	
	
	public int insert(Notice notice); // 공지사항 작성
	public int updateById(Notice notice); // 공지사항 수정
	public int deleteById(int id); // 공지사항 삭제
	
	
}

