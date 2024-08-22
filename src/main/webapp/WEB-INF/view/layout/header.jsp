<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CineDate</title>
<link href="/css/common.css" rel="stylesheet">
<link href="/css/header.css" rel="stylesheet">
<link href="/css/footer.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="/img/favicon.ico">
</head>
<body>

	<div class="ad-wrap">
		<h1>헤더 배너 부분</h1>
	</div>
	
	<div class="header">
		<div class="header-content">
		<div class="contents">
		<h2><a href="/home">로고부분</a></h2>
			<ul class="memberinfo-wrap" style="display: flex; justify-content: flex-end; gap: 30px;">
					<li><a href="/user/signIn"><span>로그인</span></a></li>
					<li><a href="/user/signUp"><span>회원가입</span></a></li>
					<li><a href="/home/myPage"><span>마이페이지</span></a></li>
				<li><a href="/home/CS"><span>고객센터</span></a></li>
			</ul>
			</div>
		</div>

		<div class="nav-menu">
			<ul class="nav nav-pills flex-column" style="display: flex; gap: 30px;">
				<li class="nav-item"><h2>
						<a class="nav-link" href="/movie/movies">영화</a>
					</h2></li>
				<li class="nav-item"><h2>
						<a class="nav-link" href="/cinema/cinema">극장</a>
					</h2></li>
				<li class="nav-item"><h2>
						<a class="nav-link" href="/reservation/reservation">예매</a>
					</h2></li>
				<li class="nav-item"><h2>
						<a class="nav-link" href="/event/event">이벤트</a>
					</h2></li>
				<li class="nav-item"><h2>
						<a class="nav-link" href="/date/date">데이트</a>
					</h2></li>
			</ul>
		</div>

	</div>