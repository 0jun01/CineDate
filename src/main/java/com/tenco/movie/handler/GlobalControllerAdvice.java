package com.tenco.movie.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.handler.exception.RedirectException;
import com.tenco.movie.handler.exception.UnAuthorizedException;

@ControllerAdvice
public class GlobalControllerAdvice {

	// 예외 로깅
	@ExceptionHandler(Exception.class)
	public void exception(Exception e) {
		System.out.println("------------------------");
		System.out.println(e.getClass().getName()); // 어떤 예외클래스 발생했는지 찍어보기
		System.out.println(e.getMessage()); // 발생한 예외클래스 로깅처리
		System.out.println("------------------------");
	}
	
	/**
	 * 데이터 전송 오류 발생 시 예외 처리
	 * @return 이전 페이지
	 */
	@ResponseBody
	@ExceptionHandler(DataDeliveryException.class)
	public String dataDeliveryException(DataDeliveryException e) {
		StringBuffer sb = new StringBuffer();
		sb.append(" <script>");
		sb.append(" alert('"+ e.getMessage()  +"');");
		sb.append(" window.history.back();");
		sb.append(" </script>");
		return sb.toString();
	}
	
	/**
	 * 인증되지 않은, 권한이 없는 사용자 접근 시 예외 처리
	 * @return 로그인 페이지
	 */
	@ResponseBody
	@ExceptionHandler(UnAuthorizedException.class)
	public String unAuthorizedException(UnAuthorizedException e) {
		StringBuffer sb = new StringBuffer();
		sb.append(" <script>");
		sb.append(" alert('"+ e.getMessage()  +"');");
		sb.append(" location.href='/user/signIn';");
		sb.append(" </script>");
		return sb.toString();
	}
	
	/**
	 * 잘못된 요청 시 예외처리
	 * @return 에러 페이지
	 */
	@ExceptionHandler(RedirectException.class)
	public ModelAndView redirectException(RedirectException e) {

		ModelAndView modelAndView = new ModelAndView("errorPage");
		modelAndView.addObject("statusCode", e.getStatus().value());
		modelAndView.addObject("message", e.getMessage());
		return modelAndView;
	}
	
}
