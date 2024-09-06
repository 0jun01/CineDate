<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<link href="/css/reservation.css" rel="stylesheet">
<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">

	<div id="in--wrap">
		<div class="choice--box">
			<div class="date--box">
				<div class="date--title--inner">
					<h3>날짜</h3>
				</div>
				<div class="scroll--list">
					<ul id="date--list">
						<li>
							<div>
								<span class="year">${currentYear}</span> <br> <span class="">${currentMonth}월</span>
							</div>
						</li>
						<c:forEach var="entry" items="${date}">

							<c:set var="formattedMonth">
								<fmt:formatNumber value="${entry.month}" pattern="00" />
							</c:set>
							<c:set var="formattedDay">
								<fmt:formatNumber value="${entry.day}" pattern="00" />
							</c:set>
							<c:set var="formattedDate" value="${entry.year}-${formattedMonth}-${formattedDay}" />
							<c:if test="${entry.day == 1}">
								<li>
									<div>
										<span class="year">${entry.year}</span> <br> <span class="month">${entry.month}월</span>
									</div>
								</li>
							</c:if>

							<li id="date-${formattedDate}" class="selectable-date"><a href="javascript:void(0)" onclick="viewSelectedDate('${formattedDate}', this)"> <c:choose>
										<c:when test="${holidays.contains(formattedDate)}">
											<span class="holiday" data-date-value="${formattedDate}">${entry.dayOfWeek}</span>
											<span class="holiday" data-date-value="${formattedDate}">${entry.day} </span>
										</c:when>
										<c:when test="${entry.dayOfWeek == '일'}">
											<span class="holiday" data-date-value="${formattedDate}">${entry.dayOfWeek}</span>
											<span class="holiday" data-date-value="${formattedDate}">${entry.day} </span>
										</c:when>
										<c:when test="${entry.dayOfWeek == '토'}">
											<span class="satur" data-date-value="${formattedDate}">${entry.dayOfWeek}</span>
											<span class="satur" data-date-value="${formattedDate}">${entry.day} </span>
										</c:when>
										<c:otherwise>
									<span data-date-value="${formattedDate}">${entry.dayOfWeek}</span>
									<span data-date-value="${formattedDate}">${entry.day}</span>
								</c:otherwise>
									</c:choose>
							</a></li>
							<br>

						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="list--box">
				<div class="top--title--inner">
					<h3>영화</h3>
				</div>
				<div class="filter--box">
					<button type="button" id="sortByKorean" value="korean" name="filter--btn">가나다순</button>
					<button type="button" id="sortByAge" value="age" name="filter--btn">시청등급순</button>
				</div>
				<div class="scroll--list">
					<ul id="movie-list">
						<c:forEach var="movie" items="${movieList}">
							<li><a href="javascript:void(0)" data-movie-id="${movie.movieId}" onclick="handleAvailableMovieClick(this)"><c:choose>
										<c:when test="${movie.watchGradeNm eq '전체관람가'}">
											<span class="grade-all">ALL</span>
											<span data-id="${movie.movieId}">${movie.title}</span>
										</c:when>
										<c:when test="${movie.watchGradeNm eq '12세이상관람가'}">
											<span class="grade-12">12</span>
											<span data-id="${movie.movieId}">${movie.title}</span>
										</c:when>
										<c:when test="${movie.watchGradeNm eq '15세이상관람가'}">
											<span class="grade-15">15 </span>
											<span data-id="${movie.movieId}">${movie.title}</span>
										</c:when>
										<c:when test="${movie.watchGradeNm eq '19세이상관람가'}">
											<span class="grade-19">19</span>
											<span data-id="${movie.movieId}">${movie.title}</span>
										</c:when>
										<c:otherwise>
											<span class="grade-default" data-id="${movie.movieId}">${movie.title}</span>
										</c:otherwise>
									</c:choose></a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="list--box">
				<div class="top--title--inner">
					<h3>극장</h3>
				</div>
				<div class="region--box">
					<div class="region--list">
						<ul id="region-list">
							<c:forEach var="region" items="${regionList}">
								<li class="region--name--box"><a href="javascript:void(0)" onclick="applyRegionFilter('${region.id}')">${region.name}</a></li>
							</c:forEach>
						</ul>
					</div>
					<div class="scroll--list">
						<ul id="sub--region--list">
							<c:forEach var="subRegion" items="${subRegionList}">
								<li>${subRegion.name}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="list--box">
				<div class="top--title--inner">
					<h3>시간</h3>
				</div>
				<div class="movie--list">
					<ul id="movie-list">
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="choice--movie--box">영화 이미지 자리, 영화 타이틀 자리 , 극장선택 , >좌석 선택> 결제 좌석선택 버튼</div>
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

function applyRegionFilter(regionId){
    fetch(`http://localhost:8080/reservation/regions?regionId=`+ regionId)
        .then(response => {
            if (!response.ok){
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('success:',data);
            updateSubRegionList(data);
        })
        .catch(error =>{
            alert('An error occurred while fetching the movies.');
            console.error('Fetch error:', error);
        });
}

function updateSubRegionList(subRegions) {
    const listElement = document.getElementById('sub--region--list');

    // 기존 리스트 아이템 제거
    listElement.innerHTML = '';

    // 새로운 리스트 아이템 추가
    subRegions.forEach(subRegion => {
    	const subRegionItem = document.createElement('li');
    	subRegionItem.innerHTML = '<span>' + subRegion.name + '</span>';
    	listElement.appendChild(subRegionItem);
    });
}

function viewSelectedDate(selectedDate, element) {
    // 모든 날짜 항목에서 선택 상태를 제거
    document.querySelectorAll('.selectable-date').forEach(item => {
        item.style.backgroundColor = ''; // 기본 배경색으로 복구
        item.classList.remove('selected'); // 선택 상태 클래스 제거
    });
    
 	// 클릭된 요소의 자식 span에서 data-date-value 가져오기
    const spanElement = element.querySelector('span');
    const date = spanElement ? spanElement.getAttribute('data-date-value') : null;
    const textValue = spanElement ? spanElement.textContent : null; // 텍스트 값 가져오기
    console.log('Selected Date:', date);
    console.log('Span Text Value:', textValue);
    // 선택된 날짜 항목 강조
    const selectedItem = document.getElementById('date-' + selectedDate);
    if (selectedItem) {
        selectedItem.style.backgroundColor = 'pink'; // 강조 색상
        selectedItem.classList.add('selected');
    }
    
    fetch(`http://localhost:8080/reservation/date?date=` + selectedDate)
        .then(response =>{
            if (!response.ok){
                throw new Error('연결을 실패했습니다.')
            }
            return response.json();
        })
        .then(data => {
            console.log('success',data);
            updateMovieListByDate(data)
        })
        .catch(error =>{
            alert('패치중 문제가 발생햇습니다.')
            console.error('Fetch error:', error);
        })
}

//클릭 이벤트 핸들러 함수 정의
function handleAvailableMovieClick(element) {
const movieId = element.getAttribute('data-movie-id');
    console.log(movieId)
    alert(`선택한 영화의 ID:` + movieId);
}

function handleUnavailableMovieClick() {
    const message = '선택한 영화에 원하시는 상영스케줄이 없습니다\n계속하시겠습니까? (선택한 날짜 및 극장이 해제됩니다.)';
    const confirmReset = confirm(message);
    if (confirmReset) {
        location.reload(); // 페이지 새로고침
    }
}

//영화 리스트를 업데이트하는 함수
function updateMovieListByDate(movieList) {
    const movieItems = document.querySelectorAll('#movie-list li'); // 모든 영화 요소 선택
    const movieListIds = movieList.map(movie => String(movie.movieId)); // 받아온 영화 리스트에서 ID 추출
    const movieId = item.querySelector('span[data-id]').getAttribute('data-id');

    movieItems.forEach(item => {

        // 기존 클릭 이벤트 리스너 제거
        item.removeEventListener('click', handleAvailableMovieClick);
        item.removeEventListener('click', handleUnavailableMovieClick);

        if (movieListIds.includes(movieId)) {
            item.style.backgroundColor = ''; // 영화가 리스트에 있으면 기본 배경색으로
            item.style.opacity = '1';
            item.addEventListener('click', handleAvailableMovieClick);
        } else {
            item.style.backgroundColor = ''; // 영화가 리스트에 없으면 기본 배경색으로
            item.style.opacity = '0.1'; // 흐리게 보이도록
            item.addEventListener('click', handleUnavailableMovieClick);
        }
    });
    
    fetch(`http://localhost:8080/reservation/selectedMovie?movie=`+movieId)
    .then(response =>{
        if (!response.ok){
            throw new Error('연결을 실패했습니다.')
        }
        return response.json();
    })
    .then(data =>{
        console.log('success',data);
    })
    .catch(error =>{
        alert('패치중 문제가 발생')
        console.error('Fetch error:', error);
    })

    // 영화 리스트가 비어있을 때의 처리
    if (movieList.length === 0) {
        alert('선택한 날짜에는 영화가 없습니다.');
    }
    
    
}

</script>
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
	</body>
	</html>