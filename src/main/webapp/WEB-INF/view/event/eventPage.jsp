<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- header.jsp -->
    <%@ include file="/WEB-INF/view/layout/header.jsp" %>
    
        <!-- start of content.jsp(xxx.jsp) -->
	<div>
	<h1>EVENT</h1>
	</div>
	<div class="evt--area">
    <ul class="evt--area--menu">
        <li><a href="/event/event" title="" class="">SPECIAL</a></li>
        <li><a href="/event/event" title="" class="">영화/예매</a></li>
        <li><a href="/event/event" title="" class="">멤버십/CLUB</a></li>
        <li><a href="/event/event" title="" class="">극장별</a></li>
        <li><a href="/event/event" title="" class="">제휴/할인</a></li>        
    </ul>    
    <div class="">
        <a href="/event/event" class=""><i>당첨자 발표</i></a>
        <a href="/event/event" class=""><i>종료된 이벤트</i></a>
    </div>
</div>
	<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
</body>
</html>