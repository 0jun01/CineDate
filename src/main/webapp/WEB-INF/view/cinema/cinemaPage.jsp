<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
	<div id="in--wrap">
		<h1 class="section--title">극장 조회</h1>
		<div class="container box">
			<div class="content-wrapper">
				<ul class="content-region">
					<c:forEach var="region" items="${regions}">
						<li><a href="?id=${region.id}" class="${region.id == selectedRegionId ? 'selected-region' : 'default'}"> ${region.name} </a></li>
					</c:forEach>
				</ul>
			</div>

			<c:if test="${not empty subregions}">
				<div class="subregions">
					<ul class="content-region">
						<c:forEach var="subregion" items="${subregions}">
							<li><a href="?id=${selectedRegionId}&subregionId=${subregion.id}" class="${subregion.id == selectedSubregionId ? 'selected-subregion' : 'default'}">${subregion.name} </a></li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		</div>

		<div class="wrap--cinema">
			<img src="https://img.cgv.co.kr/R2014/images/title/h3_theater.gif" width="15%">
			<div class="sect--cinema">
				<div class="wrap-cinemainfo">
					<!-- 극장 이미지 추가 -->
					<c:if test="${not empty subregions && selectedSubregionId != null}">
						<c:forEach var="subregion" items="${subregions}">
							<c:if test="${subregion.id == selectedSubregionId}">
								<div class="box--img">
									<img alt="극장 이미지" src="https://img.cgv.co.kr/Theater/Theater${subregion.regionImage}">
								</div>
							</c:if>
						</c:forEach>	 
					</c:if>

					<c:if test="${not empty theaters}">
						<div class="box-contents">
							<ul class="movie--contents--list">
								<c:forEach var="theater" items="${theaters}">
									<li>${theater.name}</li>
									<li>${theater.address}</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- footer.jsp -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
