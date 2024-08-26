<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tenco.movie.repository.model.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div class="notice--list">
	<h3 class="notice--title">공지사항</h3>

	<c:choose>
		<c:when test="${noticeList != null}">
			<table class="table">
				<thead>
					<tr>
						<th>id</th>
						<th>카테고리</th>
						<th>제목</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="notice" items="${noticeList}">
						<tr>
							<td>${notice.id}</td>
							<td>${notice.category}</td>
							<td><a href="/notice/detail/${notice.id}?type=all">${notice.title}</a></td>
							<%--<td><a href="/notice/detail/${notice.id}?type=all">${account.title}</a></td> --%>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<div class="jumbotron display-4">
				<h5>게시된 공지가 없습니다.</h5>
			</div>
		</c:otherwise>
	</c:choose>

</div>
</div>
</div>
<!-- end of content.jsp(xxx.jsp) -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</body>
</html>
