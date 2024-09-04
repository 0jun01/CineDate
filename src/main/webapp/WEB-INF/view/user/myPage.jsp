<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<!-- 성후 -->

<!-- start of content.jsp(xxx.jsp) -->
<div class="wrap">
    <input type="hidden" id="isTown" name="isTown" value="Y"> 
    <input type="hidden" id="userTownMemberInfo" name="userTownMemberInfo" value="">
    <div class="top--title">
        <div class="person-info">
            <em>${user.loginId}</em> 
            <strong>${user.name}님</strong> 
        </div>
    </div>
</div>
<h1>나의 정보 변경</h1>
<br>
<br>
<br>
<br>

<!-- Update User Form -->
<form action="/user/updateUser" method="post">
 <input type="hidden" name="userId" value="${user.loginId}">
    <table summary="나의 프로필 정보" class="">
        <caption>나의 프로필 정보</caption>
        <colgroup>
            <col width="19%">
            <col width="*">
        </colgroup>
        <tbody>
            <tr>
                <th scope="row">이름</th>
                  <td><span>${user.name}</span></td>
            </tr>
            <tr>
                 <th scope="row">비밀번호</th>
                <td>
                    <input type="password" id="password" name="password" value="${user.password}" required>
                    <input type="checkbox" id="show-password" onclick="togglePassword()"> 비밀번호 보이기
                </td>
            </tr>
            <tr>
                <th scope="row">이메일</th>
               <td><input type="email" id="email" name="email" value="${user.email}" required></td>
                </tr>
            <tr>
                <th scope="row">휴대폰번호</th>
               <td><input type="text" id="phoneNum" name="phoneNum" value="${user.phoneNum}" required></td>
                </tr>
                
   
        </tbody>
    </table>
    <div class="set--btn aright">
        <button type="submit" id="set_profile" class="btn">
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            <span>수정하기</span>
=======
            <span>등록하기</span>
>>>>>>> ed11005 (mypage이미지미리보기추가)
=======
            <span>수정하기</span>
>>>>>>> 5a6eb19 (로그인 및 회원가입 완료)
=======
=======
>>>>>>> fce9943 (event 시작)
=======
>>>>>>> f3197cc (mypage이미지미리보기추가)
            <span>수정하기</span>
=======
            <span>등록하기</span>
>>>>>>> 90a04a5 (mypage이미지미리보기추가)
<<<<<<< HEAD
>>>>>>> 671bbcf (mypage이미지미리보기추가)
=======
=======
            <span>수정하기</span>
>>>>>>> b0fb7d2 (event 시작)
<<<<<<< HEAD
>>>>>>> fce9943 (event 시작)
=======
=======
            <span>수정하기</span>
=======
            <span>등록하기</span>
>>>>>>> ed11005 (mypage이미지미리보기추가)
>>>>>>> fc104bb (mypage이미지미리보기추가)
>>>>>>> f3197cc (mypage이미지미리보기추가)
        </button>
    </div>
</form>
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
</body>
</html>