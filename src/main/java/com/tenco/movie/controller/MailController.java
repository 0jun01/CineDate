package com.tenco.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.movie.service.MailSendService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@ToString
@Builder
@Controller
@RequestMapping("/mail")
public class MailController {
	
	private MailSendService sendService;
	
	@GetMapping("/mail")
	public String mailConfirm(@RequestParam(value = "name", defaultValue = "World") String name, Model model) {
		System.out.println("메일 여기로 들어오는 왔늬");
//		model.addAttribute("name", name);
        return "main"; // templates/main.html 파일을 렌더링
		
	}
	
}
