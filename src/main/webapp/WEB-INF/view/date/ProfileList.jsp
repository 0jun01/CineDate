<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- header.jsp -->
<meta name="_csrf" content="${_csrf.token}">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
	<div id="in--wrap">

		<h1 class="section--title super">슈퍼 리스트🍿</h1>
		<div class="table--scroll super--scroll">
			<c:choose>
				<c:when test="${superList != null}">
					<table class="table super--table">
						<tr class="table--title">
							<th>사진</th>
							<th>닉네임</th>
							<th>자기소개</th>
							<th>신청</th>
						</tr>
						<c:forEach var="superList" items="${superList}">
							<tr>
								<td><img class="m--profile list--profile super--profile"
									alt="슈퍼 프로필 사진"
									src="/DateProfileIMAGE/${superList.firstUploadFileName}"></td>
								<td>${superList.nickName}</td>
								<td>${superList.introduce}</td>
								<td><c:choose>
										<c:when test="${superList.status == 2}">
											<button onclick="openChat('${superList.userId}')">매칭완료(대화창열기)</button>
										</c:when>
										<c:otherwise>
											<button onclick="openPopup('${superList.userId}')">상세보기</button>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div class="jumbotron display-4">
						<h1>📽</h1>
						<h5>아무도 없어요 💦</h5>
					</div>
				</c:otherwise>
			</c:choose>

			<!-- 슈퍼 리스트의 효과  -->
			<div class="bubble b1"></div>
			<div class="bubble b2"></div>
			<div class="bubble b3"></div>
			<div class="bubble b4"></div>
			<div class="bubble b5"></div>
			<div class="bubble b6"></div>
			<div class="bubble b7"></div>
		</div>

		<h1 class="section--title">일반 리스트</h1>
		<div class="table--scroll">
			<c:choose>
				<c:when test="${list != null}">
					<table class="table">
						<tr class="table--title">
							<th>사진</th>
							<th>닉네임</th>
							<th>자기소개</th>
							<th>신청</th>
						</tr>
						<c:forEach var="list" items="${list}">
							<tr>
								<td><img class="m--profile list--profile" alt="프로필 사진"
									src="/DateProfileIMAGE/${list.firstUploadFileName}"></td>
								<td>${list.nickName}</td>
								<td>${list.introduce}</td>
								<td><c:choose>
										<c:when test="${list.status == 2}">
											<button onclick="openChat('${list.userId}')"
												id="${list.userId}">매칭완료(대화창열기)</button>
										</c:when>
										<c:otherwise>
											<button onclick="openPopup('${list.userId}')">상세 보기</button>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div class="jumbotron display-4">
						<h5>아무도 없어요 흑흑흑</h5>
					</div>
				</c:otherwise>
			</c:choose>
		</div>



		<!-- 대화창 HTML 구조 추가 -->
		<div id="chat--container">
			<div id="chat--header">
				<span>대화창</span>
				<button id="close--chat" onclick="toggleChat()"
					style="border: none; background: none; cursor: pointer;">닫기</button>
			</div>
			<div id="chat--messages"></div>
			<div id="chat--footer">
				<textarea id="chat--input" placeholder="메시지를 입력하세요..."></textarea>
				<button id="send--chat" onclick="sendChatMessage()">보내기</button>
			</div>
		</div>
	</div>
</div>
<script>
    const userId = ${principal.id}; // 현재 사용자 ID
    console.log('받은 userId:', userId); // 디버깅용: 현재 사용자 ID 출력

    let socket = new WebSocket('ws://localhost:8080/ws/chat'); // 웹소켓 연결

    function initializeWebSocket() {
        socket.onmessage = function(event) {

        	console.log('받은 메시지:', message);
            console.log('현재 userId:', userId);
            console.log('메시지 보낸 사람 ID:', message.senderId);
            
            const message = JSON.parse(event.data); // 수신한 메시지를 JSON으로 파싱
            console.log('받은 메시지:', message); // 디버깅용: 수신한 메시지 출력

            const chatMessages = document.getElementById('chat--messages');
            if (!chatMessages) {
                console.error('채팅 메시지 요소를 찾을 수 없습니다.'); // 채팅 메시지 요소가 없는 경우 오류 출력
                return;
            }

            const messageDiv = document.createElement('div');
            messageDiv.classList.add('chat-message', message.senderId === userId ? 'sent' : 'received');
            // `message.senderId`가 현재 사용자 ID와 같으면 'sent' 클래스를, 그렇지 않으면 'received' 클래스를 추가
            messageDiv.textContent = `${message.senderId == userId ? '나' : message.senderId}: ${message.message}`;
            chatMessages.appendChild(messageDiv); // 메시지 div를 채팅 메시지 컨테이너에 추가
            chatMessages.scrollTop = chatMessages.scrollHeight; // 채팅 메시지 컨테이너를 스크롤하여 가장 최근 메시지가 보이도록 함
        };

        socket.onopen = function() {
            console.log('웹소켓 연결이 설정되었습니다.'); // 웹소켓 연결이 열렸을 때 로그
        };

        socket.onclose = function() {
            console.log('웹소켓 연결이 닫혔습니다.'); // 웹소켓 연결이 닫혔을 때 로그
        };

        socket.onerror = function(error) {
            console.error('웹소켓 오류:', error); // 웹소켓 오류가 발생했을 때 로그
        };
    }

    function sendMessage(message, recipientId) {
        const data = {
            recipientId: recipientId, // 수신자 ID
            senderId: userId, // 현재 사용자 ID
            message: message // 전송할 메시지
        };

        fetch('/date/sendMessage', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-Token': document.querySelector('meta[name="_csrf"]').getAttribute('content')
            },
            body: JSON.stringify(data)
        })
        .then(response => {
        if (!response.ok) {
            throw new Error('네트워크 응답이 올바르지 않습니다.');
        }
        return response.json();
    })
        .then(result => {
            if (result.success) {
                console.log('메시지가 성공적으로 전송되었습니다.'); // 메시지 전송 성공 시 로그
                socket.send(JSON.stringify(data)); // 웹소켓을 통해 메시지 전송
            } else {
                alert('메시지 전송에 실패했습니다.'); // 메시지 전송 실패 시 경고
                console.log('전송할 데이터:', JSON.stringify(data)); // 들어는 갔음
            }
        })
        .catch(error => {
            console.error('오류:', error); // 전송 중 오류 발생 시 로그
            alert('메시지 전송 중 오류가 발생했습니다.'); // 전송 중 오류 발생 시 경고
        });
    }

    function fetchConversation(senderId, recipientId) {
        const url = '/date/conversation?userId=' + senderId + '&recipientId=' + recipientId;
        fetch(url)
        .then(response => response.json())
        .then(messages => {
            console.log('대화 내용:', messages); // 대화 내용 출력

            const chatMessages = document.getElementById('chat--messages');
            if (!chatMessages) {
                console.error('채팅 메시지 요소를 찾을 수 없습니다.'); // 채팅 메시지 요소가 없는 경우 오류 출력
                return;
            }

             chatMessages.innerHTML = ''; // 기존 메시지 제거

            messages.forEach(msg => {
                const messageDiv = document.createElement('div');
                messageDiv.classList.add('chat-message', msg.senderId === userId ? 'sent' : 'received');
                // `msg.senderId`가 현재 사용자 ID와 같으면 'sent' 클래스를, 그렇지 않으면 'received' 클래스를 추가
                messageDiv.textContent = `${msg.senderId == userId ? '나' : msg.senderId}: ${msg.message}`;
                chatMessages.appendChild(messageDiv); // 메시지 div를 채팅 메시지 컨테이너에 추가
            });
            chatMessages.scrollTop = chatMessages.scrollHeight; // 채팅 메시지 컨테이너를 스크롤하여 가장 최근 메시지가 보이도록 함
        })
        .catch(error => {
            console.error('대화 내용을 가져오는 중 오류:', error); // 대화 내용을 가져오는 중 오류 발생 시 로그
        });
    }

    function openChat(recipientId) {
        if (!recipientId) {
            console.error('유효하지 않은 사용자 ID입니다.'); // 유효하지 않은 사용자 ID인 경우 오류 출력
            return;
        }

        console.log('현재 수신자 ID:', recipientId); // 디버깅용: 현재 수신자 ID 출력
        toggleChat(); // 채팅 창 토글
        fetchConversation(userId, recipientId); // 현재 사용자 ID와 수신자 ID를 사용하여 대화 내용 가져오기

        if (socket) {
            socket.close(); // 기존 소켓 연결 닫기
        } else {
            // 웹소켓 새 연결 설정
            socket = new WebSocket('ws://localhost:8080/ws/chat');
            initializeWebSocket(); // 웹소켓 이벤트 초기화
        }
    }

    function sendChatMessage() {
        const chatInput = document.getElementById('chat--input');
        const message = chatInput.value.trim();

        if (message) {
            sendMessage(message, window.currentRecipientId); // 메시지 전송 함수 호출
            chatInput.value = ''; // 입력 필드 비우기
        }
    }

    function toggleChat() {
        const chatContainer = document.getElementById('chat--container');
        if (chatContainer) {
            chatContainer.style.display = chatContainer.style.display === 'none' ? 'flex' : 'none'; // 채팅 창의 표시 상태를 토글
        } else {
            console.error('채팅 컨테이너 요소를 찾을 수 없습니다.'); // 채팅 컨테이너 요소가 없는 경우 오류 출력
        }
    }

    // 채팅 창 드래그 기능 추가
    const chatHeader = document.getElementById('chat--header');
    const chatContainer = document.getElementById('chat--container');
    let isDragging = false;
    let startX, startY, startLeft, startTop;

    if (chatHeader) {
        chatHeader.addEventListener('mousedown', (e) => {
            isDragging = true;
            startX = e.clientX;
            startY = e.clientY;
            const chatContainerStyle = window.getComputedStyle(chatContainer);
            startLeft = parseInt(chatContainerStyle.left, 10);
            startTop = parseInt(chatContainerStyle.top, 10);
            e.preventDefault(); // 텍스트 선택 방지
        });
    }

    document.addEventListener('mousemove', (e) => {
        if (isDragging && chatContainer) {
            const dx = e.clientX - startX;
            const dy = e.clientY - startY;
            chatContainer.style.left = `${startLeft + dx}px`;
            chatContainer.style.top = `${startTop + dy}px`;
        }
    });

    document.addEventListener('mouseup', () => {
        isDragging = false;
    });

    // 페이지 로드 시 채팅 창 숨기기
    document.addEventListener('DOMContentLoaded', () => {
        if (chatContainer) {
            chatContainer.style.display = 'none'; // 페이지 로드 시 채팅 창을 숨김
        }
    });
</script>



<%@ include file="/WEB-INF/view/layout/footer.jsp"%>