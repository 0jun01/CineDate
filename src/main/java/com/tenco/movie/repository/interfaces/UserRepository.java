package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tenco.movie.repository.model.User;

@Mapper
public interface UserRepository {


	int insert(User user);
	int updateById(User user);
	int deleteById();
	User findById(); // loginId
	User findPassword();
	List<User> findAll();

}
