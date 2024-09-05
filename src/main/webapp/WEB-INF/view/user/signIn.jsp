<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
	<div id="in--wrap">
		<div class="flex--wrap">
			<div class="top--title">
				<h1 class="text-center eng">LOGIN</h1>
			</div>

			<form action="/user/signIn" method="POST" enctype="multipart/form-data">
				<div class="login--wrap">
					<input type="text" id="id" name="loginId" placeholder="아이디를 입력해주세요." value="ryeonng">
					<input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요." value="dkssud123!">
					<input type="text" id="id" name="loginId" placeholder="아이디를 입력해주세요." value="rtg369">
					<input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요." value="asd123">
					<button type="submit" class="btn" id="login--btn">로그인</button>
				</div>
			</form>

			<div class="login--items">
				<span class="btn"><a href="/user/findID">아이디찾기</a></span>
				<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
				<span class="btn"><a href="/user/findPassword">비밀번호찾기</a></span>
				<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
				<span class="btn"><a href="/user/signUp">회원가입</a></span>
			</div>

			<div class="login--line">소설 로그인으로 시작</div>

			<div class="social--login">
				<div class="social--google btn">
					<a href="https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?client_id=973551558208-05iorgfbtui4jkb49on38lpuv0o13jf5.apps.googleusercontent.com&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fuser%2Fgoogle&response_type=code&scope=email profile&service=lso&o2v=2&ddm=1&flowName=GeneralOAuthFlow"><img src="/img/google.png" alt="구글로 시작하기"></a>
				</div>
				<div class="social--kakao btn">
					<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=75ba363608bd6f2a3cfbf6acaf901f10&redirect_uri=http://localhost:8080/user/kakao"><img src="/img/kakao.png" alt="카카오로 시작하기"></a>
				</div>
				
				<div class="social--naver btn" id="naver_id_login">
					<a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=fLZ62UlS4udx_2KU4B4r&state=test&redirect_uri=http://localhost:8080/user/naver"><img src="/img/naver.png" alt="네이버로 시작하기"></a>
				</div>
			</div>
		</div>
	</div>
	

	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>