package com.tenco.movie.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tenco.movie.service.CalendarService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CalendarController {

	@Autowired
	private final CalendarService calendarService;

	@GetMapping("/date")
	public void getHolidays() {

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
		Map<LocalDate, String> dateToDayOfWeekMap = new LinkedHashMap<>();

		// 공휴일 같은 데이터는 중복을 제거 해줘야 하기 때문에 set 자료구조를 사용
		Set<LocalDate> holidays = calendarService.getHolidays();

		// stream은 이 리스트를 스트림으로 변환시켜줌.
		// stream은 컬렉션의 요소를 순차적으로 처리할 수 있게 해주는 기능
		for (LocalDate date : allDates) {
			dateToDayOfWeekMap.put(date, changeDayOfWeekInKorean(date.getDayOfWeek()));
		}
		System.out.println("All Dates with DayOfWeek: " + dateToDayOfWeekMap);
		System.out.println("Holidays: " + holidays);
	}

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
}
