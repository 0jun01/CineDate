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
						<input type="text" id="loginId" name="loginId" placeholder="아이디를 입력해주세요. (6~15자)" value="hjeong0711">
						<button type="button" class="duplication" id="duplicationId">중복확인</button>
						<div id="loginIdResult"></div>
						<script type="text/javascript" >
							// 비동기 통신 (AJAX) - Fetch 함수 사용(GET 방식)
							const duplicationId = document.querySelector('#duplicationId');
							const loginIdElement = document.querySelector('#loginId');
							const loginIdResultElement = document.querySelector('#loginIdResult');
							duplicationId.addEventListener("click", function() {
								const loginId = loginIdElement.value;
								const url = "http://localhost:8080/api-user/check-loginId?loginId=" + loginId;
								fetch(url)
								.then(response  => response.json()) // 응답을 JSON 형식으로 반환
								.then(isUse =>{
									console.log("결과확인", isUse);
									if(isUse){
										loginIdResultElement.textContext = "사용가능";
										alert("사용 가능한 아이디입니다.");
									} else {
										loginIdResultElement.textContext = "사용불가능";
										alert("중복된 아이디입니다.");
									}
								})
								.catch(error => {
									console.log("error", error);
									loginIdResultElement.textContext = "잘못된 요청입니다.";
								});
							});
						</script>
					</div>

					<label for="name">이름</label>
					<input type="text" id="name" name="name" placeholder="이름을 입력해주세요." value="유형정">	
					<label for="password">비밀번호</label>
					<input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요. (문자, 숫자,특수문자 포함 8~20자 )" value="asdf123@@">
					
					<label for="passwordEnter">비밀번호 확인</label>
					<input type="password" id="enterPassword" name="enterPassword" placeholder="비밀번호를 입력해주세요. (문자, 숫자,특수문자 포함 8~20자 )" value="asdf123@@">
					
					<div class="join--btn">
				        <label for="email">이메일 주소</label>
				        <input type="email" id="email" name="email" placeholder="이메일 주소를 입력해주세요." value="hjeong0711@gmail.com">
				        <button type="button" class="duplication" id="duplicationEmail">중복확인</button>
				    </div>
				
				    <div class="join--btn">
				        <label for="mailSend">이메일 인증번호</label>
				        <input type="number" id="mailSend" name="mailSend" placeholder="이메일 인증번호를 입력해주세요.">
				        <div class="email--certification">
					        <button type="button" class="btn" id="sendAuthCode">인증번호 요청</button>
					        <button type="button" class="btn" id="sendAuthEnterCode">인증번호 인증</button>
				        </div>
				    </div>
					
					<div class="join--btn">
						<label for="phoneNum">휴대폰 번호</label>
						<input type="number" id="phoneNum" name="phoneNum" placeholder="휴대폰 번호를 입력해주세요." value="01012336544">
						
						<div id="emailResult"></div>
						<script type="text/javascript" >
							// 비동기 통신 (AJAX) - Fetch 함수 사용(GET 방식)
							const duplicationEmail = document.querySelector('#duplicationEmail');
							const emailElement = document.querySelector('#email');
							const emailResultElement = document.querySelector('#emailResult');

							duplicationEmail.addEventListener("click", function() {
								const email = emailElement.value;
								const url = "http://localhost:8080/api-user/check-email?email=" + email;
								fetch(url)
								.then(response  => response.json()) // 응답을 JSON 형식으로 반환
								.then(isUse =>{
									console.log("결과확인", isUse);
									if(isUse){
										emailResultElement.textContext = "사용가능";
										alert("사용 가능한 이메일입니다.");
									} else {
										emailResultElement.textContext = "사용불가능";
										alert("중복된 이메일입니다.");
									}
								})
								.catch(error => {
									console.log("error", error);
									emailResultElement.textContext = "잘못된 요청입니다.";
								});
							});
						</script>
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
						<input type="radio" id="female" name="gender" value="여" checked><span class="gender--items">여자</span>
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

    
	    // 이메일 인증
	    document.addEventListener("DOMContentLoaded", function() {
	        const sendAuthCodeButton = document.querySelector('#sendAuthCode');
	        const emailElement = document.querySelector('#email');
	        const mailSendElement = document.querySelector('#mailSend');
	        
	        sendAuthCodeButton.addEventListener("click", function() {
	        	 event.preventDefault(); // 폼 제출 방지
	            const email = emailElement.value;
	
	            fetch('http://localhost:8080/mail/mail', {
	                method: 'POST',
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	                body: JSON.stringify({ email: email })
	            })
	            .then(response => response.json())
	            .then(data => {
	                if (data.success) {
	                    alert("인증번호가 발송되었습니다.");
	                } else {
	                    alert("인증번호 발송에 실패하였습니다.");
	                }
	            })
	            .catch(error => {
	                console.error("Error:", error);
	                alert("서버와의 통신 오류가 발생했습니다.");
	            });
	        });
	    });


    </script>
	
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>