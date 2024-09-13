package com.tenco.movie.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tenco.movie.dto.EventWriterDTO;
import com.tenco.movie.dto.NoticeWriterDTO;
import com.tenco.movie.dto.UserWriterDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.model.DateProfile;
import com.tenco.movie.repository.model.Event;
import com.tenco.movie.repository.model.History;
import com.tenco.movie.repository.model.HistoryTimeLine;
import com.tenco.movie.repository.model.Notice;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.service.AdminPageService;
import com.tenco.movie.service.DateProfileService;
import com.tenco.movie.service.PaymentService;
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
	@Autowired
	private DateProfileService dateProfileService;

	@Autowired
	private final PaymentService paymentService;

	private final HttpSession session;

	private Notice notice;
	private Event event;
	private User member;
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
	public String AdminMain(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal, Model model,
			RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

		String name = principal.getLoginId();

		User user = userService.getUserById(name);
		DateProfile userProfile = dateProfileService.searchProfile(user.getId());

		int reviewCount = adminPageService.countReview();
		int sellCount = adminPageService.countSell();
		int memberCount = adminPageService.countMember();
		
		int profileCount = adminPageService.countProfileAll();
		
		List<DateProfile> profileList = adminPageService.readMainProfile();
		List<History> historyList = adminPageService.readMainHistory();

		model.addAttribute("sellCount", sellCount);
		model.addAttribute("reviewCount", reviewCount);
		model.addAttribute("memberCount", memberCount);
		model.addAttribute("profileCount", profileCount);
		model.addAttribute("user", user);
		model.addAttribute("profile",userProfile);
		model.addAttribute("profileList", profileList);
		model.addAttribute("historyList", historyList);

		return "/adminMain";
	}

	// 어드민 공지사항 페이지 요청
	@GetMapping("/adminNotice")
	public String getAdminNoticePage(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model,
			RedirectAttributes redirectAttributes) {

		if (principal == null) {
			return "redirect:/home";
		}

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
	public String getAdminNoticeProc(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			@RequestParam(name = "search") String search, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,

			Model model, RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

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
	public String adminNoticeWrite(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			Model model, RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}
		String name = principal.getLoginId();
		User user = userService.getUserById(name);

		model.addAttribute("user", user);

		return "/admin/adminNoticeWrite";
	}

	@PostMapping("/adminNoticeWrite")
	public String adminNoticeWriteProc(NoticeWriterDTO dto,
			@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal, Model model,
			RedirectAttributes redirectAttributes) {

		if (principal == null) {
			return "redirect:/home";
		}

		adminPageService.createNotice(dto);

		String name = principal.getLoginId();
		User user = userService.getUserById(name);

		model.addAttribute("user", user);

		return "redirect:/adminNotice";
	}

	// 어드민 공지사항 수정 요청
	@GetMapping("/adminNoticeReWrite/{id}")
	public String adminNoticeRewrite(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

		notice = adminPageService.findById(id);

		String name = principal.getLoginId();
		User user = userService.getUserById(name);

		model.addAttribute("user", user);

		model.addAttribute("notice", notice);

		return "/admin/adminNoticeReWrite";
	}

	@PostMapping("/adminNoticeReWrite/{id}")
	public String adminNoticeRewriteProc(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			NoticeWriterDTO dto, @PathVariable(name = "id") int id, Model model,
			RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

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
	public String adminNoticeDetail(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

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
	public String adminEventPage(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model,
			RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

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
	public String adminEventProc(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			@RequestParam(name = "search") String search, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model,
			RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

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
	public String adminEventDetail(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

		event = adminPageService.findEventById(id);

		String name = principal.getLoginId();
		User user = userService.getUserById(name);

		model.addAttribute("user", user);
		model.addAttribute("event", event);
		System.out.println(event);

		return "/admin/adminEventDetail";
	}

	@GetMapping("/adminEventWrite")
	public String adminEventWritePage(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			Model model, RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

		String name = principal.getLoginId();
		User user = userService.getUserById(name);

		model.addAttribute("user", user);

		return "/admin/adminEventWrite";
	}

	@PostMapping("/adminEventWrite")
	public String adminEventWriteProc(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			@RequestParam(name = "mFileOne") MultipartFile mFileOne, @RequestParam(name = "title") String title,
			@RequestParam(name = "releaseDate") Date releaseDate, @RequestParam(name = "endDate") Date endDate,
			Model model, RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

		if (title == null || title.isEmpty()) {
			throw new DataDeliveryException("제목을 입력하세요", HttpStatus.BAD_REQUEST);
		}

		if (releaseDate == null) {
			throw new DataDeliveryException("시작 날짜를 입력하세요", HttpStatus.BAD_REQUEST);
		}

		if (endDate == null) {
			throw new DataDeliveryException("마감 날짜를 입력하세요", HttpStatus.BAD_REQUEST);
		}

		String name = principal.getLoginId();

		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String str1 = format.format(releaseDate);
	     String str2 = format.format(endDate);

		EventWriterDTO dto = EventWriterDTO.builder().title(title).releaseDate(str1).endDate(str2)
				.mFileOne(mFileOne).build();

		adminPageService.createEvent(dto);

		return "redirect:/adminEvent";
	}

	@GetMapping("/adminEventReWrite/{id}")
	public String adminEventReWritePage(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}
		event = adminPageService.findEventById(id);

		String name = principal.getLoginId();
		User user = userService.getUserById(name);

		model.addAttribute("user", user);

		model.addAttribute("event", event);

		return "/admin/adminEventReWrite";

	}

	@PostMapping("/adminEventReWrite/{id}")
	public String adminEventReWriteProc(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			EventWriterDTO dto, @PathVariable(name = "id") int id,
			@RequestParam(name = "mFileOne") MultipartFile mFileOne,
			@RequestParam(name = "releaseDate") Date releaseDate, @RequestParam(name = "endDate") Date endDate,
			@RequestParam(name = "title") String title, Model model, RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

		if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
			throw new DataDeliveryException("제목을 입력하세요!", HttpStatus.BAD_REQUEST);
		}

		if (dto.getReleaseDate() == null) {
			throw new DataDeliveryException("시작 날짜를 입력하세요!", HttpStatus.BAD_REQUEST);
		}
		if (dto.getEndDate() == null) {
			throw new DataDeliveryException("마감 날짜를 입력하세요!", HttpStatus.BAD_REQUEST);
		}

		String name = principal.getLoginId();
		User user = userService.getUserById(name);

		
		model.addAttribute("user", user);

		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String str1 = format.format(releaseDate);
	     String str2 = format.format(endDate);
	     
	    dto.setReleaseDate(str1);
	    dto.setEndDate(str2);
		
		
		adminPageService.reCreateEvent(dto, id);

		return "redirect:/adminEvent";
	}

	// 어드민 이벤트 삭제 요청
	@GetMapping("/adminEventDelete/{id}")
	public String adminEventDelete(@SessionAttribute(Define.PRINCIPAL) User principal,
			@PathVariable(name = "id") Integer id) {

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
	public String adminMemberList(@SessionAttribute(Define.PRINCIPAL) User principal,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model,
			RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

		int totalRecords = adminPageService.countMemberAll();
		int totalPages = (int) Math.ceil((double) totalRecords / size);
		String name = principal.getLoginId();
		User user = userService.getUserById(name);

		List<User> userList = adminPageService.readMemberList(page, size);
		model.addAttribute("user", user);
		model.addAttribute("userList", userList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);

		return "/admin/adminMemberList";
	}

	@PostMapping("/adminMemberList")
	public String adminMemberListProc(@SessionAttribute(Define.PRINCIPAL) User principal,
			@RequestParam(name = "search") String search, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model,
			RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

		int totalRecords = adminPageService.countMember(search);
		int totalPages = (int) Math.ceil((double) totalRecords / size);

		String name = principal.getLoginId();
		User user = userService.getUserById(name);

		List<User> userList = adminPageService.searchMemberPage(search, page, size);

		model.addAttribute("user", user);
		model.addAttribute("search", search);
		model.addAttribute("userList", userList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);

		return "/admin/adminMemberList";

	}
	
	@GetMapping("/adminMemberDetail/{id}")
	public String adminMemberDetailPage(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		
		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}
		
		member = adminPageService.readMemberById(id);

		String name = principal.getLoginId();
		User user = userService.getUserById(name);

		model.addAttribute("user", user);
		model.addAttribute("member", member);
		System.out.println(event);

		return "/admin/adminMemberDetail";
		
		
	}
	
	@PostMapping("/adminMemberDetail/{id}")
	public String adminMemberDetailProc(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal,
			@PathVariable(name = "id") Integer id, UserWriterDTO dto, Model model, RedirectAttributes redirectAttributes) {
		
		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}
		
		String name = principal.getLoginId();
		User user = userService.getUserById(name);
 
		
		model.addAttribute("user", user);
		
		adminPageService.reCreateMember(dto);
		
		return "redirect:/adminMemberList";
		
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
	public String adminHistoryPage(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal, Model model,
			RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute("접근할 수 없는 페이지 입니다", HttpStatus.BAD_REQUEST);
			return "redirect:/home";
		}

		String name = principal.getLoginId();

		User user = userService.getUserById(name);

		List<HistoryTimeLine> historyTimeLine = adminPageService.countHistory();
		List<History> historyList = adminPageService.readAllHistory();

		model.addAttribute("user", user);
		model.addAttribute("historyTimeLine", historyTimeLine);
		model.addAttribute("historyList", historyList);

		return "/admin/adminHistory";
	}

	/**
	 * 결제 취소
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@PostMapping("/cancel")
	public String adminPaymentCancel(@RequestParam(name = "payId") int id) throws IOException, InterruptedException {

		String cancel = paymentService.cancelPaymentHistory(id);

		return "redirect:/adminHistory";
	}

	/**
	 * 결제 취소 리스트
	 * 
	 * @param param
	 * @return
	 */
	@GetMapping("/adminCancelHistory")
	public String getCancelHistory(@RequestParam String param,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model) {

		return new String(); // <-- 나중에 리스트 뽑기 cancel_toss_History_tb
	}

	// ==================== profile ====================

	/**
	 * 데이팅 프로필 리스트
	 * 
	 * @param page
	 * @param size
	 * @param search
	 * @param model
	 * @return
	 */
	@GetMapping("/adminProfileList")
	public String getMethodName(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "search", defaultValue = "") String search, Model model) {

		int totalCount = adminPageService.countAdminProfileList(search);
		int totalPages = (int) Math.ceil((double) totalCount / size);

		List<DateProfile> list = adminPageService.readProfileList(search, page, size);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);

		return "admin/adminProfileList";
	}

	@PostMapping("/lifeStatus")
	public String postMethodName(@RequestParam(name = "id") int id) {
		adminPageService.lifeStatusUpdate(id);
		return "redirect:/adminProfileList";
	}

}