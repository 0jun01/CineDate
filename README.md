SpringBoot-Project-CineDate
=============
스프링 부트 + JSP 파싱을 이용한 영화예매와 데이팅 사이트

🖥️프로젝트 소개
-------------
Spring Boot, MyBatis, JSP, Parsing 을 통한 영화 업데이트 그리고 MySQL을 이용하여 개발된 웹 기반의 영화관 데이트 시스템
영화관 데이트를 목적으로 남, 녀 의 커플 매칭, 상점 및 영화결제 시스템
비동기 처리를 활용한 영화예매 시스템

🕰️개발 기간
-------------
```24.08.20 ~ 24.09.20```

🧑‍🤝‍🧑멤버구성
-------------
<ul>
	<li>팀장  : 변영준 - Database 구축, 영화 정보 API 파싱, 영화 상세보기 구현, 데이팅 상점 및 아이템 사용 구현, 예매 기능 구현</li>
	<li>팀원1 : 김가령 - 극장 페이지 구현, 관람평 기능 구현, 공지사항 페이지 구현</li>
	<li>팀원2 : 김성후 - 영화 페이지 구현, 이벤트 페이지 구현, 마이페이지 및 예매 조회 구현</li>
	<li>팀원3 : 유형정 - CSS 총괄, 회원가입 기능 구현 (SMTP), 로그인 기능 구현(카카오톡, 구글, 네이버)</li>
	<li>팀원4 : 주윤재 - Database 구축, 관리자 페이지 구현</li>
	<li>팀원5 : 배병호 - Database 구축, 결제 API 구현, 데이트 채팅 구현, 매칭 시스템 구현, 데이트 리스트 구현, 프로필 상세보기 구현</li>
</ul>

개발 환경
-------------
<ul>
	<li>JAVA 21</li>
	<li>JavaScript</li>
	<li>IDE : STS 4</li>
	<li>Framework : springboot</li>
	<li>Database : MySQL</li>
	<li>ORM : Mybatis</li>
</ul>

📌주요 기능
-------------
<h5>로그인 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(Login)">상세보기</a></h5>
<ul>
	<li>유효성 검사</li>
	<li>소셜 로그인</li>
	<li>ID찾기, PW찾기</li>
</ul>

<h5>회원가입 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85)">상세보기</a></h5>
<ul>
	<li>ID 중복 체크</li>
	<li>SMTP 이메일 인증</li>
</ul>

<h5>메인 홈페이지 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EB%A9%94%EC%9D%B8-%ED%99%88%ED%8E%98%EC%9D%B4%EC%A7%80)">상세보기</a></h5>
<ul>
	<li>API 영화 주간 박스오피스 정보 파싱</li>
</ul>

<h5>예매 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EC%98%88%EB%A7%A4)">상세보기</a></h5> 
<ul>
	<li>fetch를 이용한 비동기 처리</li>
	<li>날짜 공휴일 API 파싱 후 표시</li>
	<li>좌석 예매 시스템</li>
	<li>동시에 예약했을 경우 먼저 예매한 사람이 등록</li>
</ul>

ERD 다이어그램 - <a href="https://github.com/0jun01/CineDate/wiki/ERD-%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8">상세보기</a>
-------------
![image](https://github.com/user-attachments/assets/53ba2621-0fd1-47b3-8653-622aa89c39ad)

