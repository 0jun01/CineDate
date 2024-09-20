<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
	<div id="in--wrap">
		<h1 class="section--title eng">My Page</h1>

		<div class="thumb--wrap">
			<div class="thumb--image">
				<img src="/image/${profile.firstUploadFileName}" alt="${profile.firstUploadFileName}">
			</div>
			<div class="person--info--wrap">
				<span class="person-info">아이디 : ${profile.userId}</span>
				<span class="person-info">닉네임 : ${profile.nickName}</span>
				<hr class="hr">
				<div class="corn--wrap">
					<span>MY CORN</span>
					<img src="/img/corn.png" alt="콘 아이콘">
					<p class="cornp">${profile.con}개</p>
				</div>
			</div>
		</div>

		<div class="cols-benefit-info">
			<div class="col-my-con">
				<div class="btn"></div>
			</div>
		</div>

		<!--  팝콘 선택 폼 
		<div class="popcorn--charge--box">
		    <form action="/date/popcornStore" method="POST">
		        <p>팝콘을 선택하세요:</p>
		        <label> <input type="radio" name="popcorn" value="1" /> 1개
		        </label><br /> <label> <input type="radio" name="popcorn" value="10" /> 10개
		        </label><br /> <label> <input type="radio" name="popcorn" value="30" /> 30개
		        </label><br /> <label> <input type="radio" name="popcorn" value="50" /> 50개
		        </label><br /> <label> <input type="radio" name="popcorn" value="100" /> 100개
		        </label><br />
		        <button type="submit" value="제출">제출</button>
		    </form>
		</div-->

		<!-- Update Profile Form -->
		<form action="/date/updateProfile" method="post" enctype="multipart/form-data" onsubmit="return validateForm()" id="profile--wrap">
			<table class="date--profile" summary="나의 프로필 정보">
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
								각 서비스(이벤트, 매거진, 영화리뷰 등)의 리뷰 및 덧글 작성 시 등록하신 대표 이미지가 노출됩니다.<br> 프로필 이미지 종류를 선택해 주세요.
							</p>
							<input type="hidden" id="user-image" name="user-image" value="">
							<div class="profile--img">
								<div class="box--contents">
									<span class="thumb--image">
										<!-- 1번사진 -->
										<c:choose>
											<c:when test="${profile.firstUploadFileName != null}">
												<img id="img_userprofileimage6" src="/image/${profile.firstUploadFileName}" alt="프로필 사진" onclick="document.getElementById('profile_upload_file6').click();">
											</c:when>
											<c:otherwise>
												<img id="img_userprofileimage6" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file6').click();">
											</c:otherwise>
										</c:choose>
										<input type="file" id="profile_upload_file6" name="profile_upload_file6" title="내용" onchange="previewImage(this, 'img_userprofileimage6')" style="display: none;">
									</span>
									<span class="thumb--image"> 
										<!-- 2번사진 -->
										<c:choose>
											<c:when test="${profile.secondUploadFileName != null}">
												<img id="img_userprofileimage2" src="/image/${profile.secondUploadFileName}" alt="프로필 사진" onclick="document.getElementById('profile_upload_file2').click();">
											</c:when>
											<c:otherwise>
												<img id="img_userprofileimage2" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file2').click();">
											</c:otherwise>
										</c:choose>
										<input type="file" id="profile_upload_file2" name="profile_upload_file2" title="내용" onchange="previewImage(this, 'img_userprofileimage2')" style="display: none;">
									</span>
									<span class="thumb--image">
										<!-- 3번사진 -->
										<c:choose>
											<c:when test="${profile.thirdOriginFileName != null}">
												<img id="img_userprofileimage3" src="/image/${profile.thirdOriginFileName}" alt="프로필 사진" onclick="document.getElementById('profile_upload_file3').click();">
											</c:when>
											<c:otherwise>
												<img id="img_userprofileimage3" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file3').click();">
											</c:otherwise>
										</c:choose>
										<input type="file" id="profile_upload_file3" name="profile_upload_file3" title="내용" onchange="previewImage(this, 'img_userprofileimage3')" style="display: none;">
									</span>
									<span class="thumb--image">
										<!-- 4번사진 -->
										<c:choose>
											<c:when test="${profile.fourthOriginFileName != null}">
												<img id="img_userprofileimage4" src="/image/${profile.fourthOriginFileName}" alt="프로필 사진" onclick="document.getElementById('profile_upload_file4').click();">
											</c:when>
											<c:otherwise>
												<img id="img_userprofileimage4" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file4').click();">
											</c:otherwise>
										</c:choose>
										<input type="file" id="profile_upload_file4" name="profile_upload_file4" title="내용" onchange="previewImage(this, 'img_userprofileimage4')" style="display: none;">
									</span>
									<span class="thumb--image">
										<!-- 5번 사진 -->
										<c:choose>
											<c:when test="${profile.fifthOriginFileName != null}">
												<img id="img_userprofileimage5" src="/image/${profile.fifthOriginFileName}" alt="프로필 사진" onclick="document.getElementById('profile_upload_file5').click();">
											</c:when>
											<c:otherwise>
												<img id="img_userprofileimage5" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file5').click();">
											</c:otherwise>
										</c:choose>
										<input type="file" id="profile_upload_file5" name="profile_upload_file5" title="내용" onchange="previewImage(this, 'img_userprofileimage5')" style="display: none;">
									</span>
								</div>
							</div>
							<p class="profile--info">jpg, gif, BMP, png 파일만 등록 가능합니다. (최대 3MB)</p>
								</td>
							</tr>
							<tr>
								<th style="line-height: 1.3">개인정보 수집 및<br>활용 동의</th>
								<td>
									<div class="personal--info--wrap">
										<div class="personal--info--container">
											<div class="personal per">
												<p class="personal--title">항목</p>
												<p>이름, 프로필 사진, 아이디</p>
											</div>
											<div class="personal">
												<p class="personal--title">이용목적</p>
												<p style="line-height: 1.5;">공개된 게시판 서비스의 이미지 등록<br>공개된 게시판의 익명성 보장</p>
											</div>
											<div class="personal">
												<p class="personal--title">보유기간</p>
												<p>약관 철회 후 1주일 까지</p>
											</div>
										</div>
										
									</div>
								</td>
							</tr>
							<tr>
								<th></th>
								<td class="margintop20">
									<p>동의여부</p>
									<div class="label--wrap">
										<label style="display: flex; align-items: center; gap: 10px;"><input type="radio" name="consent" value="agree" required> 동의하기</label>
										<label style="display: flex; align-items: center; gap: 10px;"><input type="radio" name="consent" value="disagree" required>동의하지 않기</label>
									</div>
									<p class="marginT10" style="color: #ff0000; font-size: 12px;">※ 동의를 거부할 권리가 있으며, 이 경우 게시판 작성 시 아이디가 일부 숨김 처리 되어 보여집니다.</p>
								</td>
							</tr>
					</tbody>
				</table>
							<button type="submit" class="edit-button btn">수정하기</button>
			</form>
		</div>
	</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
