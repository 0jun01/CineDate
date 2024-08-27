<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/css/main.css" rel="stylesheet">
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">

	<div id="in--wrap">
		<div class="flex--between--wrap">
			<div class="top--title">
				<a href="#none" class="title--word">무비차트</a>
				<a href="#none" class="title--word">상영예정작</a>
			</div>
			<div class="top--title">
				<a id="btn--all--view" class='btn--all--view'>전체보기</a>
			</div>
			<div class="movie--list--box">
			<c:forEach var="movieList" items="${movieList}">
				<div class="movie--text--box">
				<img alt="" src="https://image.tmdb.org/t/p/w342/${movieList.movieImg}">
				<h3>${movieList.title}</h3>
				</div>
			</c:forEach>
			</div>
			</div>
		<!-- 공지사항 -->
		<div class="notice--wrap">
			<div class="top--title">
				<h1>공지사항</h1>
				<a>~~유의사항 태그</a>
			</div>
		</div>
	</div>
	
</div>


<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

</body>
</html>
