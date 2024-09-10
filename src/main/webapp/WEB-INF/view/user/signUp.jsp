<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
	<div id="in--wrap">
		<div class="flex--wrap">
			<div class="top--title">
				<h1 class="text-center eng">JOIN</h1>
			</div>

			<form action="/user/signUp" method="post" id="signupForm" >
				<div class="login--wrap">
					<div class="join--btn">
						<label for="loginId">아이디</label> 
						<input type="text" id="loginId" name="loginId" placeholder="아이디를 입력해주세요. (6~15자)" value="hjeong0711" />
						<input type="hidden" id="hiddenLoginId" name="loginId" value="" />
						<button type="button" class="duplication" id="duplicationId">중복확인</button>
						<div id="loginIdResult"></div>

						<script type="text/javascript">
			              // 비동기 통신 (AJAX) - Fetch 함수 사용(GET 방식)
			              const duplicationId = document.querySelector("#duplicationId");
			              const loginIdElement = document.querySelector("#loginId");
			              let isLoginIdChecked = false;
			              duplicationId.addEventListener("click", function () {
			            	 
			            	// 빈칸 검사
			            	const login = loginIdElement.value.trim();
			                    if (!login) {
			                        alert("아이디를 입력해주세요.");
			                        return;
			                    }
			                    
			                 // 글자 수 검사
			                    if (login.length < 6 || login.length > 15) {
			                        alert("아이디는 6자 이상 15자 이하로 입력해주세요.");
			                        return;
			                    }

			                    // 영문자만 허용하는 정규 표현식
			                    const alphanumericRegex = /^[a-zA-Z0-9]+$/;
						        if (!alphanumericRegex.test(login)) {
						            alert("아이디는 영문자와 숫자만 포함해야 합니다.");
						            return;
						        }
			            	  
			                const loginId = loginIdElement.value;
			                const url = "http://localhost:8080/api-user/check-loginId?loginId=" + loginId;
			                fetch(url)
			                  .then((response) => response.json()) // 응답을 JSON 형식으로 반환
			                  .then((isUse) => {
			                    console.log("결과확인", isUse);
			                    if (isUse) {
			                      alert("사용 가능한 아이디입니다.");
			                      
			                      document.querySelector("#hiddenLoginId").value = loginId; // Set hidden field value
			                      document.querySelector("#loginId").disabled = true;
			                      document.querySelector("#duplicationId").disabled = true;
			                      
			                      const verificationIdButton = document.getElementById("duplicationId");
			                      const verificationLoginIdButton = document.getElementById("loginId");
			                      //verificationLoginIdButton.disabled = true;
			                      verificationLoginIdButton.style.backgroundColor = "#efefef";
			                      verificationLoginIdButton.style.color = "#999";
			                      
			                      verificationIdButton.disabled = true;
			                      verificationIdButton.style.backgroundColor = "#d198b2";
			                      
			                    } else {
			                      alert("중복된 아이디입니다.");
			                      loginIdResultElement.textContent = "사용불가능";
			                      isLoginIdChecked = false;
			                    }
			                  })
			                  .catch((error) => {
			                    console.log("error", error);
			                    loginIdResultElement.textContent = "잘못된 요청입니다.";
			                  });
			              });
			            </script>
					</div>

					<label for="name">이름</label>
					<input type="text" id="name" name="name" placeholder="이름을 입력해주세요." value="유형정" />
					<label for="password">비밀번호</label> 
					<input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요. (문자, 숫자,특수문자 포함 8~20자 )" value="asdf123@@" /> 
					<label for="passwordEnter">비밀번호 확인</label> 
					<input type="password" id="enterPassword" name="enterPassword" placeholder="비밀번호를 입력해주세요. (문자, 숫자,특수문자 포함 8~20자 )" value="asdf123@@" />

					<div class="join--btn">
						<label for="email">이메일 주소</label> 
						<input type="email" id="email" name="email" placeholder="이메일 주소를 입력해주세요." value="hjeong0711@gmail.com" />
						<input type="hidden" id="hiddenEmail" name="email" value="" />
						<button type="button" class="duplication" id="duplicationEmail">중복확인</button>
						
						<div id="emailResult"></div>
						<script type="text/javascript">
				              // 비동기 통신 (AJAX) - Fetch 함수 사용(GET 방식)
				              const duplicationEmail = document.querySelector("#duplicationEmail");
				              const emailElement = document.querySelector("#email");
				              let isEmailChecked = false;
				              duplicationEmail.addEventListener("click", function () {
				            	  
				            	  const emailDu = emailElement.value.trim();

				                  // 유효성 검사
				                  if (!emailDu) {
				                      alert("이메일을 입력해주세요.");
				                      return;
				                  }
				                  
					              const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
				                  if (!emailRegex.test(emailDu)) {
				                      alert("유효한 이메일 주소를 입력해주세요.");
				                      return;
				                  }
				                  
				                const email = emailElement.value;
				                const url = "http://localhost:8080/api-user/check-email?email=" + email;
				                fetch(url)
				                  .then((response) => response.json()) // 응답을 JSON 형식으로 반환
				                  .then((isUse) => {
				                    console.log("결과확인", isUse);
				                    if (isUse) {
				                      alert("사용 가능한 이메일입니다.");
				                      
				                      document.querySelector("#hiddenEmail").value = email; // Set hidden field value
				                      document.querySelector("#email").disabled = true;
				                      document.querySelector("#duplicationEmail").disabled = true;
				                      
				                      const verificationEmailButton = document.getElementById("email");
				                      //verificationEmailButton.disabled = true;
				                      verificationEmailButton.style.backgroundColor = "#efefef";
				                      verificationEmailButton.style.color = "#999";
				                   
				                      duplicationEmail.disabled = true;
				                      duplicationEmail.style.backgroundColor = "#d198b2";                                                
				                       
				                      
				                    } else {
				                      alert("중복된 이메일입니다.");
				                      emailResultElement.textContent = "사용불가능";
				                      isEmailChecked = false;
				                    }
				                  })
				                  .catch((error) => {
				                    console.log("error", error);
				                    emailResultElement.textContent = "잘못된 요청입니다.";
				                  });
				              });
            			</script>
					</div>

					<div class="join--btn">
						<label for="mailSend">이메일 인증번호</label>
						<input type="text" id="mailSend" name="mailSend" placeholder="이메일 인증번호를 입력해주세요." />
						<button type="button" class="duplication btn" id="sendAuthCode">인증번호 요청</button>
						<button type="button" class="duplication btn" id="sendAuthEnterCode" style="display: none">인증하기</button>
					</div>

					<div class="join--btn">
						<label for="phoneNum">휴대폰 번호</label>
						<input type="number" id="phoneNum" name="phoneNum" placeholder="휴대폰 번호를 입력해주세요." value="01012336544" />
					</div>

					<label for="birth">생년월일</label>
					<div class="select--birth" id="info--birth">
						<select class="birth" id="year" name="year">
							<option value="default" selected>년도</option>
						</select> 
						<select class="birth" id="month" name="month">
							<option value="default" selected>월</option>
						</select> 
						<select class="birth" id="day" name="day">
							<option value="default" selected>일</option>
						</select>
					</div>

					<label for="gender">성별</label>
					<div class="gender--box">
						<input type="radio" id="male" name="gender" value="남" />
						<span class="gender--items">남자</span>
						<input type="radio" id="female" name="gender" value="여" checked />
						<span class="gender--items">여자</span>
					</div>

					<button type="submit" class="btn" id="join--btn">가입하기</button>
				</div>
			</form>
		</div>
	</div>

	<script>
	
    // 연도와 관련된 셀렉트 박스 요소를 선택
    const yearSelectEl = document.querySelector("#year");

    // 년도 옵션이 생성되었는지 여부를 확인하는 변수
    let isYearOptionExisted = false;

    yearSelectEl.addEventListener("focus", function () {
      // 연도 목록이 생성되지 않았을 때 (최초 클릭 시)
      if (!isYearOptionExisted) {
        isYearOptionExisted = true;
        for (let i = 1950; i <= 2024; i++) {
          const yearOption = document.createElement("option");
          yearOption.setAttribute("value", i);
          yearOption.innerText = i;
          yearSelectEl.appendChild(yearOption);
        }
      }
    });

    // 월과 관련된 셀렉트 박스 요소를 선택
    const monthSelectEl = document.querySelector("#month");

    // 월 옵션이 생성되었는지 여부를 확인하는 변수
    let isMonthOptionExisted = false;

    monthSelectEl.addEventListener("focus", function () {
      // 월 목록이 생성되지 않았을 때 (최초 클릭 시)
      if (!isMonthOptionExisted) {
        isMonthOptionExisted = true;
        for (let i = 1; i <= 12; i++) {
          const monthOption = document.createElement("option");
          monthOption.setAttribute("value", i);
          monthOption.innerText = i;
          monthSelectEl.appendChild(monthOption);
        }
      }
    });

    // 일과 관련된 셀렉트 박스 요소를 선택
    const daySelectEl = document.querySelector("#day");

    // 일 옵션이 생성되었는지 여부를 확인하는 변수
    let isDayOptionExisted = false;

    daySelectEl.addEventListener("focus", function () {
      // 일 목록이 생성되지 않았을 때 (최초 클릭 시)
      if (!isDayOptionExisted) {
        isDayOptionExisted = true;
        for (let i = 1; i <= 31; i++) {
          const dayOption = document.createElement("option");
          dayOption.setAttribute("value", i);
          dayOption.innerText = i;
          daySelectEl.appendChild(dayOption);
        }
      }
    });

    document.getElementById("sendAuthCode").addEventListener("click", function () {
        const email = document.getElementById("email").value;

        // 이메일이 중복되지 않은 경우 인증번호 요청
        const url2 = "http://localhost:8080/mail/verification-requests?email=" + email;
        fetch(url2, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: new URLSearchParams({ email: email }),
        })
          .then((response) => response.json())
          .then((data) => {
            if (data.success) {
              alert("인증번호가 발송되었습니다.");
              
              const sendAuthCodeButton = document.getElementById("sendAuthCode");
              sendAuthCodeButton.style.display = "none";
              
              const sendAuthEnterCode = document.getElementById("sendAuthEnterCode");
              sendAuthEnterCode.style.display = "block";
            } else {
              alert("인증번호 발송에 실패하였습니다.");
            }
          })
          .catch((error) => {
            console.error("Error:", error);
            alert("서버와의 통신 오류가 발생했습니다.");
          });
      });

    // 이메일 인증번호 확인
    document.getElementById("sendAuthEnterCode").addEventListener("click", function () {
        const email = document.getElementById("email").value;
        const authCode = document.getElementById("mailSend").value;
        

        // 서버로 인증 코드 검증 요청
        const emailUrl = "http://localhost:8080/mail/verifications?email=";
        fetch(emailUrl + email + `&code=` + authCode, {
          method: "GET",
        })
          .then((response) => response.json())
          .then((data) => {
            if (data.success) {
              alert("이메일 인증이 성공했습니다.");
              
              const mailSend = document.getElementById("mailSend");
              mailSend.disabled = true;
              mailSend.style.backgroundColor = "#efefef";
              mailSend.style.color = "#999";
              
              sendAuthEnterCode.disabled = true;
              sendAuthEnterCode.style.backgroundColor = "#d198b2";

                // 이메일 인증 완료 플래그 설정
                isEmailVerified = true;
            } else {
              alert("인증 코드가 유효하지 않습니다.");
            }
          })
          .catch((error) => {
            console.error("Error:", error);
            alert("오류가 발생했습니다.");
          });
      });
    
    document.querySelector("#signupForm").addEventListener("submit", function (event) {
        // 아이디 및 이메일 필드 값 확인
        const loginId = document.querySelector("#loginId").value.trim();
        const email = document.querySelector("#email").value.trim();

        if (!loginId) {
            alert("아이디를 입력해주세요.");
            event.preventDefault(); // 폼 제출 방지
            return;
        }

        if (!email) {
            alert("이메일 주소를 입력해주세요.");
            event.preventDefault(); // 폼 제출 방지
            return;
        }

        // 이메일 인증 완료 여부
        const mailSendInput = document.querySelector("#mailSend").value.trim();
        if (!mailSendInput || mailSendInput === "") {
            alert("이메일 인증번호를 입력해 주세요.");
            event.preventDefault(); // 폼 제출 방지
            return;
        }
        
        // 생년월일 선택 확인
        const year = document.querySelector("#year").value;
        const month = document.querySelector("#month").value;
        const day = document.querySelector("#day").value;

        if (year === "default" || month === "default" || day === "default") {
            alert("생년월일을 올바르게 선택해 주세요.");
            event.preventDefault(); // 폼 제출 방지
            return;
        }
    });
    
    

  </script>

	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</div>
