
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">
	<div id="in--wrap">

		<h1 class="section--title">매칭 리스트</h1>
		<div class="table--scroll">
			<c:choose>
				<c:when test="${list != null}">
					<table class="table">
						<tr class="table--title">
							<th>사진</th>
							<th>닉네임</th>
							<th>자기 소개</th>
							<th>상태</th>
						</tr>
	
						<c:forEach var="list" items="${list}">
							<tr>
								<td><img class="m--profile list--profile" alt="" src="/image/${list.firstUploadFileName}" style="width: 100px; height: 100px;"></td>
								<td>${list.nickName}</td>
								<td>${list.introduce}</td>
								<td>
									<c:if test="${list.status == 0}">
										<h5>응답대기중</h5>
									</c:if>
									<c:if test="${list.status == 1}">
										<button onclick="openChat('${list.userId}')" id="${list.userId}">메세지보내기</button>
									</c:if>
									<c:if test="${list.status == 2}">
										<h5>거절 ㅠㅠ</h5>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
	
				<c:otherwise>
					<div class="jumbotron display-4">
						<h1>📽</h1>
						<h5>매칭된 사람이 없어요 💦</h5>
					</div>
				</c:otherwise>
	
			</c:choose>
		</div>
	</div>
</div>

<script>
function openChat(id){
	  window.open('http://localhost:8080/date/message?userId=' + encodeURIComponent(${principal.id}) +'&id=' +  encodeURIComponent(id),
		'메세지',
		'width=700,height=600,left=100,top=100,resizable=yes,scrollbars=no');
}
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
