<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>팝콘 충전</title>
<style>
.popcorn--charge--box {
	background-color: #ffeb3b; /* 밝은 노란색 배경 */
	border: 2px solid #ff9800; /* 주황색 테두리 */
	border-radius: 10px;
	padding: 20px;
	max-width: 600px;
	margin: auto;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	font-family: 'Arial', sans-serif;
}

.popcorn--charge--box h2 {
	text-align: center;
	color: #ff5722; /* 강렬한 주황색 텍스트 */
	margin-bottom: 20px;
}

.popcorn-options {
	display: flex;
	flex-direction: column;
	gap: 15px;
}

.popcorn-options label {
	display: flex;
	align-items: center;
	gap: 10px;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 5px;
	padding: 10px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.popcorn-options label:hover {
	background-color: #f5f5f5;
}

.popcorn-img {
	width: 50px;
	height: auto;
	border-radius: 5px;
}

.popcorn-label {
	font-size: 16px;
	color: #333;
}

.submit-btn {
	display: block;
	width: 100%;
	padding: 10px;
	background-color: #ff5722;
	border: none;
	border-radius: 5px;
	color: #fff;
	font-size: 18px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.submit-btn:hover {
	background-color: #e64a19;
}
</style>
</head>
<body>
	<div class="popcorn--charge--box">
		<form action="/date/popcornStore" method="POST">
			<p>팝콘을 선택하세요</p>
			<label> <img src="/img/corn.png" style="width: 30px; height: auto;"><input type="radio" name="popcorn" value="1" /> 1개
			</label><br /> <label> <img src="/img/corn.png" style="width: 30px; height: auto;"><input type="radio" name="popcorn" value="10" /> 10개
			</label><br /> <label> <img src="/img/corn.png" style="width: 30px; height: auto;"><input type="radio" name="popcorn" value="30" /> 30개
			</label><br /> <label> <img src="/img/corn.png" style="width: 30px; height: auto;"><input type="radio" name="popcorn" value="50" /> 50개
			</label><br /> <label> <img src="/img/corn.png" style="width: 30px; height: auto;"><input type="radio" name="popcorn" value="100" /> 100개
			</label><br />
			<button type="submit" value="제출">제출</button>
		</form>
	</div>
</body>
</html>
