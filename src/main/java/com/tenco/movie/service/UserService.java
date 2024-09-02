package com.tenco.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.dto.GoogleProfile;
import com.tenco.movie.dto.NaverProfileResponse;
import com.tenco.movie.dto.SignInDTO;
import com.tenco.movie.dto.SignUpDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.handler.exception.RedirectException;
import com.tenco.movie.repository.interfaces.UserRepository;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.repository.model.search;
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
	 * @param dto
	 * @author 형정
	 */
	@Transactional
	public void createUser(SignUpDTO dto) {

		System.out.println("여기로 왔냐");

		int result = 0;

		try {
			System.out.println("11111111여긴왔낭리ㅏㅓㅇ너ㅏㅣㅎ이ㅏㄴ");
			result = userRepository.insert(dto.toUser());
			System.out.println("11111성공했늬");
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException(Define.DUPLICATION_NAME, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result != 1) {
			throw new DataDeliveryException(Define.FAIL_TO_CREATE_USER, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/**
	 * 카카오 로그인
	 * @param dto
	 * @author 형정
	 */
	@Transactional
	public void createKakaoUser(SignUpDTO dto) {

		System.out.println("카카오 로그인이다");
		int result = 0;

		try {
			System.out.println("카카오 여기 왔니?");
			result = userRepository.kakaoInsert(dto.kakaoUser());
			System.out.println("11111성공했늬");
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException(Define.DUPLICATION_NAME, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result != 1) {
			throw new DataDeliveryException(Define.FAIL_TO_CREATE_USER, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/**
	 * 네이버 로그인 기능
	 * @param dto
	 * @author 형정
	 */
	@Transactional
	public void createNaverUser(NaverProfileResponse dto) {

		System.out.println("네이버 로그인이다");
		int result = 0;

		try {
			System.out.println("네이버 여기 왔니?");
			result = userRepository.naverInsert(dto.naverUser());
			System.out.println("11111성공했늬");
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException(Define.DUPLICATION_NAME, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result != 1) {
			throw new DataDeliveryException(Define.FAIL_TO_CREATE_USER, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/**
	 * 구글 로그인 기능
	 * @param dto
	 * @author 형정
	 */
	@Transactional
	public void createGoogleUser(GoogleProfile dto) {

		System.out.println("구글 로그인이다");
		int result = 0;

		try {
			System.out.println("구글 여기 왔니?");
			result = userRepository.googleInsert(dto.googleUser());
			System.out.println("11111성공했늬");
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException(Define.DUPLICATION_NAME, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result != 1) {
			throw new DataDeliveryException(Define.FAIL_TO_CREATE_USER, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 로그인 기능
	 * @param dto
	 * @author 형정
	 */
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
		// throw new DataDeliveryException(Define.NOT_VALIDATE_PASSWORD,
		// HttpStatus.BAD_REQUEST);
		// }

		return userEntity;

	}

	/**
	 * 이메일로 아이디 찾기
	 * @return
	 * @author 형정
	 */
	public User findByLoginIdForEmail(String name, String email) {

		User userEntity = null;
		try {
			userEntity = userRepository.findByLoginIdForEmail(name, email);
		} catch (DataAccessException e) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new DataDeliveryException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (userEntity == null) {
			throw new DataDeliveryException("등록된 정보가 없습니다. 다시 확인해주세요.", HttpStatus.BAD_REQUEST);
		}

		return userEntity;
	}

	/**
	 * 비밀번호 찾기
	 * @param loginId
	 * @param password
	 * @return
	 * @author 형정
	 */
	public User findByLoginIdForPassword(String loginId, String email) {

		User userEntity = null;
		try {
			userEntity = userRepository.findByLoginIdForPassword(loginId, email);
		} catch (DataAccessException e) {
			throw new DataDeliveryException(Define.FAILED_PROCESSING, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new DataDeliveryException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (userEntity == null) {
			throw new DataDeliveryException("등록된 정보가 없습니다. 다시 확인해주세요.", HttpStatus.BAD_REQUEST);
		}

		return userEntity;
	}

	/**
	 * name 사용자 존재 여부 조회
	 * @param name
	 * @return User
	 * @author 형정
	 */
	public User searchUsername(String name) {
		return userRepository.findByUsername(name);
	}
	
	/**
	 * LoginId 사용자 존재 여부 조회
	 * @param loginId
	 * @return User
	 * @author 형정
	 */
	public User searchLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	/**
	 * phoneNum 사용자 존재 여부 조회
	 * @return User
	 * @author 형정 
	 */
	public User searchPhoneNum(String phoneNum) {
		return userRepository.findByPhoneNum(phoneNum);
	}
	
	/**
	 * email 사용자 존재 여부 조회
	 * @return User
	 * @author 형정
	 */
	public User searchEmail(String email) {
		return userRepository.findByEmail(email);
	}
	/**
	 * 마이페이지
	 * @param name 성후
	 * @return
	 */
	 @Transactional
	    public User getUserById(String name) {
	        User user = userRepository.findById(name);
	        if (user == null) {
	            throw new DataDeliveryException(Define.INVALID_INPUT, HttpStatus.NOT_FOUND);
	        }
	        return user;
	    }
/**
 * 마이페이지 업데이트
 * @author 성후
 * @param userId
 * @param newPassword
 * @param newEmail
 * @param newPhoneNum
 */
		@Transactional
		public void updateUser(String loginId, String newPassword, String newEmail, String newPhoneNum) {
		    User user = userRepository.findById(loginId);

		    if (newPassword != null) {
		        user.setPassword(newPassword);
		    }
		    if (newEmail != null) {
		        user.setEmail(newEmail);
		    }
		    if (newPhoneNum != null) {
		        user.setPhoneNum(newPhoneNum);
		    }
		    user.setPassword(newPassword);
		    user.setEmail(newEmail);
		    user.setPhoneNum(newPhoneNum);
		    
		    
		    
		    userRepository.update(user);
		}
}