<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">
	<div id="in--wrap">
		<div class="flex--between--wrap">
			<div class="top--title">
				<a href="#none">무비차트</a>
				<a href="#none">상영예정작</a>
			</div>
			<div class="top--title">
				<a id="btn--all--view" class='btn--all--view'>전체보기</a>
			</div>
		</div>
		
		<!-- 공지사항 -->
		<div class="notice--wrap">
			<div class="top--var">
				<h1>공지사항</h1>
				<a>~~유의사항 태그</a>
			</div>
		</div>
	</div>
	
</div>


<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

</body>
</html>
