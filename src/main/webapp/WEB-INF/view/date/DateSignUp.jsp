<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						<input type="text" id="nickName" name="nickName" placeholder="닉네임를 입력해주세요. search(3~10자)">
						<button class="duplication" id="duplicationId">중복확인</button>
					</div>

					<label for="introduce">자기소개</label>
					<input type="text" id="introduce" name="introduce" placeholder="자기소개을 입력해주세요.(최대50자)">
					
					<label for="mFileOne">첫번 째 사진 </label>
					<input type="file" id="mFileOne" name="mFileOne" placeholder="사진을 업로드 해주세요">
					
					<label for="mFileTwo">두번 째 사진 </label>
					<input type="file" id="mFileTwo" name="mFileTwo" placeholder="사진을 업로드 해주세요">
					

					<button type="submit" class="btn" id="join--btn">가입하기</button>
				</div>
			</form>
		</div>
	</div>
	
	
	
	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>