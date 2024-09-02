<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<link href="/css/reservation.css" rel="stylesheet">
<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">

	<div id="in--wrap">

		<div class="movie--list--box">
			<div class="top--title--inner">
				<h3>영화</h3>
			</div>
			<div class="filter--box">
				<!--<a href="#"><h5>가나다순&nbsp;&nbsp;</h5></a>
				<h5>시청등급순</h5> -->
				<button type="button" id="sortByKorean" value="korean">가나다순</button>
				<button type="button" id="sortByAge" value="age">시청등급순</button>
			</div>
			<div class="movie--list">
				<ul id="movie-list">
					<c:forEach var="movie" items="${movieList}">
						<li><c:choose>
								<c:when test="${movie.watchGradeNm eq '전체관람가'}">
									<span class="grade-all">ALL</span> ${movie.title}
								</c:when>
								<c:when test="${movie.watchGradeNm eq '12세이상관람가'}">
									<span class="grade-12">12</span> ${movie.title}
								</c:when>
								<c:when test="${movie.watchGradeNm eq '15세이상관람가'}">
									<span class="grade-15">15 </span> ${movie.title}
								</c:when>
								<c:when test="${movie.watchGradeNm eq '19세이상관람가'}">
									<span class="grade-19">19</span> ${movie.title}
								</c:when>
								<c:otherwise>
									<span class="grade-default">${movie.title}</span>
								</c:otherwise>
							</c:choose></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('sortByKorean').addEventListener('click', function() {
        fetchMovies('korean');
    });

    document.getElementById('sortByAge').addEventListener('click', function() {
        fetchMovies('age');
    });
});

function fetchMovies(sortBy) {
    fetch(`http://localhost:8080/reservation/movies?sortBy=` + sortBy)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            updateMovieList(data); // 데이터가 성공적으로 반환되면 업데이트 함수 호출
        })
        .catch(error => {
            alert('An error occurred while fetching the movies.');
        });
}

function updateMovieList(movies) {
    const movieListUl = document.getElementById('movie-list');
    movieListUl.innerHTML = '';
    movies.forEach(movie => {
        const movieItem = document.createElement('li');
     // 등급에 따라 클래스를 동적으로 설정
        let gradeClass = '';
        let gradeText = '';

        switch (movie.watchGradeNm) {
            case '전체관람가':
                gradeClass = 'grade-all';
                gradeText = 'ALL';
                break;
            case '12세이상관람가':
                gradeClass = 'grade-12';
                gradeText = '12';
                break;
            case '15세이상관람가':
                gradeClass = 'grade-15';
                gradeText = '15';
                break;
            case '19세이상관람가':
                gradeClass = 'grade-19';
                gradeText = '19';
                break;
            default:
                gradeClass = 'grade-default';
                gradeText = movie.watchGradeNm; // 기본값으로 등급명 표시
        }

        movieItem.innerHTML = '<span class="' + gradeClass + '">' + gradeText + '</span> ' + movie.title;
        movieListUl.appendChild(movieItem);
    });
}


		

</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</body>
</html>
