<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- header.jsp -->
    <%@ include file="/WEB-INF/view/layout/header.jsp" %>
    
        <!-- start of content.jsp(xxx.jsp) -->
	<div class="evt--title">
	<h1 style="margin-top: 25px;">EVENT</h1>
	</div>
	<div class="evt--area">
    <ul class="evt--area--menu">
        <li><a href="/event/event" title="" class="">SPECIAL</a></li>
        <li><a href="/event/event" title="" class="">영화/예매</a></li>
        <li><a href="/event/event" title="" class="">멤버십/CLUB</a></li>
        <li><a href="/event/event" title="" class="">극장별</a></li>
        <li><a href="/event/event" title="" class="">제휴/할인</a></li>        
    <div class="evt--prize">
        <a href="/event/event" class="evt--prize--win"><i>당첨자 발표</i></a>
        <a href="/event/event" class="evt--prize--win"><i>종료된 이벤트</i></a>
    </div>
    </ul>    
    <!-- 이벤트 만드는중 성후 -->
		<div class="evt--item--a">
	<div class="evt--item--a">
	<img src="/img/cat3.jpg" alt="[맙소사] 고양이 귀엽다!"style="width: 100%">
	</div>
	<div class="">
	<p class="txt1">[맙소사] 고양이 귀엽다!</p>
	<p class="txt2">2024.08.22~2024.08.29<span> . </span><em>D-1</em></p>
	</div>
	<br>
	<div style="display: flex; justify-content: flex-end;">
	<a href="/event/event">목록으로</a>
	</div>
</div>
</div>
	<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
</body>
</html>