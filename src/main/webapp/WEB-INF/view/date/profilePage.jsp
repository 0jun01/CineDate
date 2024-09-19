<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>


<!-- start of content.jsp(xxx.jsp) -->
<div class="wrap">
	<!-- 형정 누나가 고쳐줄 예정 -->
	<div class="con--item--wrap">
		<div class="item--box">
			<h3>아이템 이름 들어갈자리</h3>
			<span> <img>
			</span> <a class="use--btn" data-id="">사용하기</a>
		</div>
		<div class="item--count">
			<h4>3</h4>
		</div>
	</div>
	<input type="hidden" id="isTown" name="isTown" value="Y"> <input type="hidden" id="userTownMemberInfo" name="userTownMemberInfo" value="">
	<div class="in--wrap">
		<span class="thumb-image"> <img src="/DateProfileIMAGE/${profile.firstUploadFileName}" alt="Profile Image" style="width: 100px; margin-top: 50px;">
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
			<span style="display: flex;"> <img src="/img/corn.png" alt="콘 아이콘" style="width: 20px;">
				<h2>${profile.con}개</h2>
			</span>
		</div>
	</div>
</div>

<!-- Update Profile Form -->
<form action="/date/updateProfile" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
	<table summary="나의 프로필 정보" class="">
		<caption>나의 프로필 정보</caption>
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
						각 서비스(이벤트, 매거진, 영화리뷰 등)의 리뷰 및 덧글 작성 시 등록하신 대표 이미지가 노출됩니다.<br>프로필 이미지 종류를 선택해 주세요.
					</p> <input type="hidden" id="user-image" name="user-image" value="">
					<div class="profile--img">
						<div class="box--contents">
							<span class="thumb--image"> <!-- 1번사진 --> <c:choose>
									<c:when test="${profile.firstUploadFileName != null}">
										<img id="img_userprofileimage6" src="/DateProfileIMAGE/${profile.firstUploadFileName}" alt="프로필 사진" onclick="document.getElementById('profile_upload_file6').click();">
									</c:when>
									<c:otherwise>
										<img id="img_userprofileimage6" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file6').click();">
									</c:otherwise>
								</c:choose> <input type="file" id="profile_upload_file6" name="profile_upload_file6" title="내용" onchange="previewImage(this, 'img_userprofileimage6')" style="display: none;">
							</span> <span class="thumb--image"> <!-- 2번사진 --> <c:choose>
									<c:when test="${profile.secondUploadFileName != null}">
										<img id="img_userprofileimage2" src="/DateProfileIMAGE/${profile.secondUploadFileName}" alt="프로필 사진" onclick="document.getElementById('profile_upload_file2').click();">
									</c:when>
									<c:otherwise>
										<img id="img_userprofileimage2" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file2').click();">
									</c:otherwise>
								</c:choose> <input type="file" id="profile_upload_file2" name="profile_upload_file2" title="내용" onchange="previewImage(this, 'img_userprofileimage2')" style="display: none;">
							</span> <span class="thumb--image"> <!-- 3번사진 --> <c:choose>
									<c:when test="${profile.thirdOriginFileName != null}">
										<img id="img_userprofileimage3" src="/DateProfileIMAGE/${profile.thirdOriginFileName}" alt="프로필 사진" onclick="document.getElementById('profile_upload_file3').click();">
									</c:when>
									<c:otherwise>
										<img id="img_userprofileimage3" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file3').click();">
									</c:otherwise>
								</c:choose> <input type="file" id="profile_upload_file3" name="profile_upload_file3" title="내용" onchange="previewImage(this, 'img_userprofileimage3')" style="display: none;">
							</span> <span class="thumb--image"> <!-- 4번사진 --> <c:choose>
									<c:when test="${profile.fourthOriginFileName != null}">
										<img id="img_userprofileimage4" src="/DateProfileIMAGE/${profile.fourthOriginFileName}" alt="프로필 사진" onclick="document.getElementById('profile_upload_file4').click();">
									</c:when>
									<c:otherwise>
										<img id="img_userprofileimage4" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file4').click();">
									</c:otherwise>
								</c:choose> <input type="file" id="profile_upload_file4" name="profile_upload_file4" title="내용" onchange="previewImage(this, 'img_userprofileimage4')" style="display: none;">
							</span> <span class="thumb--image"> <!-- 5번 사진 --> <c:choose>
									<c:when test="${profile.fifthOriginFileName != null}">
										<img id="img_userprofileimage5" src="/DateProfileIMAGE/${profile.fifthOriginFileName}" alt="프로필 사진" onclick="document.getElementById('profile_upload_file5').click();">
									</c:when>
									<c:otherwise>
										<img id="img_userprofileimage5" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('profile_upload_file5').click();">
									</c:otherwise>
								</c:choose> <input type="file" id="profile_upload_file5" name="profile_upload_file5" title="내용" onchange="previewImage(this, 'img_userprofileimage5')" style="display: none;">
							</span>

						</div>
					</div>
					<p>jpg, gif, BMP, png 파일만 등록 가능합니다. (최대 3MB)</p>
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
									<td><label><input type="radio" name="consent" value="agree" required> 동의하기</label><br> <label><input type="radio" name="consent" value="disagree"
											required> 동의하지 않기</label></td>
								</tr>
							</tbody>
						</table>
						<p class="marginT10">※ 동의를 거부하실 권리가 있으며 이 경우 게시판 작성 시 아이디가 일부 숨김처리되어 보여집니다.</p>
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
			img.src = '/img/Basic.jpg'; // 파일이 선택되지 않은 경우 기본 이미지로 설정
		}
	}

	document.addEventListener('DOMContentLoaded', () => {
	    fetchItemList(); // 페이지가 로드되면 함수 실행
	});

	function fetchItemList() {
	    fetch('http://localhost:8080/date/inventory')
	        .then(response => {
	            if (!response.ok) {
	                throw new Error('Network response was not ok');
	            }
	            return response.json();
	        })
	        .then(data => {
	        	console.log(data);
	            updateItemList(data); // 받아온 데이터를 사용하여 업데이트
	        })
	        .catch(error => {
	            console.error('Error fetching inventory:', error);
	        });
	}

	function updateItemList(itemDataList) {
	    const itemContainer = document.querySelector('.con--item--wrap'); // 아이템을 담을 컨테이너

	    // 기존 아이템들 삭제 (필요한 경우)
	    itemContainer.innerHTML = '';

	    // 데이터 배열을 순회하며 각 아이템에 대한 HTML 생성
	    itemDataList.forEach(itemData => {
	        const itemBox = document.createElement('div');
	        itemBox.classList.add('item--box');

	        // 아이템 이름 추가
	        const itemName = document.createElement('h3');
	        itemName.textContent = itemData.name;
	        itemBox.appendChild(itemName);

	        // 아이템 이미지 추가
	        const itemImageSpan = document.createElement('span');
	        const itemImg = document.createElement('img');
	        itemImg.src = itemData.itemImg;
	        itemImageSpan.appendChild(itemImg);
	        itemBox.appendChild(itemImageSpan);

	        // 사용하기 버튼 추가
	        const useBtn = document.createElement('a');
	        useBtn.className = 'use--btn';
	        useBtn.href = "javascript:void(0)";
	        useBtn.dataset.id = itemData.itemId;
	        useBtn.textContent = '사용하기';
	        
	     // 현재 보유중인 개수 추가
	        const itemCount = document.createElement('div');
	        itemCount.classList.add('item--count');
	        const itemCountValue = document.createElement('h4');
	        itemCountValue.textContent = `X ` + itemData.quantity; // 보유 개수
	        itemCount.appendChild(itemCountValue);
	        itemBox.appendChild(itemCount);
	        
	        // 클릭 시 실행될 이벤트 핸들러 추가
	        useBtn.onclick = function() {
	        	if(itemCount > 0){
	            handleUseButtonClick(itemData.itemId);
	        	} else{
	        		 const userResponse = confirm("아이템 개수가 부족합니다. 아이템을 구매하러 가시겠습니까?");
	        		 if (userResponse) {
	        	            // 사용자가 "예"를 선택했을 경우 아이템 구매 페이지로 이동
	        	            window.location.href = '/date/popcornStore'; // 이동할 경로로 변경
	        	        }
	        	        return;
	        	}
	        		 return;
	        };
	        
	        itemBox.appendChild(useBtn);

	        // 최종적으로 아이템 박스를 컨테이너에 추가
	        itemContainer.appendChild(itemBox);
	    });
	}
	function handleUseButtonClick(itemId) {
	    // 클릭 시 실행될 로직을 여기에 추가
	    console.log('Clicked item ID:', itemId);
	    fetch(`http://localhost:8080/date/useItem`,{
	    	method: 'POST',
       	 	headers: {
       		 'Content-Type': 'application/json',
       	 },
       	 body: JSON.stringify({
       		 itemId: itemId,
       	 })
	    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
        	console.log(data.message);
        	console.log(data.updateItemList);
        	 if (data.updateItemList) {
                 updateItemList(data.updateItemList);  // 갱신된 아이템 목록으로 UI 업데이트
             }
        })
        .catch(error => {
            console.error('Error fetching inventory:', error);
        });
	}
</script>
</body>
</html>