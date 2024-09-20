<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tenco.movie.repository.model.Event"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div class="wrap">
	<div id="in--wrap">

		<div class="event--wrap">
			<c:choose>
				<c:when test="${event != null}">
					<h1 class="event--title">[EVENT] ${event.title}</h1>

					<div class="notoce--detail">
						<p class="txt">ID : ${event.id}</p>
						<!--div class="notice--detail--category">${event.timestartToString()} ~ ${event.timeendToString()}</div-->
						<!--div class="notice--detail--time">현재 이벤트 상태 : ${event.status}</div-->
						<div class="list--btn btn">
							<a href="/event/event"><img src="/img/list_icon_gray.png" width="100%"></a>
						</div>
					</div>
					<div class="notice--detail--content">
						<img src="/eventImage/${event.uploadFileName}" alt="${event.uploadFileName}">
					</div>
				</c:when>
			</c:choose>

		</div>
	</div>
</div>



<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
