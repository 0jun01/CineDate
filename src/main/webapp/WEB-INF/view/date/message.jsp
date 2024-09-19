<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 창</title>
<style type="text/css">
#chatBox{
    height: 400px;
    overflow-y: scroll; 
}
</style>
</head>
<body>
    <h2>채팅</h2>
    
    <div id="chatBox">
        <c:forEach var="list" items="${chatHistory}">
            <div>
                ${list.position} : ${list.message} : ${list.timestampToString()}
            </div>
        </c:forEach>
    </div> 

    <input type="text" id="messageInput" placeholder="메시지를 입력하세요">
    <button onclick="sendMessage()">전송</button>

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
            senderId: userId,
            recipientId: partnerId,
            message: messageInput,
            timestamp: new Date().toISOString(), // 현재 시간을 ISO 형식으로 변환
            position: 'Sender' // 필요에 따라 다른 위치 정보 설정 가능
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
    document.getElementById("messageInput").addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            event.preventDefault(); // 기본 Enter 키 동작(줄바꿈) 방지
            sendMessage();
        }
    });
    
    </script>

</body>
</html>