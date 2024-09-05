package com.tenco.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminHistoryPageController {

	@GetMapping("/adminHistory")
	public String adminHistoryPage() {
		
		return "/admin/adminHistory";
	}
	
}
