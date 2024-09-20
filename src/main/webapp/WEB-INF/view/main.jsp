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
	<div id="movie--film"></div>
	<div id="in--wrap">
		<div id="main--container" class="main--movie--chart">
			<h1 class="index--title eng">CINE CHART</h1>

			<div class="swiper mySwiper" id="movieSwiper">
				<div class="swiper-wrapper">
					<div class="swiper-slide">
						<c:forEach var="movieList" items="${movieList}">
							<div class="movie--text--box">
								<div class="movie--img">
									<img src="https://image.tmdb.org/t/p/w342/${movieList.movieImg}" alt="${movieList.title}">
								</div>
								<h3>${movieList.title}</h3>
								<div class="index--link">
									<a href="movie/detail?title=${movieList.title}" class="overlay--link">상세보기</a> <a href="reservation/reservation" class="overlay--link">예매하기</a>
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
			var swiper = new Swiper("#movieSwiper", {
				spaceBetween : 30,
				centeredSlides : true,
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

	</div>
	<div id="removie--film"></div>




	<!-- 공지사항 -->
	<div id="in--wrap">
		<div id="main--container" class="notice--wrap">
			<div class="notice--btn">
				<h1 class="index--title eng">NOTICE</h1>
				<a href="/notice">more</a>
			</div>
			
		</div>
	</div>




	<!-- footer.jsp  -->
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>