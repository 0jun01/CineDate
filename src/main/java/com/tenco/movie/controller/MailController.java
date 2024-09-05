package com.tenco.movie.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenco.movie.service.MailSendService;


@Controller
@RequestMapping("/mail")
public class MailController {
	
	private MailSendService mailSendService;
	
	// @PostMapping("/mail")
	// @ResponseBody
/*	public ResponseEntity<Map<String,Object>> mailPage(@RequestBody Map<String, Object> body) {
		System.out.println("메일 들어왔늬 : " + body.get("email"));
		Map<String, Object> response = new HashMap<>();
		
        try {
        	// String code = mailSendService.sendSimpleMessage("email");
            response.put("success", true);
            System.out.println("메일 들어왔다아");
            // response.put("code", code); // Send the code back to the client for verification
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "메일 전송 실패다아");
        }
		
		// response.put("success", true);
		// response.put("success", false);
		return ResponseEntity.ok(response);
	}*/
	
	
}
