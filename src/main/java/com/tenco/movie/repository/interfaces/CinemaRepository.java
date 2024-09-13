package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.Regions;
import com.tenco.movie.repository.model.SubRegions;
import com.tenco.movie.repository.model.Theater;

@Mapper
public interface CinemaRepository {

	List<Regions> findAllRegions();

	List<SubRegions> findByRegionId(@Param("regionId") int regionId);

	// 영화관 조회 추가
	List<Theater> findTheatersBySubregionId(@Param("subregionId") int subregionId);
	
}