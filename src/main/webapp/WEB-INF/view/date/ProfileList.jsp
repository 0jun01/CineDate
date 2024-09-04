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
					<td><img class="m--profile" alt="" src="/DateProfileIMAGE/${list.firstUploadFileName}"></td>
					<td>${list.nickName}</td>
					<td>${list.introduce}</td>
					<td>
						<button>
							<c:choose>
								<c:when test="${list.status == 0}">
                                    신청
                                </c:when>
								<c:when test="${list.status == 1}">
                                    신청취소
                                </c:when>
								<c:when test="${list.status == 2}">
                                    매칭완료
                                </c:when>
								<c:when test="${list.status == 3}">
                                    재도전
                                </c:when>
							</c:choose>
						</button>
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

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</body>
</html>