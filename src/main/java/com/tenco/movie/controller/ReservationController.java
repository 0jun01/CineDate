package com.tenco.movie.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.model.MovieDetail;
import com.tenco.movie.repository.model.MovieDetailTB;
import com.tenco.movie.repository.model.Movies;
import com.tenco.movie.repository.model.Regions;
import com.tenco.movie.repository.model.SubRegions;
import com.tenco.movie.service.CalendarService;
import com.tenco.movie.service.ReservationService;
import com.tenco.movie.utils.Define;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	private final ReservationService reservationService;
	private final CalendarService calendarService;

	@Autowired
	public ReservationController(ReservationService reservationService, CalendarService calendarService) {
		this.reservationService = reservationService;
		this.calendarService = calendarService;
	}

	/**
	 * 예매 페이지
	 * 
	 * @author 변영준
	 */
	@GetMapping("/reservation")
	public String reservationPage(Model model) {

		// 무비 리스트
		List<MovieDetailTB> movieList = null;
		// 지역 리스트
		List<Regions> regionList = null;
		// 소분류 지역 리스트
		List<SubRegions> subRegionList = null;

		// 현재 날짜와 다음 달의 모든 날짜 생성
		LocalDate today = LocalDate.now();
		LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());
		LocalDate startOfNextMonth = endOfMonth.plusDays(1);
		LocalDate endOfNextMonth = startOfNextMonth.withDayOfMonth(startOfNextMonth.lengthOfMonth());
		// 리스트 생성
		List<LocalDate> allDates = new ArrayList<>();

		// for문을 돌려서 date에 현재 날짜를 초기화
		// 만약 현재 날짜가 2024-09-05면 05부터 반복
		// date.isBefore는 반복문이 계속될지를 결정하는 조건 문 if date가 endOfNextMonth보다 인전인지 확인
		for (LocalDate date = today; date.isBefore(endOfNextMonth.plusDays(1)); date = date.plusDays(1)) {
			allDates.add(date);
		}

		// 날짜와 요일을 저장할 Map
		List<Map<String, Object>> dateToDayOfWeekList = new ArrayList<>();

		// 공휴일 같은 데이터는 중복을 제거 해줘야 하기 때문에 set 자료구조를 사용
		Set<String> holidays = calendarService.getHolidays();

		// stream은 이 리스트를 스트림으로 변환시켜줌.
		// stream은 컬렉션의 요소를 순차적으로 처리할 수 있게 해주는 기능
		for (LocalDate date : allDates) {
			Map<String, Object> dateMap = new LinkedHashMap<>();
			dateMap.put("date", date);
			dateMap.put("year", date.getYear());
			dateMap.put("month", date.getMonthValue());
			dateMap.put("day", date.getDayOfMonth());
			dateMap.put("dayOfWeek", changeDayOfWeekInKorean(date.getDayOfWeek()));
			dateToDayOfWeekList.add(dateMap);
		}
		movieList = reservationService.readAllMovie();
		regionList = reservationService.readAllRegion();
		subRegionList = reservationService.readfirstSubRegion();

		if (movieList == null) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.BAD_REQUEST);
		}

		if (regionList == null) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.BAD_REQUEST);
		}
		// 모델에 데이터 추가
		model.addAttribute("currentYear", today.getYear());
		model.addAttribute("currentMonth", today.getMonthValue());
		model.addAttribute("date", dateToDayOfWeekList);
		model.addAttribute("holidays", holidays);
		model.addAttribute("regionList", regionList);
		model.addAttribute("movieList", movieList);
		model.addAttribute("subRegionList", subRegionList);
		return "/reservation/reservationPage";
	}

	@GetMapping("/movies")
	@ResponseBody
	public List<MovieDetailTB> getFiltedMovies(@RequestParam("sortBy") String sortBy) {
		List<MovieDetailTB> movieList;
		if ("korean".equalsIgnoreCase(sortBy)) {
			movieList = reservationService.readAllMoviesSortedByTitle();
		} else if ("age".equalsIgnoreCase(sortBy)) {
			movieList = reservationService.readAllMoviesSortedByWatchGrade();
		} else {
			movieList = reservationService.readAllMovie(); // 기본값
		}
		return movieList;
	}

	@GetMapping("/regions")
	@ResponseBody
	public List<SubRegions> regionProc(@RequestParam("regionId") int regionId, Model model) {
		List<SubRegions> subRegiList = null;

		subRegiList = reservationService.readSubRegion(regionId);
		if (subRegiList == null) {
			throw new DataDeliveryException(Define.ERROR_INVALID_SCREEN, HttpStatus.BAD_REQUEST);
		}

		return subRegiList;
	}

	/**
	 * 영어로 나오는 요일을 한글로 변환시키는 메서드
	 * 
	 * @param dayOfWeek
	 * @return String
	 */
	private String changeDayOfWeekInKorean(DayOfWeek dayOfWeek) {
		switch (dayOfWeek) {
		case MONDAY:
			return "월";
		case TUESDAY:
			return "화";
		case WEDNESDAY:
			return "수";
		case THURSDAY:
			return "목";
		case FRIDAY:
			return "금";
		case SATURDAY:
			return "토";
		case SUNDAY:
			return "일";
		default:
			return "";
		}
	}

	@GetMapping("/date")
	@ResponseBody
	public List<MovieDetail> fetchDateItems(@RequestParam("date") String date) {
		List<MovieDetail> fetchedMovieList = reservationService.fetchMovieListByDate(date);

		if (fetchedMovieList == null) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.BAD_REQUEST);
		}
		return fetchedMovieList;
	}

	@GetMapping("/selectedMovie")
	@ResponseBody
	public void fetchSelectedMovie(@RequestParam("movie") int movieId) {
		System.out.println("-------------------");
		System.out.println("movieId : " + movieId);
		System.out.println("-------------------");
	}
}
