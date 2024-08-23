package com.tenco.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.dto.SignUpDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.handler.exception.RedirectException;
import com.tenco.movie.repository.interfaces.UserRepository;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	private final UserRepository userRepository;

	//@Autowired
	// private final PasswordEncoder passwordEncoder;

	/**
	 * 회원 가입 기능
	 * 
	 * @param dto
	 */
	@Transactional
	public void createUser(SignUpDTO dto) {
		System.out.println("여기로 왔냐");

		int result = 0;

		try {
			System.out.println("여긴왔낭리ㅏㅓㅇ너ㅏㅣㅎ이ㅏㄴ");
			result = userRepository.insert(dto.toUser());
			System.out.println("성공했늬");
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException(Define.DUPLICATION_NAME, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException(Define.UNKNOWN, HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result != 1) {
			throw new DataDeliveryException(Define.FAIL_TO_CREATE_USER, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//	public User readUser(SignInDTO dto) {
//
//		// 유효성 검사는 Controller 에서 먼저 하자.
//		User userEntity = null; // 지역 변수 선언
//
//		// 기능 수정
//		// Username 으로만 select 처리
//		// 2가지의 경우의 수 -> 객체가 존재, null
//
//		// 객체안에 사용자의 password 가 존재한다. (암호화 되어 있는 값)
//		// passwordEncoder 안에 matches 메서드를 사용해서 판별한다. "1234".equals(!@#$FDGE$#%&%^*);
//
//		try {
//			userEntity = userRepository.findByUsername(dto.getLoginId());
//		} catch (DataAccessException e) {
//			throw new DataDeliveryException("잘못된 처리 입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
//		} catch (Exception e) {
//			throw new DataDeliveryException("알 수 없는 오류", HttpStatus.SERVICE_UNAVAILABLE);
//		}
//
//		if (userEntity == null) {
//			throw new DataDeliveryException("존재하지 않는 아이디입니다.", HttpStatus.BAD_REQUEST);
//		}
//
//		boolean isPwdMathched = passwordEncoder.matches(dto.getPassword(), userEntity.getPassword());
//		if(isPwdMathched == false) {
//			throw new DataDeliveryException("비밀번호가 틀렸습니다.", HttpStatus.BAD_REQUEST);
//		}
//
//		return userEntity;
//
//	}

}
