package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.Event;

@Mapper
public interface EventRepository {
	public List<Event> findAll(@Param("limit") int limit,
			@Param("offset") int offset);
	
	Event findById(int id);
	
	public int countEvent();
}
