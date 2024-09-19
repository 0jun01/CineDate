<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>팝콘 충전</title>
<style>
.popcorn--pay {
	width: 100%;
	max-width: 500px; /* 필요에 따라 조절 */
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* 제목 박스 스타일 */
.title--box {
	text-align: center; /* 제목을 가운데 정렬 */
	margin-bottom: 20px;
}

.title--box p {
	font-size: 18px;
	font-weight: bold;
	margin: 0;
	border: 2px solid #007BFF; /* 테두리 추가 */
	padding: 10px;
	border-radius: 8px;
	background-color: #f8f9fa;
	display: inline-block;
}

/* 팝콘 구매 박스 스타일 */
.popcorn--charge--box {
	margin-bottom: 20px;
}

/* 결제 금액 박스 스타일 */
.amount--box {
	display: flex;
	justify-content: space-between; /* 왼쪽과 오른쪽 끝으로 배치 */
	font-size: 16px;
}

.amount-left, .amount-right {
	font-size: 16px;
}

/* 버튼 스타일 */
.popcorn--charge--box button {
	padding: 10px 20px;
	font-size: 16px;
	border: none;
	border-radius: 4px;
	background-color: #007BFF;
	color: #fff;
	cursor: pointer;
}

.popcorn--charge--box button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="popcorn--pay">
		<div class="title--box">
			<p>팝콘 구매하기</p>
		</div>
		<div class="popcorn--charge--box">
			<form id="popcorn-form" action="/date/popcornStore" method="POST">
				<label> <img src="/img/corn.png" style="width: 30px; height: auto;"> <input type="radio" name="popcorn" value="1" data-price="110" /> 1개
				</label><br /> <label> <img src="/img/corn.png" style="width: 30px; height: auto;"> <input type="radio" name="popcorn" value="10" data-price="1100" /> 10개
				</label><br /> <label> <img src="/img/corn.png" style="width: 30px; height: auto;"> <input type="radio" name="popcorn" value="30" data-price="3300" /> 30개
				</label><br /> <label> <img src="/img/corn.png" style="width: 30px; height: auto;"> <input type="radio" name="popcorn" value="50" data-price="5500" /> 50개
				</label><br /> <label> <img src="/img/corn.png" style="width: 30px; height: auto;"> <input type="radio" name="popcorn" value="100" data-price="11000" /> 100개
				</label><br />
				<button type="submit" value="제출" onclick="calculateTotal()">구매하기</button>
			</form>
		</div>
		<div class="amount--box">
			<div class="amount-left">결제금액</div>
			<div class="amount-right" id="amount-right">0원</div>
		</div>
	</div>
	<script>
	document.addEventListener('DOMContentLoaded', () => {
	    const popcornForm = document.getElementById('popcorn-form');
	    const amountRight = document.getElementById('amount-right');

	    popcornForm.addEventListener('change', (event) => {
	        if (event.target.type === 'radio' && event.target.name === 'popcorn') {
	            const selectedInput = event.target; // 클릭된 input 요소
	            const value = selectedInput.value; // value 값
	            const price = selectedInput.dataset.price; // data-price 값

	            console.log('Selected Value:', value);
	            console.log('Selected Price:', price);
	            
	            // amount-right에 해당 가격 표시
	            if (price) {
	                amountRight.textContent = value + `개 /`+price+ `원`; // 가격을 "원" 단위로 표시
	            }
	        }
	    });
	});
	</script>
</body>
</html>
