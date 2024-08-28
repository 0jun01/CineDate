package com.tenco.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
=======
>>>>>>> 3d5e07c (event 초안)
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
<<<<<<< HEAD
import org.springframework.web.client.RestTemplate;
=======
import org.springframework.web.bind.annotation.SessionAttribute;
>>>>>>> 3d5e07c (event 초안)

import com.tenco.movie.dto.GoogleOAuthToken;
import com.tenco.movie.dto.GoogleProfile;
import com.tenco.movie.dto.KakaoOAuthToken;
import com.tenco.movie.dto.KakaoProfile;
import com.tenco.movie.dto.NaverOAuthToken;
import com.tenco.movie.dto.NaverProfile;
import com.tenco.movie.dto.SignInDTO;
import com.tenco.movie.dto.SignUpDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.service.UserService;
import com.tenco.movie.utils.Define;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	private final HttpSession session;

	@Value("${tenco.key}")
	private String tencoKey;

	public UserController(UserService userService, HttpSession session) {
		this.userService = userService;
		this.session = session;
	}

	/**
	 * 로그인
	 * 
	 * @author 성후
	 */
	@GetMapping("/signIn")
	public String signIn() {
		return "user/signIn";
	}

	/**
	 * 로그인 주소 설계
	 * 
	 * @param
	 * @return
	 * @author 형정
	 */
	@PostMapping("/signIn")
	public String signPro(SignInDTO dto) {
		System.out.println("들어는 왔냐");

		// 로그인 아이디 유효성 검사
		// 로그인 아이디가 없거나 비어 있을 때
		if (dto.getLoginId() == null || dto.getLoginId().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_YOUR_ID, HttpStatus.BAD_REQUEST);
		}
		System.out.println("여기냐11111");

		// 비밀번호 유효성 검사
		// 비밀번호가 없거나 비어 있을 때
		if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_YOUR_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		System.out.println("여기냐22222");

		// user principal 생성
		User principal = userService.readUser(dto);

		System.out.println("여기냐4444433");

		// user principal 세션 생성
		session.setAttribute(Define.PRINCIPAL, principal);

		return "redirect:/home";
	}

	/**
	 * 회원가입
	 * 
	 * @author 성후
	 */
	@GetMapping("/signUp")
	public String signUp() {
		return "user/signUp";
	}

	/**
	 * 로그아웃
	 * 
	 * @author 성후, 형정
	 */
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/user/signIn";
	}

	/**
	 * 회원가입
	 * 
	 * @return
	 * @author 형정
	 */
	@PostMapping("/signUp")
	public String signUpPage(SignUpDTO dto, Model model) {

		System.out.println("회원가입 들어왔니?");

		// 아이디 유효성 검사
		// 아이디 비어져있을 때
		if (dto.getLoginId() == null || dto.getLoginId().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_YOUR_ID, HttpStatus.BAD_REQUEST);
		}

		// 아이디 글자 제한
		if (dto.getLoginId().length() < 7 || dto.getLoginId().length() > 16) {
			throw new DataDeliveryException(Define.ENTER_ID_LENGTH, HttpStatus.BAD_REQUEST);
		}

		// 이름 유효성 검사
		// 이름 비어져있을 때
		if (dto.getName() == null || dto.getName().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_YOUR_NAME, HttpStatus.BAD_REQUEST);
		}

		// 비밀번호 유효성 검사
		// 비밀번호가 없거나 비어 있을 때
		if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_YOUR_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		// 비밀번호 글자 제한
		if (dto.getPassword().length() < 8 || dto.getPassword().length() > 20) {
			throw new DataDeliveryException(Define.ENTER_PASSWORD_LENGTH, HttpStatus.BAD_REQUEST);
		}
		// 비밀번호 영어 포함
		if (!dto.getPassword().matches(".*[A-Za-z].*")) {
			throw new DataDeliveryException(Define.ENTER_PASSWORD_CHAR, HttpStatus.BAD_REQUEST);
		}
		// 비밀번호
		if (!dto.getPassword().matches(".*\\d.*")) {
			throw new DataDeliveryException(Define.ENTER_PASSWORD_NUM, HttpStatus.BAD_REQUEST);
		}
		// 비밀번호 특수 문자
		if (!dto.getPassword().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
			throw new DataDeliveryException(Define.ENTER_PASSWORD_SPECIAL_CHAR, HttpStatus.BAD_REQUEST);
		}
		// 비밀번호 중복 검사
		if (!dto.getPassword().equals(dto.getEnterPassword())) {
			throw new DataDeliveryException(Define.NOT_VALIDATE_PASSWORD, HttpStatus.BAD_REQUEST);
		}

		// 이메일 유효성 검사
		// 이메일이 없거나 빈칸일 때
		if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_YOUR_EMAIL, HttpStatus.BAD_REQUEST);
		}

		// 휴대폰 번호 유효성 검사
		// 휴대폰 번호가 없거나 빈킨일 때
		if (dto.getPhoneNum() == null || dto.getPhoneNum().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_YOUR_PHONE_NUM, HttpStatus.BAD_REQUEST);
		}
		// 휴대폰 번호가 10~11자가 아닐 때
		if (!dto.getPhoneNum().matches("\\d{10,11}")) {
			throw new DataDeliveryException(Define.NOT_VALIDATE_PHONE_NUM, HttpStatus.BAD_REQUEST);
		}

		// 성별 유효성 검사
		if (dto.getGender() == null || (!dto.getGender().equals("여") && !dto.getGender().equals("남"))) {
			throw new DataDeliveryException(Define.ENTER_YOUR_GENDER, HttpStatus.BAD_REQUEST);
		}

		System.out.println("여기까지는 왔나?");

		userService.createUser(dto);

		System.out.println("회원가입 성공");

		return "redirect:/user/signIn";

	}

	/**
	 * 마이페이지
	 * 
	 * @author 성후
	 */
	@GetMapping("/myPage")
	public String myPage( @SessionAttribute(Define.PRINCIPAL) User principal, Model model) {
		
		String name = principal.getLoginId();
		
	    User user = userService.getUserById(name);
	    model.addAttribute("user", user);
	    return "user/myPage";
	}

	/*
	 * 마이페이지
	 *
	 * @author 성후
	 */
<<<<<<< HEAD
	@PostMapping("/myPage")
	public String myPageProFile() {
		// 이름, 아이디, 닉네임 등록, 프로필 이미지 등록, 동의여부 확인, 수정하기버튼활성화
		return "redirect:/user/myPage";
	}
=======
	@PostMapping("/updateUser")
    public String myPageUpDateUser(@RequestParam("login_id")String name, @RequestParam("name") String username) {
        userService.updateUsername(name, username);
        return "redirect:/myPage?login_id=" + name;
    }
	
>>>>>>> 3d5e07c (event 초안)

	/**
	 * @return
	 * @author 형정
	 */
	@GetMapping("/findID")
	public String findIdPage() {
		return "user/findID";
	}

	@PostMapping("/findID")
	public String findId(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,
			Model model) {

		User user = userService.findByLoginIdForEmail(name, email);

		model.addAttribute("loginId", user.getLoginId());

		return "user/findNextID";
	}

	@PostMapping("/findNextID")
	public String findNextID() {
		return "redirect:/user/findNextID";
	}

	/**
	 * 비밀번호 찾기
	 * 
	 * @param name
	 * @param phoneNum
	 * @param model
	 * @author 형정
	 * @return
	 */
	@GetMapping("/findPassword")
	public String findPasswrod() {

		return "user/findPassword";
	}

	@PostMapping("/findNextPassword")
	public String findNextPassword(@RequestParam(name = "loginId") String loginId,
			@RequestParam(name = "email") String email, Model model) {

		System.out.println("비밀번호 찾기 들어옴?2222222222");
		System.out.println(loginId);
		System.out.println(email);
		User user = userService.findByLoginIdForPassword(loginId, email);
		model.addAttribute("password", user.getPassword());

		return "user/findNextPassword";
	}

	/**
	 * 카카오 로그인
	 * 
	 * @param code
	 * @return
	 * @author 형정
	 */
	@GetMapping("/kakao")
	public String kakao(@RequestParam(name = "code") String code) {

		System.out.println("code : " + code);

		RestTemplate rt1 = new RestTemplate();

		HttpHeaders header1 = new HttpHeaders();
		header1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<String, String>();
		params1.add("grant_type", "authorization_code");
		params1.add("client_id", "75ba363608bd6f2a3cfbf6acaf901f10");
		params1.add("redirect_uri", "http://localhost:8080/user/kakao");
		params1.add("code", code);

		HttpEntity<MultiValueMap<String, String>> reqkakaoMessage = new HttpEntity<>(params1, header1);

		ResponseEntity<KakaoOAuthToken> response1 = rt1.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				reqkakaoMessage, KakaoOAuthToken.class);

		System.out.println("response : " + response1.getBody().toString());

		RestTemplate rt2 = new RestTemplate();

		HttpHeaders headers2 = new HttpHeaders();

		headers2.add("Authorization", "Bearer " + response1.getBody().getAccessToken());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> reqKakaoInfoMessage = new HttpEntity<>(headers2);

		ResponseEntity<KakaoProfile> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				reqKakaoInfoMessage, KakaoProfile.class);

		KakaoProfile kakaoProfile = response2.getBody();

		System.out.println("kakaoProfile : " + kakaoProfile);

		SignUpDTO signUpDTO = SignUpDTO.builder()
				.loginId(kakaoProfile.getProperties().getNickname() + "_" + kakaoProfile.getId())
				.username(kakaoProfile.getProperties().getNickname()).password(tencoKey)
				.email(kakaoProfile.getProperties().getEmail()).build();

		User oldUser = userService.searchUsername(signUpDTO.getUsername());
		if (oldUser == null) {

			oldUser = new User();

			signUpDTO.setOriginFileName(kakaoProfile.getProperties().getProfileImage());

			userService.createKakaoUser(signUpDTO);

			oldUser.setName(signUpDTO.getUsername());
			oldUser.setPassword(null);
			// oldUser.set(signUpDTO.getName());

			// 프로필 이미지 여부에 따라 조건식 추가
			signUpDTO.setOriginFileName(kakaoProfile.getProperties().getThumbnailImage());
			userService.createKakaoUser(signUpDTO);

			System.out.println("여기됐나나아아아아");
		}

		// 자동 로그인 처리
		session.setAttribute(Define.PRINCIPAL, oldUser);

		return "redirect:/home";
	}

	/**
	 * 네이버 로그인
	 * 
	 * @return
	 * @author 형정
	 */
	@GetMapping("/naver")
	public String naver(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
		System.out.println("네이버 들어옴?");

		System.out.println("code : " + code);
		System.out.println("state : " + state);
		RestTemplate rt1 = new RestTemplate();

		HttpHeaders header1 = new HttpHeaders();
		header1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<>();
		params1.add("grant_type", "authorization_code");
		params1.add("client_id", "fLZ62UlS4udx_2KU4B4r");
		params1.add("client_secret", "AH4vWNTpun");
		params1.add("code", code);
		params1.add("state", state);

		HttpEntity<MultiValueMap<String, String>> reqNaverMessage = new HttpEntity<>(params1, header1);

		ResponseEntity<NaverOAuthToken> response1 = rt1.exchange("https://nid.naver.com/oauth2.0/token",
				HttpMethod.POST, reqNaverMessage, NaverOAuthToken.class);

		System.out.println("NaverOAuthToken : " + response1.getBody().toString());

		RestTemplate rt2 = new RestTemplate();

		HttpHeaders headers2 = new HttpHeaders();

		headers2.add("Authorization", "Bearer " + response1.getBody().getAccessToken());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> reqNavernfoMessage = new HttpEntity<>(headers2);

		ResponseEntity<NaverProfile> response2 = rt2.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.POST,
				reqNavernfoMessage, NaverProfile.class);

		System.out.println("NaverProfile : " + response2.getBody().toString());

		NaverProfile profile = response2.getBody();

		User oldUser = userService.searchLoginId(profile.getResponse().getId());
		System.out.println(oldUser);
		if (oldUser == null) {
			oldUser = new User();
			oldUser.setLoginId(profile.getResponse().getId());
			oldUser.setPassword(profile.getResponse().getId());

			System.out.println("네이버 올드유저까지 왔늬");
			userService.createNaverUser(profile.getResponse());

		}
		session.setAttribute(Define.PRINCIPAL, oldUser);

		return "redirect:/home";

	}

	@GetMapping("/google")
	public String google(@RequestParam(name = "code") String code) {

		System.out.println("구글 들어옴요");

		System.out.println("code : " + code);
		RestTemplate rt1 = new RestTemplate();

		HttpHeaders header1 = new HttpHeaders();
		header1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<>();

		// 구글 시크릿 코드 및 add 자리
		params1.add("code", code);
		params1.add("client_id", "");
		params1.add("client_secret", "");
		params1.add("redirect_uri", "http://localhost:8080/user/google");
		params1.add("grant_type", "authorization_code");

		HttpEntity<MultiValueMap<String, String>> reqGoogleMessage = new HttpEntity<>(params1, header1);

		ResponseEntity<GoogleOAuthToken> response1 = rt1.exchange("https://oauth2.googleapis.com/token",
				HttpMethod.POST, reqGoogleMessage, GoogleOAuthToken.class);

		System.out.println("GoogleAuthToken : " + response1.getBody().toString());

		RestTemplate rt2 = new RestTemplate();

		HttpHeaders headers2 = new HttpHeaders();

		headers2.add("Authorization", "Bearer " + response1.getBody().getAccessToken());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> reqGoogleInfoMessage = new HttpEntity<>(headers2);

		ResponseEntity<GoogleProfile> response2 = rt2.exchange("https://www.googleapis.com/userinfo/v2/me",
				HttpMethod.GET, reqGoogleInfoMessage, GoogleProfile.class);

		System.out.println("googleProfile : " + response2.getBody().toString());

		GoogleProfile profile = response2.getBody();

		System.out.println("profile :::: " + profile);
		System.out.println("==========================================================================");
		System.out.println(profile.getId() + "=====" + profile.getEmail() + "=====" + profile.getName());
		System.out.println("==========================================================================");

		User oldUser = userService.searchLoginId(profile.getId());

		System.out.println("oldUser : " + oldUser);
		if (oldUser == null) {
			oldUser = new User();
			oldUser.setLoginId(profile.googleUser().getId());
			oldUser.setPassword(profile.googleUser().getId());

			System.out.println("구글 올드유저까지 왔늬");

			userService.createGoogleUser(profile);

		}
		session.setAttribute(Define.PRINCIPAL, oldUser);

		return "redirect:/home";
	}

}
