package com.tenco.movie.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tenco.bank.handler.exception.DataDeliveryException;
import com.tenco.bank.handler.exception.RedirectException;
import com.tenco.bank.handler.exception.UnAuthorizedException;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	
	@ResponseBody
	@ExceptionHandler(DataDeliveryException.class)
	public String dataDelieryException(DataDeliveryException e) {
		StringBuffer sb = new StringBuffer();
		sb.append(" <script>");
		sb.append(" alert('"+ e.getMessage() + "');");
		sb.append(" window.history.back();");
		sb.append(" </script>");
		return sb.toString();
	}
	
}
