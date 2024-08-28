package com.tenco.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/event")
public class EventController {

	/**
	 * 이벤트 페이지 요청
	 * @author 김가령
	 */
	@GetMapping("/event")
	public String getEventPage() {
		return "/event/eventPage";
	}
	
	/**
	 *  이벤트 페이지 이동
	 * @return ksh
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
