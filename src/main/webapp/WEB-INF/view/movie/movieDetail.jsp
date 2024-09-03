<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/css/main.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.tenco.movie.utils.MaskingUtil" %>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">

	<div id="in--wrap">
		영화 제목: ${movie.title} <br> 영화 영어 제목 : ${movieDetail.titleEn} <br> 상영시간 : ${movieDetail.showTm}분 <br> ${movieDetail.prdStatNm} <br> ${movieDetail.watchGradeNm}
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
                    <p><strong>ID:</strong> ${MaskingUtil.maskUserId(review.userLoginId)}</p>
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
    
<br>
<!-- 리뷰 작성 폼 -->
    <c:choose>
        <c:when test="${isLoggedIn}">
            <!-- 로그인 된 경우 리뷰 작성 폼 표시 -->
            <div class="review-form-container">
                <h3>리뷰 작성하기</h3>
                
                <form action="/movie/review" method="post">
                    <input type="hidden" name="movieId" value="${movie.id}" />
                    
                    <div class="form-group">
                        <label for="reviewText">관람평:</label>
                        <textarea id="reviewText" name="reviewText" rows="4" cols="50" required></textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="rating">평점:</label>
                        <input type="number" id="rating" name="rating" min="1" max="10" step="0.5" required />
                    </div>
                    
                    <div class="form-group">
                        <button type="submit" value="제출" class="submit-button">제출 </button>
                    </div>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <!-- 로그인 안 된 경우 표시 -->
            <div>
                <p>로그인 후 리뷰를 작성할 수 있습니다.</p>
            </div>
        </c:otherwise>
    </c:choose>
    
    

</div>



<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

</body>
</html>
