<<<<<<< HEAD
<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
>>>>>>> 528fecc (영화 디테일 1차완료)
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
>>>>>>> 96e61be (영화API파싱 거의 95완료)
<link href="/css/main.css" rel="stylesheet">
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">

	<div id="in--wrap">
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		영화 제목: ${movie.title} <br> 영화 영어 제목 : ${movieDetail.titleEn} <br>
		상영시간 : ${movieDetail.showTm}분 <br> ${movieDetail.prdStatNm} <br>
		${movieDetail.watchGradeNm} <br> ${movieDetail.genre} <br>
<<<<<<< HEAD
<<<<<<< HEAD
		<a href="/reservation/reservation">예매하기</a>
		<br>
=======
>>>>>>> 06e1c65 (영화 디테일 페이지 완료)
=======
		<a href="/reservation/reservation">예매하기</a>
		<br>
>>>>>>> 9898ef7 (merge 전 commit)
		감독 : ${movieDetail.director} 
		<img alt="이미지 준비중입니다."
				src="https://image.tmdb.org/t/p/w342${movieDetail.directorFaceFile}">
		<br> 배우 :
		<c:forEach var="actors" items="${actors}" varStatus="status">
			<c:if test="${status.index < 5}">
			${actors.name}
			<img alt="이미지 준비중입니다."
				src="https://image.tmdb.org/t/p/w342${actors.actorFaceFile}">
			</c:if>
		</c:forEach>
		<br> <img alt="이미지 준비중입니다."
			src="https://image.tmdb.org/t/p/w342${movie.movieImg}"> <br>
		개봉 : ${movie.releaseDate} <br> ${movie.movieDesc}
		
	</div>

=======
			${movie.title}
			<br>
<<<<<<< HEAD
			장르 : ${movie.genre}
=======
			${movieDetail.titleEn}
=======
			영화 제목: ${movie.title}
			<br>
			영화 영어 제목 : ${movieDetail.titleEn}
>>>>>>> a3aafde (3차 자동 인설트 완성)
			<br>
			상영시간 : ${movieDetail.showTm}분
			<br>
			${movieDetail.prdStatNm}
			<br>
			${movieDetail.watchGradeNm}
			<br>
			${movieDetail.genre}
			
>>>>>>> 8b40cc4 (영화 API DB로 자동 연결구현)
			<br>
			감독 : ${movieDetail.director}
			<br>
			배우 : 
			<c:forEach var="actors" items="${actors}" varStatus="status">
			<c:if test="${actors.role == '배우'}">
=======
		영화 제목: ${movie.title} <br> 영화 영어 제목 : ${movieDetail.titleEn} <br>
		상영시간 : ${movieDetail.showTm}분 <br> ${movieDetail.prdStatNm} <br>
		${movieDetail.watchGradeNm} <br> ${movieDetail.genre} <br>
		감독 : ${movieDetail.director} <br> 배우 :
		<c:forEach var="actors" items="${actors}" varStatus="status">
			<c:if test="${status.index < 5}">
>>>>>>> 96e61be (영화API파싱 거의 95완료)
			${actors.name}
			<img alt="이미지 준비중입니다."
				src="https://image.tmdb.org/t/p/w342${actors.actorFaceFile}">
			</c:if>
<<<<<<< HEAD
			
			</c:forEach>
			<br>
<<<<<<< HEAD
			<img alt="" src="https://image.tmdb.org/t/p/w342/${movie.movieImg}">
=======
			<img alt="이미지 준비중입니다." src="https://image.tmdb.org/t/p/w342${movie.movieImg}">
>>>>>>> 8b40cc4 (영화 API DB로 자동 연결구현)
			<br> 
			개봉 : ${movie.releaseDate}
			<br>
			${movie.movieDesc}
	</div>
	
>>>>>>> 528fecc (영화 디테일 1차완료)
=======
		</c:forEach>
		<br> <img alt="이미지 준비중입니다."
			src="https://image.tmdb.org/t/p/w342${movie.movieImg}"> <br>
		개봉 : ${movie.releaseDate} <br> ${movie.movieDesc}
	</div>

>>>>>>> 96e61be (영화API파싱 거의 95완료)

</div>


<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

</body>
</html>
