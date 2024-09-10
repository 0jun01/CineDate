package com.tenco.movie.repository.interfaces;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.tenco.movie.repository.model.Message;

@Mapper
public interface MessageRepository {
	 	public void save(Message message) throws SQLException;

	}