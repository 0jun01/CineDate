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
	
	<!-- 평균 평점 표시 -->
    <div>
        <h3>관람객 평점: ${averageRating}</h3>
    </div>
	
	<br>
    <!-- 관람평 목록 -->
    <div id="review-section">
        <h2>관람평</h2><br>
        <div class="review-list">
            <c:forEach var="review" items="${reviews}">
                <div class="review-item">
                    <p><strong>ID:</strong> ${review.userId}</p>
                    <p><strong>관람평:</strong> ${review.reviewText}</p>
                    <p><strong>평점:</strong> ${review.rating}</p>
                    <p><strong>작성일:</strong> ${review.timestampToString()}</p>
                    <hr>
                </div>
            </c:forEach>
            <c:if test="${empty reviews}">
                <p>현재 작성된 관람평이 없습니다.</p>
            </c:if>
        </div>
    </div>

</div>


<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

</body>
</html>
