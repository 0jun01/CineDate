<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 창</title>
<style type="text/css">
@font-face {
	font-family: 'NanumSquareNeo';
	src:
		url(https://hangeul.pstatic.net/hangeul_static/webfont/NanumSquareNeo/NanumSquareNeoTTF-bRg.eot);
	src:
		url(https://hangeul.pstatic.net/hangeul_static/webfont/NanumSquareNeo/NanumSquareNeoTTF-bRg.eot?#iefix)
		format("embedded-opentype"),
		url(https://hangeul.pstatic.net/hangeul_static/webfont/NanumSquareNeo/NanumSquareNeoTTF-bRg.woff)
		format("woff"),
		url(https://hangeul.pstatic.net/hangeul_static/webfont/NanumSquareNeo/NanumSquareNeoTTF-bRg.ttf)
		format("truetype");
}
body, input, button {
	font-family: 'NanumSquareNeo';
	font-size: 14px;
}

#chatBox {
	height: 440px;
	overflow-y: scroll;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 10px;
	padding: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	margin-bottom: 15px;
}

.chat--message {
	position: relative;
	margin: 10px 0;
	padding: 10px 10px 20px;
	border-radius: 10px;
}

.chat--message.Sender {
	background-color: #fff2ff;
	margin-left: auto;
	text-align: right;
}

.chat--message.Sender::after {
	content: '';
    position: absolute;
    bottom: -16px;
  	right: 15px;
    border-width: 8px;
    border-style: solid;
    border-color: #fff2ff transparent transparent transparent;
}

.chat--message.Sender .chat--timestamp{
	float: left;
}

.chat--message.Receiver {
	background-color: #d1e0ff;
	margin-right: auto;
	text-align: left;
}

.chat--message.Receiver::after {
	content: '';
	position: absolute;
    bottom: -16px;
    left: 15px;	
    border-width: 8px;
    border-style: solid;
    border-color: #d1e0ff transparent transparent transparent;
}

.chat--text{
	padding: 5px 0;
}

.chat--wrap {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

#messageInput {
	width: 80%;
	border-radius: 10px;
	border: 1px solid #ddd;
	padding: 9px;
	font-size: 14px;
	outline: none;
	transition: border 0.3s;
}

#messageInput:focus {
	border: 1px solid #ff2588;
}

#btn {
	width: 16%;
	background-color: #ff2588;
	color: #fff;
	border: none;
	border-radius: 10px;
	padding: 10px;
	font-size: 14px;
	cursor: pointer;
	transition: background-color 0.3s;
}

#btn:hover {
	background-color: #e02f6d;
}

.chat--timestamp {
	float: right;
	font-size: 12px;
	color: #999;
}
</style>
</head>
<body>
	<h2>채팅</h2>

	<div id="chatBox">
        <c:forEach var="list" items="${chatHistory}">
            <div class="chat--message ${list.senderId == principalId ? 'Sender' : 'Receiver'}">
                <div class="chat--user">${list.senderId == principalId ? '나' : '상대방'} : </div>
                <div class="chat--text">${list.message}</div>
                <div class="chat--timestamp">${list.timestampToString()}</div>
            </div>
        </c:forEach>
    </div>
	<div class="chat--wrap">
		<input type="text" id="messageInput" placeholder="메시지를 입력하세요">
		<button onclick="sendMessage()" id="btn">전송</button>
	</div>

	<script>
		var socket = new WebSocket("ws://localhost:8080/ws/chat");

		socket.onopen = function() {
			console.log("WebSocket connection opened");
		};

		socket.onmessage = function(event) {
			var chatBox = document.getElementById("chatBox");
			window.location.reload();
		};

		socket.onclose = function() {
			console.log("WebSocket connection closed");
		};

		function sendMessage() {
			var messageInput = document.getElementById("messageInput").value;
			var userId = '${principalId}'; // 서버에서 전달받은 사용자 ID
			var partnerId = '${partnerId}'; // 서버에서 전달받은 사용자 ID

			var messageObject = {
				senderId : userId,
				recipientId : partnerId,
				message : messageInput,
				timestamp : new Date().toISOString(), // 현재 시간을 ISO 형식으로 변환
				position : 'Sender' // 필요에 따라 다른 위치 정보 설정 가능
			};

			socket.send(JSON.stringify(messageObject)); // JSON 형식으로 전송
			document.getElementById("messageInput").value = ""; // 메시지 전송 후 입력 필드 비우기
		}

		// 페이지가 로드된 후 스크롤을 아래로 이동
		window.onload = function() {
			var chatBox = document.getElementById("chatBox");
			chatBox.scrollTop = chatBox.scrollHeight;
		};

		// Enter 키를 눌렀을 때 메시지를 전송하도록 설정
		document.getElementById("messageInput").addEventListener("keypress",
				function(event) {
					if (event.key === "Enter") {
						event.preventDefault(); // 기본 Enter 키 동작(줄바꿈) 방지
						sendMessage();
					}
				});
	</script>

</body>
</html>