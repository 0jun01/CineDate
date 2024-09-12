<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
	<div id="in--wrap">
		<div class="flex--wrap">
			<div class="top--title">
				<h1 class="text-center pointFont">비밀번호 찾기</h1>
			</div>

			<form action="/user/signIn" method="GET">
				<div class="login--wrap flex--center">
					<span class="find--result" style="text-align: center; line-height: 1.5;">
						입력하신 정보의 임시 비밀번호는 아래와 같습니다.<br>
						로그인 후 <strong style="color: #ff2588">비밀번호를 변경</strong> 해주세요.
					</span>
					<div class="result"> ${password}</div>
					<span></span>
					
					<button type="submit" class="btn" id="join--btn">로그인</button>
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