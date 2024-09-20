package com.tenco.movie.repository.interfaces;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.dto.ChoicedMovie;
import com.tenco.movie.dto.ChoicedMovieAndTheater;
import com.tenco.movie.dto.MyReservationDTO;
import com.tenco.movie.dto.RegionCountDTO;
import com.tenco.movie.dto.SubRegionDTO;
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

	//////////////////////////////////////// 예매 리팩토링 ///////////////////////////////

	// 영화랑, 극장 선택했을 경우
	List<ChoicedMovieAndTheater> findDateByMovieIdAndTheaterId(@Param("movieId") int movieId,
			@Param("theaterId") int theaterId);

	// 영화만 선택 했을 경우 날짜와 극장 업데이트
	List<ChoicedMovie> findDateAndTheatersByMovieId(int movieId);

	// 영화만 선택 했을 경우 대분류 지역 카운트
	List<RegionCountDTO> countRegion(int movieId);

	// 영화만 선택시 상영중인 영화 지역 서브 지역!
	List<SubRegionDTO> findSubRegionByMovie(int movieId);
	
	
	////////////////////////////////////////// 내 예약 정보 보기 /////////////////////////////
	
	List<MyReservationDTO> myreservation(@Param("userId")int userId);
	
	List<MyReservationDTO> checkUserReservationForMovie(@Param("userId")int userId,@Param("movieId")int movieId);
}
