<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파트너 디테일</title>
<style type="text/css">
/* 폰트 */
@font-face {
	font-family: 'NanumSquareNeoBold';
	src:
		url(https://hangeul.pstatic.net/hangeul_static/webfont/NanumSquareNeo/NanumSquareNeoTTF-cBd.eot);
	src:
		url(https://hangeul.pstatic.net/hangeul_static/webfont/NanumSquareNeo/NanumSquareNeoTTF-cBd.eot?#iefix)
		format("embedded-opentype"),
		url(https://hangeul.pstatic.net/hangeul_static/webfont/NanumSquareNeo/NanumSquareNeoTTF-cBd.woff)
		format("woff"),
		url(https://hangeul.pstatic.net/hangeul_static/webfont/NanumSquareNeo/NanumSquareNeoTTF-cBd.ttf)
		format("truetype");
}

body {
	font-family: 'NanumSquareNeoBold';
	color: #333;
	background-color: #f4f4f4;
	margin: 0;
	padding: 20px;
}

#profile--wrap {
	display: flex;
	flex-direction: column;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
	max-width: 400px;
	position: relative;
	margin: auto;
}

.representative--image {
	width: 100%;
	height: 300px;
	overflow: hidden;
	margin-bottom: 10px;
	border-radius: 8px;
}

.representative--image::before {
	content: '🦩';
	display: block;
	font-size: 36px;
	position: absolute;
	top: 0;
	left: 0;
}

.representative--image::after {
	content: '🦩';
	display: block;
	font-size: 36px;
	position: absolute;
	bottom: 0;
	right: 0;
}

.representative--image img {
	width: 100%;
	height: 100%;
	object-fit: cover;
	transition: transform 0.3s ease;
}

.profile-images {
	width: 100%;
	display: flex;
	gap: 10px;
	margin-bottom: 20px;
}

.profile-images img {
	width: 20%;
	height: 80px;
	object-fit: cover;
	cursor: pointer;
	transition: transform 0.3s ease, box-shadow 0.3s ease;
	border-radius: 4px;
}

.profile-images img:hover {
	transform: scale(1.1);
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
}

.modal {
	display: flex;
	justify-content: center;
	align-items: center;
	position: fixed;
	z-index: 1000;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.8);
	overflow: hidden;
}

.modal-content {
	max-width: 90%;
	max-height: 90%;
	margin: auto;
}

.close {
	position: absolute;
	top: 20px;
	right: 20px;
	font-size: 36px;
	font-weight: bold;
	color: white;
	cursor: pointer;
}

.hidden {
	display: none;
}

button.disabled {
	background-color: #ddd !important;
	color: #666 !important;
	cursor: not-allowed;
}

#action-button, #retry-button {
	width: 100%;
	margin-top: 30px;
	padding: 10px 0;
	font-size: 16px;
	border-radius: 6px;
	border: none;
	background-color: #ff2588;
	color: #fff;
	transition: background-color 0.3s ease;
}

#action-button:hover:not(.disabled) {
	background-color: #e91e63;
}

retry-button:hover:not(.disabled) {
	background-color: #e91e63;
}

label {
	padding: 6px;
	border-radius: 25px;
	margin-right: 10px;
	margin-bottom: 10px;
	box-sizing: border-box;
	display: inline-block;
	background-color: #ff2588;
	color: #fff;
}

::selection {
	background-color: #ff2588;
	color: #fff;
}
</style>
</head>
<body>

	<div id="profile--wrap">
		<c:if test="${detail != null}">
			<div class="representative--image">
				<img id="main-image" src="/DateProfileIMAGE/${detail.firstUploadFileName}" alt="">
			</div>
			<div class="profile-images">
				<img class="m--profile list--profile" alt="" src="/DateProfileIMAGE/${detail.firstUploadFileName}" data-fullsize="/DateProfileIMAGE/${detail.firstUploadFileName}"> <img
					class="m--profile list--profile" alt="" src="/DateProfileIMAGE/${detail.secondUploadFileName}" data-fullsize="/DateProfileIMAGE/${detail.secondUploadFileName}">
				<c:if test="${detail.thirdOriginFileName != null}">
					<img class="m--profile list--profile" alt="" src="/DateProfileIMAGE/${detail.thirdOriginFileName}" data-fullsize="/DateProfileIMAGE/${detail.thirdOriginFileName}">
				</c:if>
				<c:if test="${detail.fourthOriginFileName != null}">
					<img class="m--profile list--profile" alt="" src="/DateProfileIMAGE/${detail.fourthOriginFileName}" data-fullsize="/DateProfileIMAGE/${detail.fourthOriginFileName}">
				</c:if>
				<c:if test="${detail.fifthOriginFileName != null}">
					<img class="m--profile list--profile" alt="" src="/DateProfileIMAGE/${detail.fifthOriginFileName}" data-fullsize="/DateProfileIMAGE/${detail.fifthOriginFileName}">
				</c:if>
			</div>
			<div class="detail--partner--info">
				<div>
					<label>닉네임 : </label> ${detail.nickName}
				</div>
				<div>
					<label>자기소개 : </label> ${detail.introduce}
				</div>
				<div id="image-modal" class="modal hidden">
					<span class="close">&times;</span> <img class="modal-content" id="modal-img">
				</div>
			</div>
		</c:if>

		<c:if test="${dto != null}">
			<div>
				<label>이상형 : </label> ${dto.idealType}
			</div>
			<div>
				<label>혈액형 : </label> ${dto.bloodtype}
			</div>
			<div>
				<label>직업 : </label> ${dto.myJop}
			</div>
			<div>
				<label>좋아하는영화 : </label> ${dto.bestMovie}
			</div>
			<div>
				<label>음주여부 : </label> ${dto.drinking}
			</div>
			<div>
				<label>흡연여부 : </label> ${dto.smoking}
			</div>
		</c:if>

		<c:choose>
			<c:when test="${detail.status == 1}">
				<button id="action-button" value="${detail.userId}">신청</button>
			</c:when>
			<c:when test="${detail.status == 2}">
				<button id="action-button" class="disabled" disabled>매칭 요청 대기 중</button>
			</c:when>
			<c:when test="${detail.status == 4}">
				<button id="retry-button" value="${detail.userId}">재도전</button>
			</c:when>
		</c:choose>
	</div>

	<c:if test="${dto != null}">
		<div>
			<label>이상형 : ${dto.idealType} </label>
		</div>

		<div>
			<label>혈액형 : ${dto.bloodtype} </label>
		</div>

		<div>
			<label>직업 : ${dto.myJop} </label>
		</div>

		<div>
			<label>좋아하는영화 : ${dto.bestMovie} </label>
		</div>

		<div>
			<label>음주여부 : ${dto.drinking} </label>
		</div>

		<div>
			<label>흡연여부 : ${dto.smoking} </label>
		</div>
	</c:if>

	<c:choose>
		<c:when test="${detail.status == 1}">
			<button id="action-button" value="${detail.userId}">신청</button>
		</c:when>
		<c:when test="${detail.status == 2}">
			<button id="action-button" class="disabled" disabled>매칭 요청 대기 중</button>
		</c:when>
		<c:when test="${detail.status == 4}">
			<button id="retry-button" value="${detail.userId}">재도전</button>
		</c:when>
	</c:choose>
	</div>

	<script>
	    document.addEventListener('DOMContentLoaded', function() {
	        var images = document.querySelectorAll('.profile-images img');
	        var modal = document.getElementById('image-modal');
	        var modalImg = document.getElementById('modal-img');
	        var span = document.getElementsByClassName('close')[0];
	        var actionButton = document.getElementById('action-button');
	        var btn = document.querySelector("#action-button");
	        var rebtn = document.querySelector("#retry-button");
	        if (rebtn) {
	        rebtn.addEventListener('click', function() {
	        	const partNerId = rebtn.value;
	        	const url = "http://192.168.0.46:8080/duplication/retry?userId=" + `${userId}` + "&partNerId="+ partNerId;
	        	fetch(url)
	        		.then(response => response.json())
	        		 .then(isDate => {
	                     if (isDate) {
	                         alert("신청 완료");
	                         window.location.reload();
	                     } else {
	                         alert("신청 실패");
	                     }
	                 })
	                 .catch(error => {
	                     console.log("error ", error);
	                 });
	         });
	        }
	        if (btn) {
	        btn.addEventListener('click', function() {
	        	const partNerId = btn.value;
	        	const url = "http://192.168.0.46:8080/duplication/movieSuggest?userId=" + ${userId} + "&partNerId="+ partNerId;
	        	fetch(url)
	        		.then(response => response.json())
	        		 .then(isDate => {
	                     if (isDate) {
	                         alert("신청 완료");
	                         window.location.reload();
	                     } else {
	                         alert("신청 실패");
	                     }
	                 })
	                 .catch(error => {
	                     console.log("error ", error);
	                 });
	         });
	        }
	        
	        images.forEach(function(img) {
	            img.addEventListener('click', function() {
	                var fullSizeSrc = img.getAttribute('data-fullsize');
	                modalImg.src = fullSizeSrc;
	                modal.classList.remove('hidden');
	            });
	        });
	
	        span.addEventListener('click', function() {
	            modal.classList.add('hidden');
	        });
	
	        modal.addEventListener('click', function(event) {
	            if (event.target === modal) {
	                modal.classList.add('hidden');
	            }
	        });
	
	        if (actionButton && actionButton.classList.contains('disabled')) {
	            actionButton.addEventListener('click', function(event) {
	                event.preventDefault();
	                event.stopImmediatePropagation();
	            });
	        }
	    });
   
    </script>
</body>
</html>
