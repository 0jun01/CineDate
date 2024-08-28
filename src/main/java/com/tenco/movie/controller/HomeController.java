
package com.tenco.movie.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tenco.movie.dto.MovieDetailDTO;
import com.tenco.movie.dto.MovieDetailDTO.AuditDTO;
import com.tenco.movie.dto.MovieDetailDTO.GenreDTO;
import com.tenco.movie.dto.MovieDetailDTO.MovieInfo;
import com.tenco.movie.dto.MovieDetailDTO.MovieInfoResult;
import com.tenco.movie.dto.MovieDetailDTO.NationDTO;
import com.tenco.movie.dto.MovieDetailDTO.PersonDTO;
import com.tenco.movie.dto.TMDBDTO;
import com.tenco.movie.dto.TMDBDTO.TMDBMovies;
import com.tenco.movie.dto.WeeklyBoxOffice;
import com.tenco.movie.dto.WeeklyBoxOffice.BoxOfficeResult;
import com.tenco.movie.dto.WeeklyBoxOffice.Movie;
import com.tenco.movie.repository.model.Movies;

@Controller
public class HomeController {

	// TMDB에서 발급 받은 키
	private final String TMDBKEY = "7f0468e0f41d1bc7ea9f83175039d39c";
	// 영화진흥위원회에서 발급 받은 키
	private final String CONTENTKEY = "b1d9a2ca8f2d3d41e783a73278270803";;
	// TMDB API 사용 할 기본 URL
	private final String TMDBBASEURL = "https://api.themoviedb.org/3/search/movie?api_key=";
	// 일별 박스 오피스
	private final String DAILYBOXOFFICEURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
	// 주간 박스오피스 요청 URI
	private final String WEEKLYBOXOFFICEURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json";
	// 무비 상세 URI
	private final String MOVIEDETAILURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json";

	/**
	 * 오픈 API에서 주간 박스오피스 데이터 파싱
	 * 
	 * @param Model
	 * @return mainPage
	 * @author 변영준
	 */
	@GetMapping("/home")
	public String getMethodName(Model model) {
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

		// 요청 URI JSON으로 값 받기
		URI uri1 = UriComponentsBuilder
				.fromUriString(WEEKLYBOXOFFICEURL + "?" + "key=" + CONTENTKEY + "&targetDt=" + weeklyBoxDate).build()
				.toUri();

		// RestTemplate로 응답
		RestTemplate restTemplate1 = new RestTemplate();
		ResponseEntity<WeeklyBoxOffice> response = restTemplate1.exchange(uri1, HttpMethod.GET, null,
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
						ResponseEntity<TMDBDTO> response2 = restTemplate1.exchange(uri2, HttpMethod.GET, null,
								TMDBDTO.class);
						TMDBDTO tmdbdto = response2.getBody();
						if (tmdbdto != null) {
							tmdbMoviesList = tmdbdto.getResults();
							if (tmdbMoviesList != null) {
								TMDBMovies tmdbMovie = tmdbMoviesList.get(0);
								// tmdbMovie 클래스를 movies로 변환하기 위해 빌더를 사용
								Movies movies = Movies.builder().title(tmdbMovie.getTitle())
										.movieDesc(tmdbMovie.getOverview()).movieImg(tmdbMovie.getPosterPath())
										.releaseDate(tmdbMovie.getReleaseDate()).build();
								moviesList.add(movies);
							}
						}
					}
				}
			}
		}
		model.addAttribute("movieList", moviesList);
		return "main";
	}

	// TODO삭제예정
	@GetMapping("/TMDB")
	@ResponseBody
	public TMDBDTO parseTMDB() {

//		String tmdbURL = baseURL + "popular?api_key=" + key
//				+ "&language=ko-KR&page=1&query=범죄도시&append_to_response=credits&language=ko-kr";

		// URI 영화 title이 필사의 추격인 영화 조회
		URI uri = UriComponentsBuilder.fromUriString(TMDBBASEURL + TMDBKEY + "&language=ko-KR&page=1&query=에이리언: 로물루스")
				.build().toUri();

		// RestTemplate으로 호출
		RestTemplate restTemplate1 = new RestTemplate();
		ResponseEntity<TMDBDTO> response = restTemplate1.exchange(uri, HttpMethod.GET, null, TMDBDTO.class);

		// json 받기
		TMDBDTO tmdbdto = response.getBody();

		// TMDB 오픈 API의 결과 가져오기
		if (tmdbdto != null) {
			List<TMDBMovies> tmdbMoviesList = tmdbdto.getResults();
			if (tmdbMoviesList != null) {
				TMDBMovies tmdbMovie = tmdbMoviesList.get(0);
				System.out.println("title: " + tmdbMovie.getOriginalTitle());
				System.out.println("overview: " + tmdbMovie.getOverview());
				System.out.println("imageAddress : " + tmdbMovie.getPosterPath());
			}
		}
		System.out.println("tmdbDTO : " + tmdbdto);
		System.out.println(response.getBody().toString());
		return response.getBody();
	}

	// TODO삭제예정
	@GetMapping("/movieSearch")
	@ResponseBody
	public MovieDetailDTO parseMovieDate() {

		// 현재 날짜 가져오기
		LocalDate now = LocalDate.now();
		// 일주일 전
		LocalDate aWeek = now.minusDays(7);
		// 하루 전
		LocalDate yesterday = now.minusDays(1);
		// yyyMMdd 형식으로 데이터를 날려야하기 때문에 포멧 해줬다
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String weeklyBoxDate = aWeek.format(formatter);

		String movieCd = "";

		// 요청 URI JSON으로 값 받기
		URI uri = UriComponentsBuilder
				.fromUriString(
						WEEKLYBOXOFFICEURL + "?" + "key=" + CONTENTKEY + "&targetDt=" + weeklyBoxDate + "&weekGb=1")
				.build().toUri();

		// RestTemplate로 응답
		RestTemplate restTemplate1 = new RestTemplate();
		RestTemplate restTemplate2 = new RestTemplate();

		MovieDetailDTO movieDetailDTO = null;

		ResponseEntity<WeeklyBoxOffice> response = restTemplate1.exchange(uri, HttpMethod.GET, null,
				WeeklyBoxOffice.class);
		WeeklyBoxOffice weeklyBoxOffice = response.getBody();

		// 값 받아내기
		if (weeklyBoxOffice != null) {
			BoxOfficeResult boxOfficeResult = weeklyBoxOffice.getBoxOfficeResult();
			if (boxOfficeResult != null) {
				List<Movie> movieList = boxOfficeResult.getWeeklyBoxOfficeList();
				if (movieList != null && !movieList.isEmpty()) {
					for (int i = 0; i < movieList.size(); i++) {
						Movie firstMovie = movieList.get(i);
						movieCd = firstMovie.getMovieCd();

						System.out.println(movieCd);
						URI uri2 = UriComponentsBuilder
								.fromUriString(MOVIEDETAILURL + "?" + "key=" + CONTENTKEY + "&movieCd="+movieCd).build()
								.toUri();

						ResponseEntity<MovieDetailDTO> response2 = restTemplate2.exchange(uri2, HttpMethod.GET, null,
								MovieDetailDTO.class);
						movieDetailDTO = response2.getBody();
						System.out.println(movieDetailDTO);
						if (movieDetailDTO != null) {
							MovieInfoResult movieInfoResult = movieDetailDTO.getMovieInfoResult();
							if (movieInfoResult != null) {
								MovieInfo movieInfo = movieInfoResult.getMovieInfo();
								if (movieInfo != null) {
									System.out.println("Movie Name (Korean): " + movieInfo.getMovieNm());
									System.out.println("Movie Name (English): " + movieInfo.getMovieNmEn());
									System.out.println("Show Time: " + movieInfo.getShowTm());
									System.out.println("Open Date: " + movieInfo.getOpenDt());
									System.out.println("Production Status: " + movieInfo.getPrdtStatNm());
									
									 // 국가, 장르, 감독, 배우 정보 출력
				                    if (movieInfo.getNations() != null) {
				                        for (NationDTO nation : movieInfo.getNations()) {
				                            System.out.println("Nation: " + nation.getNationNm());
				                        }
				                    }

				                    if (movieInfo.getGenres() != null) {
				                        for (GenreDTO genre : movieInfo.getGenres()) {
				                            System.out.println("Genre: " + genre.getGenreNm());
				                        }
				                    }
				                    if (movieInfo.getAudits() != null) {
				                    	for (AuditDTO genre : movieInfo.getAudits()) {
				                    		System.out.println("WatchGradeNm: " + genre.getWatchGradeNm());
				                    	}
				                    }
				                    
				                    
				                    if (movieInfo.getDirectors() != null) {
				                        for (PersonDTO director : movieInfo.getDirectors()) {
				                            System.out.println("Director: " + director.getPeopleNm() + " (" + director.getPeopleNmEn() + ")");
				                        }
				                    }

				                    if (movieInfo.getActors() != null) {
				                        for (PersonDTO actor : movieInfo.getActors()) {
				                            System.out.println("Actor: " + actor.getPeopleNm() + " (" + actor.getPeopleNmEn() + ")");
				                        }
				                    }

				                    if (movieInfo.getAudits() != null) {
				                        for (AuditDTO audit : movieInfo.getAudits()) {
				                            System.out.println("Audit Grade: " + audit.getWatchGradeNm());
				                        }
				                    }
								}
							}
						}
					}
				}
			}
		}

		return movieDetailDTO;
	}
}
