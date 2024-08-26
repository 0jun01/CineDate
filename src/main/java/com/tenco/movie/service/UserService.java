package com.tenco.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.dto.SignInDTO;
import com.tenco.movie.dto.SignUpDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.handler.exception.RedirectException;
import com.tenco.movie.repository.interfaces.UserRepository;
import com.tenco.movie.repository.model.User;
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

	public User readUser(SignInDTO dto) {

		User userEntity = null;
		
		try {
			userEntity = userRepository.findByUsername(dto.getLoginId());
		} catch (DataAccessException e) {
			throw new DataDeliveryException("잘못된 처리 입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new DataDeliveryException("알 수 없는 오류", HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (userEntity == null) {
			throw new DataDeliveryException("존재하지 않는 아이디입니다.", HttpStatus.BAD_REQUEST);
		}

		//boolean isPwdMathched = passwordEncoder.matches(dto.getPassword(), userEntity.getPassword());
		//if(isPwdMathched == false) {
		//	throw new DataDeliveryException("비밀번호가 틀렸습니다.", HttpStatus.BAD_REQUEST);
		//}

		return userEntity;

	}

}
