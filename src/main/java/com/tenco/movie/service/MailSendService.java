package com.tenco.movie.service;

import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailSendService {

	private final JavaMailSender mailSender;

	public void sendSimpleMessage(String toEmail, String title, String text) {
		
		SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
		
		try {
			mailSender.send(emailForm);
			log.info("이메일이 성공적으로 전송되었습니다. 대상: {}", toEmail);
		} catch (Exception e) {
			log.error("MailService.sendEmail exception occurred. toEmail: {}, title: {}, text: {}", toEmail, title, text, e);
            throw new RuntimeException("이메일 전송 중 오류가 발생했습니다.");
		}
		
	}

	private SimpleMailMessage createEmailForm(String toEmail, String title, String text) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(toEmail);
		message.setSubject(title);
		message.setText(text);
		
		return message;
	}

	// 랜덤 암호 생성
	private static String createKey() {
		StringBuffer key = new StringBuffer();
		Random random = new Random();

		for (int i = 0; i < 8; i++) {
			int index = random.nextInt();

			switch (index) {
			case 0:
				// a ~ z (ex. 1+97=98 => (char)98 = 'b')
				key.append((char) ((int) (random.nextInt(26)) + 97));
				break;

			case 1:
				// a ~ z
				key.append((char) (int) (random.nextInt(26) + 65));
				break;

			case 2:
				// 0 ~ 9
				key.append((random.nextInt(10)));
				break;
			}
		}
		return key.toString();
	}

}