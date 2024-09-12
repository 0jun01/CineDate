<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tenco.movie.repository.model.Movies"%>
	<!-- header.jsp -->
    <%@ include file="/WEB-INF/view/layout/header.jsp" %>
   
	<div id="wrap">
	   	<div id="in--wrap">
	   
	   		<h1 class="section--title">현재 상영중인 영화</h1>
	   		<c:choose>
		        <c:when test="${movieList != null && !movieList.isEmpty()}">
		            <div class="seach--movie--wrap">
		                <c:forEach var="movie" items="${movieList}">
		                    <div class="seach--movie--item">
		                    	<div class="movie--post">
			                        <img src="https://image.tmdb.org/t/p/w342/${movie.movieImg}" alt="${movie.title}" width="100%" height="411px">
		                    	</div>
		                    	<div class="search--movie--sub">
			                    	<div class="seach--movie--info">
	                                <div class="movie--title">${movie.title}</div>
	                                <div class="movie--release">${movie.releaseDate}</div>
	                            </div>
		                        
		                        <div class="">
                                	<button type="button" class="movie--btn btn"><a href="/movie/detail?title=${movie.title}">상세보기</a></button>
                                	<button type="button" class="movie--btn btn"><a href="/reservation/reservation">예매하기</a></button>
                            	</div>
		                    </div>
		                    </div>
		                </c:forEach>
		            </div>
		        </c:when>
		        <c:otherwise>
		            <p>No movies found.</p>
		        </c:otherwise>
    		</c:choose>
	   </div>
   </div>
	  
	  
	<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
