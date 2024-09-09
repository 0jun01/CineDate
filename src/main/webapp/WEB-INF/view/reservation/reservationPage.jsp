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
								<li id="subregion-${subRegion.id}"><a href="javascript:void(0)" onclick="checkMovie('${subRegion.name}', '${subRegion.id}')"> ${subRegion.name} </a></li>
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
	<div class="choice--movie--box">
		<div class="img--title--box">
			<span class="movie--poster"> <img alt="#" src="">
			</span> <span class="movie--title"> <a></a>
			</span>
		</div>
		<div class="movie--detail--box">
			<span>극장</span><span class="theater"></span><span>일시&nbsp;&nbsp;&nbsp;&nbsp;</span> <span class="choosen--date"></span><span>상영관</span><span>인원</span>
		</div>
	</div>
	<script>
	
let selectedMovieId = null;
let selectedDate1 = null;
let step = 0;

document.addEventListener('DOMContentLoaded', function() {
	
	if (selectedMovieId && selectedDate1) {
        fetchSubRegionShowtimes();
    }
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



function viewSelectedDate(selectedDate, element) {
	selectedDate1 = selectedDate;
    // 모든 날짜 항목에서 선택 상태를 제거
    document.querySelectorAll('.selectable-date').forEach(item => {
        item.style.backgroundColor = ''; // 기본 배경색으로 복구
        item.style.opacity = '1'; // 투명도 초기화
        item.classList.remove('selected'); // 선택 상태 클래스 제거
    });
    
 	// 클릭된 요소의 자식 span에서 data-date-value 가져오기
    const spanElement = element.querySelector('span');
    const date = spanElement ? spanElement.getAttribute('data-date-value') : null;
    const textValue = spanElement ? spanElement.textContent : null; // 텍스트 값 가져오기
    console.log('Selected Date:', date);
    console.log('Span Text Value:', textValue);
    step = 1;
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
        	if (data.length === 0) {
                // 상영 일정이 없는 경우 투명도를 낮추고 팝업을 띄움
                selectedItem.style.opacity = '0.1'; // 투명도 낮춤
                alert('선택한 날짜에 상영 일정이 없습니다.');
            } else {
            	selectedItem.style.backgroundColor = 'pink'; // 강조 색상
                selectedItem.classList.add('selected');
                const chosenDateSpan = document.querySelector('.choosen--date');
                if (chosenDateSpan) {
                    chosenDateSpan.innerHTML = selectedDate;
                }
                updateMovieListByDate(data);
            }
        })
        .catch(error =>{
            alert('패치중 문제가 발생햇습니다.')
            console.error('Fetch error:', error);
        })
}

//클릭 이벤트 핸들러 함수 정의
function handleAvailableMovieClick(element) {
const movieId = element.getAttribute('data-movie-id');
selectedMovieId = movieId;
    console.log(movieId)
    alert(`선택한 영화의 ID:` + movieId);
    fetchMovieDetails(movieId);
}


let shouldUpdate = true; // 초기값을 true로 설정
let movieCheck = true;
//영화의 세부 정보를 가져오는 함수
function fetchMovieDetails(movieId) {
	 shouldUpdate = true;
	
    fetch(`http://localhost:8080/reservation/movieDetail?movieId=` + movieId)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
        	if(step != 1){
        	alert('날짜를 먼저 선택해주세요');
        	} else{
        	movieCheck = false;
            console.log('Movie Details:', data);
            if(shouldUpdate){
            updateMovieDetail(data);
          	  }
            fetchSelectedDateAndMovie();
        	}
        })
        .catch(error => {
            console.error('Error fetching movie details:', error);
        });
}

function fetchSelectedDateAndMovie(){
	fetch(`http://localhost:8080/reservation/theater?movieId=` + selectedMovieId + `&date=` + selectedDate1)
		.then(response => {
			if (!response.ok){
				throw new Error('error');
			}
			return response.json();
		})
		.then(data => {
			console.log('theater : ', data);
			// 데이터에서 지역 목록 추출
            const availableRegions = data.map(item => item.regionName);
            const availableSubRegions = data.map(item => item.subId.toString()); // ID를 문자열로 변환
            document.querySelectorAll('#region-list li').forEach(li => {
                const regionName = li.textContent.trim();
                if (!availableRegions.includes(regionName)) {
                    li.style.opacity = '0.1'; // 투명도를 낮춤
                } else {
                    li.style.opacity = '1'; // 원래 투명도로 설정
                }
            });
            
            // 서브 지역 리스트 항목의 투명도 조정
            document.querySelectorAll('#sub--region--list li').forEach(li => {
            	const subRegionId = li.id.split('-')[1];
                if (!availableSubRegions.includes(subRegionId)) {
                    li.style.opacity = '0.1'; // 투명도를 낮춤
                } else {
                    li.style.opacity = '1'; // 원래 투명도로 설정
                }
            });
		})
		.catch(error => {
            console.error('Error fetching movie details:', error);
        });
}


function updateMovieDetail(movie) {
    // 영화 포스터와 제목 요소를 선택
    const posterImg = document.querySelector('.movie--poster img');
    const titleLink = document.querySelector('.movie--title a');
    
    // 가져온 데이터로 요소 업데이트
    if (posterImg) {
        posterImg.src = `https://image.tmdb.org/t/p/w342` + movie.movieImg;
        posterImg.alt = movie.title; // 영화 제목을 이미지의 alt 속성으로 설정
    }

    if (titleLink) {
        titleLink.textContent = movie.title; // 영화 제목 설정
        titleLink.href = '#'; // 링크가 클릭 가능한 상태로 설정
    }
}

function handleUnavailableMovieClick() {
    const message = '선택한 영화에 원하시는 상영스케줄이 없습니다\n계속하시겠습니까? (선택한 날짜 및 극장이 해제됩니다.)';
    const confirmReset = confirm(message);
    if (confirmReset) {
        location.reload(); // 페이지 새로고침
    } else {
        // 취소를 선택한 경우 영화 제목과 사진 업데이트를 방지
        // 예: 영화 제목과 사진을 기본 상태로 되돌리거나 유지하는 로직
        shouldUpdate = false;
        console.log('취소가 선택되었습니다. 영화 제목과 사진은 그대로 유지됩니다.');
    }
}

//영화 리스트를 업데이트하는 함수
function updateMovieListByDate(movieList) {
    const movieItems = document.querySelectorAll('#movie-list li'); // 모든 영화 요소 선택
    const movieListIds = movieList.map(movie => String(movie.movieId)); // 받아온 영화 리스트에서 ID 추출

    movieItems.forEach(item => {

    const movieId = item.querySelector('span[data-id]').getAttribute('data-id');
        // 기존 클릭 이벤트 리스너 제거
        item.removeEventListener('click', handleAvailableMovieClick);
        item.removeEventListener('click', handleUnavailableMovieClick);

        if (movieListIds.includes(movieId)) {
            item.style.opacity = '1';
            item.addEventListener('click', handleAvailableMovieClick);
        } else {
            item.style.opacity = '0.1'; // 흐리게 보이도록
            item.addEventListener('click', handleUnavailableMovieClick);
        }
    });
}
function fetchSubRegionShowtimes() {
    if (!selectedMovieId || !selectedDate1) {
        alert('선택된 영화나 날짜가 없습니다.');
        return; // 영화 ID나 날짜가 설정되지 않으면 함수 종료
    }

    return fetch(`http://localhost:8080/reservation/subregions?date=` + selectedDate1 + `&movieId=` + selectedMovieId)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .catch(error => {
            alert('An error occurred while fetching the subregions.');
            console.error('Fetch error:', error);
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
        	if(movieCheck){
        		alert('영화를 먼저 선택해주세요')
        	} else{
        	
            console.log('success:',data);
            updateSubRegionList(data);
        	}
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
    	  // <a> 태그 생성
        const link = document.createElement('a');
        link.href = 'javascript:void(0)';
        link.textContent = subRegion.name;

        // 필요에 따라 onclick 이벤트 핸들러 추가
        link.onclick = function() {
            // 클릭 시 동작 정의 (예: 서브지역 필터링 등)
            if(movieCheck){
            	alert ('영화를 먼저 선택해주세요.')
            } else {
             console.log('Clicked sub-region:', subRegion.name);
            // filterSubRegion(subRegion.id);
             const theaterElement = document.querySelector('.movie--detail--box .theater');
             if (theaterElement) {
                 theaterElement.textContent = subRegion.name; // subRegion.name을 theater에 표시
             }

            }
        };

        // <li>에 <a> 태그 추가
        subRegionItem.appendChild(link);

        // <ul>에 <li> 추가
        listElement.appendChild(subRegionItem);
    });
}

function checkMovie(theaterName, subregionId){
	console.log('Clicked sub-region ID:', subregionId);
    console.log('Clicked sub-region Name:', theaterName);
	if(movieCheck){
    	alert ('영화를 먼저 선택해주세요.')
    } else {
    // filterSubRegion(subRegion.id);
     const theaterElement = document.querySelector('.movie--detail--box .theater');
     if (theaterElement) {
         theaterElement.textContent = theaterName; // subRegion.name을 theater에 표시
     }

    }
}
</script>
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
	</body>
	</html>