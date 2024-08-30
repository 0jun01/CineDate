<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
    <div id="in--wrap">
        <div class="flex--wrap">
            <div class="top--title">
                <h1 class="text-center eng">PROFILE</h1>
            </div>

            <form action="/date/signUp" method="post" enctype="multipart/form-data">
                <div class="login--wrap">
                    <div class="join--btn">
                        <label for="nickName">닉네임</label>
                        <input type="text" id="nickName" name="nickName" placeholder="닉네임을 입력해주세요. (3~10자)" required>
                        <button type="button" class="duplication" id="nickNameSelect">중복확인</button>
                    </div>

                    <label for="introduce">자기소개</label>
                    <input type="text" id="introduce" name="introduce" placeholder="자기소개를 입력해주세요. (최대 50자)">

                    <label for="mFileOne">첫 번째 사진</label>
                    <input type="file" id="mFileOne" name="mFileOne" accept="image/*" placeholder="사진을 업로드 해주세요">

                    <label for="mFileTwo">두 번째 사진</label>
                    <input type="file" id="mFileTwo" name="mFileTwo" accept="image/*" placeholder="사진을 업로드 해주세요">

                    <button type="submit" class="btn" id="join--btn">가입하기</button>
                </div>
            </form>
        </div>
    </div>

    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function() {
            const btn = document.querySelector("#nickNameSelect");
            const username = document.querySelector("#nickName");

            btn.addEventListener("click", function() {
                const nickName = username.value;
                fetch('http://localhost:8080/duplication/checkNickName?nickName=' + nickName)
                    .then(response => response.json()) // 응답을 JSON 형식으로 반환
                    .then(isUse => {
                    	
                        if (isUse) {
                            alert("사용 가능");
                        } else {
                            alert("중복된 이름입니다");
                        }
                    })
                    .catch(error => {
                        console.log("error ", error);
                    });
            });
        });
    </script>

    <%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</div>
