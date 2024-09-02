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
	  @RequestMapping("/event1")
	    public String showEvent1() {
	        return "event/event1"; // JSP 파일의 경로를 리턴
	    }
	  @RequestMapping("/event2")
	  public String showEvent2() {
		  return "event/event2";
	  }
	  @RequestMapping("/event3")
	  public String showEvent3() {
		  return "event/event3"; 
	  }
	  @RequestMapping("/event4")
	  public String showEvent4() {
		  return "event/event4"; 
	  }
	  @RequestMapping("/event5")
	  public String showEvent5() {
		  return "event/event5"; 
	  }
	  @RequestMapping("/event6")
	  public String showEvent6() {
		  return "event/event6"; 
	  }
}
