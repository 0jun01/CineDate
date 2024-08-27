package com.tenco.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.tenco.movie.dto.SignInDTO;
import com.tenco.movie.dto.SignUpDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.interfaces.UserRepository;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.service.UserService;
import com.tenco.movie.utils.Define;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	private UserRepository userRepository;

	private final HttpSession session;

	@Autowired
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

		if (dto.getLoginId() == null || dto.getLoginId().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_YOUR_ID, HttpStatus.BAD_REQUEST);
		}
		System.out.println("여기냐11111");

		// 비밀번호 유효성 검사
		if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_YOUR_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		System.out.println("여기냐22222");

		User principal = userService.readUser(dto);

		System.out.println("여기냐4444433");
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
	 * 카카오 소셜로그인
	 * 
	 * @author 성후
	 */
	@GetMapping("/kakao")
	public String kakao() {
		return "redirect:/main";
	}

	/**
	 * 네이버 소셜로그인
	 * 
	 * @author 성후
	 */
	@GetMapping("/naver")
	public String naver() {
		return "redirect:/main";
	}

	/**
	 * 구글 소셜로그인
	 * 
	 * @author 성후
	 */
	@GetMapping("/google")
	public String google() {
		return "redirect:/main";
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
	public String signUpPage(SignUpDTO dto) {

		System.out.println("회원가입 들어왔니?");

		// 아이디 유효성 검사
		if (dto.getLoginId() == null) {
			throw new DataDeliveryException(Define.ENTER_YOUR_ID, HttpStatus.BAD_REQUEST);
		}
		if (dto.getLoginId().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_ID_TRIM, HttpStatus.BAD_REQUEST);
		}
//		if (dto.getLoginId().length() > 7 || dto.getLoginId().length() > 16) {
//			throw new DataDeliveryException(Define.ENTER_ID_LENGTH, HttpStatus.BAD_REQUEST);
//		}
		if (!dto.getLoginId().equals(dto.getLoginId())) {
			throw new DataDeliveryException(Define.DUPLICATION_ID, HttpStatus.BAD_REQUEST);
		}

		// 이름 유효성 검사
		if (dto.getName() == null) {
			throw new DataDeliveryException(Define.ENTER_YOUR_NAME, HttpStatus.BAD_REQUEST);
		}
		if (dto.getName().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_NAME_TRIM, HttpStatus.BAD_REQUEST);
		}

		// 비밀번호 유효성 검사
		if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_YOUR_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if (dto.getPassword().length() < 8 || dto.getPassword().length() > 20) {
			throw new DataDeliveryException(Define.ENTER_PASSWORD_LENGTH, HttpStatus.BAD_REQUEST);
		}
		if (!dto.getPassword().matches(".*[A-Za-z].*")) {
			throw new DataDeliveryException(Define.ENTER_PASSWORD_CHAR, HttpStatus.BAD_REQUEST);
		}
		if (!dto.getPassword().matches(".*\\d.*")) {
			throw new DataDeliveryException(Define.ENTER_PASSWORD_NUM, HttpStatus.BAD_REQUEST);
		}
		if (!dto.getPassword().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
			throw new DataDeliveryException(Define.ENTER_PASSWORD_SPECIAL_CHAR, HttpStatus.BAD_REQUEST);
		}
		if (!dto.getPassword().equals(dto.getEnterPassword())) {
			throw new DataDeliveryException(Define.NOT_VALIDATE_PASSWORD, HttpStatus.BAD_REQUEST);
		}

		// 이메일 유효성 검사
		if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_YOUR_EMAIL, HttpStatus.BAD_REQUEST);
		}

		// 휴대폰 번호 유효성 검사
		if (dto.getPhoneNum() == null || dto.getPhoneNum().trim().isEmpty()) {
			throw new DataDeliveryException(Define.ENTER_YOUR_PHONE_NUM, HttpStatus.BAD_REQUEST);
		}
		if (!dto.getPhoneNum().matches("\\d{10,11}")) {
			throw new DataDeliveryException(Define.NOT_VALIDATE_PHONE_NUM, HttpStatus.BAD_REQUEST);
		}
		if (!dto.getPhoneNum().equals(dto.getPhoneNum())) {
			throw new DataDeliveryException(Define.DUPLICATION_PASSWORD, HttpStatus.BAD_REQUEST);
		}

		// 성별 유효성 검사
		if (dto.getGender() == null || (!dto.getGender().equals("여") && !dto.getGender().equals("남"))) {
			throw new DataDeliveryException(Define.ENTER_YOUR_GENDER, HttpStatus.BAD_REQUEST);
		}
		

		System.out.println("여기까지는 왔나?");

		userService.createUser(dto);

		System.out.println("여기는...?");

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
	/**

	 *마이페이지 
	 *
	 *@author 성후
	 */
	@PostMapping("/updateUser")
    public String myPageUpDateUser(@RequestParam("login_id")String name, @RequestParam("name") String username) {
        userService.updateUsername(name, username);
        return "redirect:/myPage?login_id=" + name;
    }
	@PostMapping("/myPage")
	public String myPageProFile() {
		// 이름, 아이디, 닉네임 등록, 프로필 이미지 등록, 동의여부 확인, 수정하기버튼활성화
		return "redirect:/user/myPage";
	}

	

	/**
	 * 아이디 찾기 페이지 이동
	 * 
	 * @return
	 * @author 형정
	 */
	@GetMapping("/searchID")
	public String searchIdPage() {
		return "user/searchID";
	}

	@PostMapping("/findID")
	public String findId() {
		return "redirect:/user/signIn";
	}

}
