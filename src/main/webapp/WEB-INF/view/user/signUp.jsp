<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
	<div id="in--wrap">
		<div class="flex--wrap">
			<div class="top--title">
				<h1 class="text-center eng">JOIN</h1>
			</div>

			<form action="/user/signUp" method="post">
				<div class="login--wrap">
					<div class="join--btn">
						<label for="loginId">아이디</label>
						<input type="text" id="loginId" name="loginId" placeholder="아이디를 입력해주세요. (6~15자)">
						<button class="duplication" id="duplicationId">중복확인</button>
					</div>

					<label for="name">이름</label>
					<input type="text" id="name" name="name" placeholder="이름을 입력해주세요.">
					
					<label for="password">비밀번호</label>
					<input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요. (문자, 숫자,특수문자 포함 8~20자 )">
					
					<label for="passwordEnter">비밀번호 확인</label>
					<input type="password" id="enterPassword" name="enterPassword" placeholder="비밀번호를 입력해주세요. (문자, 숫자,특수문자 포함 8~20자 )">
					
					<label for="email">이메일 주소</label>
					<input type="email" id="email" name="email" placeholder="이메일 주소를 입력해주세요.">
					
					
					<div class="join--btn">
						<label for="phoneNum">휴대폰 번호</label>
						<input type="number" id="phoneNum" name="phoneNum" placeholder="휴대폰 번호를 입력해주세요.">
						<button class="duplication" id="duplicationPhone">중복확인</button>
					</div>
					
					<label for="birth">생년월일</label>
					<div class="select--birth" id="info--birth">
						<select class="birth" id="year" name="year">
						    <option value="default" disabled selected>년도</option>
						</select>
						<select class="birth" id="month" name="month">
						    <option value="default" disabled selected>월</option>
						</select>
						<select class="birth" id="day" name="day">
						    <option value="default" disabled selected>일</option>
						</select>
					</div>

					<label for="gender">성별</label>
					<div class="gender--box">
						<input type="radio" id="male" name="gender" value="남"><span class="gender--items">남자</span>
						<input type="radio" id="female" name="gender" value="여"><span class="gender--items">여자</span>
					</div>

					<button type="submit" class="btn" id="join--btn">가입하기</button>
				</div>
			</form>
		</div>
	</div>
	
	<script>
        // 연도와 관련된 셀렉트 박스 요소를 선택
        const yearSelectEl = document.querySelector('#year');
    
        // 년도 옵션이 생성되었는지 여부를 확인하는 변수
        let isYearOptionExisted = false;
    
        yearSelectEl.addEventListener('focus', function () {
            // 연도 목록이 생성되지 않았을 때 (최초 클릭 시)
            if (!isYearOptionExisted) {
                isYearOptionExisted = true;
                for (let i = 1950; i <= 2024; i++) {
                    const yearOption = document.createElement('option');
                    yearOption.setAttribute('value', i);
                    yearOption.innerText = i;
                    yearSelectEl.appendChild(yearOption);
                }
            }
        });

        // 월과 관련된 셀렉트 박스 요소를 선택
        const monthSelectEl = document.querySelector('#month');
    
        // 월 옵션이 생성되었는지 여부를 확인하는 변수
        let isMonthOptionExisted = false;
    
        monthSelectEl.addEventListener('focus', function () {
            // 월 목록이 생성되지 않았을 때 (최초 클릭 시)
            if (!isMonthOptionExisted) {
                isMonthOptionExisted = true;
                for (let i = 1; i <= 12; i++) {
                    const monthOption = document.createElement('option');
                    monthOption.setAttribute('value', i);
                    monthOption.innerText = i;
                    monthSelectEl.appendChild(monthOption);
                }
            }
        });

        // 일과 관련된 셀렉트 박스 요소를 선택
        const daySelectEl = document.querySelector('#day');
    
        // 일 옵션이 생성되었는지 여부를 확인하는 변수
        let isDayOptionExisted = false;
    
        daySelectEl.addEventListener('focus', function () {
            // 일 목록이 생성되지 않았을 때 (최초 클릭 시)
            if (!isDayOptionExisted) {
                isDayOptionExisted = true;
                for (let i = 1; i <= 31; i++) {
                    const dayOption = document.createElement('option');
                    dayOption.setAttribute('value', i);
                    dayOption.innerText = i;
                    daySelectEl.appendChild(dayOption);
                }
            }
        });

    </script>
	
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>