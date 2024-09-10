package com.tenco.movie.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.tenco.movie.dto.MessageDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.model.DateProfile;
import com.tenco.movie.repository.model.Message;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.service.DateManagerService;
import com.tenco.movie.service.DateProfileService;
import com.tenco.movie.service.MessageService;
import com.tenco.movie.service.PaymentService;
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
<<<<<<< HEAD
=======
	@Autowired
	private final MessageService messageService;
>>>>>>> 20e99aabf7bc5e935266a23b97ce0d938aac430a

	/**
	 * 데이트 페이지 요청
	 * 
	 * @author 배병호 principal 기준으로 회원가입 페이지 date page or 회원가입 페이지 전화
	 * @author 김가령
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
		} else if(proifile.getLifeStatus() == 1) {
			throw new DataDeliveryException(Define.PROFILE_SUSPENDING, HttpStatus.BAD_REQUEST);
		}
		
		
		
		List<DateProfile> list = dateService.searchPartner(principal.getId(), principal.getGender());
		System.out.println(list);

		model.addAttribute("list", list);

		return "date/ProfileList";
	}

	/**
	 * 데이트페이지 정보수정 이동
	 * 
	 * @author 성후
	 */
	@GetMapping("/profilePage")
	public String getProfilePage(@SessionAttribute(Define.PRINCIPAL) User principal, Model model) {
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

//	    if (principal.getId() != userId) {
//	        return ""; // 적절한 에러 페이지 URL로 수정
//	    }
		DateProfileDTO update = DateProfileDTO.builder().nickName(nickName).introduce(introduce).mFileOne(file1)
				.mFileTwo(file2).mFile3(file3).mFile4(file4).mFile5(file5).build();

		dateService.updateProfile(update, principal.getId());

		return "redirect:/date/date";
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
			@RequestParam(name = "introduce") String introduce) {

		if (nickName == null || nickName.isEmpty()) {
			throw new DataDeliveryException("닉네임을 입력하세요", HttpStatus.BAD_REQUEST);
		}

		DateProfileDTO dto = DateProfileDTO.builder().nickName(nickName).introduce(introduce).mFileOne(mFileOne)
				.mFileTwo(mFileTwo).mFile3(mFile3).mFile4(mFile4).mFile5(mFile5).build();

		dateService.createdProfile(principal, dto);

		return "redirect:/date/date";
	}

	@GetMapping("/popcornStore")
	public String postPopcornStore() {

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

	@GetMapping("/detailPartner")
	public String getMethodName(@RequestParam(name = "id") int id, @RequestParam(name = "userId") int userId,
			Model model) {

		DateProfile detail = dateService.detailPartner(userId, id);

		model.addAttribute("userId", userId);
		model.addAttribute("detail", detail);

		return "date/detailPartner";
	}

	@GetMapping("/machingList")
	public String getMethodName(@SessionAttribute(name = Define.PRINCIPAL) User principal, Model model) {

		List<DateProfile> list = dateManagerService.matchingList(principal.getId());

		model.addAttribute("list", list);

		return "date/matchingList";
	}
<<<<<<< HEAD

=======
	/**
	 * 메세지기능
	 * @author 성후
	 * @param messageDTO
	 * @return
	 */
	@PostMapping("/sendMessage")
	public @ResponseBody Map<String, Object> sendMessage(@RequestBody MessageDTO messageDTO, @SessionAttribute(Define.PRINCIPAL) User principal) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	    	
	    	System.out.println("messageDTO 오나: " + messageDTO); // 데이터 확인
	        System.out.println("Principal 오나: " + principal); // 세션에서의 사용자 정보 확인
	    	
	        Message message = new Message();
	        message.setSenderId(principal.getId()); // 발신자 ID
	        message.setRecipientId(messageDTO.getRecipientId()); // 수신자 ID
	        message.setMessage(messageDTO.getMessage()); // 메시지 내용
	        message.setTimestamp(LocalDateTime.now()); // 타임스탬프
	        
	        System.out.println("여기는 컨트롤러 메세지 들어오나: " + message); // 로그 추가
	        
	        messageService.save(message);
	        response.put("success", true);
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("error", e.getMessage());
	    }
	    return response;
	}
	
	
>>>>>>> 20e99aabf7bc5e935266a23b97ce0d938aac430a
}