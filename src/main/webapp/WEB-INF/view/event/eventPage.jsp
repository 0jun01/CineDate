<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tenco.movie.repository.model.Event"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>


<div id="wrap">
	<div id="in--wrap">
		<h1 class="section--title">이벤트</h1>
		<ul class="evt--area--menu">
			<div class="evt--prize">
				<a href="/event/event" class="evt--prize--win" style="color: #ff2588;">현재 진행중인 이벤트</a>
				<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
				<a href="/event/allevent" class="evt--prize--win">이벤트 전체보기</a>
				<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
				<a href="/event/endevent" class="evt--prize--win">종료된 이벤트</a>
			</div>
		</ul>

		<div class="evt--area">
			<c:choose>
				<c:when test="${eventList != null}">
					<c:forEach var="event" items="${eventList}">
						<div class="evt--item">
							<a href="/event/eventDetail?id=${event.id}">
								<div class="event--img" style="background-image: url('/DateProfileIMAGE/${event.uploadFileName}');"></div>
								<div class="event--txt">
									<!-- p class="txt">${event.id}</p-->
									<p class="txt1">[EVENT] ${event.title}</p>
									<p class="txt2">${event.timestartToString()}~${event.timeendToString()}</p>
									<p class="status ${event.status == '진행중' ? 'status-active' : 'status-end'}">${event.status}</p>
								</div>
							</a>
						</div>
					</c:forEach>
				</c:when>
			</c:choose>
		</div>

	</div>
</div>



<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
