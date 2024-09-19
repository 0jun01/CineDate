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
					<span> <img src="/img/corn.png" style="width: 30px; height: auto;">${item.price}
					</span> <br> <br> <a id="${item.id} " class="purchase--btn" data-price="${item.price}">구매하기</a>
				</div>
			</c:forEach>
		</div>



	</div>
</div>

<script src="/js/store.js"></script>
<script>
	
</script>




























<!--  팝콘 선택 폼 
<div class="popcorn--charge--box">
	<form action="/date/popcornStore" method="POST">
		<p>팝콘을 선택하세요:</p>
		<label> <input type="radio" name="popcorn" value="1" /> 1개
		</label><br /> <label> <input type="radio" name="popcorn" value="10" /> 10개
		</label><br /> <label> <input type="radio" name="popcorn" value="30" /> 30개
		</label><br /> <label> <input type="radio" name="popcorn" value="50" /> 50개
		</label><br /> <label> <input type="radio" name="popcorn" value="100" /> 100개
		</label><br />
		<button type="submit" value="제출">제출</button>
	</form>
</div>
-->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>