<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<!-- 성후가 건드는중임 -->

<!-- start of content.jsp(xxx.jsp) -->
<div class="wrap">
	<input type="hidden" id="isTown" name="isTown" value="Y"> <input
		type="hidden" id="userTownMemberInfo" name="userTownMemberInfo"
		value="">
	<div class="in--wrap">
		<span class="thumb-image"> <img src="/img/ksh.jpg"
			alt="님 프로필 사진" style="width: 100px;'"> <span
			class="profile-mask"></span>
		</span>
	</div>
	<div class="top--title">
		<div class="person-info">
			<strong>${profile.id}님</strong> <em>${profile.id}</em> <span>닉네임
				: <i>닉네임을 설정해주세요.</i>
			</span>
		</div>
	</div>
</div>
<div class="cols-benefit-info">
	<div class="col-my-con">
		<h3>MY CORN</h3>
		<div class="btn">
			<span style="display: flex;"> <img src="/img/corn.png"
				alt="콘 아이콘" style="width: 20px;">
				<h2>X${mycorn}개</h2>
			</span>
		</div>
	</div>
</div>

<div class="col--detail">

	<input type="hidden" id="isIPIN" name="isIPIN"
		value="HWVxg2oRKNVYapPOMKh3kVxBLkoYselGuGHR5jHrTV6CnR87LOIxZ23SJ8FUp5B/9Swf7xfLvDAyA48Sjm1QNg==">
	<div class="mypage">
		<h3>나의 정보</h3>
	</div>
	<!--1-->
	<form name="aspnetForm" method="post"
		action="./edit-myinfo-myprofile.aspx" id="aspnetForm"
		enctype="multipart/form-data" novalidate="novalidate">
		<div>
			<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE"
				value="/wEPDwULLTE5NDY1OTc2MjJkZGXGZF9rPaBjXwH1HbdmUHs5/x2S">
		</div>

		<div>

			<input type="hidden" name="__VIEWSTATEGENERATOR"
				id="__VIEWSTATEGENERATOR" value="5FA425E8"> <input
				type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION"
				value="/wEdAAPab1W9EqCtJtKoS74voskW1JUVjiIIXAQpNIHKkzj23tvY44NDYVtgxRb4YZO0EMk1AUcKYOmn5JzQRJaQcdy8Ti+h2g==">
		</div>
		<div class="tbl-form">
			<table summary="나의프로필정보 이름,아이디, 닉네임,프로필이미지 표기">
				<caption>나의프로필정보</caption>
				<colgroup>
					<col width="19%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">이름</th>
						<td><strong>${username}</strong></td>
					</tr>
					<tr>
						<th scope="row">아이디</th>
						<td><span>${userId}</span></td>
					</tr>
					<tr>
						<th scope="row"><label for="nick_name">닉네임</label></th>
						<td>
							<p>한글, 영문, 숫자 혼용 가능 (한글 기준 10자 이내)</p>
							 <input type="text" id="nick_name" name="nick_name" value="" required="required" maxlength="10" class="s-medium" >
							<button id="check_duplication" type="button" class="round gray">
								<span>중복확인</span>
							</button>
						</td>
					</tr>

					<tr>
						<th scope="row">프로필이미지</th>
						<td>
							<p class="profile--info">
								각 서비스(이벤트, 매거진, 영화리뷰 등)의 리뷰 및 덧글작성시 등록하신 대표이미지가 노출됩니다.<br>프로필
								이미지 종류를 선택해 주세요.
							</p> <input type="hidden" id="user-image" name="user-image" value="">
							<input type="hidden" id="user-image"
							name="user-image" value="">
							<div class="profile--img">
								<div class="box--image">
									<span class="thumb--image"> <img
										id="img_userprofileimage"
										src="http://img.cgv.co.kr/R2014/images/common/default_profile.gif"
										alt="님 프로필 사진" onerror="errorImage(this, {type:'profile'})">
										<span class="profile-mask"></span>
									</span>
								</div>
								<div class="box--contents">
									<p>jpg, gif, BMP 파일만 등록 가능합니다. (최대 3M)</p>
									<input type="file" id="profile_upload_file"
										name="profile_upload_file" title="내용">
								</div>
							</div> 
							<div class="tbl--re marginT20">
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
											<th scope="row">프로필 사진, 닉네임</th>
											<td>
												<ul class="dep1_lst">
													<li class="dep1_lst_li">· 공개된 게시판 서비스의 이미지 등록</li>
													<li class="dep1_lst_li">· 공개된 게시판의 익명성 보장</li>
												</ul>
											</td>
											<td>약관 철회 후 1주일 까지</td>
											<td>
												<!-- 동의함 또는 동의안함 체크시 inp_inbox에 on 클래스 toggle 처리 필요 --> <span
												class="inp_inbox on"> <input
													name="rd_agree_profileInfo" id="rd_agree_profileInfo_Y"
													type="radio" value="Y"><label for="agreeChk2-1">동의함</label>
											</span> <span class="inp_inbox"> <input
													name="rd_agree_profileInfo" id="rd_agree_profileInfo_N"
													type="radio" value="N" checked="checked"><label
													for="agreeChk2-2">동의안함</label>
											</span>
											</td>
										</tr>
									</tbody>
								</table>
								<p class="marginT10">※ 동의를 거부하실 권리가 있으며 이 경우 게시판 작성 시 아이디가
									일부 숨김처리되어 보여집니다.</p>
							</div>

						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="set--btn aright">
			<button type="submit" id="set_profile" class="btn">
				<span>등록하기</span>
			</button>
		</div>
	</form>
</div>
<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

</body>
</html>
