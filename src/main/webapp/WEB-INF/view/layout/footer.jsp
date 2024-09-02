<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- footer start -->
	<footer>
		<div id="in--wrap">
			<div class="footer--top">
				<div class="footer--logo">
					<a href="/home"><img src="/img/footer_logo.png" alt="푸터 로고"></a>
				</div>
				<div class="footer--tems">
					<span><a href="/user/termsOfUse">이용약관</a></span> <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <span><a href="/user/privacyPolicy">개인정보처리방침</a></span>
				</div>
			</div>
			<div class="footer--middle">상호 : (주)시네데이트&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;대표자 : 김근호&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;소재지 : 부산광역시 부산진구 중앙대로
				749 범향빌딩 3층&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;개인정보관리책임자 : 변영준&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; 이메일 :
				cinedate@gmail.com&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;전화번호 : 070-6975-5478</div>
			<span class="copyright eng">copyright ⓒ 2024 cinedate. all rights reserved.</span>
		</div>
		<button type="button" id="go--top" class="btn">
            <span class="arr"></span>
            <p>TOP</p>
        </button>
        
        <!-- top 버튼 생성 및 top 버튼 클릭시 상단 이동 -->
        <script type="text/javascript" >
	        $(window).scroll(function () {
	            if ($(this).scrollTop() > 100) {
	              $("#go--top").fadeIn();
	            } else {
	              $("#go--top").fadeOut();
	            }
	          });
	          $("#go--top").click(function () {
	            $("html, body").animate({ scrollTop: 0 }, 400);
	            return false;
	          });
        </script>
	</footer>
	<!-- footer end -->

</body>
</html>