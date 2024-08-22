package com.tenco.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.handler.exception.RedirectException;
import com.tenco.movie.dto.SignUpDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.interfaces.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	private final UserRepository userRepository;
	
	@Transactional
	public void createUser(SignUpDTO dto) {
		System.out.println("여기로 왔냐");
		
		int result = 0;
		
		try {
			System.out.println("여긴왔낭리ㅏㅓㅇ너ㅏㅣㅎ이ㅏㄴ");
			result = userRepository.insert(dto.toUser());
			System.out.println("성공했늬");
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException("중복 이름을 사용할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException("알 수 없는 오류", HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		if(result != 1) {
			throw new DataDeliveryException("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
