package com.tenco.movie.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.tenco.movie.dto.DateProfileSignUp;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.model.DateProfile;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.service.DateProfileService;
import com.tenco.movie.service.PaymentService;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/date")

@RequiredArgsConstructor
public class DateController {

	@Autowired
	private final DateProfileService dateService;

	private final PaymentService payservice;
	

	/**
	 * 데이트 페이지 요청
	 * 
	 * @author 김가령
	 */
	@GetMapping("/date")
	public String getDatePage(@SessionAttribute(Define.PRINCIPAL) User principal,Model model) {

		/**
		 * @author 배병호 principal 기준으로 회원가입 페이지 date page or 회원가입 페이지 전화
		 */
		DateProfile prifile = dateService.searchProfile(principal.getId());
		if (prifile == null) {
			return "date/DateSignUp";
		}
		List<DateProfile> list = dateService.searchPartner(principal.getId(), principal.getGender());
		System.out.println(list);
		model.addAttribute("list", list);
		
		return "date/ProfileList";
	}
	/**
	 * 데이트페이지 정보수정 이동
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
	public String updateProfile(
			 @RequestParam("nickname") String nickName,
		        @RequestParam("introduce") String introduce,
		        @RequestParam("profile_upload_file1") MultipartFile file1,
		        @RequestParam("profile_upload_file2") MultipartFile file2,
		        @RequestParam("profile_upload_file3") MultipartFile file3,
		        @RequestParam("profile_upload_file4") MultipartFile file4,
		        @RequestParam("profile_upload_file5") MultipartFile file5,
		        @RequestParam("profile_upload_file6") MultipartFile file6,
		        @RequestParam("profile_upload_file7") MultipartFile file7,
		        @RequestParam("userId") int userId,
		        @SessionAttribute("principal") User principal) throws IOException {

	    if (principal.getId() != userId) {
	        return ""; // 적절한 에러 페이지 URL로 수정
	    }

	    dateService.updateProfile(nickName, introduce, file1, file2, file3, file4, file5, file6, file7, userId);

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
			@RequestParam(name = "mFileTwo") MultipartFile mFileTwo, @RequestParam(name = "nickName") String nickName,
			@RequestParam(name = "introduce") String introduce) {

		if (nickName == null || nickName.isEmpty()) {
			throw new DataDeliveryException("닉네임을 입력하세요", HttpStatus.BAD_REQUEST);
		}

		DateProfileSignUp dto = DateProfileSignUp.builder().nickName(nickName).introduce(introduce).mFileOne(mFileOne)
				.mFileTwo(mFileTwo).build();

		dateService.createdProfile(principal, dto);

		return "date/datePage";
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

}