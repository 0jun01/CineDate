package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.Event;

@Mapper
public interface EventRepository {
	public List<Event> findAll();
	
	Event findById(int id);
	
	Event delete(int id);
	
}
