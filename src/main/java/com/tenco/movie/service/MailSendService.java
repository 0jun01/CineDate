package com.tenco.movie.service;

import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.dto.EmailVerificationResult;
import com.tenco.movie.repository.interfaces.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailSendService {

	private final JavaMailSender mailSender;
	private final UserRepository userRepository;
	private static final String AUTH_CODE_PREFIX = "AuthCode ";
	
	private final Map<String, String> authCodeStore = new ConcurrentHashMap<>();

	 public void sendEmail(String email, String title, String text) {
		 System.out.println(text);
	        SimpleMailMessage emailForm = createEmailForm(email, title, text);
	        try {
	        	mailSender.send(emailForm);
	            log.info("이메일이 성공적으로 전송되었습니다. 대상: {}", email);
	        } catch (Exception e) {
	            log.error("MailService.sendEmail exception occurred. toEmail: {}, title: {}, text: {}", email, title, text);
	            throw new RuntimeException("이메일 전송 중 오류가 발생했습니다.");
	        }
	    }
	
	// 이메일 보내는 메서드
	public String sendCodeToEmail(String email) {
		String title = "CineDate 이메일 인증 번호";
		String authCode = createKey();
		
		System.out.println("인증 요청");
		System.out.println(authCode);
		// 이메일 발송
		sendEmail(email, title, authCode);
		
		authCodeStore.put(AUTH_CODE_PREFIX + email, authCode);
		
		return authCode;
	}
	
	// 인증 코드 검증
	public EmailVerificationResult verifiedCode(String email, String authCode) {
		
		String storedAuthCode = authCodeStore.get(AUTH_CODE_PREFIX + email);
		boolean authResult = storedAuthCode != null && storedAuthCode.equals(authCode);
		
		return EmailVerificationResult.of(authResult);
		
	}

	private SimpleMailMessage createEmailForm(String email, String title, String text) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(email);
		message.setSubject(title);
		message.setText(text);
		
		return message;
	}

	// 이메일 중복 확인
	public boolean isEmailDuplicate(String email) {
		return userRepository.findByEmails(email).isPresent();
	}
	
	// 랜덤 암호 생성
	private static String createKey() {
		StringBuffer key = new StringBuffer();
		Random random = new Random();

		for (int i = 0; i < 8; i++) {
			int index = random.nextInt(3);

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