<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tenco.movie.repository.model.Event"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<!-- 성후 -->
<!-- start of content.jsp(xxx.jsp) -->
<div class="evt--title">
	<h1 style="margin-top: 25px;">EVENT</h1>
</div>
<div class="evt--area">
	<ul class="evt--area--menu">
		<div class="evt--prize">
			<a href="/event/event" class="evt--prize--win"><i>현재 진행중인 이벤트</i></a>
			<a href="/event/allevent" class="evt--prize--win"><i>이벤트 전체보기</i></a>
			<a href="/event/endevent" class="evt--prize--win"><i>종료된 이벤트</i></a>
		</div>
	</ul>
	<!-- 이벤트 만드는중 성후 -->

	<c:choose>
		<c:when test="${event != null}">
			<div class="evt--item--detail">
				<div class="evt--item--e">
					<img src="${event.originFileName}" alt="[맙소사] 고양이 귀엽다!"
						style="width: 100%;">
				</div>
				<div class="">
					<p class="txt">${event.id}</p>
					<p class="txt1">[EVENT] ${event.title}</p>
					<p class="txt2">${event.timestartToString()}~${event.timeendToString()}</p>
					<p class="status">현재 이벤트 상태 : ${event.status}</p>
				</div>
			</div>
			<!-- .evt--item 끝 -->
</div>
<!-- .evt--item-container 끝 -->
</c:when>
</c:choose>

</div>

<br>
<div style="display: flex; justify-content: flex-end; color: red;">
	<a href="/event/event">목록으로</a>
</div>

</div>
</div>
</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</body>
</html>