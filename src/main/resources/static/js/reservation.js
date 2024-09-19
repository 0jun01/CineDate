let selectedMovieId = null;
let selectedDate1 = null;
let selectedTheater = null;
let selectedTime = null;
let step = 0;
let showTimeId = null;
const principal = "${principal.id}";


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

//클릭 이벤트 핸들러 함수 정의
function handleAvailableMovieClick(element) {
	const movieId = element.getAttribute('data-movie-id');
	selectedMovieId = movieId;
	console.log(movieId)
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
			if (step != 1) {
				alert('날짜를 먼저 선택해주세요');
			} else {
				movieCheck = false;
				console.log('Movie Details:', data);
				if (shouldUpdate) {
					updateMovieDetail(data);
				}
				fetchSelectedDateAndMovie();
			}
		})
		.catch(error => {
			console.error('Error fetching movie details:', error);
		});
}

function fetchSelectedDateAndMovie() {
	fetch(`http://localhost:8080/reservation/theater?movieId=` + selectedMovieId + `&date=` + selectedDate1)
		.then(response => {
			if (!response.ok) {
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
		titleLink.href = `/movie/detail?title=` + movie.title; // 링크가 클릭 가능한 상태로 설정
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

function applyRegionFilter(regionId) {
	fetch(`http://localhost:8080/reservation/regions?regionId=` + regionId)
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(data => {
			if (movieCheck) {
				alert('영화를 먼저 선택해주세요')
			} else {

				console.log('success:', data);
				updateSubRegionList(data);
			}
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
		// <a> 태그 생성
		const link = document.createElement('a');
		link.href = 'javascript:void(0)';
		link.textContent = subRegion.name;

		// 필요에 따라 onclick 이벤트 핸들러 추가
		link.onclick = function() {
			// 클릭 시 동작 정의 (예: 서브지역 필터링 등)
			if (movieCheck) {
				alert('영화를 먼저 선택해주세요.')
			} else {
				console.log('Clicked sub-region:', subRegion.name);
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
function checkMovie(theaterName, subregionId) {
	console.log('Clicked sub-region ID:', subregionId);
	console.log('Clicked sub-region Name:', theaterName);
	selectedTheater = theaterName;
	if (movieCheck) {
		alert('영화를 먼저 선택해주세요.')
	} else {
		const theaterElement = document.querySelector('.movie--detail--box .theater');
		if (theaterElement) {
			theaterElement.textContent = theaterName; // subRegion.name을 theater에 표시
		}
		fetch(`http://localhost:8080/reservation/timeList?date=` + selectedDate1 + `&movieId=` + selectedMovieId + `&subregionId=` + subregionId)
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

function viewSeats() {
	if (selectedDate1 == null) {
		alert('날짜 먼저 선택해주세요')
	} else if (selectedMovieId == null) {
		alert('영화를 선택해 주세요')
	} else if (selectedTheater == null) {
		alert('극장을 선택해 주세요')
	} else if (selectedTime == null) {
		alert('시간을 선택해 주세요')
	} else {
		// 기존 콘텐츠 숨기기
		document.getElementById('choice--movie').style.display = 'none';
		// 새 콘텐츠 보이기
		document.getElementById('movie-container').style.display = 'block';


		fetch(`http://localhost:8080/reservation/checkDupliSeat?showTimeId=` + showTimeId)
			.then(response => {
				if (!response.ok) {
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
			} else if (selectedTicketCount == null || selectedTicketCount == 0) {
				alert("좌석을 선택해 주세요")
			} else {


				fetch(`http://localhost:8080/reservation/booking`, {
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
