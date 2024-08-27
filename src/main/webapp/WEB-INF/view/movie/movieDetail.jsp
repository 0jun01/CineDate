<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/css/main.css" rel="stylesheet">
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">

	<div id="in--wrap">
			${movie.title}
			<br>
			장르 : ${movie.genre}
			<br>
			감독 : ${movie.director}
			<br>
			배우 : 
			<c:forEach var="actors" items="${actors}" varStatus="status">
			<c:if test="${actors.role == '배우'}">
			${actors.name}
				<c:if test="${!status.last}">,</c:if>
			</c:if>
			
			</c:forEach>
			<br>
			<img alt="" src="https://image.tmdb.org/t/p/w342/${movie.movieImg}">
			<br> 
			개봉 : ${movie.releaseDate}
			<br>
			${movie.movieDesc}
	</div>
	

</div>


<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

</body>
</html>
