package com.tenco.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.handler.exception.RedirectException;
import com.tenco.movie.repository.interfaces.EventRepository;
import com.tenco.movie.repository.model.Event;
import com.tenco.movie.repository.model.Notice;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

	@Autowired
	private final EventRepository eventRepository;

	@Transactional
	public List<Event> readEventList() {
       return eventRepository.findNow();
    }

	public Event readEventDetail(int id) {
		Event event = null;
		 try {
			 event = eventRepository.findById(id);
	        } catch (DataAccessException e) {
	            throw new DataDeliveryException(Define.INVALID_INPUT, HttpStatus.INTERNAL_SERVER_ERROR);
	        } catch (Exception e) {
	            throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
	        }
		
		return event;
	}
	
	public List<Event> readEventEnd() {
		List<Event>  event = eventRepository.findEnd();
		return event;
	}
	
	
}
