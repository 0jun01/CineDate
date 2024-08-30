package com.tenco.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

	@GetMapping("/search")
	public String mainSearch() {
		System.out.println("검색하기 들어왔늬");
		
		
		
		return "search/search";
	}
	
}
