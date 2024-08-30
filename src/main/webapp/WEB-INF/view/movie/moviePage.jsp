<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tenco.movie.repository.model.Movies"%>
	<!-- header.jsp -->
    <%@ include file="/WEB-INF/view/layout/header.jsp" %>
    
        <!-- start of content.jsp(xxx.jsp) -->
	  <c:choose>
        <c:when test="${movieList != null && !movieList.isEmpty()}">
            <div class="movie-container">
                <c:forEach var="movie" items="${movieList}">
                    <div class="movie-item">
                        <a href="/movie/detail?title=${movie.title}" class="movie-link">
                            <img alt="" src="https://image.tmdb.org/t/p/w342/${movie.movieImg}">
                            <div class="movie-item-details">
                                <h3>${movie.title}</h3>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <p>No movies found.</p>
        </c:otherwise>
    </c:choose>
	<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
</body>
</html>