package com.tenco.movie.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.tenco.movie.dto.EventWriterDTO;
import com.tenco.movie.dto.NoticeWriterDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.model.Event;
import com.tenco.movie.repository.model.History;
import com.tenco.movie.repository.model.HistoryTimeLine;
import com.tenco.movie.repository.model.Notice;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.service.AdminPageService;
import com.tenco.movie.service.UserService;
import com.tenco.movie.utils.Define;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminPageController {
	@Autowired
	private AdminPageService adminPageService;
	@Autowired
	private UserService userService;
	
	private final HttpSession session;
	
	private Notice notice;
	private Event event;
	// 메인보드 시작
	
	@GetMapping("/logout")
	public String logoutAdmin() {
		session.invalidate();
		return "redirect:/user/signIn";
	}
	
	
	// 메인보드 끝
	// ----------------------------------------------------------------------
	// 공지사항 시작

	// 어드민관리 페이지 연결
	@GetMapping("/adminMain")
	public String AdminMain(@SessionAttribute(Define.PRINCIPAL) User principal, Model model) {
		
		String name = principal.getLoginId();
		
		User user = userService.getUserById(name);
		
		model.addAttribute("user", user);
		
		
		
		return "/adminMain";
	}

	// 어드민 공지사항 페이지 요청
	@GetMapping("/adminNotice")
	public String getAdminNoticePage(@SessionAttribute(Define.PRINCIPAL) User principal, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model) {

		int totalRecords = adminPageService.countNoticeAll();
		int totalPages = (int) Math.ceil((double) totalRecords / size);
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		

		List<Notice> noticeList = adminPageService.readNoticePage(page, size);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);
		model.addAttribute("user", user);

		return "/admin/adminNoticePage";
	}

	@PostMapping("/adminNotice")
	public String getAdminNoticeProc(@SessionAttribute(Define.PRINCIPAL) User principal, @RequestParam(name = "search") String search,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,

			Model model) {

		int totalRecords = adminPageService.countNotice(search);
		int totalPages = (int) Math.ceil((double) totalRecords / size);
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		

		List<Notice> noticeList = adminPageService.searchNoticePage(search, page, size);

		model.addAttribute("search", search);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);
		model.addAttribute("user", user);

		return "/admin/adminNoticePage";
	}

	// 어드민 공지사항 글쓰기 요청
	@GetMapping("/adminNoticeWrite")
	public String adminNoticeWrite(@SessionAttribute(Define.PRINCIPAL) User principal, Model model) {
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		
		model.addAttribute("user", user);

		return "/admin/adminNoticeWrite";
	}

	@PostMapping("/adminNoticeWrite")
	public String adminNoticeWriteProc(NoticeWriterDTO dto, @SessionAttribute(Define.PRINCIPAL) User principal, Model model) {

		adminPageService.createNotice(dto);
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		
		model.addAttribute("user", user);


		return "redirect:/adminNotice";
	}

	// 어드민 공지사항 수정 요청
	@GetMapping("/adminNoticeReWrite/{id}")
	public String adminNoticeRewrite(@SessionAttribute(Define.PRINCIPAL) User principal, @PathVariable(name = "id") Integer id, Model model) {

		notice = adminPageService.findById(id);
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		
		model.addAttribute("user", user);

		
		model.addAttribute("notice", notice);

		return "/admin/adminNoticeReWrite";
	}

	@PostMapping("/adminNoticeReWrite/{id}")
	public String adminNoticeRewriteProc(@SessionAttribute(Define.PRINCIPAL) User principal, NoticeWriterDTO dto, @PathVariable(name = "id") int id, Model model) {
		if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
			throw new DataDeliveryException("제목을 입력하세요!", HttpStatus.BAD_REQUEST);
		}
		if (dto.getContent() == null || dto.getContent().isEmpty()) {
			throw new DataDeliveryException("내용을 1글자 이상 " + "+입력하세요", HttpStatus.BAD_REQUEST);
		}

		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		
		model.addAttribute("user", user);

		
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
	public String adminNoticeDetail(@SessionAttribute(Define.PRINCIPAL) User principal, @PathVariable(name = "id") Integer id, Model model) {

		notice = adminPageService.findById(id);
		model.addAttribute("notice", notice);
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		
		model.addAttribute("user", user);

		

		return "admin/adminNoticeDetail";
	}

//공지사항 끝	 	
//-------------------------------------------------------------
//이벤트 시작

	@GetMapping("/adminEvent")
	public String adminEventPage(@SessionAttribute(Define.PRINCIPAL) User principal, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model) {

		int totalRecords = adminPageService.countEventAll();
		int totalPages = (int) Math.ceil((double) totalRecords / size);
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		


		List<Event> eventList = adminPageService.readEventPage(page, size);
		model.addAttribute("user", user);
		model.addAttribute("eventList", eventList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);

		return "/admin/adminEventPage";
	}
	
	@PostMapping("/adminEvent")
	public String adminEventProc(@SessionAttribute(Define.PRINCIPAL) User principal, @RequestParam(name = "search") String search,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			Model model) {
		
		int totalRecords = adminPageService.countEvent(search);
		int totalPages = (int) Math.ceil((double) totalRecords / size);
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		


		List<Event> eventList = adminPageService.searchEventPage(search, page, size);
		
		model.addAttribute("user", user);
		model.addAttribute("search", search);
		model.addAttribute("eventList", eventList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);

		return "/admin/adminEventPage";
	}

	@GetMapping("/adminEventDetail/{id}")
	public String adminEventDetail(@SessionAttribute(Define.PRINCIPAL) User principal, @PathVariable(name = "id") Integer id, Model model) {

		event = adminPageService.findEventById(id);
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		

		model.addAttribute("user", user);
		model.addAttribute("event", event);

		return "/admin/adminEventDetail";
	}

	@GetMapping("/adminEventWrite")
	public String adminEventWritePage(@SessionAttribute(Define.PRINCIPAL) User principal, Model model) {
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		
		model.addAttribute("user", user);

		
		return "/admin/adminEventWrite";
	}

	@PostMapping("/adninEventWrite")
	public String adminEventWriteProc(@SessionAttribute(Define.PRINCIPAL) User principal, EventWriterDTO dto, @PathVariable(name = "file") File file, Model model) {
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		
		model.addAttribute("user", user);

		return "redirect:/adminEvent";
	}

	// 어드민 이벤트 삭제 요청
	@GetMapping("/adminEventDelete/{id}")
	public String adminEventDelete(@SessionAttribute(Define.PRINCIPAL) User principal, @PathVariable(name = "id") Integer id) {

		System.out.println(id);

		event = adminPageService.findEventById(id);

		adminPageService.deleteEvent(event.getId());

		return "/admin/adminEventDelete";
	}

//이벤트 끝
//-------------------------------------------------------------
//회원정보 시작

	// 기본 회원정보 띄어주는거
	@GetMapping("/adminMemberList")
	public String adminMemberList(@SessionAttribute(Define.PRINCIPAL) User principal, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model) {

		int totalRecords = adminPageService.countMemberAll();
		int totalPages = (int) Math.ceil((double) totalRecords / size);
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		
		
		List<User> userList = adminPageService.readMemberList(page, size);
		model.addAttribute("user", user);
		model.addAttribute("userList",userList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);

		return "/admin/adminMemberList";
	}
	
	@PostMapping("/adminMemberList")
	public String adminMemberListProc(@SessionAttribute(Define.PRINCIPAL) User principal, @RequestParam(name = "search") String search,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			Model model) {
		
		int totalRecords = adminPageService.countMember(search);
		int totalPages = (int) Math.ceil((double) totalRecords / size);
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
		

		List<User> userList = adminPageService.searchMemberPage(search, page, size);
		
		model.addAttribute("user", user);
		model.addAttribute("search", search);
		model.addAttribute("userList", userList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);

		return "/admin/adminMemberList";
		
	}
	
	
	// 어드민 멤버 삭제 요청
		@GetMapping("/adminMemberDelete/{id}")
		public String adminMemberDelete(@PathVariable(name = "id") Integer id) {

			System.out.println(id);


			return "/admin/adminMemberDelete";
		}
//회원정보 끝
//-------------------------------------------------------------
//결제 테이블 시작
		
		@GetMapping("/adminHistory")
		public String adminHistoryPage(@SessionAttribute(Define.PRINCIPAL) User principal, Model model) {
			
			String name = principal.getLoginId();
			
			User user = userService.getUserById(name);
			
			
			List<HistoryTimeLine> historyTimeLine = adminPageService.countHistory();
			List<History> historyList = adminPageService.readAllHistory();
			
			model.addAttribute("user", user);
			model.addAttribute("historyTimeLine",historyTimeLine);
			model.addAttribute("historyList",historyList);
			
			
			return "/admin/adminHistory";
		}
}