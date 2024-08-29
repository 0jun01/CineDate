package com.tenco.movie.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Actors {

	private int id; // 액터 pk
<<<<<<< HEAD
<<<<<<< HEAD
	private String name; // 배우 이름
	private String role; // 직업
	private String actorFaceFile; // 출연진 사진

=======
	private int movieId; // 무비 pk 번호
=======
>>>>>>> 96e61be (영화API파싱 거의 95완료)
	private String name; // 배우 이름
	private String role; // 직업
	private String actorFaceFile; // 출연진 사진
<<<<<<< HEAD
	
>>>>>>> 528fecc (영화 디테일 1차완료)
=======

>>>>>>> 96e61be (영화API파싱 거의 95완료)
}
