package com.tenco.movie.service;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tenco.movie.dto.HolidayResponse;
import com.tenco.movie.dto.HolidayResponse.Item;

@Service
public class CalendarService {

	private final String HOLIDAYURL = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";
	private final RestTemplate restTemplate = new RestTemplate();

	@Value("${api.serviceKey}")
	private String serviceKey;

	@Cacheable("holidayCache")
	public Set<String> getHolidays() {

		System.out.println("캐시 호출 됨.");
		// URI 구성
		URI uri = UriComponentsBuilder.fromHttpUrl(HOLIDAYURL).queryParam("serviceKey", serviceKey)
				.queryParam("solYear", 2024).queryParam("numOfRows", 25).build().toUri();

		// API 호출
		ResponseEntity<HolidayResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				HolidayResponse.class);
		HolidayResponse holidayResponse = response.getBody();

		List<Item> items = holidayResponse.getResponse().getBody().getItems().getItem();

		// 공휴일 같은 데이터는 중복을 제거 해줘야 하기 때문에 set 자료구조를 사용
		Set<LocalDate> holidays = new HashSet<>();

		// stream은 이 리스트를 스트림으로 변환시켜줌.
		// stream은 컬렉션의 요소를 순차적으로 처리할 수 있게 해주는 기능
		holidays = items.stream()
				// map메서드는 스트림의 각 요소를 변환하는데 사용 여기서는 item 객체를 LocalDate 객체로 변환
				.map(item -> LocalDate.of(item.getLocdate() / 10000, // year 예: 20240101 나누기를 하면 2024가 나옴 연도 추출
						(item.getLocdate() % 10000) / 100, // month 예: 20240101에 나머지 0101이 나오고 여기서 나누기 100을하면 01이 나옴 월
															// 추출
						item.getLocdate() % 100 // day 예: 나머지가 01로 나오기 때문에 01추출
				))
				// 스트림의 각 요소를 컬렉션으로 수집, 스트림의 요소를 set으로 변환하여 중복 제거
				.collect(Collectors.toSet());

		// localDate타입을 String으로 변환하는 과정
		Set<String> holidayStrings = holidays.stream().map(date -> date.format(DateTimeFormatter.ISO_LOCAL_DATE))
				.collect(Collectors.toSet());
		return holidayStrings;
	}
}
