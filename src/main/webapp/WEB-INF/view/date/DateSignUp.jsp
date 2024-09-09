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

		            <label for="mFileOne">사진(1번, 2번은 필수)</label>
					<div class="thumb--image" style="width: 400px; display: flex; justify-content: space-between;"> <!-- 3번사진 -->
	                   <img id="img_userprofileimage1" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file1').click();" width="18%">
	                   <input type="file" id="profile_upload_file1" name="mFileOne" title="내용" onchange="previewImage(this, 'img_userprofileimage1')" style="display: none;">
	                   
	                   <img id="img_userprofileimage2" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file2').click();" width="18%">
	                   <input type="file" id="profile_upload_file2" name="mFileTwo" title="내용" onchange="previewImage(this, 'img_userprofileimage2')" style="display: none;">
	                   
	                   <img id="img_userprofileimage3" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file3').click();" width="18%">
	                   <input type="file" id="profile_upload_file3" name="mFile3" title="내용" onchange="previewImage(this, 'img_userprofileimage3')" style="display: none;">
	                   
	                   <img id="img_userprofileimage4" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file4').click();" width="18%">
	                   <input type="file" id="profile_upload_file4" name="mFile4" title="내용" onchange="previewImage(this, 'img_userprofileimage4')" style="display: none;">
	                   
	                   <img id="img_userprofileimage5" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file5').click();" width="18%">
	                   <input type="file" id="profile_upload_file5" name="mFile5" title="내용" onchange="previewImage(this, 'img_userprofileimage5')" style="display: none;">
	                   
                     </div>
					
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
        function previewImage(input, imgId) {
            var file = input.files[0];
            var img = document.getElementById(imgId);
            
            if (file) {
                var reader = new FileReader();
                
                reader.onload = function(e) {
                    img.src = e.target.result; // 미리보기 이미지 업데이트
                };
                
                reader.readAsDataURL(file);
            } else {
                img.src = '/img/Basic.jpg'; // 파일이 선택되지 않은 경우 기본 이미지로 설정
            }
        }
    </script>

    <%@ include file="/WEB-INF/view/layout/footer.jsp"%>
</div>
