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
	
    @Autowired
    private FileStorageService fileStorageService;

	// @Autowired
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
			throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result != 1) {
			throw new DataDeliveryException(Define.FAIL_TO_CREATE_USER, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public User readUser(SignInDTO dto) {

		User userEntity = null;

		try {
			userEntity = userRepository.findByLoginId(dto.getLoginId());
		} catch (DataAccessException e) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new DataDeliveryException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (userEntity == null) {
			throw new DataDeliveryException(Define.NOT_ID, HttpStatus.BAD_REQUEST);
		}

		if (!userEntity.getPassword().equals(dto.getPassword())) {
			throw new DataDeliveryException(Define.NOT_VALIDATE_PASSWORD, HttpStatus.BAD_REQUEST);
		}

		// TODO - 비밀번호 암호화
		// boolean isPwdMathched = passwordEncoder.matches(dto.getPassword(),
		// userEntity.getPassword());
		// if(isPwdMathched == false) {
		// throw new DataDeliveryException(Define.NOT_VALIDATE_PASSWORD, HttpStatus.BAD_REQUEST);
		// }

		return userEntity;

	}

	public boolean isLoginIdDuplicated(String loginId) {
		
		User user = userRepository.isLoginIdDuplicated(loginId);

		if (user != null) {
			throw new DataDeliveryException(Define.DUPLICATION_ID, HttpStatus.BAD_REQUEST);
		}

		return true;
	}

	public boolean isPhoneNumDuplicated(String phoneNum) {
		
		User user = userRepository.isPhoneNumDuplicated(phoneNum);
		return user != null;
	}

	 @Transactional
	    public User getUserById(String name) {
	        User user = userRepository.findById(name);
	        if (user == null) {
	            throw new DataDeliveryException("User not found", HttpStatus.NOT_FOUND);
	        }
	        return user;
	    }

	    @Transactional
	    public void updateUsername(String name, String newUsername) {
	        User user = userRepository.findById(name);
	        if (user == null) {
	            throw new DataDeliveryException("User not found", HttpStatus.NOT_FOUND);
	        }
	        user.setName(newUsername);
	        userRepository.update(user);
	    }

}
