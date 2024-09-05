<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/css/main.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<!-- 평균 평점 표시 -->
<div>
    <h3>관람객 평점: 
        <span class="rating-container">
            <!-- 별점 표시 -->
            <span class="star filled" style="width: ${averageRating * 10}%;">&#9733;</span>
            <!-- 평균 평점 숫자 표시 -->
            <fmt:formatNumber value="${averageRating}" type="number" maxFractionDigits="2" />
        </span>
    </h3>
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
                     <p><strong>평점:</strong>
                    <c:forEach var="i" begin="1" end="5">
                        <c:choose>
                            <c:when test="${review.rating >= i * 2}">
                                <span class="star filled">&#9733;</span> <!-- 채워진 별 -->
                            </c:when>
                            <c:when test="${review.rating >= ( i * 2 ) - 1 }">
                                <span class="star star-half">&#9733;</span> <!-- 반쪽 별 -->
                            </c:when>
                            <c:otherwise>
                                <span class="star">&#9734;</span> <!-- 빈 별 -->
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </p>
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
                    <input type="hidden" id="ratingValue" name="rating" />
                    <div id="starRating" class="star-rating">
                        <span class="star" data-value="2">&#9733;</span>
                        <span class="star" data-value="4">&#9733;</span>
                        <span class="star" data-value="6">&#9733;</span>
                        <span class="star" data-value="8">&#9733;</span>
                        <span class="star" data-value="10">&#9733;</span>
                    </div>
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

</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    const stars = document.querySelectorAll('#starRating .star');
    const ratingInput = document.getElementById('ratingValue');

    stars.forEach(star => {
        star.addEventListener('click', function() {
            const value = parseFloat(this.getAttribute('data-value'));
            const currentState = parseInt(this.getAttribute('data-state')) || 0;
            const newState = (currentState + 1) % 3; // 상태 0 (빈 별), 1 (반쪽 별), 2 (전체 별)만 가능

            updateStars(newState, value);
            ratingInput.value = getRatingValue(newState, value);
            this.setAttribute('data-state', newState);
        });
    });

    function updateStars(state, value) {
        stars.forEach(star => {
            const starValue = parseFloat(star.getAttribute('data-value'));

            if (starValue < value) {
                star.classList.add('filled');
                star.classList.remove('half');
            } else if (starValue === value) {
                if (state === 1) {
                    star.classList.add('half');
                    star.classList.remove('filled');
                } else if (state === 2) {
                    star.classList.add('filled');
                    star.classList.remove('half');
                } else {
                    star.classList.remove('filled', 'half');
                }
            } else {
                star.classList.remove('filled', 'half');
            }
        });
    }

    function getRatingValue(state, value) {
        if (state === 1) {
            return value - 1; // 반쪽 별 (1점)
        } else if (state === 2) {
            return value; // 전체 별 (2점 단위)
        } else {
            return 0; // 빈 별
        }
    }
});
</script>




<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

</body>
</html>
