<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
	<div id="in--wrap">
		<div class="flex--wrap">
			<div class="top--title">
				<h1 class="text-center pointFont">비밀번호 찾기</h1>
			</div>

			<form action="/user/findNextPassword" method="POST">
				<div class="login--wrap">
					<label for="loginId">아이디</label>
					<input type="text" id="loginId" name="loginId" placeholder="아이디를 입력해주세요.">
					
					<label for="email">이메일 주소</label>
					<input type="email" id="email" name="email" placeholder="이메일 주소를 입력해주세요.">
					
					<button type="submit" class="btn" id="join--btn">비밀번호 찾기</button>
				</div>
			</form>
			
			<div class="login--items">
				<span class="btn"><a href="/user/signIn">로그인</a></span>
				<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
				<span class="btn"><a href="/user/signUp">회원가입</a></span>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>