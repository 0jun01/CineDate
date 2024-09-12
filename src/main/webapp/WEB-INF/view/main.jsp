<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">

	<main>
		<!-- Swiper -->
		<div class="swiper mySwiper">
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
			var swiper = new Swiper(".mySwiper", {
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
	
	<div id="in--wrap">
		<div class="flex--between--wrap">
			<div class="top--title">
				<a href="#none" class="title--word">무비차트</a> <a href="#none" class="title--word">상영예정작</a>
			</div>
			<div class="top--title">
				<a id="btn--all--view" class='btn--all--view'>전체보기</a>
			</div>
			<div>
			<div class="movie--list--box">
				<c:forEach var="movieList" items="${movieList}">
					<div class="movie--text--box">
						<img alt="" src="https://image.tmdb.org/t/p/w342/${movieList.movieImg}">
							<a href="movie/detail?title=${movieList.title}" class="overlay-link">상세보기</a>
							<a href="reservation/reservation" class="overlay-link">예매하기</a> 
						<h3>${movieList.title}</h3>
					</div>
				</c:forEach>
			</div>
		</div>
		<!-- 공지사항 -->
		<div class="notice--wrap">
			<div class="top--title">
				<h1>
					<a href="/notice">공지사항</a>
				</h1>
				<a>~~유의사항 태그</a>
			</div>
		</div>
	</div>

</div>


<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
