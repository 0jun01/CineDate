package com.tenco.movie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.movie.repository.model.Event;
import com.tenco.movie.service.EventService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
	private final EventService eventService;
	/**
	 * 이벤트 페이지 요청
	 * @author 김가령, 성후
	 */
	@GetMapping("/event")
	public String getEventPage(Model model) {
        List<Event> eventList = eventService.readEventList();
        model.addAttribute("eventList", eventList);
        return "/event/eventPage";
	}
	/**
	 * 이벤트 상세화면
	 * @author 성후
	 */
	@GetMapping("/eventDetail")
	public String getEventDetail(@RequestParam("id") int id, Model model) {
		Event event = eventService.readEventDetail(id);
		System.out.println("------------------");
		System.out.println(event.getOriginFileName());
		System.out.println("------------------");
		 model.addAttribute("event", event);
	        return "/event/eventDetail";
	}	
	/**
	 *  이벤트 페이지 이동
	 * @return 성후
	 */
	  @GetMapping("/endevent")
	    public String endEvent(Model model) {
		  	List<Event> eventList = eventService.readEventEnd();
	        model.addAttribute("eventList", eventList);
	        return "/event/endEvent"; 
	    }
	  
	  
	  @GetMapping("/allevent")
	    public String allEvent(Model model) {
		  	List<Event> eventList = eventService.readEventAll();
	        model.addAttribute("eventList", eventList);
	        return "/event/allEvent"; 
	    }
	 
}
