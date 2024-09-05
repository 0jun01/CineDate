<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/css/main.css" rel="stylesheet">
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">

	<div id="in--wrap">
		영화 제목: ${movie.title} <br> 영화 영어 제목 : ${movieDetail.titleEn} <br> 상영시간 : ${movieDetail.showTm}분 <br>개봉일 : ${movieDetail.releaseDate}<br> ${movieDetail.prdStatNm} <br> ${movieDetail.watchGradeNm}
		<br> ${movieDetail.genre} <br> <a href="/reservation/reservation">예매하기</a> <br> 감독 : ${movieDetail.director} <img alt="이미지 준비중입니다."
			src="https://image.tmdb.org/t/p/w342${movieDetail.directorFaceFile}"> <br> 배우 :
			
		<c:forEach var="actors" items="${actors}" varStatus="status">
			<c:if test="${status.index < 5}">
			${actors.name}
			<img alt="이미지 준비중입니다." src="https://image.tmdb.org/t/p/w342${actors.actorFaceFile}">
			</c:if>
		</c:forEach>
		${movieDetail.movieDesc}
	</div>

</div>



<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

</body>
</html>
