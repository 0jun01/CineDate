const movieSelect = document.getElementById('movie');
const container = document.querySelector('.container');
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');


let ticketPrice = +movieSelect.value;
let selectedSeats = null;

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
		selectedSeats = seatNumber;

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
		document.querySelector('.seat--num').textContent = seatNumber;
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