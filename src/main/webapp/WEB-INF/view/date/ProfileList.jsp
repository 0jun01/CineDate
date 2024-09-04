<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
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
					<td><img class="m--profile list--profile" alt="" src="/DateProfileIMAGE/${list.firstUploadFileName}" style="width: 100px; height: 100px;"></td>
					<td>${list.nickName}</td>
					<td>${list.introduce}</td>
					<td>
							<c:choose>
								<c:when test="${list.status == 2}">
								<button style="width: 60px; height: 150px;">매칭완료(쪽지보내기)</button>
							</c:when>
							<c:otherwise>
								<button onclick="openPopup('${list.userId}')" value="${list.id}">상세 보기</button>
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
<script>
	function openPopup(id) {
		
		// 새 창을 열기 위한 window.open 메서드 사용
		window.open('http://localhost:8080/date/detailPartner?userId=' + encodeURIComponent(${principal.id}) +'&id=' +  encodeURIComponent(id), // 새 창에서 로드할 URL
		'상세보기', // 새 창의 이름
		'width=700,height=600,left=100,top=100,resizable=yes,scrollbars=no' // 창의 옵션
		);
	}
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</body>
</html>