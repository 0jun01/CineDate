<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<!-- 성후 -->

<!-- start of content.jsp(xxx.jsp) -->
<div class="wrap">
    <input type="hidden" id="isTown" name="isTown" value="Y">
    <input type="hidden" id="userTownMemberInfo" name="userTownMemberInfo" value="">
    <div class="in--wrap">
        <span class="thumb-image">
            <img src="${imageUrl}" alt="Profile Image" style="width: 100px; margin-top: 50px;">
        </span>
    </div>
    <div class="top--title">
        <div class="person-info">
            <span>닉네임 : <i>${profile.nickName}</i></span>
        </div>
    </div>
</div>

<div class="cols-benefit-info">
    <div class="col-my-con">
        <h3>MY CORN</h3>
        <div class="btn">
            <span style="display: flex;">
                <img src="/img/corn.png" alt="콘 아이콘" style="width: 20px;">
                <h2>${profile.con}개</h2>
            </span>
        </div>
    </div>
</div>

<!-- Update Profile Form -->
<form action="/date/updateProfile" method="post" enctype="multipart/form-data">
    <table summary="나의 프로필 정보" class="">
        <caption>나의 프로필 정보</caption>
        <input type="hidden" name="userId" value="${profile.userId}">
        <colgroup>
            <col width="19%">
            <col width="*">
        </colgroup>
        <tbody>
            <tr>
                <th scope="row">닉네임</th>
                <td><input type="text" id="nickname" name="nickname" value="${profile.nickName}" required></td>
            </tr>
            <tr>
                <th scope="row">자기소개</th>
                <td><input type="text" id="introduce" name="introduce" value="${profile.introduce}" required></td>
            </tr>
            <tr>
                <th scope="row">프로필 이미지</th>
                <td>
                    <p class="profile--info">
                        각 서비스(이벤트, 매거진, 영화리뷰 등)의 리뷰 및 덧글 작성 시 등록하신 대표 이미지가 노출됩니다.<br>프로필
                        이미지 종류를 선택해 주세요.
                    </p>
                    <input type="hidden" id="user-image" name="user-image" value="">
                    <div class="profile--img">
                        <div class="box--image">
                            <span class="thumb--image">
                                <img id="img_userprofileimage6" src="/img/usernone.jpg" alt="프로필 사진">
                            </span>
                        </div>
                        <div class="box--contents">
                            <p>jpg, gif, BMP, png 파일만 등록 가능합니다. (최대 3MB)</p>
                            <input type="file" id="profile_upload_file6" value="이미지 미리보기" name="profile_upload_file6" title="내용" onchange="previewImage(this, 'img_userprofileimage6')">
                            <input type="file" id="profile_upload_file1" name="profile_upload_file1" title="내용" onchange="previewImage(this, 'img_userprofileimage1')">
                            <input type="file" id="profile_upload_file2" name="profile_upload_file2" title="내용" onchange="previewImage(this, 'img_userprofileimage2')">
                            <input type="file" id="profile_upload_file3" name="profile_upload_file3" title="내용" onchange="previewImage(this, 'img_userprofileimage3')">
                            <input type="file" id="profile_upload_file4" name="profile_upload_file4" title="내용" onchange="previewImage(this, 'img_userprofileimage4')">
                            <input type="file" id="profile_upload_file5" name="profile_upload_file5" title="내용" onchange="previewImage(this, 'img_userprofileimage5')">
                            <input type="file" id="profile_upload_file7" name="profile_upload_file7" title="내용" onchange="previewImage(this, 'img_userprofileimage7')">
                        </div>
                    </div>
                    <div class="a">
                        <table style="width: 100%;" summary="개인정보 수집 및 활용 동의 표">
                            <caption>개인정보 수집 및 활용 동의</caption>
                            <colgroup>
                                <col style="width: 16%;">
                                <col style="width: 34%;">
                                <col style="width: 34%;">
                                <col style="width: 16%;">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col">항목</th>
                                    <th scope="col">이용목적</th>
                                    <th scope="col">보유기간</th>
                                    <th scope="col">동의여부</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">이름, 프로필 사진, 아이디</th>
                                    <td>
                                        <ul class="dep1_lst">
                                            <li class="dep1_lst_li">· 공개된 게시판 서비스의 이미지 등록</li>
                                            <li class="dep1_lst_li">· 공개된 게시판의 익명성 보장</li>
                                        </ul>
                                    </td>
                                    <td>약관 철회 후 1주일 까지</td>
                                    <td><label><input type="radio" name="consent" value="agree" required> 동의하기</label><br> <label><input type="radio" name="consent" value="disagree" required> 동의하지 않기</label></td>
                                </tr>
                            </tbody>
                        </table>
                        <p class="marginT10">※ 동의를 거부하실 권리가 있으며 이 경우 게시판 작성 시 아이디가 일부
                            숨김처리되어 보여집니다.</p>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
    <div class="set--btn aright">
        <button type="submit" id="set_profile" class="btn">
            <span>수정하기</span>
        </button>
    </div>
</form>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

<script>
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
        img.src = '/img/usernone.jpg'; // 파일이 선택되지 않은 경우 기본 이미지로 설정
    }
}
</script>
</body>
</html>
