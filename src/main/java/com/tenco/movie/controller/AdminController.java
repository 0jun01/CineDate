package com.tenco.movie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenco.movie.repository.model.Notice;
import com.tenco.movie.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")

@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminService;
	
	@GetMapping("/adminMain")
	public String AdminMain() {
		return "/adminMain";
	}
	/**
     * 공지사항 페이지 요청
     */
    @GetMapping("/notice")
    public String getNoticePage(Model model) {
        List<Notice> noticeList = adminService.readNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "/admin/noticePage";
    }
    
    @GetMapping("/adminNotice")
    public String getAdminNoticePage(Model model) {
        List<Notice> noticeList = adminService.readNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "/admin/adminNoticePage";
    }
	
}
