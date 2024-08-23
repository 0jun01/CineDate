package com.tenco.movie.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.User;


@Mapper
public interface UserRepository {
	
	public int insert(User user);

//	public User findByUsernameAndPassword(@Param("name") String name, @Param("password") String password);
//	public User findByUsername(@Param("name") String name);
}
