<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<link href="/css/reservation.css" rel="stylesheet">
<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">

	<div id="in--wrap">
		<div class="choice--box" id="choice--movie">
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

							<li id="date-${formattedDate}" class="selectable-date"><a href="javascript:void(0)" onclick="onDateSelect('${formattedDate}', this)"> <c:choose>
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
							<li><a href="javascript:void(0)" data-movie-id="${movie.movieId}" onclick="onMovieSelect(${movie.movieId})"><c:choose>
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
								<li class="region--name--box"><a href="javascript:void(0)" onclick="onRegionSelect('${region.id}')">${region.name}</a></li>
							</c:forEach>
						</ul>
					</div>
					<div class="scroll--list">
						<ul id="sub--region--list">
							<c:forEach var="subRegion" items="${subRegionList}">
								<li id="subregion-${subRegion.id}"><a href="javascript:void(0)" onclick="onTheaterSelect('${subRegion.name}', '${subRegion.id}')"> ${subRegion.name} </a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="list--box">
				<div class="top--title--inner">
					<h3>시간</h3>
				</div>
				<div class="time--list">
					<span class="title"> <span class="floor"></span> <span class="seatcount"></span>
					</span>
					<ul>
						<li><a> <span class=time> <span></span>
							</span> <span class="count"></span>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="movie-container hidden" id="movie-container">
			<div class="movie-container">
				<label for="movie">유형</label> <select id="movie">
					<option value="12000">일반</option>
					<option value="8000">청소년</option>
				</select>
			</div>
			<ul class="showcase">
				<li>
					<div class="seat"></div> <small>리클라이너</small>
				</li>
				<li>
					<div class="seat selected"></div> <small>선택</small>
				</li>
				<li>
					<div class="seat occupied"></div> <small>예매완료</small>
				</li>
				<li>
					<div class="seat couple"></div> <small>커플석</small>
				</li>
				<li>
					<div class="seat handicap"></div> <small>장애인석</small>
				</li>
			</ul>

			<div class="container">
				<div class="row">
					<div class="row-label">A</div>
					<div id="1" class="seat" data-seat="A1"></div>
					<div id="2" class="seat" data-seat="A2"></div>
					<div id="3" class="seat" data-seat="A3"></div>
					<div id="4" class="seat" data-seat="A4"></div>
					<div id="5" class="seat" data-seat="A5"></div>
					<div id="6" class="seat" data-seat="A6"></div>
					<div id="7" class="seat" data-seat="A7"></div>
					<div id="8" class="seat" data-seat="A8"></div>
					<div id="9" class="seat" data-seat="A9"></div>
					<div id="10" class="seat" data-seat="A10"></div>
					<div id="11" class="seat" data-seat="A11"></div>
					<div id="12" class="seat" data-seat="A12"></div>
					<div id="13" class="seat" data-seat="A13"></div>
				</div>

				<div class="row">
					<div class="row-label">B</div>
					<div id="14" class="seat" data-seat="B1"></div>
					<div id="15" class="seat" data-seat="B2"></div>
					<div id="16" class="seat" data-seat="B3"></div>
					<div id="17" class="seat" data-seat="B4"></div>
					<div id="18" class="seat" data-seat="B5"></div>
					<div id="19" class="seat" data-seat="B6"></div>
					<div id="20" class="seat" data-seat="B7"></div>
					<div id="21" class="seat" data-seat="B8"></div>
					<div id="22" class="seat" data-seat="B9"></div>
					<div id="23" class="seat" data-seat="B10"></div>
					<div id="24" class="seat" data-seat="B11"></div>
					<div id="25" class="seat" data-seat="B12"></div>
					<div id="26" class="seat" data-seat="B13"></div>
				</div>

				<div class="row">
					<div class="row-label">C</div>
					<div id="27" class="seat" data-seat="C1"></div>
					<div id="28" class="seat" data-seat="C2"></div>
					<div id="29" class="seat" data-seat="C3"></div>
					<div id="30" class="seat" data-seat="C4"></div>
					<div id="31" class="seat" data-seat="C5"></div>
					<div id="32" class="seat" data-seat="C6"></div>
					<div id="33" class="seat" data-seat="C7"></div>
					<div id="34" class="seat" data-seat="C8"></div>
					<div id="35" class="seat" data-seat="C9"></div>
					<div id="36" class="seat" data-seat="C10"></div>
					<div id="37" class="seat" data-seat="C11"></div>
					<div id="38" class="seat" data-seat="C12"></div>
					<div id="39" class="seat" data-seat="C13"></div>
				</div>

				<div class="row">
					<div class="row-label">D</div>
					<div id="40" class="seat" data-seat="D1"></div>
					<div id="41" class="seat" data-seat="D2"></div>
					<div id="42" class="seat" data-seat="D3"></div>
					<div id="43" class="seat" data-seat="D4"></div>
					<div id="44" class="seat" data-seat="D5"></div>
					<div id="45" class="seat" data-seat="D6"></div>
					<div id="46" class="seat" data-seat="D7"></div>
					<div id="47" class="seat" data-seat="D8"></div>
					<div id="48" class="seat" data-seat="D9"></div>
					<div id="49" class="seat" data-seat="D10"></div>
					<div id="50" class="seat" data-seat="D11"></div>
					<div id="51" class="seat" data-seat="D12"></div>
					<div id="52" class="seat" data-seat="D13"></div>
				</div>

				<div class="row">
					<div class="row-label">E</div>
					<div id="53" class="seat" data-seat="E1"></div>
					<div id="54" class="seat" data-seat="E2"></div>
					<div id="55" class="seat" data-seat="E3"></div>
					<div id="56" class="seat" data-seat="E4"></div>
					<div id="57" class="seat" data-seat="E5"></div>
					<div id="58" class="seat" data-seat="E6"></div>
					<div id="59" class="seat" data-seat="E7"></div>
					<div id="60" class="seat" data-seat="E8"></div>
					<div id="61" class="seat" data-seat="E9"></div>
					<div id="62" class="seat" data-seat="E10"></div>
					<div id="63" class="seat" data-seat="E11"></div>
					<div id="64" class="seat" data-seat="E12"></div>
					<div id="65" class="seat" data-seat="E13"></div>
				</div>

				<div class="row">
					<div class="row-label">F</div>
					<div id="66" class="seat couple" data-seat="F1"></div>
					<div id="67" class="seat couple" data-seat="F2"></div>
					<div id="68" class="seat couple" data-seat="F3"></div>
					<div id="69" class="seat couple" data-seat="F4"></div>
					<div class="seat empty-seat"></div>
					<div class="seat empty-seat"></div>
					<div class="seat empty-seat"></div>
					<div class="seat empty-seat"></div>
					<div class="seat empty-seat"></div>
					<div id="70" class="seat couple" data-seat="F10"></div>
					<div id="71" class="seat couple" data-seat="F11"></div>
					<div class="seat empty-seat"></div>
					<div id="72" class="seat handicap" data-seat="F13"></div>
				</div>
			</div>
			<p class="text">
				선택한 좌석 수 <span id="count">0</span> <span id="total">0</span>원
			</p>
		</div>
	</div>
</div>
<div class="choice--movie--box">
	<div class="img--title--box">
		<span class="movie--poster"> <img alt="" src="">
		</span> <span class="movie--title"> <a href=""></a>
		</span>
	</div>
	<div class="movie--detail--box">
		<div>
			<span>극장</span> <span class="theater"> <a href=""></a>
			</span>
		</div>
		<div>
			<span>일시</span> <span class="choosen--date"></span>
		</div>
		<div>
			<span>상영관</span> <span class="room"></span>
		</div>
		<span>인원</span> <a href="javascript:void(0)" onclick="viewSeats()" id="seat--selection--btn" class="btn--right">좌석선택!!!!!!!!!!!!!!!!!</a>
	</div>

	<div class="movie--seat--box">
		<div>
			<span>좌석명</span> <span class="seat--type"></span>
		</div>
		<div>
			<span>좌석번호</span> <span class="seat--num"></span>
		</div>
	</div>
</div>
<script src="/js/seat.js"></script>
<script>
// 처음 웹이 로드가 되었을 때 극장 카운트 하는 역할 
window.onload = function() {
    fetchTheaters();
    fetchSubRegions();
};
let selectedMovieId = null;
let selectedDate1 = null;
let selectedTheater = null;
let selectedTheaterId = null;
let selectedRegionId = null;
let selectedTime = null;
let showTimeId = null;
const principal = "${principal.id}";


// 화면이 처음 띄워졌을 시 극장 대분류 지역 카운트 날리는 역할
function fetchTheaters(){
	fetch(`http://localhost:8080/reservation/firstTheater`)
	 .then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
     .then(data => {
          // 받아온 데이터를 기반으로 지역별 극장 수 업데이트
         updateTheaterCountUI(data); // UI 업데이트 함수 호출
     })
     .catch(error => {
         console.error('Error fetching theater count by region:', error);
     });
}

//날짜만 선택시 영화상영 시간에 맞는 서브지역 찾는 패치
function fetchSubRegions() {
    fetch(`http://localhost:8080/reservation/firstSubRegion`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Fetched sub-regions:', data);
            updateSubRegionOpacity(data);
        })
        .catch(error => {
            console.error('Error fetching sub-regions:', error);
        });
}

// 영화 선택시
function onMovieSelect(movieId) {
	// movieId 얻음.
	selectedMovieId = movieId;
	fetchMovieDetails(movieId);
	// 무비만 클릭했을 경우
	if(selectedMovieId && !selectedDate1 && !selectedTheaterId){
	updateTheatersAndDates();
	fetchSubRegionsByMovie(movieId);
	}
	
	// 날짜랑 무비 클릭했을 경우
	if(selectedMovieId && selectedDate1 && !selectedTheaterId){
	fetchTheaterCountByDateAndMovie(selectedMovieId, selectedDate1);
	fetchSubRegionsByMovieAndDate(selectedMovieId, selectedDate1);
	fetchTheatersAndDateByMovie(movieId);
	}
	
	// 셋다 클릭 했을 경우
	if(selectedMovieId && selectedTheaterId && selectedDate1) {
		console.log("여기얌!!");
		fetchViewTimeList(selectedMovieId,selectedTheaterId,selectedDate1);
	}
}

// 날짜 선택시
function onDateSelect(selectedDate, element){
	selectedDate1 = selectedDate;
	viewSelectedDate(selectedDate, element)
	// 날짜만 선택시
	if(selectedDate1 && !selectedMovieId && !selectedTheaterId){
		fetchTheaterCountByDate(selectedDate1);
		fetchSubRegionsByDate(selectedDate1);
	}
	
	// 날짜와 영화 선택시
	if(selectedMovieId && selectedDate1 && !selectedTheaterId){
		fetchTheaterCountByDateAndMovie(selectedMovieId, selectedDate1);
		fetchSubRegionsByMovieAndDate(selectedMovieId, selectedDate1);
		fetchTheatersAndDateByMovie(selectedMovieId);
		}
	
	
	if(selectedMovieId && selectedTheaterId && selectedDate1) {
		fetchViewTimeList(selectedMovieId,selectedTheaterId,selectedDate1);
	}
	
}

// 소분류 극장 선택시
function onTheaterSelect(theaterName, subregionId){
	console.log(selectedMovieId);
	console.log(selectedTheaterId);
	console.log(selectedDate1);
	checkMovie(theaterName, subregionId);
	
	if(selectedMovieId && selectedTheaterId && selectedDate1) {
		console.log("여기 안들어오냐?");
		fetchViewTimeList(selectedMovieId,selectedTheaterId,selectedDate1);
	}
	
}

// 대분류 극장 선택시
function onRegionSelect(regionId) {
	 selectedRegionId = regionId;
	 applyRegionFilter(regionId);
}

// 영화 선택시 극장이랑 날짜 업데이트하기 
function updateTheatersAndDates(){
	if(selectedMovieId){
		// 영화만 선택했을 경우
		fetchTheatersAndDateByMovie(selectedMovieId);
		fetchTheaterCountByMovie(selectedMovieId);
	} 
	
	
}

// 영화 클릭시 그 영화에 맞는 날짜와 극장 데이터 가져오기!
function fetchTheatersAndDateByMovie(movieId){
	fetch(`http://localhost:8080/reservation/firstMovie?movieId=` + movieId)
	.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
    .then(data => {
        console.log(data);  // 받아온 극장 정보를 콘솔에 출력
        // 여기에 받은 데이터를 처리하는 로직 추가
        updateDateOpacity(data);
    })
    .catch(error => {
        console.error('Error fetching theaters:', error);
    });
}


//상영 날짜 목록을 기준으로 날짜 요소의 투명도를 조정하는 함수
function updateDateOpacity(data) {
    // 데이터에서 상영 날짜만 추출하여 Set으로 만듦
    const activeDates = new Set(data.map(item => item.showDate));

    // 모든 날짜 요소를 순회하며 스타일 업데이트
    document.querySelectorAll('.selectable-date').forEach(dateElement => {
        // 날짜 요소의 data-date-value 속성에서 날짜 값을 가져옴
        const dateValue = dateElement.querySelector('span').getAttribute('data-date-value');

        if (activeDates.has(dateValue)) {
            // 상영 날짜인 경우 강조 처리
            dateElement.style.opacity = '1';  // 투명도 초기화
        } else {
            // 상영 날짜가 아닌 경우 투명도 낮추기
            dateElement.style.opacity = '0.1';  // 비상영 날짜는 투명도 낮추기
        }
    });
}


//영화의 세부 정보를 가져오는 함수 밑에 영화 세부정보 bar에 타이틀이랑 이미지 넣는 함수
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
				console.log('Movie Details:', data);
				if (shouldUpdate) {
					updateMovieDetail(data);
				}
		})
		.catch(error => {
			console.error('Error fetching movie details:', error);
		});
}

//영화의 세부 정보를 가져오는 함수 밑에 영화 세부정보 bar에 타이틀이랑 이미지 넣는 함수
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
		titleLink.href = `/movie/detail?title=` + movie.title; // 링크가 클릭 가능한 상태로 설정
	}
}

// 패치 영화만 클릭했을시 카운트 하기 위해 패치 날림 
function fetchTheaterCountByMovie(movieId){
	fetch(`http://localhost:8080/reservation/regionCount?movieId=` + movieId)
	 .then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
     .then(data => {
          // 받아온 데이터를 기반으로 지역별 극장 수 업데이트
         updateTheaterCountUI(data); // UI 업데이트 함수 호출
     })
     .catch(error => {
         console.error('Error fetching theater count by region:', error);
     });
}

// 패치 날짜만 클릭했을시 카운트 하기 위해 패치 날림 
function fetchTheaterCountByDate(date){
	fetch(`http://localhost:8080/reservation/dateCount?date=` + date)
	 .then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
     .then(data => {
          // 받아온 데이터를 기반으로 지역별 극장 수 업데이트
         updateTheaterCountUI(data); // UI 업데이트 함수 호출
     })
     .catch(error => {
         console.error('Error fetching theater count by region:', error);
     });
}

// 패치 날짜 선택 후 영화 클릭했을시 카운트 하기 위해 패치 날림 
function fetchTheaterCountByDateAndMovie(movieId, date){
	fetch(`http://localhost:8080/reservation/regionCountByDateAndMovie?movieId=` + movieId + `&date=` + date)
	 .then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
     .then(data => {
          // 받아온 데이터를 기반으로 지역별 극장 수 업데이트
         updateTheaterCountUI(data); // UI 업데이트 함수 호출
     })
     .catch(error => {
         console.error('Error fetching theater count by region:', error);
     });
}

//지역 카운트 업데이트 해주기
function updateTheaterCountUI(regionData) {
    // 모든 지역의 극장 카운트를 초기화
    document.querySelectorAll('.region--name--box').forEach(function(li) {
        var anchor = li.querySelector('a'); // <a> 태그를 선택
        if (anchor) {
            var regionName = anchor.textContent.split(' (')[0].trim(); // 기존 텍스트에서 이름만 추출
            anchor.innerHTML = regionName + ' (0)'; // 기본적으로 (0)으로 초기화
        }
    });

    // 데이터가 빈 배열인지 확인
    if (!regionData || regionData.length === 0) {
        return; // 데이터가 없으면 함수 종료
    }

    // 데이터에 따라 지역별 극장 카운트를 업데이트
    regionData.forEach(function(region) {
        var regionName = region.regionName; // 데이터에서 지역 이름 추출
        var theaterCount = region.theaterCount; // 데이터에서 극장 수 추출

        document.querySelectorAll('.region--name--box').forEach(function(li) {
            var anchor = li.querySelector('a');
            if (anchor) {
                // <a> 태그의 텍스트에서 지역 이름을 확인
                var textContent = anchor.textContent.split(' (')[0].trim();
                if (textContent === regionName) {
                    // 극장 수를 제대로 반영
                    anchor.innerHTML = regionName + ' (' + theaterCount + ')';
                }
            }
        });
    });
}

// 영화만 선택시 영화상영 시간에 맞는 서브지역 찾는 패치
function fetchSubRegionsByMovie(movieId) {
    fetch(`http://localhost:8080/reservation/subRegionsByMovie?movieId=` + movieId)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Fetched sub-regions:', data);
            updateSubRegionOpacity(data);
        })
        .catch(error => {
            console.error('Error fetching sub-regions:', error);
        });
}

// 날짜만 선택시 영화상영 시간에 맞는 서브지역 찾는 패치
function fetchSubRegionsByDate(date) {
    fetch(`http://localhost:8080/reservation/subRegionsByDate?date=` + date)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Fetched sub-regions:', data);
            updateSubRegionOpacity(data);
        })
        .catch(error => {
            console.error('Error fetching sub-regions:', error);
        });
}

// 날짜만 선택시 영화상영 시간에 맞는 서브지역 찾는 패치
function fetchFirstOpacity() {
    fetch(`http://localhost:8080/reservation/firstOpacity`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Fetched sub-regions:', data);
            updateSubRegionOpacity(data);
        })
        .catch(error => {
            console.error('Error fetching sub-regions:', error);
        });
}

// 날짜와 영화만 선택시 영화상영 시간에 맞는 서브지역 찾는 패치
function fetchSubRegionsByMovieAndDate(movieId, date) {
    fetch(`http://localhost:8080/reservation/subRegionsByMovieAndDate?movieId=` + movieId +`&date=` + date)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Fetched sub-regions:', data);
            updateSubRegionOpacity(data);
        })
        .catch(error => {
            console.error('Error fetching sub-regions:', error);
        });
}

function updateSubRegionOpacity(subRegionData) {
    console.log('Sub-region data:', subRegionData);
    
    // 모든 서브 지역의 투명도를 낮추기
    document.querySelectorAll('#sub--region--list li').forEach(function(li) {
        li.style.opacity = '0.3'; // 비활성화 상태의 투명도
    });

    // 상영 일정이 있는 서브 지역의 투명도를 높이기
    subRegionData.forEach(function(subRegion) {
        const subRegionId = subRegion.id;
        
        console.log('Processing subRegion with ID:', subRegionId);

        if (subRegionId !== null && subRegionId !== undefined) {
            const element = document.querySelector('#subregion-' + subRegionId);

            console.log('Checking element with ID: #subregion-' + subRegionId, element);

            if (element) {
                element.style.opacity = '1'; // 활성화 상태의 투명도
            } else {
                console.warn('Element with ID #subregion-' + subRegionId + ' not found.');
            }
        } else {
            console.warn('Invalid subRegion ID: ' + subRegionId);
        }
    });
}

function checkMovie(theaterName, subregionId) {
	console.log('Clicked sub-region ID:', subregionId);
	console.log('Clicked sub-region Name:', theaterName);
	theaterCheck = true;
	selectedTheater = theaterName;
	selectedTheaterId = subregionId;
		const theaterElement = document.querySelector('.movie--detail--box .theater');
		if (theaterElement) {
			theaterElement.textContent = theaterName; // subRegion.name을 theater에 표시
		}
}

function fetchViewTimeList(selectedMovieId,selectedTheaterId,selectedDate1,subregionId){
	fetch(`http://localhost:8080/reservation/timeList?date=` + selectedDate1 + `&movieId=` + selectedMovieId + `&subregionId=` + selectedTheaterId)
	.then(response => {
		if (!response.ok) {
			throw new Error('Network response was not ok');
		}
		return response.json();
	})
	.then(data => {
		console.log('들어온당')
		updateTimeList(data);
	})
}

//영화와 상영 시간 정보를 기반으로 HTML 업데이트
function updateTimeList(data) {
	const timeListContainer = document.querySelector('.time--list');
	let room = null;
	let checkTheater = 0;
	// 기존 시간 리스트 제거
	const existingUl = timeListContainer.querySelector('ul');
	if (existingUl) {
		existingUl.remove();
	}

	// 데이터가 없으면 빈 리스트를 표시
	if (data.length === 0) {
		alert('상영 일정이 없습니다!!');
		checkTheater = 1;
	}

	// 새로운 시간 리스트 생성
	const ul = document.createElement('ul');

	// 데이터의 첫 항목에서 floor와 seatcount 정보를 가져오기
	if (data.length > 0) {
		const firstItem = data[0];
		console.log('First item data:', firstItem);
		const titleSpan = timeListContainer.querySelector('.title');
		const floorSpan = titleSpan.querySelector('.floor');
		const seatcountSpan = titleSpan.querySelector('.seatcount');

		floorSpan.textContent = firstItem.name; // 1관 설정
		room = firstItem.name
		seatcountSpan.textContent = `총(` + firstItem.capacity + `)`; // 총 좌석 수 설정
	}

	// 시간과 좌석 수 항목 추가
	data.forEach(item => {
		const li = document.createElement('li');
		const a = document.createElement('a');

		// a 태그에 href와 onclick 속성 추가
		a.href = "javascript:void(0)";


		const timeSpan = document.createElement('span');
		timeSpan.className = 'time';
		const timeInnerSpan = document.createElement('span');

		// 'showTime'이 'HH:MM:SS' 형식이므로 'HH:MM' 형식으로 변환
		const timeString = item.showTime; // '20:00:00'
		const timeParts = timeString.split(':'); // ['20', '00', '00']
		timeInnerSpan.textContent = timeParts[0] + `:` + timeParts[1]; // '20:00'

		showTimeId = item.showTimeId;

		timeSpan.appendChild(timeInnerSpan);

		const countSpan = document.createElement('span');
		countSpan.className = 'count';
		countSpan.textContent = item.capacity + `석`; // 좌석 수

		a.onclick = function() {
			const roomElement = document.querySelector('.movie--detail--box .room');
			if (roomElement) {
				roomElement.textContent = room; // 상영관 정보를 업데이트
			}
			const choosenDateElement = document.querySelector('.choosen--date');
			if (choosenDateElement) {
				// 기존 내용에 새로운 시간 추가
				choosenDateElement.textContent = selectedDate1 + ` ` + timeParts[0] + `:` + timeParts[1]; // 기존 내용 뒤에 새로운 날짜를 추가
				selectedTime = timeParts[0] + `:` + timeParts[1];
			}
		};


		a.appendChild(timeSpan);
		a.appendChild(countSpan);

		li.appendChild(a);
		ul.appendChild(li);
	});

	timeListContainer.appendChild(ul);
}

function applyRegionFilter(regionId) {
	fetch(`http://localhost:8080/reservation/regions?regionId=` + regionId)
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(data => {
				console.log('success:', data);
				updateSubRegionList(data);
		})
		.catch(error => {
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
        subRegionItem.id = 'subregion-' + subRegion.id; // ID 설정
        // <a> 태그 생성
        const link = document.createElement('a');
        link.href = 'javascript:void(0)';
        link.textContent = subRegion.name;

        // 필요에 따라 onclick 이벤트 핸들러 추가
        link.onclick = function() {
            console.log('Clicked sub-region:', subRegion.name);
            selectedTheaterId = subRegion.id;
            if(selectedMovieId && selectedTheaterId && selectedDate1) {
        		console.log("여기 안들어오냐?");
        		fetchViewTimeList(selectedMovieId,selectedTheaterId,selectedDate1);
        	}
            const theaterElement = document.querySelector('.movie--detail--box .theater');
            if (theaterElement) {
                theaterElement.textContent = subRegion.name; // subRegion.name을 theater에 표시
            }
        };

        // <li>에 <a> 태그 추가
        subRegionItem.appendChild(link);

        // <ul>에 <li> 추가
        listElement.appendChild(subRegionItem);
    });

    // 선택된 영화가 있는 경우 서브 지역의 투명도 조정
    if (selectedMovieId) {
    	
        fetchSubRegionsByMovie(selectedMovieId);
        
    	}else if(selectedDate1){
    		
    		fetchSubRegionsByDate(selectedDate1);
    		
    	} else if(!selectedDate1 && !selectedMovieId && !selectedTheaterId){
    		fetchFirstOpacity();
    	} 
    	else {
        // 선택된 영화가 없을 때 모든 서브 지역 표시
        document.querySelectorAll('#sub--region--list li').forEach(function(li) {
            li.style.opacity = '1'; // 기본 투명도
        });
    }
}

//서브 지역의 투명도를 조절하는 함수
function updateSubRegionTransparency(relevantSubRegions) {
    const listElement = document.getElementById('sub--region--list');
    const subRegionItems = listElement.querySelectorAll('li');

    subRegionItems.forEach(item => {
        const link = item.querySelector('a');
        const subRegionId = parseInt(link.dataset.id, 10);
        
        // 현재 서브 지역이 상영 시간에 포함되는지 확인
        const isRelevant = relevantSubRegions.some(subRegion => subRegion.id === subRegionId);

        if (isRelevant) {
            item.style.opacity = '1'; // 상영 시간에 맞는 서브 지역의 투명도 높임
        } else {
            item.style.opacity = '0.3'; // 상영 시간에 맞지 않는 서브 지역의 투명도 낮춤
        }
    });
}

function viewSelectedDate(selectedDate, element) {
	selectedDate1 = selectedDate;
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
	step = 1;
	// 선택된 날짜 항목 강조
	const selectedItem = document.getElementById('date-' + selectedDate);
	if (selectedItem) {
		selectedItem.style.backgroundColor = 'pink'; // 강조 색상
		selectedItem.classList.add('selected');
	}

	fetch(`http://localhost:8080/reservation/date?date=` + selectedDate)
		.then(response => {
			if (!response.ok) {
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
		.catch(error => {
			alert('패치중 문제가 발생햇습니다.')
			console.error('Fetch error:', error);
		})
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

//클릭 이벤트 핸들러 함수 정의
function handleAvailableMovieClick(element) {
	const movieId = element.getAttribute('data-movie-id');
	selectedMovieId = movieId;
	console.log(movieId)
	fetchMovieDetails(movieId);
}

// 투명도가 낮아진 무비를 클릭했을 경우
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



function viewSeats(){
	if(selectedDate1 == null){
		alert('날짜를 선택해주세요')
	} else if(selectedMovieId == null){
		alert('영화를 선택해 주세요')
	} else if(selectedTheaterId == null){
		alert('극장을 선택해 주세요')
	} else if(selectedTime == null){
		alert('시간을 선택해 주세요')
	} else {
	// 기존 콘텐츠 숨기기
	 document.getElementById('choice--movie').style.display = 'none';
	// 새 콘텐츠 보이기
     document.getElementById('movie-container').style.display = 'block';
	
	
	fetch(`http://localhost:8080/reservation/checkDupliSeat?showTimeId=` + showTimeId)
		.then(response => {
            if (!response.ok){
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
       .then(data => {
    	   updateSeatClasses(data);
        })
	
 	 // 버튼 요소 선택
     const button = document.getElementById('seat--selection--btn');

     // 버튼의 텍스트를 '결제선택'으로 변경
     button.textContent = '결제선택';

     // 인라인 이벤트 리스너 제거
     button.onclick = null; // 기존의 onclick 핸들러 제거

     // 새 이벤트 리스너 추가
     button.addEventListener('click', function() {
         console.log(selectedSeatsId);
         console.log(selectedTicketCount);
         
         if (!principal || principal === "null") {
        	 if (confirm("로그인이 필요한 서비스입니다. \n로그인 페이지로 이동하시겠습니까?")) {
        	        window.location.href = "/user/signIn"; // 로그인 페이지로 이동
        	    } else {
        	        // 사용자가 취소(No)를 선택한 경우 처리
        	        console.log("사용자가 로그인 페이지 이동을 취소했습니다.");
        	    }
        	} else if(selectedTicketCount == null || selectedTicketCount == 0){
        		alert("좌석을 선택해 주세요")
        	} else {
       
         
         fetch(`http://localhost:8080/reservation/booking`,{
        	 method: 'POST',
        	 headers: {
        		 'Content-Type': 'application/json',
        	 },
        	 body: JSON.stringify({
        		 userId: principal,
        		 showTimeId: showTimeId,
        		 quantity: selectedTicketCount,
        		 selectedSeatsId: selectedSeatsId // << 얘 배열입니다.
        	 })
         })
         .then(response => {
    if (response.ok) {
        return response.json(); // 성공 시 JSON 데이터 반환
    } else if (response.status === 409) {
        // 중복 좌석일 경우
        return response.json().then(data => {
            alert(data.message); // 서버에서 전달된 메시지 출력
            throw new Error('Seat conflict');
        });
    } else if (response.status === 400) {
        // 예매 실패 시
        return response.json().then(data => {
            alert(data.message); // 실패 메시지 출력
            throw new Error('Booking failed');
        });
    } else {
        throw new Error('Network response was not ok');
    }
			})
			.then(data => {
				alert(data.message); // "예매 성공" 메시지
				window.location.href = `/date/tickets?quantity=` + selectedTicketCount;
			})
			.catch(error => {
				console.error('There has been a problem with your fetch operation:', error);
			});
		}
	});
}
}

function updateSeatClasses(occupiedSeats) {
    seats.forEach(seat => {
        const seatId = seat.id;

        if (occupiedSeats.includes(parseInt(seatId))) {
            // 'selected' 클래스를 제거
            seat.classList.remove('selected');
            // 'occupied' 클래스를 추가
            seat.classList.add('occupied');
        } else {
            // 'occupiedSeats'에 포함되지 않는 좌석은 기본 상태로 유지
            if (seat.classList.contains('occupied')) {
                seat.classList.remove('occupied');
            }
        }
    });
}



</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</body>
</html>