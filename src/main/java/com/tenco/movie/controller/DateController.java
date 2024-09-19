package com.tenco.movie.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tenco.movie.dto.DateProfileDTO;
import com.tenco.movie.dto.ItemRequest;
import com.tenco.movie.dto.MessageDTO;
import com.tenco.movie.dto.profileDetailDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.model.ConItems;
import com.tenco.movie.repository.model.DateProfile;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.service.DateManagerService;
import com.tenco.movie.service.DateProfileService;
import com.tenco.movie.service.PaymentService;
import com.tenco.movie.service.UserService;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/date")

@RequiredArgsConstructor
public class DateController {

	@Autowired
	private final DateProfileService dateService;
	@Autowired
	private final PaymentService payservice;

	@Autowired
	private final DateManagerService dateManagerService;
	
	@Autowired
	private final UserService userService;

	/**
	 * 데이트 페이지 요청
	 * 
	 * @author 배병호 principal 기준으로 회원가입 페이지 date page or 회원가입 페이지 전화
	 * @author 김가령
	 * @author 유형정 슈퍼 리스트 추가
	 */
	@GetMapping("/date")
	public String getDatePage(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal, Model model,
			RedirectAttributes redirectAttributes) {
		/**
		 * 데이트 페이지 들어갈때 로그인 안되있으면 로그인 하라고 방어코드 추가함
		 * 
		 * @author 성후
		 */
		if (principal == null) {
			redirectAttributes.addFlashAttribute(Define.ENTER_YOUR_LOGIN, HttpStatus.BAD_REQUEST);
			return "redirect:/user/signIn"; // 로그인으로 리다이렉트
		}

		DateProfile proifile = dateService.searchProfile(principal.getId());
		if (proifile == null) {
			return "date/DateSignUp";
		} else if (proifile.getLifeStatus() == 1) {
			throw new DataDeliveryException(Define.PROFILE_SUSPENDING, HttpStatus.BAD_REQUEST);
		}

		List<DateProfile> list = dateService.searchPartner(principal.getId(), principal.getGender());
		System.out.println(list);

		List<DateProfile> superList = dateService.superPartner(principal.getId(), principal.getGender());
		System.out.println("superList : " + superList);

		model.addAttribute("list", list);
		model.addAttribute("superList", superList);

		return "date/ProfileList";
	}

	/**
	 * 데이트페이지 정보수정 이동
	 * 
	 * @author 성후
	 */
	@GetMapping("/profilePage")
	public String getProfilePage(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal, Model model, RedirectAttributes redirectAttributes) {

		if (principal == null) {
			redirectAttributes.addFlashAttribute(Define.ENTER_YOUR_LOGIN, HttpStatus.BAD_REQUEST);
			return "redirect:/user/signIn"; // 로그인으로 리다이렉트
		}
		
		DateProfile profile = dateService.searchProfile(principal.getId());
		if (profile == null) {
			return "date/DateSignUp";
		}
		
		String imageUrl = "/DateProfileIMAGE/" + profile.getFirstUploadFileName();
		model.addAttribute("profile", profile);
		model.addAttribute("imageUrl", imageUrl);
		return "date/profilePage";
	}

	/**
	 * 데이트페이지 수정
	 * 
	 * @author 성후
	 * @param nickName
	 * @param introduce
	 * @param file1
	 * @param file2
	 * @param file3
	 * @param file4
	 * @param file5
	 * @param userId
	 * @param principal
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/updateProfile")
	public String updateProfile(@RequestParam("nickname") String nickName, @RequestParam("introduce") String introduce,
			@RequestParam(name = "profile_upload_file6") MultipartFile file1,
			@RequestParam(name = "profile_upload_file2") MultipartFile file2,
			@RequestParam(name = "profile_upload_file3") MultipartFile file3,
			@RequestParam(name = "profile_upload_file4") MultipartFile file4,
			@RequestParam(name = "profile_upload_file5") MultipartFile file5,
			@SessionAttribute(Define.PRINCIPAL) User principal) throws IOException {

		DateProfileDTO update = DateProfileDTO.builder().nickName(nickName).introduce(introduce).mFileOne(file1)
				.mFileTwo(file2).mFile3(file3).mFile4(file4).mFile5(file5).build();

		dateService.updateProfile(update, principal.getId());

		return "redirect:/date/date";
	}

	@GetMapping("/signUp")
	public String getSignUp() {
		return "date/DateSignUp";
	}

	/**
	 * @author 성후
	 * @param principal
	 * @param mFileOne
	 * @param mFileTwo
	 * @param nickName
	 * @param introduce
	 * @return
	 */
	@PostMapping("/signUp")
	public String postDateSignUp(@SessionAttribute(Define.PRINCIPAL) User principal,
			@RequestParam(name = "mFileOne") MultipartFile mFileOne,
			@RequestParam(name = "mFileTwo") MultipartFile mFileTwo,
			@RequestParam(name = "mFile3") MultipartFile mFile3, @RequestParam(name = "mFile4") MultipartFile mFile4,
			@RequestParam(name = "mFile5") MultipartFile mFile5, @RequestParam(name = "nickName") String nickName,
			@RequestParam(name = "introduce") String introduce, @RequestParam(name = "idealType") String idealType,
			@RequestParam(name = "bloodtype") String bloodtype, @RequestParam(name = "myJop") String myJop,
			@RequestParam(name = "bestMovie") String bestMovie, @RequestParam(name = "drinking") String drinking,
			@RequestParam(name = "smoking") String smoking) {

		if (nickName == null || nickName.isEmpty()) {
			throw new DataDeliveryException("닉네임을 입력하세요", HttpStatus.BAD_REQUEST);
		}
		if (idealType == null || idealType.trim().isEmpty() || idealType.equalsIgnoreCase("")) {
			throw new DataDeliveryException("이상형을 입력하세요", HttpStatus.BAD_REQUEST);
		}
		if (bloodtype == null || bloodtype.trim().isEmpty() || bloodtype.equalsIgnoreCase("")) {
			throw new DataDeliveryException("형액형을 선택하세요", HttpStatus.BAD_REQUEST);
		}
		if (myJop == null || myJop.trim().isEmpty() || myJop.equalsIgnoreCase("")) {
			throw new DataDeliveryException("이상형을 입력하세요", HttpStatus.BAD_REQUEST);
		}
		if (bestMovie == null || bestMovie.trim().isEmpty() || bestMovie.equalsIgnoreCase("")) {
			throw new DataDeliveryException("좋아하는 영화를 입력하세요", HttpStatus.BAD_REQUEST);
		}
		if (drinking == null || drinking.trim().isEmpty() || drinking.equalsIgnoreCase("")) {
			throw new DataDeliveryException("음주여부을 선택하세요", HttpStatus.BAD_REQUEST);
		}
		if (smoking == null || smoking.trim().isEmpty() || smoking.equalsIgnoreCase("")) {
			throw new DataDeliveryException("흡연여부을 선택하세요", HttpStatus.BAD_REQUEST);
		}

		DateProfileDTO dto = DateProfileDTO.builder().nickName(nickName).introduce(introduce).mFileOne(mFileOne)
				.mFileTwo(mFileTwo).mFile3(mFile3).mFile4(mFile4).mFile5(mFile5).build();

		profileDetailDTO detailDTO = profileDetailDTO.builder().userId(principal.getId()).idealType(idealType)
				.bloodtype(bloodtype).myJop(myJop).bestMovie(bestMovie).drinking(drinking).smoking(smoking).build();

		dateService.createdProfile(principal, dto);
		dateService.createdProfileDetail(detailDTO);

		return "redirect:/date/date";
	}

	/**
	 * 
	 * @return date/popcornStore
	 * @author 변영준
	 */
	@GetMapping("/popcornStore")
	public String postPopcornStore(Model model) {
		List<ConItems> itemList = dateService.viewStoreList();

		model.addAttribute("item", itemList);
		return "date/popcornStore";
	}
	/**
	 * 팝콘 -> 토스로 충전
	 * 
	 * @author BB
	 * @param popcorn
	 * @param model
	 * @return
	 */
	@PostMapping("/popcornStore")
	public String postPopcornStore(@RequestParam(value = "popcorn", required = false) String popcorn, Model model,
			@SessionAttribute(Define.PRINCIPAL) User principal) {
		// 개수에 따라 가격 변동
		int conCount = Integer.parseInt(popcorn);

		int amount = 100 * conCount;
		String orderId = payservice.getOderId(); /// 6~ 64
		String orderName = "con";
		String customerName = principal.getName();

		// 모델에 데이터 추가
		model.addAttribute("amount", amount);
		model.addAttribute("orderId", orderId);
		model.addAttribute("orderName", orderName);
		model.addAttribute("customerName", customerName);

		return "pay/tossPay";
	}

	@GetMapping("/tickets")
	public String postTicketProc(@RequestParam("quantity") int quantity, Model model,
			@SessionAttribute(Define.PRINCIPAL) User principal) {
		System.out.println(quantity);
		System.out.println(quantity);
		System.out.println(quantity);
		int amount = 1 * quantity;
		String orderId = payservice.getOderId();
		String orderName = "ticket";
		String customerName = principal.getName();

		// 모델에 데이터 추가
		model.addAttribute("amount", amount);
		model.addAttribute("orderId", orderId);
		model.addAttribute("orderName", orderName);
		model.addAttribute("customerName", customerName);

		return "pay/tossPay";

	}
	
	@GetMapping("/detailPartner")
	public String getMethodName(@RequestParam(name = "id") int id, @RequestParam(name = "userId") int userId,
			Model model) {

		DateProfile detail = dateService.detailPartner(userId, id);
		profileDetailDTO dto = dateService.detailPartnerDetail(id);
		dateManagerService.datailCount(userId);
		
		model.addAttribute("userId", userId);
		model.addAttribute("detail", detail);
		model.addAttribute("dto", dto);

		return "date/detailPartner";
	}
/**
 *  매칭 리스트로 이동 ( 성후 예외처리 완료)
 * @param principal
 * @param model
 * @param redirectAttributes
 * @return
 */
	@GetMapping("/machingList")
	public String getMethodName(@SessionAttribute(value = Define.PRINCIPAL, required = false) User principal, Model model, RedirectAttributes redirectAttributes) {
		if (principal == null) {
			redirectAttributes.addFlashAttribute(Define.ENTER_YOUR_LOGIN, HttpStatus.BAD_REQUEST);
			return "redirect:/user/signIn"; // 로그인으로 리다이렉트
		}
		List<DateProfile> list = dateManagerService.matchingList(principal.getId());

		model.addAttribute("list", list);

		return "date/matchingList";
	}

	// ======= 메시지 채팅 ========

	@GetMapping("/message")
	public String getMessage(@RequestParam(name = "id") int partnerId, @RequestParam(name = "userId") int principalId,
			Model model) {
		List<MessageDTO> chatHistory = dateManagerService.chatHistory(principalId, partnerId);
		model.addAttribute("partnerId", partnerId);
		model.addAttribute("principalId", principalId);
		model.addAttribute("chatHistory", chatHistory);
		return "date/message";
	}

	/**
	 * 현재 보유중인 con 확인하는 메서드
	 * 
	 * @param principal
	 * @return con갯수
	 * @author 변영준
	 */
	@GetMapping("/currentMoney")
	@ResponseBody
	public int fetchCurrentMoney(@SessionAttribute(Define.PRINCIPAL) User principal) {
		int con = dateService.getCurrentCon(principal.getId());
		return con;
	}

	/**
	 * 아이템 구매 내역 갱신
	 * 
	 * @param request
	 * @return
	 * @author 변영준
	 */
	@PostMapping("/payItem")
	public ResponseEntity<Map<String, String>> buyItem(@RequestBody ItemRequest request,
			@SessionAttribute(Define.PRINCIPAL) User principal) {
		Map<String, String> response = new HashMap<>();
		int userId = principal.getId();
		int currentCon = dateService.getCurrentCon(principal.getId());
		int price = request.getPrice();
		int amount = currentCon - request.getPrice();
		int itemId = request.getItemId();

		try {
			int result = dateService.insertConHistory(userId, price, amount, itemId);

			if (result > 0) {
				response.put("message", "구입 성공");
				return ResponseEntity.ok(response);
			} else {
				// 예매 실패: bookingId가 0인 경우
				response.put("message", "구입 실패");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@GetMapping("/popcornCharge")
	public String popcornChargePage() {
		return "pay/popcornCharge";
	}
}