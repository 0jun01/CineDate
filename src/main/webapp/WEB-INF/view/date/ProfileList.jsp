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
										<c:when test="${superList.status == 1}">
											<button> 매칭 요청 대기중 </button>
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
										<c:when test="${list.status == 1}">
											<button> 매칭 요청 대기중 </button>
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
					<h1>📽</h1>
					<h5>아무도 없어요 💦</h5>
				</c:otherwise>
			</c:choose>
		</div>



		
<script>
function openChat(id){
	  window.open('http://localhost:8080/date/message?userId=' + encodeURIComponent(${principal.id}) +'&id=' +  encodeURIComponent(id),
		'메세지',
		'width=700,height=600,left=100,top=100,resizable=yes,scrollbars=no');
}

function openPopup(id) {
        window.open('http://localhost:8080/date/detailPartner?userId=' + encodeURIComponent(${principal.id}) +'&id=' +  encodeURIComponent(id),
        '상세보기',
        'width=700,height=600,left=100,top=100,resizable=yes,scrollbars=no');
    }

  
    
    // 채팅 창 드래그 기능 추가
    
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

</script>



<%@ include file="/WEB-INF/view/layout/footer.jsp"%>