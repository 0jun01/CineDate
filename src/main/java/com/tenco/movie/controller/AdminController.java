package com.tenco.movie.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenco.movie.dto.NoticeWriterDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.model.Notice;
import com.tenco.movie.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")

@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminService;
	private Notice notice;
	

    
    // ---------------------------------------------------------------------------
 
}
