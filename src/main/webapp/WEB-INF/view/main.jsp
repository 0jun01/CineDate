<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="com.tenco.movie.repository.model.Notice"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>

<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">

	<main>
		<!-- Swiper -->
		<div class="swiper mySwiper" id="main--swiper">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<img src="/img/imgSlide1.jpg">
				</div>
				<div class="swiper-slide">
					<img src="/img/imgSlide2.jpg">
				</div>
				<div class="swiper-slide">
					<img src="/img/imgSlide3.jpg">
				</div>
			</div>
			<div class="swiper-pagination"></div>
		</div>

		<!-- Swiper JS -->
		<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

		<!-- ImageSlide Swiper -->
		<script>
			var swiper = new Swiper("#main--swiper", {
				spaceBetween : 30,
				centeredSlides : true,
				autoplay : {
					delay : 2000,
					disableOnInteraction : false,
				},
				pagination : {
					el : ".swiper-pagination",
					clickable : true,
				},
				navigation : {
					nextEl : ".swiper-button-next",
					prevEl : ".swiper-button-prev",
				},
			});
		</script>
	</main>
	<div id="movie--film"></div>
	<div id="in--wrap">
		<div id="main--container" class="main--movie--chart">
			<h1 class="index--title eng">CINE CHART</h1>

			<div class="swiper mySwiper" id="movieSwiper">
				<div class="swiper-wrapper">
					<c:forEach var="movieList" items="${movieList}">
						<div class="swiper-slide">
							<div class="movie--text--box">
								<div class="movie--img">
									<img src="https://image.tmdb.org/t/p/w342/${movieList.movieImg}" alt="${movieList.title}">
								</div>
								<h3 class="movieList--title">${movieList.title}</h3>
								<div class="index--link">
									<div class="index--linka">
										<span>${movieList.movieDesc}</span>
										<a href="movie/detail?title=${movieList.title}" class="overlay--link">상세보기</a>
										<a href="reservation/reservation" class="overlay--link">예매하기</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="swiper-button-next"></div>
			<div class="swiper-button-prev"></div>

		</div>

	</div>
	<script>
		var movieSwiper = new Swiper("#movieSwiper", {
			slidesPerView : 3,
			spaceBetween : 20,
			loop : true,
			autoplay : {
				delay : 3000,
				disableOnInteraction : false,
			},
			navigation : {
				nextEl : ".swiper-button-next",
				prevEl : ".swiper-button-prev",
			},
		});
	</script>
	<div id="removie--film"></div>

	<!-- 공지사항 -->
	<div id="in--wrap">
		<div id="main--container" class="notice--wrap">
			<div class="notice--btn">
				<h1 class="index--title eng">NOTICE</h1>
				<a href="/notice" class="eng">more</a>
			</div>

			<div class="notice--list">
				<c:choose>
					<c:when test="${noticeList != null}">
						<table class="table">
							<thead>
								<tr>
									<th>카테고리</th>
									<th>제목</th>
									<th>날짜</th>
									<th>조회수</th>
									<!-- 조회수 열 추가 -->
								</tr>
							</thead>
							<tbody>
								<c:forEach var="notice" items="${noticeList}">
									<tr>
										<td>${notice.category}</td>
										<td><a href="/notice/detail?id=${notice.id}">${notice.title}</a></td>
										<td>${notice.timestampToString()}</td>
										<td>${notice.viewCount}</td>
										<!-- 조회수 출력 -->
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<div class="jumbotron display-4">
							<h5>게시된 공지가 없습니다.</h5>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>




	<!-- footer.jsp  -->
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>