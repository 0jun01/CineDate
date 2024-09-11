<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<meta name="_csrf" content="${_csrf.token}">
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
	<div id="in--wrap">
	
		<h1 class="section--title super">슈퍼 리스트🍿</h1>
		<div class="table--scroll super--scroll">
			<c:choose>
				<c:when test="${superList != null}">
					<table class="table">
						<tr class="table--title">
							<th>사진</th>
							<th>닉네임</th>
							<th>자기소개</th>
							<th>신청</th>
						</tr>
						<c:forEach var="superList" items="${superList}">
							<tr>
								<td><img class="m--profile list--profile" alt="슈퍼 프로필 사진" src="/DateProfileIMAGE/${superList.firstUploadFileName}"></td>
								<td>${superList.nickName}</td>
								<td>${superList.introduce}</td>
								<td>
									<c:choose>
										<c:when test="${superList.status == 2}">
											<button onclick="openChat('${superList.userId}')">매칭완료(대화창열기)</button>
										</c:when>
										<c:otherwise>
											<button onclick="openPopup('${superList.userId}')">상세 보기</button>
										</c:otherwise>
									</c:choose>
								</td>
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
								<td><img class="m--profile list--profile" alt="프로필 사진" src="/DateProfileIMAGE/${list.firstUploadFileName}"></td>
								<td>${list.nickName}</td>
								<td>${list.introduce}</td>
								<td>
									<c:choose>
										<c:when test="${list.status == 2}">
											<button onclick="openChat('${list.userId}')">매칭완료(대화창열기)</button>
										</c:when>
										<c:otherwise>
											<button onclick="openPopup('${list.userId}')">상세 보기</button>
										</c:otherwise>
									</c:choose>
								</td>
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
				<button id="close--chat" onclick="toggleChat()" style="border: none; background: none; cursor: pointer;">닫기</button>
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
    function openPopup(id) {
        window.open('http://localhost:8080/date/detailPartner?userId=' + encodeURIComponent(${principal.id}) +'&id=' +  encodeURIComponent(id),
        '상세보기',
        'width=700,height=600,left=100,top=100,resizable=yes,scrollbars=no');
    }

    function toggleChat() {
        const chatContainer = document.getElementById('chat--container');
        if (chatContainer.style.display === 'none') {
            chatContainer.style.display = 'flex';
        } else {
            chatContainer.style.display = 'none';
        }
    }

    function openChat(userId) {
        window.currentRecipientId = userId;
        console.log('Current recipient ID:', window.currentRecipientId);
        toggleChat();
    }

    function sendChatMessage() {
        const chatInput = document.getElementById('chat-input');
        const chatMessages = document.getElementById('chat-messages');
        const message = chatInput.value.trim();

        if (message) {
            const data = {
                recipientId: window.currentRecipientId,
                message: message
            };

            console.log('Sending data:', data);

            fetch('/date/sendMessage', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-Token': document.querySelector('meta[name="_csrf"]').getAttribute('content')
                },
                body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(result => {
                if (result.success) {
                    console.log('Message sent successfully');
                    const messageDiv = document.createElement('div');
                    messageDiv.classList.add('chat-message', 'sent');
                    messageDiv.textContent = `나: `+ message;
                    chatMessages.appendChild(messageDiv);
                    chatInput.value = '';
                    chatMessages.scrollTop = chatMessages.scrollHeight;
                    console.log('Message:', message);
                } else {
                    alert('메시지 전송에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('메시지 전송 중 오류가 발생했습니다.');
            });
        }
    }
    
    
    
    // 채팅 창 드래그 기능 추가
    (function() {
        const chatHeader = document.getElementById('chat--header');
        const chatContainer = document.getElementById('chat--container');
        let isDragging = false;
        let startX, startY, startLeft, startTop;

        chatHeader.addEventListener('mousedown', (e) => {
            isDragging = true;
            startX = e.clientX;
            startY = e.clientY;
            startLeft = parseInt(window.getComputedStyle(chatContainer).left, 10);
            startTop = parseInt(window.getComputedStyle(chatContainer).top, 10);
            e.preventDefault(); // 텍스트 선택 방지
        });

        document.addEventListener('mousemove', (e) => {
            if (isDragging) {
                const dx = e.clientX - startX;
                const dy = e.clientY - startY;
                chatContainer.style.left = `${startLeft + dx}px`;
                chatContainer.style.top = `${startTop + dy}px`;
            }
        });

        document.addEventListener('mouseup', () => {
            isDragging = false;
        });
    })();
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>