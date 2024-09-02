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

<div class="cols-benefit-info">
    <div class="col-my-con">
        <h3>MY CORN</h3>
        <div class="btn">
            <span style="display: flex;">
                <img src="/img/corn.png" alt="콘 아이콘" style="width: 20px;">
                <h2>X${mycorn}개</h2>
            </span>
        </div>
    </div>
</div>
<br>
<br>
<br>
<br>
<h3>나의 정보 변경</h3>

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
            <span>수정하기</span>
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