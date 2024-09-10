<<<<<<< HEAD
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
=======
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
	<div id="in--wrap">
		<h1 style="margin-top: 150px">
			영화 검색 결과 <span style="font-size: 18px">총 10건</span>
		</h1>

		<div class="seach--movie--wrap">
			<div class="seach--movie--item">
				<div class="movie--post">
					<img src="/img/movieeeee.jpg">
				</div>
				<div class="search--movie--sub">
					<div class="seach--movie--info">
						<div class="movie--title">베테랑 3</div>
						
						<div class="seach--movie--sub--info">
							<div class="movie-opening">개봉</div>
							<div class="movie-age--limit">15세</div>
							<div class="movie-opening--date">2024-08-30</div>
						</div>
					</div>
					<div class="seach--movie--content">
						2142년, 부모 세대가 맞닥뜨렸던 암울한 미래를 피하려는 청년들이 더 나은 삶을 찾기 위해 식민지를 떠날 계획을 세운다. 하지만 버려진 우주 기지 로물루스에 도착한 이들은 악몽과도 같은 에이리언의 무자비한 공격에 쫓기기 시작한다. 그 누구도 그들의 절규를 들을 수 없는 우주 한가운데, 생존을 위한 치열한 사투를 벌여야 하는데...
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

>>>>>>> 81c16f4fa580f21c1c1dd1f9932361f8fab2e783
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>