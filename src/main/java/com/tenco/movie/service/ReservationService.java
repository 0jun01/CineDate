package com.tenco.movie.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.Session;
import com.tenco.movie.dto.ChoicedMovie;
import com.tenco.movie.dto.RegionCountDTO;
import com.tenco.movie.dto.SubRegionDTO;
import com.tenco.movie.dto.TheaterCountDTO;
import com.tenco.movie.dto.TimeDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.handler.exception.UnAuthorizedException;
import com.tenco.movie.repository.interfaces.ReservationRepository;
import com.tenco.movie.repository.model.Bookings;
import com.tenco.movie.repository.model.MovieDetail;
import com.tenco.movie.repository.model.MovieDetailTB;
import com.tenco.movie.repository.model.Movies;
import com.tenco.movie.repository.model.Regions;
import com.tenco.movie.repository.model.SubRegions;
import com.tenco.movie.utils.Define;

import jakarta.security.auth.message.config.AuthConfig;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	@Autowired
	private final ReservationRepository reservationRepository;

	/**
	 * 모든 영화 읽기
	 * 
	 * @return movieList
	 * @author 변영준
	 */
	public List<MovieDetailTB> readAllMovie() {
		List<MovieDetailTB> movieList = null;

		try {
			movieList = reservationRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return movieList;
	}

	/**
	 * 시청 등급에 따라 필터된 영화 읽기
	 * 
	 * @return movieList
	 * @author 변영준
	 */
	public List<MovieDetailTB> readAllMoviesSortedByWatchGrade() {
		List<MovieDetailTB> movieList = null;

		try {
			movieList = reservationRepository.findGradeNmMovie();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieList;
	}

	/**
	 * 가나다순으로 필터
	 * 
	 * @return movieList
	 * @author 변영준
	 */
	public List<MovieDetailTB> readAllMoviesSortedByTitle() {
		List<MovieDetailTB> movieList = null;

		try {
			movieList = reservationRepository.findAscMovie();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieList;
	}

	/**
	 * 지역 리스트 읽기
	 * 
	 * @return regionList
	 * @author 변영준
	 */
	public List<Regions> readAllRegion() {
		List<Regions> regionList = null;
		List<SubRegions> subRegionsList = null;
		try {
			regionList = reservationRepository.findAllRegions();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return regionList;
	}

	public List<SubRegions> readSubRegion(int id) {
		List<SubRegions> subRegionList = null;

		try {
			subRegionList = reservationRepository.findSubRegionById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subRegionList;
	}

	public List<SubRegions> readfirstSubRegion() {
		List<SubRegions> subRegionList = null;

		try {
			subRegionList = reservationRepository.findSubRegionById(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subRegionList;
	}

	public List<MovieDetail> fetchMovieListByDate(String date) {
		List<MovieDetail> fetchedMovieList = null;

		try {
			fetchedMovieList = reservationRepository.findMovieByFetchedDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fetchedMovieList;
	}

	public Movies fetchMovieTitleAndImg(int movieId) {
		Movies movie = null;

		try {
			movie = reservationRepository.findById(movieId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movie;

	}

	public List<TheaterCountDTO> fetchTheater(int movieId, String date) {
		List<TheaterCountDTO> dto = null;
		try {
			dto = reservationRepository.findTheaterByMovieIdAndshowDate(movieId, date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public List<TimeDTO> fetchTimeList(int subregionId, int movieId, Date date) {
		List<TimeDTO> dto = null;
		try {
			dto = reservationRepository.findTimeByShowDateAndMovieIdAndSubregionId(date, movieId, subregionId);
		} catch (Exception e) {
			throw new DataDeliveryException("데이터 오류", HttpStatus.BAD_REQUEST);
		}
		return dto;
	}

	@Transactional
	public int insertBooking(int userId, int showTimeId, int quantity, List<Integer> selectedSeatsId) {
		int bookingId = 0;
		List<Integer> seats = null;
		Bookings booking = Bookings.builder().userId(userId).showtimeId(showTimeId).quantity(quantity).build();
		if (userId == 0) {
			throw new UnAuthorizedException(Define.ENTER_YOUR_LOGIN, HttpStatus.BAD_REQUEST);
		}

		if (quantity == 0) {
			throw new DataDeliveryException("좌석을 선택해주세요", HttpStatus.BAD_REQUEST);
		}

		try {
			seats = reservationRepository.checkOccupied(showTimeId);
			// 중복된 좌석이 있는지 확인
			for (Integer seatId : selectedSeatsId) {
				if (seats.contains(seatId)) {
					throw new DataDeliveryException("이미 예약된 좌석입니다.", HttpStatus.CONFLICT);
				} else {
					reservationRepository.insertBooking(userId, showTimeId, quantity);

					booking = reservationRepository.viewBookingByUserIdAndShowTimeId(userId, showTimeId);
					if (booking != null) {
						bookingId = booking.getId();
						reservationRepository.insertBookingSeats(bookingId, selectedSeatsId);
					}
				}
			}

		} catch (Exception e) {
			throw new DataDeliveryException("이미 예약된 좌석입니다.", HttpStatus.BAD_REQUEST);
		}
		return bookingId;
	}

	public List<Integer> viewOccupiedSeats(int showTimeId) {
		List<Integer> shId = null;
		try {
			shId = reservationRepository.checkOccupied(showTimeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shId;
	}

	public List<ChoicedMovie> fetchDateAndTheater(int movieId) {
		List<ChoicedMovie> entity = null;
		try {
			entity = reservationRepository.findDateAndTheatersByMovieId(movieId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public List<RegionCountDTO> fetchRegionCount(int movieId) {
		List<RegionCountDTO> entity = null;
		try {
			entity = reservationRepository.countRegion(movieId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public List<SubRegionDTO> fetchSubRegionByMovie(int movieId) {
		List<SubRegionDTO> entity = null;
		try {
			entity = reservationRepository.findSubRegionByMovie(movieId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public List<SubRegionDTO> fetchSubRegionByMovieAndRegion(int movieId, int regionId) {
		List<SubRegionDTO> entity = null;
		try {
			entity = reservationRepository.filterSubRegionByMovieAndRegion(movieId, regionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public List<RegionCountDTO> fetchRegionCountByeDateAndMovie(int movieId, String date) {
		List<RegionCountDTO> entity = null;
		try {
			entity = reservationRepository.countRegionByMovieAndDate(movieId, date);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public List<SubRegionDTO> fetchSubRegionByMovieAndDate(int movieId, String date) {
		List<SubRegionDTO> entity = null;
		try {
			entity = reservationRepository.findSubRegionByMovieAndDate(movieId, date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

}
