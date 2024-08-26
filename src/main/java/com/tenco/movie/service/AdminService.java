package com.tenco.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.handler.exception.RedirectException;
import com.tenco.movie.repository.interfaces.NoticeRepository;
import com.tenco.movie.repository.model.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	@Autowired
	private final NoticeRepository noticeRepository;
	
	/**
	 * 공지사항 목록 요청
	 * @return
	 */
	
	@Transactional
	public List<Notice> readNoticeList() {
		List<Notice> noticeListEntity = null;
		
		noticeListEntity = noticeRepository.findAll();
		
//		try {
//			noticeListEntity = noticeRepository.findAll();
//		} catch (DataAccessException e) {
//			throw new DataDeliveryException("잘못된 처리입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
//		} catch (Exception e) {
//			throw new RedirectException("알 수 없는 오류", HttpStatus.SERVICE_UNAVAILABLE);
//		}
		
		return noticeListEntity;
	}
	
}
