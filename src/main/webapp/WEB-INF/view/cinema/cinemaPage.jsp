<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<style type="text/css">
/* 상자 스타일 */
.box {
	border: 1px solid #ddd; /* 상자의 경계선 */
	border-radius: 8px; /* 모서리 둥글게 만들기 */
	background-color: #f9f9f9; /* 상자 배경색 */
	padding: 20px; /* 상자 내부 여백 */
	margin: 20px 0; /* 상자와 다른 요소 간의 여백 */
}

/* 상자의 내용 래퍼 */
.content-wrapper {
	display: flex; /* Flexbox 레이아웃 사용 */
	justify-content: center; /* 지역 목록을 가로 방향으로 중앙 정렬 */
	width: 100%; /* 부모 컨테이너의 너비를 100%로 설정 */
	border-bottom: 2px solid #ddd; /* 하단 경계선 추가 */
	padding-bottom: 20px; /* 하단 패딩 추가 */
	margin-bottom: 20px; /* 하단 여백 추가 */
}

/* 상자 내 목록 스타일 */
ul {
	list-style-type: none; /* 기본 목록 스타일 제거 */
	padding: 0; /* 기본 패딩 제거 */
	margin: 0; /* 기본 마진 제거 */
	display: flex; /* Flexbox 레이아웃 사용 */
	flex-wrap: wrap; /* 항목들이 여러 줄로 감싸지도록 설정 */
	justify-content: center; /* 항목들을 가로 방향으로 중앙 정렬 */
}

/* 상위 지역 리스트 항목 스타일 */
.content-wrapper li {
	margin: 10px; /* 항목 간의 여백 */
}

/* 하위 지역 스타일 */
.subregions {
	margin-top: 20px; /* 상단 여백 추가 */
	padding-top: 20px; /* 상단 패딩 추가 */
	border-top: 2px solid #ddd; /* 상단 경계선 추가 */
}

/* 하위 지역 목록 스타일 */
.subregions ul {
	display: flex; /* Flexbox 레이아웃 사용 */
	flex-wrap: wrap; /* 항목들이 여러 줄로 감싸지도록 설정 */
	justify-content: center; /* 항목들을 가로 방향으로 중앙 정렬 */
	list-style-type: none; /* 기본 목록 스타일 제거 */
	padding: 0; /* 기본 패딩 제거 */
	margin: 0; /* 기본 마진 제거 */
}

/* 하위 지역 리스트 항목 스타일 */
.subregions li {
	display: flex; /* Flexbox 사용 */
	align-items: center; /* 수직 중앙 정렬 */
	position: relative; /* 위치 기준 설정 */
	margin: 10px; /* 항목 간의 여백 */
}

/* 하위 지역 리스트 항목 사이의 구분선 */
.subregions li:not(:last-child)::after {
	content: ""; /* 구분선의 내용 없음 */
	position: absolute; /* 절대 위치 설정 */
	right: -10px; /* 오른쪽 여백 */
	height: 20px; /* 구분선의 높이 */
	border-right: 3px solid #ddd; /* 구분선의 두께와 색상 */
}

/* 링크 스타일 */
a {
	text-decoration: none; /* 링크에 기본 밑줄 제거 */
	font-size: 18px; /* 기본 폰트 크기 */
	color: #333; /* 링크 색상 설정 */
}

/* 기본 색상 */
.default {
	color: #333; /* 기본 링크 색상 */
}

/* 선택된 지역 스타일 */
.selected-region {
	color: #FF69B4; /* 선택된 지역 색상 */
	font-weight: bold; /* 굵은 글씨 */
}

/* 선택된 상세지역 스타일 */
.selected-subregion {
	color: #FF69B4; /* 선택된 상세지역 색상 */
	font-weight: bold; /* 굵은 글씨 */
}

/* 하위 상세지역 스타일 */
.subregions a {
	font-size: 16px; /* 기본 폰트 크기 */
}

/* 링크에 마우스를 올렸을 때 스타일 */
a:hover {
	text-decoration: underline; /* 링크에 마우스를 올렸을 때 밑줄 추가 */
	color: #000; /* 링크 색상 변경 (옵션) */
}

/* 이미지 스타일 */
.cinema--img {
	text-align: center; /* 이미지를 중앙 정렬 */
	margin-top: 20px; /* 상단 여백 추가 */
}

.cinema--img img {
	max-width: 100%; /* 이미지 최대 너비를 100%로 설정 */
	height: auto; /* 이미지 높이를 자동으로 조절 */
	border-radius: 8px; /* 이미지 모서리 둥글게 만들기 */
}

.wrap--cinema {
    text-align: center; /* 중앙 정렬 */
    margin-top: 20px; /* 상단 여백 추가 */
}

.box--img {
    max-width: 100%; /* 최대 너비를 부모 컨테이너에 맞게 조정 */
    height: auto; /* 자동으로 높이 조정 */
    border-radius: 8px; /* 이미지 모서리 둥글게 만들기 */
}

.box--img img {
    width: 100%; /* 이미지 너비를 부모 컨테이너에 맞게 조정 */
    height: auto; /* 이미지 높이를 자동으로 조절 */
}




.wrap-cinemainfo .box-contents {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 920px;
    padding: 20px 30px;
    background: url(../images/common/bg/bg_dim80.png) 0 0 repeat;
    color: #fdfcf0;
}

.wrap-cinemainfo .box-contents {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 920px;
    padding: 20px 30px;
    background: rgba(0, 0, 0, 0.5); /* 어두운 색과 반투명도 설정 */
    color: #fdfcf0; /* 글자 색상 */
    border-radius: 8px; /* 모서리 둥글게 만들기 (선택 사항) */
}

.wrap-cinemainfo .cinema-info {
    position: relative;
    float: left;
    width: 608px;
}

/* 검색 폼 스타일 */
.search-form {
    margin: 20px 0; /* 여백 추가 */
    text-align: center; /* 중앙 정렬 */
}

.search-form input[type="text"] {
    width: 300px; /* 입력 상자의 너비 */
    padding: 8px; /* 입력 상자의 패딩 */
    border: 1px solid #ddd; /* 입력 상자의 경계선 */
    border-radius: 4px; /* 입력 상자의 모서리 둥글게 만들기 */
}

.search-form button {
    padding: 8px 16px; /* 버튼의 패딩 */
    border: none; /* 버튼의 경계선 제거 */
    background-color: #FF69B4; /* 버튼 배경 색상 */
    color: #fff; /* 버튼 글자 색상 */
    border-radius: 4px; /* 버튼 모서리 둥글게 만들기 */
    cursor: pointer; /* 마우스 커서 변경 */
}

.search-form button:hover {
    background-color: #FF1493; /* 버튼 호버 색상 */
}
</style>

<!-- contents start -->
<br>
<div class="title">
	<h2>극장 조회</h2>
</div>
<div id="in--wrap">
	<div class="container box">
		<div class="content-wrapper">
			<ul>
				<c:forEach var="region" items="${regions}">
					<li><a href="?id=${region.id}" class="${region.id == selectedRegionId ? 'selected-region' : 'default'}"> ${region.name} </a></li>
				</c:forEach>
			</ul>
		</div>

		<c:if test="${not empty subregions}">
			<div class="subregions">
				<ul>
					<c:forEach var="subregion" items="${subregions}">
						<li><a href="?id=${selectedRegionId}&subregionId=${subregion.id}" class="${subregion.id == selectedSubregionId ? 'selected-subregion' : 'default'}">
								${subregion.name} </a></li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	</div>

	<div class="wrap--cinema">
    <img src="https://img.cgv.co.kr/R2014/images/title/h3_theater.gif">
    <div class="sect--cinema">
        <div class="wrap-cinemainfo">
            <!-- 극장 이미지 추가 -->
            <c:if test="${not empty subregions && selectedSubregionId != null}">
                <c:forEach var="subregion" items="${subregions}">
                    <c:if test="${subregion.id == selectedSubregionId}">
                        <div class="box--img">
                            <div class="cinema--img">
                                <img alt="극장 이미지" src="https://img.cgv.co.kr/Theater/Theater${subregion.regionImage}">
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </c:if>

            <c:if test="${not empty theaters}">
                <div class="box-contents">
                    <ul>
                        <c:forEach var="theater" items="${theaters}">
                            <li>
                                <h4>${theater.name}</h4>
                                <p>${theater.address}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
</div>
</div>



<!-- footer.jsp -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>