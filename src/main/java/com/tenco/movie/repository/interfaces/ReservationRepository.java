package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tenco.movie.repository.model.MovieDetailTB;

@Mapper
public interface ReservationRepository {
	
	// 영화 예매 페이지 모든 영화 보기
	List<MovieDetailTB> findAll();
	
	// 영화 예매 페이지 오름 차순으로 보기
	List<MovieDetailTB> findAscMovie();
	
	List<MovieDetailTB> findGradeNmMovie();
}
