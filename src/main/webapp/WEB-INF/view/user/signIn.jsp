<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
	<div id="in--wrap">
		<div class="flex--wrap">
			<div class="top--title">
				<h1 class="text-center eng">LOGIN</h1>
			</div>

			<form action="/user/signIn" method="POST">
				<div class="login--wrap">
					<input type="text" id="id" name="loginId" placeholder="아이디를 입력해주세요." value="rtg369">
					<input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요." value="1q2w3e4r!!">
					<button type="submit" class="btn" id="login--btn">로그인</button>
				</div>
			</form>

			<div class="login--items">
				<span class="btn"><a href="">아이디찾기</a></span>
				<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
				<span class="btn"><a href="">비밀번호찾기</a></span>
				<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
				<span class="btn"><a href="/user/signUp">회원가입</a></span>
			</div>

			<div class="login--line">소설 로그인으로 시작</div>

			<div class="social--login">
				<div class="social--google btn">
					<img src="/img/google.png" alt="구글로 시작하기">
				</div>
				<div class="social--kakao btn">
					<img src="/img/kakao.png" alt="카카오로 시작하기">
				</div>
				<div class="social--naver btn">
					<img src="/img/naver.png" alt="네이버로 시작하기">
				</div>
			</div>
		</div>
	</div>


	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>