package com.tenco.movie.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
@Service
@RequiredArgsConstructor
@Data
@AllArgsConstructor
@ToString
@Builder
public class MailSendService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	private String authNum;

	// 랜덤 인증 코드
	public void createCode() {
		Random random = new Random();
		StringBuffer key = new StringBuffer();

		for (int i = 0; i < 8; i++) {
			int index = random.nextInt(3);

			switch (index) {
			case 0:
				key.append((char) ((int) random.nextInt(26) + 97));
				break;
			case 1:
				key.append((char) ((int) random.nextInt(26) + 65));
			case 2:
				key.append(random.nextInt(9));
				break;
			}
		}
		authNum = key.toString();
	}

	// 메일 양식 신청
	public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {
		createCode(); // 인증 코드 생성
		String setForm = "hjeong0711@gmail.com";
		String toEmail = email; // 받는 사람
		String title = "[CineDate 이메일 인증]"; // 제목

		MimeMessage message = emailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setTo(email);
			helper.setSubject(title);
			helper.setFrom(setForm);
			helper.setText(setContext(authNum),true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	// 실제 메일 전송
	public String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException  {
		// 메일 전송에 필요한 정보 설정
		MimeMessage emailForm = createEmailForm(toEmail);

		// 실제 메일 전송
		emailSender.send(emailForm);

		return authNum;
	}

	// 타임 리프를 이용한 context 설정
	private String setContext(String code) {
		Context context = new Context();
		context.setVariable("code", code);
		return templateEngine.process("main", context); // main.html
	}

}