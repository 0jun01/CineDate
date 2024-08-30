package com.tenco.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.handler.exception.RedirectException;
import com.tenco.movie.repository.interfaces.HomeRepository;
import com.tenco.movie.repository.model.Actors;
import com.tenco.movie.repository.model.Director;
import com.tenco.movie.repository.model.Genres;
import com.tenco.movie.repository.model.MovieActor;
import com.tenco.movie.repository.model.MovieDetailTB;
import com.tenco.movie.repository.model.MovieDirector;
import com.tenco.movie.repository.model.MovieGenre;
import com.tenco.movie.repository.model.Movies;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {

	HomeRepository homeRepository;

	@Autowired
	public HomeService(HomeRepository homeRepository) {
		this.homeRepository = homeRepository;
	}

	/**
	 * movies_tb에 값넣기
	 * 
	 * @param movies
	 */
	@Transactional
	public void insertMovies(Movies movies) {
		int result = 0;
		try {
			Movies moviesEntity = homeRepository.findByTitle(movies.getTitle());

			if (moviesEntity == null) {
				result = homeRepository.insertMovie(movies);
				if (result == 0) {
					System.out.println(movies.getTitle() + "은 이미 DB에 있어서 실패");
				} else {
					System.out.println(movies.getTitle() + "DB에 값 넣기 성공");
				}
			}

		} catch (DataAccessException e) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}

	}

	/**
	 * 타이틀로 영화 movies_tb의 아이디 값을 가져오기 위해서 타이틀을 가져옴
	 * 
	 * @param title
	 * @return
	 */
	public int readMovieByTitle(String title) {
		int movieId = 0;
		System.out.println("-------------------");
		System.out.println(title);
		System.out.println("-------------------");
		if (title == null || title.isEmpty()) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		}

		try {
			Movies movieEntity = homeRepository.findByTitle(title);
			if (movieEntity == null) {
				System.out.println(movieEntity.getTitle());
			} else {
				movieId = movieEntity.getId();
			}
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException(Define.ERROR_INVALID_MOVIE, HttpStatus.BAD_REQUEST);
		}

		return movieId;
	}

	/**
	 * movie_detail_tb 에 값 넣기
	 * 
	 * @param movieId
	 * @param title
	 * @param titleEn
	 * @param showTm
	 * @param openDt
	 * @param prdStatNm
	 * @param watchGradeNm
	 */
	@Transactional
	public void insertMovieDetail(int movieId, String title, String titleEn, String showTm, String openDt,
			String prdStatNm, String watchGradeNm) {
		int result = 0;
		try {
			MovieDetailTB moviesEntity = homeRepository.findById(movieId);

			if (moviesEntity == null) {
				result = homeRepository.insertMovieDetail(movieId, title, titleEn, showTm, openDt, prdStatNm,
						watchGradeNm);
				if (result == 0) {
					System.out.println(title + "은 이미 DB에 있어서 실패");
				} else {
					System.out.println(title + "DB에 값 넣기 성공");
				}
			}

		} catch (DataAccessException e) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}

	}

	@Transactional
	public void findGenreId(String genreName, int movieId) {
		int genreId = 0;
		Genres genresEntity = null;
		try {

			// 1. genres_tb 에서 장르이름에 맞는 id를 가져온다
			genresEntity = homeRepository.findIdByGenreName(genreName);

			// 1-1. 만약 genreId가 없으면 genres_tb에 insert 해준다.
			if (genresEntity == null) {
				// 장르가 데이터베이스에 존재하지 않으면 삽입

				homeRepository.insertGenre(genreName);
				// 장르를 삽입한 후 ID를 다시 조회
				genresEntity = homeRepository.findIdByGenreName(genreName);
				genreId = genresEntity.getId();
			}
			genreId = genresEntity.getId();
//			// 2. movie_genre_tb에 movie_id가 있는지 찾아 본다.
			MovieGenre movieGenre = homeRepository.checkMovieGenreExists(movieId, genreId);
//			// 2-1. movie_genre_tb에 movie_id가 없으면 movie_id와 genre_id를 인설트 해준다.
			if (movieGenre == null) {
				homeRepository.insertMovieGenre(movieId, genreId);
				System.out.println("---------------------------------");
				System.out.println("저장 성공");
				System.out.println("---------------------------------");
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Transactional
	public void insertDirector(int movieId, Director director) {
		Director directorEntity = null;
		
		try {

			directorEntity = homeRepository.findByDirectorName(director.getName());
			if (directorEntity == null) {
				homeRepository.insertDirector(director);
				directorEntity = homeRepository.findByDirectorName(director.getName());
			} else {
				System.out.println("이미 있습니다.");
			}
			
			MovieDirector movieDirector = homeRepository.findByMovieIdAndDirectorId(movieId, directorEntity.getId());
			
			if(movieDirector == null) {
				homeRepository.insertByMovieDirector(movieId, directorEntity.getId());
			} else {
				System.out.println("인설트 실패");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}

	}

	@Transactional
	public void insertActors(Actors actors, int movieId) {

		Actors actorsEntity = homeRepository.findByActorName(actors.getName());
		if (actorsEntity == null) {
			System.out.println("너 인설트 성공했어");
			homeRepository.insertActors(actors);
			actorsEntity = homeRepository.findByActorName(actors.getName());
		} else {
			System.out.println("이미 있어");
		}

		MovieActor mAEntity = homeRepository.findByMovieAndGenre(movieId, actorsEntity.getId());

		if (mAEntity == null) {
			System.out.println("중간테이블 성공했다");
			homeRepository.insertMovieActor(movieId, actorsEntity.getId());
		} else {
			System.out.println("이미있다니까");
		}
	}
}
