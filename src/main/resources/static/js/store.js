document.querySelectorAll('.purchase--btn').forEach(function(btn) {
	btn.addEventListener('click', function(event) {
		// 로그인 여부 확인
		const principal = "${principal != null ? principal.id : null}";
		const itemId = this.id;
		console.log("item id: ", itemId);
		// a 태그의 기본 동작을 막기 위해 preventDefault 호출
		event.preventDefault();

		// data-price 속성에서 가격 정보 추출
		const price = this.getAttribute('data-price');
		console.log('data-price : ', price);

		fetch(`http://localhost:8080/date/currentMoney`)
			.then(response => {
				if (!response.ok) {
					throw new Error('연결을 실패했습니다.');
				}
				return response.json();
			})
			.then(data => {
				if (price > data) {
					if (confirm("콘이 부족합니다.\n콘을 충전 하시겠습니까?")) {

					} else {
						console.log("사용자가 결제를 취소하였습니다.");
					}
				} else {
					if (confirm("정말로 구입하시겠습니까?")) {
						fetch(`http://localhost:8080/date/payItem`, {
							method: 'POST',
							headers: {
								'content-Type': 'application/json',
							},
							body: JSON.stringify({
								itemId: itemId,
								price: price
							})
						})
							.then(response => {
								if (response.ok) {
									return response.json();
								}
							})
							.then(data => {
								alert(data.message); // "결제 성공" 메세지
							})
							.catch(error => {
								console.error('There has been a problem with your fetch operation:', error);
							});

					} else {
						console.log("사용자가 결제를 취소하였습니다.");
					}
				}
			})
			.catch(error => {
				if (confirm("로그인이 필요한 서비스입니다. \n로그인 페이지로 이동하시겠습니까?")) {
					window.location.href = "/user/signIn"; // 로그인 페이지로 이동
				} else {
					console.log("사용자가 로그인 페이지 이동을 취소했습니다.");
				}
			});
	});
});
