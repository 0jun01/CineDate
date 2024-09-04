package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.Search;

@Mapper
public interface SearchRepository {

	// 검색 리스트 불러오기
	public List<Search> searchMoiveTitle(@Param("title")String title);

}
