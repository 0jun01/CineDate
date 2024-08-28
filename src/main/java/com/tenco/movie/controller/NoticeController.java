package com.tenco.movie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.movie.repository.model.Notice;
import com.tenco.movie.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")

@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService noticeService;
	
	/**
     * 공지사항 페이지 요청
     */
    @GetMapping("/notice")
    public String getNoticePage(Model model,@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5")int size) {
    	int totalRecords = noticeService.countNotice();
    	int totalPages = (int)Math.ceil((double)totalRecords / size);
        List<Notice> noticeList = noticeService.readNoticeList(page, size);
        model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);
        model.addAttribute("noticeList", noticeList);
        return "/notice/noticePage";
    }
  
    
    /**
     * 공지사항 상세보기 페이지 요청
     */
    @GetMapping("/notice/detail")
    public String getNoticeDetailPage(@RequestParam("id") int id, Model model) {
        Notice notice = noticeService.readNoticeDetail(id);
        model.addAttribute("notice", notice);
        return "/notice/noticeDetail"; 
    }

    
}
