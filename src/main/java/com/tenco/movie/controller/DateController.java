package com.tenco.movie.controller;

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
	public String getDatePage(@SessionAttribute(Define.PRINCIPAL) User principal) {

		/**
		 * @author 배병호 principal 기준으로 회원가입 페이지 main page or 회원가입 페이지 전화
		 */
		DateProfile prifile = dateService.searchProfile(principal.getId());
		if (prifile == null) {
			return "date/DateSignUp";
		}

		return "date/datePage";
	}

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
		String orderId = payservice.getOderId();
		String orderName = "con";
		String customerName = principal.getName();
		System.out.println(amount);
		System.out.println(orderId);
		System.out.println(orderName);
		System.out.println(customerName);
		// 모델에 데이터 추가
		model.addAttribute("amount", amount);
		model.addAttribute("orderId", orderId);
		model.addAttribute("orderName", orderName);
		model.addAttribute("customerName", customerName);

		return "pay/tossPay";
	}

}
