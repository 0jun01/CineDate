<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<link href="/css/store.css" rel="stylesheet">


<!-- start of content.jsp(xxx.jsp) -->
<div id="wrap">

	<div id="in--wrap">

		<div class="top--title">
			<h1>콘 상점</h1>
			<img src="/img/corn.png" style="width: 50px; height: 50px; padding-top: 150px">

		</div>
		<div class="con--item--wrap">
			<c:forEach var="item" items="${item}">
				<div class="item--box">
					<img alt="" src="${item.itemImg}">
					<h3>${item.name}</h3>
					<h4>${item.itemDesc}</h4>
					<span>
					 <img src="/img/corn.png" style="width: 30px; height: auto;">${item.price}
					</span> 
					<br> <br> <a id="${item.id} " class="purchase--btn" data-price="${item.price}">구매하기</a>
				</div>
			</c:forEach>
		</div>



	</div>
</div>

<script src="/js/store.js"></script>
<script>
	
</script>





























<%@ include file="/WEB-INF/view/layout/footer.jsp"%>