<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파트너 디테일</title>
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}
.profile-images {
	display: flex;
	gap: 10px;
}

.profile-images img {
	width: 100px;
	height: 100px;
	object-fit: cover;
	cursor: pointer;
	transition: transform 0.3s ease, box-shadow 0.3s ease;
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
	display: block;
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
	background-color: #ddd;
	color: #666;
	cursor: not-allowed;
}

#action-button {
	width: 135px;
	padding: 10px 0;
	font-size: 16px;
	border-radius: 6px;
	border: 1px solid #ff2588;
	background-color: #fff;
}
</style>
</head>
<body>
	<c:if test="${detail != null}">
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
				<label>닉네임 : ${detail.nickName} </label>
			</div>

			<div>
				<label>자기소개 : ${detail.introduce} </label>
			</div>
			<div id="image-modal" class="modal hidden">
				<span class="close">&times;</span> <img class="modal-content" id="modal-img">
			</div>
	</c:if>

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
			<button id="action-button" value="${detail.userId}">재도전</button>
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
	        
	        btn.addEventListener('click', function() {
	        	const partNerId = btn.value;
	        	const url = "http://localhost:8080/duplication/movieSuggest?userId=" + ${userId} + "&partNerId="+ partNerId;
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
