<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
	<div id="in--wrap">
		<div class="flex--wrap">
			<div class="top--title">
				<h1 class="text-center eng">JOIN</h1>
			</div>

			<form action="" method="">
				<div class="login--wrap">
					<div class="join--btn">
						<label for="id">아이디</label> <input type="text" id="id" name="id" placeholder="아이디를 입력해주세요. (6~15자)">
						<button class="duplication">
							<a href="">중복확인</a>
						</button>
					</div>

					<label for="name">이름</label> <input type="text" id="name" name="name" placeholder="이름을 입력해주세요."> <label for="password">비밀번호</label> <input type="password"
						id="password" name="password" placeholder="비밀번호를 입력해주세요. (문자, 숫자,특수문자 포함 8~20자 )"> <label for="passwordEnter">비밀번호 확인</label> <input type="password"
						id="passwordEnter" name="passwordEnter" placeholder="비밀번호를 입력해주세요. (문자, 숫자,특수문자 포함 8~20자 )"> <label for="email">이메일 주소</label> <input type="email" id="email"
						name="email" placeholder="이메일 주소를  입력해주세요."> <label for="phone">휴대폰 번호</label> <input type="number" id="phone" name="phone" placeholder="휴대폰 번호를  입력해주세요."> <label
						for="birth">생년월일</label>
					<div class="select--birth" id="info--birth">
						<select class="birth" id="year" name="birth">
							<option disabled selected>년도</option>
						</select> <select class="birth" id="month" name="birth">
							<option disabled selected>월</option>
						</select> <select class="birth" id="day" name="birth">
							<option disabled selected>일</option>
						</select>
					</div>

					<label for="gender">성별</label>
					<div class="gender--box">
						<input type="radio" id="female" name="gender"><span class="gender--items">여자</span> <input type="radio" id="male" name="gender"><span class="gender--items">남자</span>
					</div>

					<button type="submit" class="btn" id="join--btn">가입하기</button>
				</div>
			</form>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>