package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tenco.movie.repository.model.MovieDetailTB;
import com.tenco.movie.repository.model.Regions;
import com.tenco.movie.repository.model.SubRegions;

@Mapper
public interface ReservationRepository {
	
	// 영화 예매 페이지 모든 영화 보기
	List<MovieDetailTB> findAll();
	
	// 영화 예매 페이지 오름 차순으로 보기
	List<MovieDetailTB> findAscMovie();
	
	// 영화 예매 페이지의 관람 등급순으로 보기
	List<MovieDetailTB> findGradeNmMovie();
	
	// 영화 예매 페이지의 지역 대분류
	List<Regions> findAllRegions();
	
	// 영화 예매 페이지 id로 소분류 네임 얻어내기
	List<SubRegions> findSubRegionById(int id);
}
