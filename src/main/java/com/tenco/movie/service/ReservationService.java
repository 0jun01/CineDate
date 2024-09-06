package com.tenco.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenco.movie.repository.interfaces.ReservationRepository;
import com.tenco.movie.repository.model.MovieDetail;
import com.tenco.movie.repository.model.MovieDetailTB;
import com.tenco.movie.repository.model.Regions;
import com.tenco.movie.repository.model.SubRegions;

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
}
