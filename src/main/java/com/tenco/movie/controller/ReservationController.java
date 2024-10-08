package com.tenco.movie.controller;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenco.movie.dto.BookingRequest;
import com.tenco.movie.dto.ChoicedMovie;
import com.tenco.movie.dto.RegionCountDTO;
import com.tenco.movie.dto.SubRegionDTO;
import com.tenco.movie.dto.TheaterCountDTO;
import com.tenco.movie.dto.TimeDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
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
	public List<MovieDetailTB> fetchDateItems(@RequestParam("date") String date) {
		List<MovieDetailTB> fetchedMovieList = reservationService.fetchMovieListByDate(date);

		if (fetchedMovieList == null) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.BAD_REQUEST);
		}
		return fetchedMovieList;
	}

	@GetMapping("/fetchMovieListBySubRegion")
	@ResponseBody
	public List<MovieDetailTB> fetchDateItems(@RequestParam("subRegionId") int subRegionId) {
		List<MovieDetailTB> fetchedMovieList = reservationService.fetchMovieListBySubRegion(subRegionId);

		if (fetchedMovieList == null) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.BAD_REQUEST);
		}
		return fetchedMovieList;
	}

	/**
	 * 날짜와 극장 선택시 영화 무비 리스트 업데이트 시켜주는 메서드
	 * 
	 * @param subRegionId
	 * @return
	 * @author 변영준
	 */
	@GetMapping("/fetchMovieListByMovieAndSubRegion")
	@ResponseBody
	public List<MovieDetailTB> fetchMovieBySubRegionAndDate(@RequestParam("date") String date,
			@RequestParam("subRegionId") int subRegionId) {
		List<MovieDetailTB> fetchedMovieList = reservationService.fetchMovieListBySubRegionAndDate(date,subRegionId);

		if (fetchedMovieList == null) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.BAD_REQUEST);
		}
		return fetchedMovieList;
	}

	@GetMapping("/selectedMovie")
	@ResponseBody
	public int fetchSelectedMovie(@RequestParam("movie") int movieId) {
		System.out.println("-------------------");
		System.out.println("movieId : " + movieId);
		System.out.println("-------------------");
		return movieId;
	}

	@GetMapping("/movieDetail")
	@ResponseBody
	public Movies fetchSelectedMovieDetail(@RequestParam("movieId") int movieId) {
		Movies movie = reservationService.fetchMovieTitleAndImg(movieId);

		return movie;
	}

	@GetMapping("/subregions")
	@ResponseBody
	public void fetchSubRegion(@RequestParam("movieId") int movieId) {
	}

	@GetMapping("/theater")
	@ResponseBody
	public List<TheaterCountDTO> fetchTheater(@RequestParam("movieId") int movieId, @RequestParam("date") String date) {
		List<TheaterCountDTO> dto = reservationService.fetchTheater(movieId, date);
		System.out.println(dto.toString());
		return dto;
	}

	@GetMapping("/timeList")
	@ResponseBody
	public List<TimeDTO> fetchTimeList(@RequestParam("subregionId") int subregionId,
			@RequestParam("movieId") int movieId, @RequestParam("date") Date date) {
		List<TimeDTO> dto = reservationService.fetchTimeList(subregionId, movieId, date);
		if (dto == null) {
			throw new DataDeliveryException("알 수 없는 오류", HttpStatus.BAD_REQUEST);
		}
		return dto;
	}

	@PostMapping("/booking")
	public ResponseEntity<Map<String, String>> bookMovie(@RequestBody BookingRequest request) {
		Map<String, String> response = new HashMap<>();
		try {
			// 예약 시도
			int result = reservationService.insertBooking(request.getUserId(), request.getShowTimeId(),
					request.getQuantity(), request.getSelectedSeatsId());
			// 예매 성공
			if (result > 0) {
				response.put("message", "예매 성공");
				return ResponseEntity.ok(response);
			} else {
				// 예매 실패: bookingId가 0인 경우
				response.put("message", "예매 실패");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		} catch (DataDeliveryException e) {
			// 좌석 중복 등의 문제 발생 시
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		} catch (Exception e) {
			// 기타 예외 처리
			response.put("message", "예매 처리 중 오류가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("/checkDupliSeat")
	@ResponseBody
	public List<Integer> checkSeats(@RequestParam("showTimeId") int showTimeId) {
		List<Integer> shId = reservationService.viewOccupiedSeats(showTimeId);
		return shId;
	}

	/**
	 * 영화만 클릭 했을 시 날짜와 극장 업데이트
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/firstMovie")
	@ResponseBody
	public List<ChoicedMovie> findMovieAndDate(@RequestParam("movieId") int movieId) {
		List<ChoicedMovie> entity = reservationService.fetchDateAndTheater(movieId);
		return entity;
	}

	/**
	 * 서브지역만 클릭 했을 시 날짜와 극장 업데이트
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/updateDateBySubRegion")
	@ResponseBody
	public List<ChoicedMovie> fetchDateAndDateBySubRegion(@RequestParam("subRegionId") int subRegionId) {
		List<ChoicedMovie> entity = reservationService.fetchDateAndDateBySubRegion(subRegionId);
		return entity;
	}

	/**
	 * 영화와 서브지역만 클릭 했을 시 날짜와 극장 업데이트
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/fetchDateByMovieAndSubRegion")
	@ResponseBody
	public List<ChoicedMovie> fetchDateBySubRegionAndMovie(@RequestParam("subRegionId") int subRegionId,
			@RequestParam("movieId") int movieId) {
		List<ChoicedMovie> entity = reservationService.fetchDateByMovieAndSubRegion(subRegionId, movieId);
		return entity;
	}

	/**
	 * 영화만 클릭 했을 시 극장대분류 카운트 업데이트
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/firstTheater")
	@ResponseBody
	public List<RegionCountDTO> viewRegionCount() {
		List<RegionCountDTO> entity = reservationService.fetchFirstRegionCount();
		return entity;
	}

	/**
	 * 영화만 클릭 했을 시 극장대분류 카운트 업데이트
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/regionCount")
	@ResponseBody
	public List<RegionCountDTO> countRegion(@RequestParam("movieId") int movieId) {
		List<RegionCountDTO> entity = reservationService.fetchRegionCount(movieId);
		return entity;
	}

	/**
	 * 날짜만 클릭 했을 시 극장대분류 카운트 업데이트
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/dateCount")
	@ResponseBody
	public List<RegionCountDTO> countRegionByDate(@RequestParam("date") String date) {
		List<RegionCountDTO> entity = reservationService.fetchRegionCountByDate(date);
		return entity;
	}

	/**
	 * 처음 웹에 입장 시 극장 소분류 선택 가능한 지역업데이트
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/firstSubRegion")
	@ResponseBody
	public List<SubRegionDTO> findFirstSubRegion() {
		List<SubRegionDTO> entity = reservationService.fetchFirstSubRegion();
		return entity;
	}

	/**
	 * 영화만 클릭 했을 시 극장 소분류 선택 가능한 지역업데이트
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/subRegionsByMovie")
	@ResponseBody
	public List<SubRegionDTO> findSubRegionByMovie(@RequestParam("movieId") int movieId) {
		List<SubRegionDTO> entity = reservationService.fetchSubRegionByMovie(movieId);
		return entity;
	}

	/**
	 * 날짜만 클릭 했을 시 극장 소분류 선택 가능한 지역업데이트
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/subRegionsByDate")
	@ResponseBody
	public List<SubRegionDTO> findSubRegionByDate(@RequestParam("date") String date) {
		List<SubRegionDTO> entity = reservationService.fetchSubRegionBydate(date);
		return entity;
	}

	/**
	 * 영화만 클릭한 후대분류 클릭 했을 시 극장 서브 지역 업데이트
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/filterSubRegion")
	@ResponseBody
	public List<SubRegionDTO> fetchSubRegionByMovieAndRegion(@RequestParam("movieId") int movieId,
			@RequestParam("regionId") int regionId) {
		List<SubRegionDTO> entity = reservationService.fetchSubRegionByMovieAndRegion(movieId, regionId);
		return entity;
	}

	/**
	 * 날짜와 영화 클릭 후 count 해주기
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/regionCountByDateAndMovie")
	@ResponseBody
	public List<RegionCountDTO> fetchSubRegionByMovieAndDate(@RequestParam("movieId") int movieId,
			@RequestParam("date") String date) {
		List<RegionCountDTO> entity = null;
		entity = reservationService.fetchRegionCountByeDateAndMovie(movieId, date);
		return entity;
	}

	/**
	 * 날짜와 영화만 클릭 했을 시 극장 대분류 선택시 소분류 업데이트
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/subRegionsByMovieAndDate")
	@ResponseBody
	public List<SubRegionDTO> findSubRegionByMovieAndDate(@RequestParam("movieId") int movieId,
			@RequestParam("date") String date) {
		List<SubRegionDTO> entity = reservationService.fetchSubRegionByMovieAndDate(movieId, date);
		return entity;
	}

	/**
	 * 처음 페이지 입장시 대분류 선택시 대분류에 맞는 소분류 지역 opacity처리
	 * 
	 * @param movieId
	 * @author 변영준
	 */
	@GetMapping("/firstOpacity")
	@ResponseBody
	public List<SubRegionDTO> fetchFirstOpacity() {
		List<SubRegionDTO> entity = reservationService.fetchFirstSubOpaicty();
		return entity;
	}

}
