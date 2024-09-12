<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.tenco.movie.utils.MaskingUtil"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">

	<!-- 메시지 표시 -->
        <c:if test="${not empty message}">
            <div class="alert alert-info">
                ${message}
            </div>
        </c:if>

	<div id="in--wrap">
		<div class="movie--wrap">
			<img src="https://image.tmdb.org/t/p/w342/${movie.movieImg}" alt="${movie.title}" >
			<div class="movie--detail">
				<div class="movie--title--wrap">
					<h1>${movie.title}</h1>
					<span class="movie--prdStatNm">${movieDetail.prdStatNm}</span>
				</div>
				<h3>${movieDetail.titleEn}</h3>
				<span>개봉일 : ${movieDetail.releaseDate}</span>
				<span>상영시간 : ${movieDetail.showTm}분</span>
				<span>${movieDetail.watchGradeNm}</span>
				<span>장르 : ${movieDetail.genre}</span>
				
				<div class="movie--btn btn"><a href="/reservation/reservation">예매하기</a></div>
			</div>
		</div>
		
		<div class="movie--info">
			<h3>감독/출현</h3>
		
			<div class="swiper mySwiper">
			    <div class="swiper-wrapper">
			    	<div class="swiper-slide">
			      		<img alt="${movieDetail.directorFaceFile}" src="https://image.tmdb.org/t/p/w342${movieDetail.directorFaceFile}">
						${movieDetail.director}<br><br>감독
				  	</div>
				    <c:forEach var="actors" items="${actors}" varStatus="status">
					     <div class="swiper-slide">
							<c:if test="${status.index < 5}">
								<img alt="${actors.actorFaceFile}" src="https://image.tmdb.org/t/p/w342${actors.actorFaceFile}">
								${actors.name}<br><br>배우
							</c:if>
						</div>
					</c:forEach>
			    </div>
			  </div>
			  <div class="swiper-button-next"></div>
			  <div class="swiper-button-prev"></div>
		</div>

		<div class="movie--content">${movieDetail.movieDesc}</div>
		

		<div class="rating-container">
			<div class="rating--star">
				<h3>관람평</h3>
				<div class="rating--avg">
					<h3>관람객 평점 : </h3>				
					<!-- 별점 표시 --> 
					<span class="star filled">&#9733;</span> 
					<!-- 평균 평점 숫자 표시 -->
					<fmt:formatNumber value="${averageRating}" type="number" maxFractionDigits="2" />
				</div>
			</div>
		</div>


		<!-- 관람평 목록 -->
		<div id="review-section">
			<div class="review-list">
				<c:forEach var="review" items="${reviews}">
					<div class="review-item">
						<p>
							ID : ${MaskingUtil.maskUserId(review.userLoginId)}
						</p>
						<p>
							관람평 : ${review.reviewText}
						</p>
						<p>
							평점 :
							<c:forEach var="i" begin="1" end="5">
								<c:choose>
									<c:when test="${review.rating >= i * 2}">
										<span class="star filled">&#9733;</span>
									</c:when>
									<c:when test="${review.rating >= (i * 2) - 1}">
										<span class="star star-half">&#9733;</span>
									</c:when>
									<c:otherwise>
										<span class="star">&#9734;</span>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</p>
						<p>
							작성일: ${review.timestampToString()}
						</p>
						<c:if test="${review.userId == principal.id}">
							<!-- 리뷰 작성자일 경우만 수정 및 삭제 버튼 표시 -->
							<button type="button" class="edit-button" data-review-id="${review.id}" data-review-text="${review.reviewText}" data-review-rating="${review.rating}">수정</button>
							<form action="/movie/review/delete" method="post" style="display: inline;">
								<input type="hidden" name="id" value="${review.id}" />
								<button type="submit" class="delete-button">삭제</button>
							</form>
						</c:if>
					</div>
				</c:forEach>
				<c:if test="${empty reviews}">
					<p>현재 작성된 관람평이 없습니다.</p>
				</c:if>
			</div>

			<!-- Pagination -->
			<div class="page--btn--a">
				<ul class="pagination" style="display: flex;">
					<!-- Previous Page Link -->
					<li class="page--item <c:if test='${currentPage == 1}'>disabled</c:if>"><a class="page-link" href="?title=${param.title}&page=${currentPage - 1}&size=${size}"><</a>
					</li>

					<!-- Page Numbers -->
					<c:forEach var="page" begin="1" end="${totalPages}">
						<li class="page--item <c:if test='${page == currentPage}'>active</c:if>"><a class="page-link" href="?title=${param.title}&page=${page}&size=${size}">${page}</a></li>
					</c:forEach>

					<!-- Next Page Link -->
					<li class="page--item <c:if test='${currentPage == totalPages}'>disabled</c:if>"><a class="page-link" href="?title=${param.title}&page=${currentPage + 1}&size=${size}">></a>
					</li>
				</ul>
			</div>

			<br>
			<!-- 리뷰 작성 폼 -->
			<c:choose>
				<c:when test="${isLoggedIn}">
					<!-- 로그인 된 경우 리뷰 작성 폼 표시 -->
					<div class="review-form-container" id="reviewFormContainer">
						<h3 id="formTitle">리뷰 작성하기</h3>

						<form id="reviewForm" action="/movie/review" method="post">
							<input type="hidden" id="reviewId" name="id" /> <input type="hidden" name="movieId" value="${movie.id}" />

							<div class="form-group">
								<label for="reviewText">관람평:</label>
								<textarea id="reviewText" name="reviewText" rows="4" cols="50" required></textarea>
							</div>

							<div class="form-group">
								<label for="rating">평점:</label> <input type="hidden" id="ratingValue" name="rating" />
								<div id="starRating" class="star-rating">
									<span class="star" data-value="2">&#9733;</span>
									<span class="star" data-value="4">&#9733;</span>
									<span class="star" data-value="6">&#9733;</span> 
									<span class="star" data-value="8">&#9733;</span>
									<span class="star" data-value="10">&#9733;</span>
								</div>
							</div>
							<br>

							<div class="form-group">
								<button type="submit" class="submit-button">제출</button>
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
	    // 별점 클릭 이벤트 핸들러
	    const stars = document.querySelectorAll('#starRating .star');
	    const ratingInput = document.getElementById('ratingValue');
	    const reviewForm = document.getElementById('reviewForm');
	    const reviewFormContainer = document.getElementById('reviewFormContainer');
	    const formTitle = document.getElementById('formTitle');

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

	    // 리뷰 수정 버튼 클릭 이벤트 핸들러
	    document.querySelectorAll('.edit-button').forEach(button => {
	        button.addEventListener('click', function(event) {
	            event.preventDefault(); // 기본 폼 제출 방지

	            const reviewId = this.getAttribute('data-review-id');
	            const reviewText = this.getAttribute('data-review-text');
	            const reviewRating = parseFloat(this.getAttribute('data-review-rating'));

	            formTitle.textContent = '리뷰 수정하기';
	            reviewForm.action = '/movie/review/update';
	            document.getElementById('reviewId').value = reviewId;
	            document.getElementById('reviewText').value = reviewText;

	            setInitialRating(reviewRating);

	            reviewFormContainer.style.display = 'block'; // 폼 보이기
	        });
	    });

	    function setInitialRating(value) {
	        stars.forEach(star => {
	            const starValue = parseFloat(star.getAttribute('data-value'));
	            if (starValue <= value) {
	                star.classList.add('filled');
	                star.classList.remove('half');
	            } else if (starValue === value + 1) {
	                star.classList.add('half');
	                star.classList.remove('filled');
	            } else {
	                star.classList.remove('filled', 'half');
	            }
	        });
	    }

	 // 리뷰 작성 폼 제출 시 별점 입력 체크
	    reviewForm.addEventListener('submit', function(event) {
	        if (reviewForm.action.includes('/movie/review')) { // 리뷰 작성 요청일 때만 체크
	            if (ratingInput.value === "" || ratingInput.value === "0") {
	                event.preventDefault(); // 폼 제출 방지
	                alert('평점을 입력하세요.');
	            }
	        }
	    });

	    // 페이지 전환 전에 스크롤 위치 저장
	    window.addEventListener('beforeunload', function () {
	        sessionStorage.setItem('scrollPosition', window.scrollY);
	    });

	    // 페이지 로드 시 저장된 스크롤 위치 복원
	    window.addEventListener('load', function () {
	        const scrollPosition = sessionStorage.getItem('scrollPosition');
	        if (scrollPosition !== null) {
	            window.scrollTo(0, parseInt(scrollPosition, 10));
	            sessionStorage.removeItem('scrollPosition'); 
	        }
	    });
	});

</script>

 <!-- Swiper JS -->
  <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

  <!-- Initialize Swiper -->
  <script>
    var swiper = new Swiper(".mySwiper", {
        slidesPerView: 5,
        spaceBetween: 20,
        loop: true,
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
          },
      });
  </script>

</div>


	<!-- footer.jsp  -->
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
