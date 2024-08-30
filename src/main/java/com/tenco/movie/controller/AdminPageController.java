package com.tenco.movie.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.movie.dto.EventWriterDTO;
import com.tenco.movie.dto.NoticeWriterDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.model.Event;
import com.tenco.movie.repository.model.Notice;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.service.AdminPageService;
import com.tenco.movie.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminPageController {

	private final AdminPageService adminPageService;
	private final AdminService adminService;
	private Notice notice;
	private Event event;
	private User user;

	// ----------------------------------------------------------------------
	// 공지사항 시작

	// 어드민관리 페이지 연결
	@GetMapping("/adminMain")
	public String AdminMain() {
		return "/adminMain";
	}

	// 어드민 공지사항 페이지 요청
	@GetMapping("/adminNotice")
	public String getAdminNoticePage(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model) {

		int totalRecords = adminPageService.countNoticeAll();
		int totalPages = (int) Math.ceil((double) totalRecords / size);

		List<Notice> noticeList = adminPageService.readNoticePage(page, size);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);

		return "/admin/adminNoticePage";
	}

	@PostMapping("/adminNotice")
	public String getAdminNoticeProc(@RequestParam(name = "search") String search,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,

			Model model) {

		int totalRecords = adminPageService.countNotice(search);
		int totalPages = (int) Math.ceil((double) totalRecords / size);

		List<Notice> noticeList = adminPageService.searchNoticePage(search, page, size);

		model.addAttribute("search", search);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);

		return "/admin/adminNoticePage";
	}

	// 어드민 공지사항 글쓰기 요청
	@GetMapping("/adminNoticeWrite")
	public String adminNoticeWrite() {
		return "/admin/adminNoticeWrite";
	}

	@PostMapping("/adminNoticeWrite")
	public String adminNoticeWriteProc(NoticeWriterDTO dto, Model model) {

		adminPageService.createNotice(dto);

		return "redirect:/adminNotice";
	}

	// 어드민 공지사항 수정 요청
	@GetMapping("/adminNoticeReWrite/{id}")
	public String adminNoticeRewrite(@PathVariable(name = "id") Integer id, Model model) {

		notice = adminPageService.findById(id);
		model.addAttribute("notice", notice);

		return "/admin/adminNoticeReWrite";
	}

	@PostMapping("/adminNoticeReWrite/{id}")
	public String adminNoticeRewriteProc(NoticeWriterDTO dto, @PathVariable(name = "id") int id) {
		if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
			throw new DataDeliveryException("제목을 입력하세요!", HttpStatus.BAD_REQUEST);
		}
		if (dto.getContent() == null || dto.getContent().isEmpty()) {
			throw new DataDeliveryException("내용을 1글자 이상 " + "+입력하세요", HttpStatus.BAD_REQUEST);
		}

		adminPageService.reCreateNotice(dto, id);

		return "redirect:/adminNotice";
	}

	// 어드민 공지 삭제 요청
	@GetMapping("/adminNoticeDelete/{id}")
	public String adminNoticeDelete(@PathVariable(name = "id") Integer id) {

		System.out.println(id);

		notice = adminPageService.findById(id);

		adminPageService.deleteNotice(notice.getId());

		return "/admin/adminNoticeDelete";
	}

	@GetMapping("/adminNoticeDetail/{id}")
	public String adminNoticeDetail(@PathVariable(name = "id") Integer id, Model model) {

		notice = adminPageService.findById(id);
		model.addAttribute("notice", notice);

		return "admin/adminNoticeDetail";
	}

//공지사항 끝	 	
//-------------------------------------------------------------
//이벤트 시작

	@GetMapping("/adminEvent")
	public String adminEventPage(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model) {

		int totalRecords = adminPageService.countEventAll();
		int totalPages = (int) Math.ceil((double) totalRecords / size);
		
			
		List<Event> eventList = adminPageService.readEventPage(page, size);
		model.addAttribute("eventList", eventList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);
		
		return "/admin/adminEventPage";
	}
	
	@GetMapping("/adminEventDetail/{id}")
	public String adminEventDetail(@PathVariable(name = "id") Integer id, Model model) {

		event = adminPageService.findEventById(id);
		model.addAttribute("event", event);

		return "/admin/adminEventDetail";
	}
	
	@GetMapping("/adminEventWrite")
	public String adminEventWritePage() {
		return "/admin/adminEventWrite";
	}
	@PostMapping("/adninEventWrite")
	public String adminEventWriteProc(EventWriterDTO dto, Model model) {
		
		
		return "redirect:/adminEvent";
	}


//이벤트 끝
//-------------------------------------------------------------
//회원정보 시작

	// 기본 회원정보 띄어주는거
	@GetMapping("/adminMemberList")
	public String adminMemberList(Model model) {

		List<User> userList = adminPageService.selectAllUser();
		model.addAttribute(userList);

		return "/admin/adminMemberList";
	}

}