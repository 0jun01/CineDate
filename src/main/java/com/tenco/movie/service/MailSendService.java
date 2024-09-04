package com.tenco.movie.service;

import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.handler.exception.DataDeliveryException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailSendService {
	
	private final JavaMailSender mailSender;
	
	public void sendEmail(String toEmail, String title, String text) {
		SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
		try {
			mailSender.send(emailForm);
		} catch (RuntimeException e) {
			log.debug("MailService.sendEmail exception occur toEmail: {}," + "title: {}, text: {}", toEmail, title, text);
			throw new DataDeliveryException("이메일 오류", HttpStatus.BAD_REQUEST);
		}
	}

	// 발신할 이메일 데이터 세팅
	private SimpleMailMessage createEmailForm(String toEmail, String title, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(toEmail);
		message.setSubject(title);
		message.setText(text);
		
		return message;
	}
	
}