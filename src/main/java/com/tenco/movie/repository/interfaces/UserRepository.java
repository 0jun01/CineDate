package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.User;

@Mapper
public interface UserRepository {

	int insert(User user);
	int googleInsert(User user);
	int naverInsert(User user);
	int kakaoInsert(User user);
	int updateById(User user);
	int deleteById();
	User findById(); // loginId
	User findPassword();
	List<User> findAll();
	
	// 이름과 이메일로 아이디 찾기
	public User findByLoginIdForEmail(@Param("name") String name, @Param("email") String email);
	
	// 아이디와 이메일로 비밀번호 찾기
	public User findByLoginIdForPassword(@Param("loginId") String loginId, @Param("email") String email);
	
	public User findByUsername(@Param("name") String name);
	
	// 아이디 중복 및 유효성 검사
	public User findByLoginId(@Param("loginId") String loginId);
	
	// 휴대폰 중복 및 유효성 검사
	public User findByPhoneNum(@Param("phoneNum") String phoneNum);
	
	// 이메일 중복 검사
	public User findByEmail(@Param("email") String email);
	
	// 휴대폰 번호 중복 검사 
	User isPhoneNumDuplicated(String phoneNum);
	
	

}
