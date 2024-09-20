package com.tenco.movie.repository.interfaces;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.dto.TheaterCountDTO;
import com.tenco.movie.dto.TimeDTO;
import com.tenco.movie.repository.model.Bookings;
import com.tenco.movie.repository.model.MovieDetail;
import com.tenco.movie.repository.model.MovieDetailTB;
import com.tenco.movie.repository.model.Movies;
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

	List<MovieDetail> findMovieByFetchedDate(String date);

	List<SubRegions> findByMovieIdAndShowDate(@Param("id") int id, @Param("showDate") Date showDate);

	Movies findById(int id);

	List<TheaterCountDTO> findTheaterByMovieIdAndshowDate(@Param("movieId") int id, @Param("showDate") String showDate);

	List<TimeDTO> findTimeByShowDateAndMovieIdAndSubregionId(@Param("showDate") Date showDate,
			@Param("movieId") int movieId, @Param("subregionId") int subregionId);

	int insertBooking(@Param("userId") int userId, @Param("showTimeId") int showTimeId,
			@Param("quantity") int quantity);

	int insertBookingSeats(@Param("bookingId") int bookingId, @Param("seatIds") List<Integer> seatIds);

	Bookings viewBookingByUserIdAndShowTimeId(@Param("userId") int userId, @Param("showTimeId") int showTimeId);

	List<Integer> checkOccupied(int showtimeId);
	
    

	
}
