package com.tenco.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenco.movie.service.DateProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/duplication")
@RequiredArgsConstructor
public class DuplicationController {
	
	@Autowired
	private final DateProfileService dateService;
	
	
	
	/**
	 * 닉네임 중복검사기능
	 * @author BB
	 * @param nickName
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/checkNickName")
	public boolean checkNickName(@RequestParam(name ="nickName")String nickName) {
		boolean isUse = dateService.searchUsername(nickName) == null ? true : false;
		return isUse;
	}
	

}
