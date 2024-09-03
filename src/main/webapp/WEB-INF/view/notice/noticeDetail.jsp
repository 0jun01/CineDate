<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>




<div class="notice--list">
<c:choose>
	<c:when test="${notice != null}">
		<table class="table">
			<tr>
				<th>${notice.title}</th>
				<th>${notice.category}</th>
				<th>${notice.timestampToString()}</th>
			</tr>
			<tr>
				<td>${notice.content}</td>
			</tr>

		</table>

	</c:when>
	<c:otherwise>
		<div class="jumbotron display-4">
			<h5>해당 공지사항이 존재하지 않습니다.</h5>
		</div>
	</c:otherwise>
</c:choose>
</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</body>
</html>
