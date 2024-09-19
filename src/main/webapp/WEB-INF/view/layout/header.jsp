<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CineDate</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/header.css" rel="stylesheet">
<link href="/css/font.css" rel="stylesheet">
<link href="/css/main.css" rel="stylesheet">
<link href="/css/movie.css" rel="stylesheet">
<link href="/css/date.css" rel="stylesheet">
<link href="/css/event.css" rel="stylesheet">
<link href="/css/notice.css" rel="stylesheet">
<link href="/css/footer.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="/img/favicon.ico">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<header>
		<div class="main--banner">
			<div id="in--wrap">
				<img src="/img/xIcon.png" alt="x" class="x--icon btn">
				<img src="/img/bannerImg.png" alt="시데 데이트 배너" width="100%">
			</div>
		</div>
		<div class="memberInfo--wrap">
			<ul class="member" id="in--wrap">
				<c:choose>
					<c:when test="${principal != null}">
						<li>
							<a href="/user/logout">로그아웃</a>
						</li>
						<li>
							<a href="/user/myPage">마이페이지</a>
						</li>
						<li>
							<a href="/home/CS">고객센터</a>
						</li>
					</c:when>

					<c:otherwise>
						<li>
							<a href="/user/signIn">로그인</a>
						</li>
						<li>
							<a href="/user/signUp">회원가입</a>
						</li>
						<li>
							<a href="/home/CS">고객센터</a>
						</li>
					</c:otherwise>
				</c:choose>

				<form action="/search/search" method="GET" class="main--total--search">
					<label class="main--search"></label>
					<input type="text" id="main--search" name="search">

					<button type="submit" class="search--btn btn"></button>
				</form>
			</ul>
		</div>
		<div class="header--logo">
			<a href="/home"><img src="/img/header_logo.png" alt="CineDate Logo"></a>
		</div>
		<nav class="nav--wrap" id="in--wrap">
			<ul class="nav--menu">
				<li class="nav--menu--li">
					<a href="/movie/movies">영화</a>
				</li>
				<li class="nav--menu--li">
					<a href="/cinema/cinema">극장</a>
				</li>
				<li class="nav--menu--li">
					<a href="/reservation/reservation">예매</a>
				</li>
				<li class="date nav--menu--li">
					매칭
					<ul class="sub--menu">
						<li><a href="/date/date">일반리스트</a></li>
						<li><a href="/date/machingList">매칭리스트</a></li>
						<li><a href="/date/popcornStore">상점</a></li>
						<li><a href="/date/profilePage">마이페이지</a></li>
					</ul>
				</li>
				<li class="nav--menu--li"><a href="/event/event">이벤트</a></li>
				<li class="nav--menu--li"><a href="/notice">공지사항</a></li>

				<ul class="mobile--wrap btn">
					<li class="bar bar01"></li>
					<li class="bar bar02"></li>
					<li class="bar bar03"></li>
				</ul>

				<div class="mobile--all--menu" style="display:none">
					<div class="all--bg" style="display: block;"></div>
					<div class="all--menu">
						<ul>
							<li><a href="/movie/movies">영화</a></li>
							<li><a href="/cinema/cinema">극장</a></li>
							<li><a href="/reservation/reservation">예매</a></li>
							<li class="arrow"><a>매칭</a>
								<ul class="dep2" style="display: none;">
									<li><a href="">추가하세효1</a></li>
									<li><a href="">추가하세효2</a></li>
									<li><a href="">추가하세효3</a></li>
									<li><a href="">추가하세효4</a></li>
								</ul></li>
							<li><a href="/event/event">이벤트</a></li>
							<li><a href="/notice">공지사항</a></li>
						</ul>
					</div>
				</div>
			</ul>
		</nav>
	</header>

	<script>
		<!-- 헤더 상단 팝업 배너 -->
		document.addEventListener('DOMContentLoaded', function() {
			var xIcon = document.querySelector('.x--icon');

			xIcon.addEventListener('click', function() {
				var mainBanner = document.querySelector('.main--banner');
				mainBanner.style.display = 'none';
			});
		});
	</script>