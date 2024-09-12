package com.tenco.movie.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tenco.movie.dto.TMDBCreditsDTO;
import com.tenco.movie.dto.TMDBCreditsDTO.CastDTO;
import com.tenco.movie.dto.TMDBCreditsDTO.CrewDTO;
import com.tenco.movie.dto.TMDBDTO;
import com.tenco.movie.dto.TMDBDTO.TMDBMovies;
import com.tenco.movie.dto.WeeklyBoxOffice;
import com.tenco.movie.dto.WeeklyBoxOffice.BoxOfficeResult;
import com.tenco.movie.dto.WeeklyBoxOffice.Movie;
import com.tenco.movie.repository.model.Actors;
import com.tenco.movie.repository.model.Director;
import com.tenco.movie.repository.model.Movies;
import com.tenco.movie.service.HomeService;

@Controller
public class MovieParseController {

	// TMDB에서 발급 받은 키
	private final String TMDBKEY = "7f0468e0f41d1bc7ea9f83175039d39c";
	// 영화진흥위원회에서 발급 받은 키
	private final String CONTENTKEY = "b1d9a2ca8f2d3d41e783a73278270803";;
	// TMDB API 사용 할 기본 URL
	private final String TMDBBASEURL = "https://api.themoviedb.org/3/search/movie?api_key=";
	private final String TMDBBAACTORSEURL = "https://api.themoviedb.org/3/movie/";
	// 일별 박스 오피스
	private final String DAILYBOXOFFICEURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
	// 주간 박스오피스 요청 URI
	private final String WEEKLYBOXOFFICEURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json";
	// 무비 상세 URI
	private final String MOVIEDETAILURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json";

	HomeService homeService;

	@Autowired
	public MovieParseController(HomeService homeService) {
		this.homeService = homeService;
	}

	@GetMapping("TM")
	@ResponseBody
	public TMDBCreditsDTO parseTMDB() {
		// 현재 날짜 가져오기
		LocalDate now = LocalDate.now();
		// 일주일 전
		LocalDate aWeek = now.minusDays(7);
		// 하루 전
		LocalDate yesterday = now.minusDays(1);
		// yyyMMdd 형식으로 데이터를 날려야하기 때문에 포멧 해줬다
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String weeklyBoxDate = aWeek.format(formatter);

		// 무비 리스트들
		List<Movie> movieList = new ArrayList<>();
		List<TMDBMovies> tmdbMoviesList = new ArrayList<>();
		List<Movies> moviesList = new ArrayList<>();
		List<CastDTO> castList = new ArrayList<>();
		List<CrewDTO> crewList = new ArrayList<>();

		Movies movies = null;
		TMDBDTO tmdbdto = null;
		TMDBCreditsDTO tmDto = null;

		// 요청 URI JSON으로 값 받기
		URI uri = UriComponentsBuilder
				.fromUriString(
						WEEKLYBOXOFFICEURL + "?" + "key=" + CONTENTKEY + "&targetDt=" + weeklyBoxDate + "&weekGb=0")
				.build().toUri();
		;

		// RestTemplate로 응답
		RestTemplate restTemplate1 = new RestTemplate();
		ResponseEntity<WeeklyBoxOffice> response = restTemplate1.exchange(uri, HttpMethod.GET, null,
				WeeklyBoxOffice.class);
		// 홈페이지에 출력
		WeeklyBoxOffice weeklyBoxOffice = response.getBody();

		// 값 받아내기
		if (weeklyBoxOffice != null) {
			BoxOfficeResult boxOfficeResult = weeklyBoxOffice.getBoxOfficeResult();
			if (boxOfficeResult != null) {
				movieList = boxOfficeResult.getWeeklyBoxOfficeList();
				if (movieList != null && !movieList.isEmpty()) {
					for (int i = 0; i < movieList.size(); i++) {
						Movie firstMovie = movieList.get(i);
						// TMDB에서 이미지 주소 추출해내기 이중 파싱
						URI uri2 = UriComponentsBuilder.fromUriString(
								TMDBBASEURL + TMDBKEY + "&language=ko-KR&page=1&query=" + firstMovie.getMovieNm())
								.build().toUri();
						RestTemplate restTemplate2 = new RestTemplate();
						ResponseEntity<TMDBDTO> response2 = restTemplate2.exchange(uri2, HttpMethod.GET, null,
								TMDBDTO.class);
						tmdbdto = response2.getBody();
						if (tmdbdto != null) {
							tmdbMoviesList = tmdbdto.getResults();
							if (tmdbMoviesList != null) {
								for (TMDBMovies tmdbMovie : tmdbMoviesList) {
									String title = tmdbMovie.getTitle();

									// 정확히 일치하는 제목 찾기
									if (title.trim().equalsIgnoreCase(firstMovie.getMovieNm().trim())) {
										URI uri3 = UriComponentsBuilder
												.fromUriString(TMDBBAACTORSEURL + tmdbMovie.getId()
														+ "/credits?api_key=" + TMDBKEY + "&language=ko")
												.build().toUri();
										RestTemplate restTemplate3 = new RestTemplate();
										ResponseEntity<TMDBCreditsDTO> response3 = restTemplate3.exchange(uri3,
												HttpMethod.GET, null, TMDBCreditsDTO.class);
										tmDto = response3.getBody();
										castList = tmDto.getCast();
										crewList = tmDto.getCrew();
										// 상위 10명만 선택
										List<CastDTO> top10CastList = castList.stream().limit(5) // 상위 10명 선택
												.collect(Collectors.toList());
										for (CastDTO castDTO : top10CastList) {
											int movieId = homeService.readMovieByTitle(title);
											Actors actors = Actors.builder().name(castDTO.getName())
													.actorFaceFile(castDTO.getProfilePath()).build();
											homeService.insertActors(actors, movieId);
										}
										for (CrewDTO crewDTO : crewList) {
											if (crewDTO.getJob().equals("Director")) {
												int movieId = homeService.readMovieByTitle(title);
												Director director = Director.builder().name(crewDTO.getName())
														.directorFaceFile(crewDTO.getProfilePath()).build();
												System.out.println("Director 맞나요?" + crewDTO.getName());
												// TODO 인서트문 주석
												homeService.insertDirector(movieId, director);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return tmDto;
	}
}
