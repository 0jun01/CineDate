package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.User;

@Mapper
public interface UserRepository {


	int insert(User user);
	int updateById(User user);
	int deleteById();
	User findById(@Param("login_id")String name); // loginId
	User findPassword();
	List<User> findAll();
	void update(User user);
	
	public User findByUsernameAndPassword(@Param("loginId") String loginId, @Param("password") String password);
	
	public User findByLoginId(@Param("loginId") String loginId);
	
	// 아이디 중복 검사
	User isLoginIdDuplicated(String username);
	
	// 휴대폰 번호 중복 검사 
	User isPhoneNumDuplicated(String phoneNum);
	void updateUsername(Long userId, String username);
	
	
	

}
