<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>


<div id="wrap">
	<div id="in--wrap">
		<div class="flex--wrap">
			<input type="hidden" id="isTown" name="isTown" value="Y">
			<input type="hidden" id="userTownMemberInfo" name="userTownMemberInfo" value="">
			<div class="top--title">
				<h1 class="eng">
					My Page
				</h1>
				
			</div>

			<!-- Update User Form -->
			<form action="/user/updateUser" method="post" id="mypage--form">
				<label for="id">아이디</label>
				<input type="text" id="id" name="id" value="${user.loginId}" required />
				
				<input type="hidden" name="userId" value="">
				<label for="name">이름</label> <input type="text" id="name" name="name" value="${user.name}" />
				
				<label for="password">비밀번호</label>
				<input type="password" id="password" name="password" value="${user.password}" required />
				
				<div class="password--wrap">
					<span>비밀번호 보이기</span>
					<input type="checkbox" id="show--password" onclick="togglePassword()">
				</div>
				
				<label for="email">이메일 주소</label>
				<input type="email" id="email" name="email" value="${user.email}" required />
				
				<label for="phoneNum">휴대폰 번호</label>
				<input type="number" id="phoneNum" name="phoneNum" value="${user.phoneNum}" required />

				<div class="set--btn aright">
					<button type="submit" id="set--profile" class="btn">수정하기</button>
				</div>
			</form>
			

</div>
</div>
</div>

<script>
    // 비밀번호 표시/숨기기 기능
    function togglePassword() {
        var passwordField = document.getElementById('password');
        var showPasswordCheckbox = document.getElementById('show-password');
        if (showPasswordCheckbox.checked) {
            passwordField.type = 'text';
        } else {
            passwordField.type = 'password';
        }
    }
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

		<script>
			// 비밀번호 표시/숨기기 기능
			function togglePassword() {
				var passwordField = document.getElementById('password');
				var showPasswordCheckbox = document
						.getElementById('show--password');
				if (showPasswordCheckbox.checked) {
					passwordField.type = 'text';
				} else {
					passwordField.type = 'password';
				}
			}
		</script>
		<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
