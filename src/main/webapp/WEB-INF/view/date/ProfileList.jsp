<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- header.jsp -->
<meta name="_csrf" content="${_csrf.token}">
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- start of content.jsp(xxx.jsp) -->

<c:choose>
	<c:when test="${list != null}">
		<table class="table">
			<tr>
				<th>사진</th>
				<th>닉네임</th>
				<th>자기소개</th>
				<th>신청</th>
			</tr>
			<c:forEach var="list" items="${list}">
				<tr>
					<td><img class="m--profile list--profile" alt=""
						src="/DateProfileIMAGE/${list.firstUploadFileName}"
						style="width: 100px; height: 100px;"></td>
					<td>${list.nickName}</td>
					<td>${list.introduce}</td>
					<td><c:choose>
							<c:when test="${list.status == 2}">
								<button style="width: 60px; height: 150px;"
									onclick="openChat('${list.userId}')">매칭완료(대화창열기)</button>
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
<!-- 대화창 HTML 구조 추가 -->
<div id="chat-container"
    style="display: none; position: fixed; bottom: 20px; right: 20px; width: 300px; height: 400px; border: 1px solid #ddd; background-color: #fff; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); flex-direction: column;">
    <div id="chat-header"
        style="padding: 10px; background-color: #f1f1f1; border-bottom: 1px solid #ddd; display: flex; justify-content: space-between; align-items: center;">
        <span>대화창</span>
        <button id="close-chat" onclick="toggleChat()"
            style="border: none; background: none; cursor: pointer;">닫기</button>
    </div>
    <div id="chat-messages"
        style="overflow-y: auto; flex: 1; padding: 10px;"></div>
    <div id="chat-footer"
        style="padding: 10px; border-top: 1px solid #ddd;">
        <textarea id="chat-input"
            style="width: calc(100% - 90px); padding: 10px; margin-right: 10px; box-sizing: border-box;"
            placeholder="메시지를 입력하세요..."></textarea>
        <button id="send-chat" onclick="sendChatMessage()"
            style="width: 80px; padding: 10px;">보내기</button>
    </div>
</div>
<a href="/date/profilePage">내 정보 업데이트 바로가기</a>
<script>
    function openPopup(id) {
        window.open('http://localhost:8080/date/detailPartner?userId=' + encodeURIComponent(${principal.id}) +'&id=' +  encodeURIComponent(id),
        '상세보기',
        'width=700,height=600,left=100,top=100,resizable=yes,scrollbars=no');
    }

    function toggleChat() {
        const chatContainer = document.getElementById('chat-container');
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
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</body>
</html>