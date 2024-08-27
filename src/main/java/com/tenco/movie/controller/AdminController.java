package com.tenco.movie.controller;

import java.util.List;

import javax.swing.text.Document;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.tenco.movie.dto.NoticeWriterDTO;
import com.tenco.movie.repository.model.Notice;
import com.tenco.movie.service.AdminService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")

@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminService;
	private final HttpSession session;
	
	
	
	/**
     * 공지사항 페이지 요청
     */
    @GetMapping("/notice")
    public String getNoticePage(Model model) {
        List<Notice> noticeList = adminService.readNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "/admin/noticePage";
    }
    
    // ---------------------------------------------------------------------------
    
 // 어드민관리 페이지 연결
 	@GetMapping("/adminMain")
 	public String AdminMain() {
 		return "/adminMain";
 	}
 	
 	// 어드민 공지사항 페이지 요청
 	@GetMapping("/adminNotice")
 	public String getAdminNoticePage(Model model) {
 		List<Notice> noticeList = adminService.readNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "/admin/adminNoticePage";
 	}
 	
 	// 어드민 공지사항 글쓰기 요청
 	@GetMapping("/adminNoticeWrite")
 	public String adminNoticeWrite() {
 		return "/admin/adminNoticeWrite";
 	}
 	
 	@PostMapping("/adminNoticeWrite")
 	public String adminNoticeWriteProc(NoticeWriterDTO dto,
 		Model model) {
 		
 		adminService.createNotice(dto);
 		
 		
 		
 		return "redirect:/adminNotice";
 	}
	
}
