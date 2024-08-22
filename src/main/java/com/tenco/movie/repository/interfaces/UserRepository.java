package com.tenco.movie.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import com.tenco.movie.repository.model.User;


@Mapper
public interface UserRepository {
	
	public int insert(User user);

}
