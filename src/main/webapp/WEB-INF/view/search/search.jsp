<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<div id="wrap">
    <div id="in--wrap">
        <h1 class="section--title">
            영화 검색 결과 <span style="font-size: 18px">총 ${searchList.size()}건</span>
        </h1>

        <div class="seach--movie--wrap">
        <c:choose>
            <c:when test="${not empty searchList}">
                <c:forEach var="search" items="${searchList}">
                    <div class="seach--movie--item">
                        <div class="movie--post">
                            <img src="https://image.tmdb.org/t/p/w342/${search.movieImg}" alt="${search.title}" width="100%" height="411px">
                        </div>
                        <div class="search--movie--sub">
                            <div class="seach--movie--info">
                                <div class="movie--title">${search.title}</div>
                                <div class="movie--release">${search.releaseDate}</div>
                                <div class="movie--watchgrade">${search.watchGradeNm}</div>
                            </div>
                            
                            <div class="">
                                <button type="button" class="movie--btn btn"><a href="/movie/detail?title=${movieList.title}">상세보기</a></button>
                                <button type="button" class="movie--btn btn"><a href="/movie/detail?title=${movieList.title}">예매하기</a></button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="search--result">
                    <span>검색 결과가 없습니다.</span>
                </div>
            </c:otherwise>
        </c:choose>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>