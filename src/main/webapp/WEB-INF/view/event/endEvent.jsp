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
        <!-- start of content.jsp(xxx.jsp) -->

	
</div>
</div>
<div class="evt--area">
	<ul class="evt--area--menu">
		<li><a href="/event/event" title="" class="">SPECIAL</a></li>
		<li><a href="/event/event" title="" class="">영화/예매</a></li>
		<li><a href="/event/event" title="" class="">멤버십/CLUB</a></li>
		<li><a href="/event/event" title="" class="">극장별</a></li>
		<li><a href="/event/event" title="" class="">제휴/할인</a></li>
		<div class="evt--prize">
			<a href="/event/event" class="evt--prize--win"><i>당첨자 발표</i></a> <a
				href="/event/endevent" class="evt--prize--win"><i>종료된 이벤트</i></a>
		</div>
	</ul>
	<!-- 이벤트 만드는중 성후 -->

	<c:choose>
    <c:when test="${eventList != null}">
        <div class="evt--item-container">
            <c:forEach var="event" items="${eventList}">
                <div class="evt--item">
                    <a href="/event/eventDetail?id=${event.id}" class="event--images">
                        <div class="evt--item--e">
                            <img src="${event.originFileName}" alt="[맙소사] 고양이 귀엽다!" style="width: 300px;">
                        </div>
                        <div class="">
                            <p class="txt">${event.id}</p>
                            <p class="txt1">[EVENT] ${event.title}</p>
                            <p class="txt2">${event.timestartToString()}~${event.timeendToString()}</p>
                        </div>
                    </a>
                </div> <!-- .evt--item 끝 -->
            </c:forEach>
        </div> <!-- .evt--item-container 끝 -->
    </c:when>
</c:choose>

</div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</body>
</html>