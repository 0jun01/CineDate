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
 	
 	@GetMapping("/adminNoticeReWrite/{id}")
 	public String adminNoticeRewrite(@PathVariable(name = "id") Integer id,
 			Model model) {
 		
 		notice = adminService.findById(id);
 		model.addAttribute("notice",notice);
 		
 		return "/admin/adminNoticeReWrite";
 	}
 	
 	@PostMapping("/adminNoticeReWrite/{id}")
 	public String adminNoticeRewriteProc(NoticeWriterDTO dto,
 			@PathVariable(name = "id") int id
 			){
 		if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
			throw new DataDeliveryException("제목을 입력하세요!", HttpStatus.BAD_REQUEST);
		}
		if (dto.getContent() == null || dto.getContent().isEmpty()) {
			throw new DataDeliveryException("내용을 1글자 이상 "
					+ "+입력하세요", HttpStatus.BAD_REQUEST);
		}
 		
		adminService.reCreateNotice(dto, id);
		
		return "redirect:/adminNotice";
 	}
 	
 	@GetMapping("/adminNoticeDelete/{id}")
 	public String adminNoticeDelete(@PathVariable(name = "id") Integer id) {
 		
 		System.out.println(id);
 		
 		notice = adminService.findById(id);
 		
 		adminService.deleteNotice(notice.getId());
 		
 		return "/admin/adminNoticeDelete";
 	}
 	
 	
	
}
