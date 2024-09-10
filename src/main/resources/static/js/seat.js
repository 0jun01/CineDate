const movieSelect = document.getElementById('movie');
const container = document.querySelector('.container');
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');


let ticketPrice = +movieSelect.value;
let selectedSeats = [];
let selectedSeatsId = [];
populateUI();

// 마우스를 올렸을 때 좌석 번호 보여주기
seats.forEach(function(seat) {
	seat.addEventListener('mouseover', function() {
		const seatNumber = this.getAttribute('data-seat');
		this.setAttribute('title', seatNumber); // 마우스를 올리면 title 속성에 좌석 번호 표시
	});
	// 좌석 클릭 시 data-seat 값 추출
	seat.addEventListener('click', function() {
		const seatNumber = this.getAttribute('data-seat');
		const seatId = this.id;
		 
		// 이미 선택된 좌석인지 확인하고 중복되지 않게 처리
       if (selectedSeats.includes(seatNumber)) {
            // 선택 해제 - 배열에서 좌석 제거
            selectedSeats = selectedSeats.filter(seat => seat !== seatNumber);
            // 좌석의 색상을 원래대로 되돌림 (여기선 예시로 기본 색상으로 설정)
        } else {
            // 좌석 선택 - 배열에 좌석 추가
            selectedSeats.push(seatNumber);
        }
        // 좌석 ID가 배열에 있는지 확인
            if (selectedSeatsId.includes(seatId)) {
                // 이미 선택된 좌석이면 배열에서 제거
                selectedSeatsId = selectedSeatsId.filter(id => id !== seatId);
            } else {
                // 새로 선택된 좌석이면 배열에 추가
                selectedSeatsId.push(seatId);
            }
        console.log(selectedSeatsId);
		// 좌석 타입 식별
		let seatType = '';
		if (this.classList.contains('couple')) {
			seatType = '커플석';
		} else if (this.classList.contains('handicap')){
			seatType = '장애인석';
		} else {
			seatType = '일반좌석'
		}
		document.querySelector('.seat--type').textContent = seatType;
		document.querySelector('.seat--num').textContent = selectedSeats.join(', '); // 선택된 좌석들 표시
	});
});

function populateUI() {
	const selectedSeats = JSON.parse(localStorage.getItem('selectedSeats'));
	console.log(selectedSeats);
	if (selectedSeats !== null && selectedSeats.length > 0) {
		selectedSeats.forEach((seatIndex) => {
			seats[seatIndex].classList.add('selected');
		});
	}

	const selectedMovieIndex = localStorage.getItem('selectedMovieIndex');

	if (selectedMovieIndex !== null) {
		movieSelect.selectedIndex = selectedMovieIndex;
	}
}

function setMovieData(movieIndex, moviePrice) {
	localStorage.setItem('selectedMovieIndex', movieIndex);
	localStorage.setItem('selectedMoviePrice', moviePrice);
}

function updateSelectedCount() {
	const selectedSeats = document.querySelectorAll('.row .selected');
	const selectedSeatCount = +selectedSeats.length;

	const selectedSeatsIndex = [...selectedSeats].map((seat) => [...seats].indexOf(seat));

	localStorage.setItem('selectedSeats', JSON.stringify(selectedSeatsIndex));
	count.textContent = selectedSeatCount;
	total.textContent = selectedSeatCount * ticketPrice;
}

movieSelect.addEventListener('change', (event) => {
	ticketPrice = +event.target.value;

	setMovieData(event.target.selectedIndex, event.target.value);
	updateSelectedCount();
});

container.addEventListener('click', (event) => {
	if (event.target.classList.contains('seat') && !event.target.classList.contains('occupied')) {
		event.target.classList.toggle('selected');

		updateSelectedCount();
	}
});
updateSelectedCount();