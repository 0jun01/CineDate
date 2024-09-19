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
<<<<<<< HEAD
	List<SubRegions> findByRegionId(@Param("regionId") int regionId);
=======

	List<SubRegions> findByRegionId(@Param("regionId") int regionId);

>>>>>>> 2d44f600c135731dcfdfe87ac816cd9d3c5133b0
	// 영화관 조회 추가
	List<Theater> findTheatersBySubregionId(@Param("subregionId") int subregionId);
}
