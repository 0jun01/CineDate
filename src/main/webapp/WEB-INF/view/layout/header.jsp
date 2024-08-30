<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CineDate</title>
<link href="/css/common.css" rel="stylesheet">
<link href="/css/header.css" rel="stylesheet">
<link href="/css/main.css" rel="stylesheet">
<link href="/css/footer.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="/img/favicon.ico">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>

	<header>
        <div class="memberInfo--wrap">
            <ul class="member" id="in--wrap">
                <c:choose>
					<c:when test="${principal != null}">
						<li><a href="/user/logout"><span>로그아웃</span></a></li>			
						<li><a href="/user/myPage"><span>마이페이지</span></a></li>
						<li><a href="/home/CS"><span>고객센터</span></a></li>
					</c:when>
					
					<c:otherwise>
						<li><a href="/user/signIn"><span>로그인</span></a></li>
						<li><a href="/user/signUp"><span>회원가입</span></a></li>
						<li><a href="/home/CS"><span>고객센터</span></a></li>
					</c:otherwise>
				</c:choose>
                
                <form action="/search/search" method="GET" class="main--total--search">
                    <label for="main--search" class="main--search"></label>
                        <input type="text" id="main--search" name="main--search">
                    
                    <button type="button" class="search--btn btn"></button>
                </form>
            </ul>
        </div>
        <div class="header--logo"><a href="/home"><img src="/img/logo.png" alt="CineDate Logo"></a></div>
        <nav class="nav--wrap" id="in--wrap">
            <ul class="nav--menu">
                <li><a href="/movie/movies">영화</a></li>
                <li><a href="/cinema/cinema">극장</a></li>
                <li><a href="/reservation/reservation">예매</a></li>
                <li class="date"><a href="/date/date">매칭</a></li>
                <li><a href="/event/event">이벤트</a></li>
                <li><a href="/notice">공지사항</a></li>

                <ul class="mobile--wrap btn">
                    <!-- 햄버거 메뉴 추가 예정 -->
                </ul>
            </ul>
        </nav>
    </header>
	
	