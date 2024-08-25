
package com.tenco.movie.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenco.movie.dto.WeeklyBoxOffice;
import com.tenco.movie.dto.WeeklyBoxOffice.BoxOfficeResult;
import com.tenco.movie.dto.WeeklyBoxOffice.Movie;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String getMethodName() {
		return "main";
	}

	@GetMapping("/TMDB")
	@ResponseBody
	public String parseTMDB() {
		String key = "7f0468e0f41d1bc7ea9f83175039d39c";
		String baseURL = "https://api.themoviedb.org/3/movie/";

		String tmdbURL = baseURL + "popular?api_key=" + key
				+ "&language=ko-KR&page=1&query=범죄도시&append_to_response=credits&language=ko-kr";

		URI uri = UriComponentsBuilder.fromUriString(
				"https://api.themoviedb.org/3/search/movie?api_key=" + key + "&language=ko-KR&page=1&query=늘봄가든")
				.build().toUri();

		RestTemplate restTemplate1 = new RestTemplate();
		ResponseEntity<String> response = restTemplate1.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(response.getBody().toString());
		String json = response.getBody();
		System.out.println(json);
		return response.getBody();
	}

	@GetMapping("/movieSearch")
	@ResponseBody
	public WeeklyBoxOffice parseMovieDate() {
		// 영화진흥위원회에서 발급 받은 키
		String key = "b1d9a2ca8f2d3d41e783a73278270803";
		// 일별 박스오피스 요청 URL
		String dailyBoxOfficeURI = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
		// 주간 박스오피스 요청 URI
		String weeklyBoxOfficeUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json";
		// 무비 상제 URI
		String movieDetailUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json";

		// 현재 날짜 가져오기
		LocalDate now = LocalDate.now();
		// 일주일 전
		LocalDate aWeek = now.minusDays(7);
		// 하루 전
		LocalDate yesterday = now.minusDays(1);
		// yyyMMdd 형식으로 데이터를 날려야하기 때문에 포멧 해줬다
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String weeklyBoxDate = aWeek.format(formatter);

		// 요청 URI JSON으로 값 받기
		URI uri = UriComponentsBuilder
				.fromUriString(
						weeklyBoxOfficeUrl + "?" + "key=b1d9a2ca8f2d3d41e783a73278270803&targetDt=" + weeklyBoxDate)
				.build().toUri();
		RestTemplate restTemplate1 = new RestTemplate();
		ResponseEntity<WeeklyBoxOffice> response = restTemplate1.exchange(uri, HttpMethod.GET, null,
				WeeklyBoxOffice.class);
		WeeklyBoxOffice weeklyBoxOffice = response.getBody();

		if (weeklyBoxOffice != null) {
			BoxOfficeResult boxOfficeResult = weeklyBoxOffice.getBoxOfficeResult();
			if (boxOfficeResult != null) {
				List<Movie> movieList = boxOfficeResult.getWeeklyBoxOfficeList();
				if (movieList != null && !movieList.isEmpty()) {
					for(int i=0;i<movieList.size();i++) {
					Movie firstMovie = movieList.get(i);

					System.out.println("BoxOffice Type: " + boxOfficeResult.getBoxofficeType());
					System.out.println("Show Range: " + boxOfficeResult.getShowRange());
					System.out.println("Year Week Time: " + boxOfficeResult.getYearWeekTime());

					System.out.println("First Movie Details:");
					System.out.println("Rank: " + firstMovie.getRank());
					System.out.println("Movie Name: " + firstMovie.getMovieNm());
					System.out.println("Open Date: " + firstMovie.getOpenDt());
					System.out.println("Sales Amount: " + firstMovie.getSalesAmt());
					}
				}
			}
		}

		return response.getBody();
	}
}
