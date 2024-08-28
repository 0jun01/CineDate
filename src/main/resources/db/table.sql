
-- 회원가입 테이블
create table user_tb (
	id int primary key auto_increment comment 'user_tb pk',
    login_id varchar(15) not null unique comment '로그인 할 때 ID',
    name varchar(50) not null comment '이름',
    password varchar(20) not null comment '비밀번호',
    email varchar(255) null unique comment '이메일',
    phone_num varchar(20) null unique comment '핸드폰 번호',
    birth_day date null comment '생년월일',
    gender enum("남","여") null comment '성별'
);

-- 데이팅 앱 프 로 필
create table profile_tb(
	id int primary key auto_increment comment 'profile_tb pk',
    user_id int comment ' fk --> user_tb의 id(pk)' ,
    nick_name varchar(10) unique not null comment '닉네임 중복확인 기능', 
    introduce varchar(50) comment '자기소개!',
    first_origin_file_name  varchar(500) not null comment '프로필 기존 사진 첫 번째 사진 파일 이름',
    seocnd_origin_file_name varchar(500) not null comment '프로필 두 번째 사진 파일 이름', 
    third_origin_file_name varchar(500) comment '프로필 세 번째 사진 파일 이름', 
    fourth_origin_file_name varchar(500) comment '프로필 네 번째 사진 파일 이름',
    fifth_origin_file_name varchar(500) comment '프로필 다섯 번째 사진 파일 이름',
    first_upload_file_name varchar(500) not null comment '프로필 업로드 사진 첫 번째 사진 파일 이름',
    second_upload_file_name varchar(500) not null comment '프로필 업로드 사진 두 번째 사진 파일 이름',
    con int default 0 comment '콘 보유 개수',
    life_status integer not null default 0 comment '0이면 계정 활성화, 1이면 계정 정지!',
    list_status integer not null default 0 comment '0이면 일반, 1이면 슈퍼 리스트 활성화 상태!!'
);

-- 데이팅 프로필 평점
create table evaluation_tb(
	id int primary key auto_increment comment 'evalueation_tb pk',
    r_user_id int not null comment '유저에게 평가 받은 유저 id',
    s_user_id int not null comment '유저를 평가한 유저 id',
    user_rating decimal(2,1) comment '데이팅 유저 평점'
);

-- 영화
create table movies_tb(
	id int not null primary key auto_increment comment 'movies_tb pk',
    genre_id int not null comment 'genre_tb pk',
    director_id int not null comment 'director_tb pk', 
	title varchar(50) not null comment '영화 제목',
    movie_desc text comment '무비 상세 설명',
    movie_img varchar(500) comment '무비 이미지 사진 주소',
    release_date varchar(50) not null comment '개봉일'
);

-- 무비 디테일
create table movie_detail_tb(
	id int not null primary key auto_increment comment 'movies_detail_tb pk',
    movie_id int not null comment 'movies_tb pk',
    title varchar(50) not null comment '영화 제목',
    title_en varchar(50) not null comment '영화 영어 제목',
    show_tm varchar(10) comment'상영시간',
    open_dt varchar(20) comment'개봉 날짜',
    prd_stat_nm varchar(10) comment'개봉 상태',
    watch_grade_Nm varchar(20) comment '관람 등급'
);

-- 장르 테이블
CREATE TABLE genres_tb (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Genres table primary key',
    genre_name VARCHAR(50) NOT NULL COMMENT 'Genre name'
);

-- 디렉터 테이블
CREATE TABLE directors_tb (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Directors table primary key',
    director_name VARCHAR(50) NOT NULL COMMENT 'Director name'
);

-- 영화 배우 중간다리
create table movie_actor_tb(
	id int not null primary key auto_increment comment 'movie_actor_tb pk',
    movie_id int not null comment 'movies_tb table fk',
    actors_id int not null comment 'actors_tb table fk'
);

-- 배우 테이블
create table actors_tb(
	id int not null primary key auto_increment comment 'actors_tb pk',
    movie_id int not null comment 'movies_tb pk',
    name varchar(20) not null comment '배우 이름',
    birth varchar(20) comment'출생 연도',
    role varchar(10) not null comment'배우인지 감독인지',
    nationality varchar(30) comment '국적',
    actor_face_file varchar(500) comment '주연 사진'
);

-- 리뷰 테이블
create table review_tb(
	id int primary key auto_increment comment 'review_tb pk',
    movie_id int not null comment 'movies_tb pk',
    user_id int not null comment 'user_tb pk',
    review_text text comment '리뷰 내용',
	rating decimal(10,1) comment '평점',
    review_date timestamp not null default now() comment '작성 시간'
);

-- 영화관 상세 정보
create table location_tb(
	id int primary key auto_increment comment 'location_tb pk',
    cinema varchar(100) not null comment'상영관명',
    address varchar(100) not null comment'도로명',
    capacity int not null comment'관람정원수'
);

-- 영화관 예매
-- 상영관 정보, 유저 정보, 영화 정보, 좌석 번호,
create table reservation_tb(
	id int primary key auto_increment comment 'reservation_tb pk 예약 번호',
    user_id int not null comment'user_tb pk',
    movie_id int not null comment 'movies_tb pk',
    sit_num varchar(20) not null comment'좌석 번호'
);

-- 상영 정보
create table screen_tb(
	id int primary key auto_increment comment 'screen_tb pk ',
    location_id int comment 'location_tb pk',
    movie_id int comment 'moives_tb pk',
    screen_start timestamp comment'상영 시작 시간'
);

-- 결제 내역!
create table payment_tb(
	id int primary key auto_increment comment 'payment_tb pk',
    user_id int not null comment 'user_tb의 pk',
	amount int not null comment '결제 금액',
    payment_method varchar(50) comment '결제 방식',
    payment_item varchar(50) comment '구매한 아이템',
    oauth_code varchar(500) not null comment'인증 코드!',
    created_at timestamp not null default now()
);

-- 아이템 사용 내역 
create table con_history_tb(
	id int primary key auto_increment comment 'con_history_tb 의 pk',
	user_id int not null comment 'user_tb의 pk',
    w_con_count int comment '사용 콘 개수',
    d_con_count int comment '충전 콘 개수',
    con_amount int comment '충전 or 사용 후 콘 개수',
    item_name varchar(50) comment '아이템 사용 이름' 
    );

-- 공지사항
create table notice_tb(
	id int primary key auto_increment comment 'notice_tb의 pk',
    admin_id int not null comment '관리자 아이디',
    category varchar(20) comment '분류',
    title varchar(30) not null comment '공지사항 제목',
    content text not null comment '공지사항 내용',
    created_at timestamp not null default now()
);

-- 이벤트
create table event_tb(
	id int primary key auto_increment comment 'event_tb의 pk',
    title varchar(30) not null comment '이벤트 제목',
    created_at timestamp default now(),
    release_date timestamp comment '이벤트 시작일',
    end_date timestamp comment '이벤트 종료일',
    origin_file_name varchar(500) comment '이벤트 이미지 파일 이름',
    upload_file_name varchar(500) comment '이벤트 이미지 업로드 파일 이름'
);

 

-- 데이팅 게시글 테이블
create table dating_tb(
	id int primary key auto_increment comment'date_tb pk',
    profile_id int not null comment 'profile_tb 의 pk 유저 프로필',
    created_at timestamp not null default now() comment '작성 시간'
);

-- 신고, 차단 당한 유저 테이블
create table blackList_tb(
	id int primary key auto_increment comment 'blackList_tb pk',
    user_id int comment '유저 id pk',
    black_user_id int comment '블랙 당한 유저 id'
);

-- 쪽지 기능들
create table date_message_tb(
	id int primary key auto_increment comment '기능들 pk',
    s_user_id int not null comment '쪽지 보낸 사람 아이디',
    r_user_id int not null comment '쪽지 받는 사람 아이디',
    message varchar(100) comment '보내는 메세지 내용'
);

-- 매칭 신청
create table matching_tb(
	id int primary key auto_increment comment '기능들 pk',
    s_user_id int not null comment '매칭 요청 보낸 사람 아이디',
    r_user_id int not null comment '매칭 요청 받는 사람 아이디',
    status integer default 0 comment '0이면 수락 안 한 상태, 1이면 수락 상태, 2면 거절한 상태' 
);

-- 확성기 테이블
create table super_list_tb(
	id int primary key auto_increment comment 'megaphone_tb pk',
    profile_id int not null comment '보낸 유저 id',
	created_at timestamp default now() comment '시작시간',
    end_date timestamp comment '나중에 created_at + 30분 할 값'
);