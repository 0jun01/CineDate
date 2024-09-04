package com.tenco.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenco.movie.repository.model.Email;
import com.tenco.movie.service.MailSendService;


@Controller
@RequestMapping("/mail")
public class EmailController {

//	@Autowired
//	private MailSendService mailSendService;
//
//	@GetMapping("/mailSend")
//	public String mailSend(@RequestBody @Valid Email dto) {
//		System.out.println("여기로 들어왔늬");
//		
//		System.out.println("이메일 인증 이메일 :" + dto.getEmail());
//		return mailSendService.joinEmail(dto.getEmail());
//	}

}
