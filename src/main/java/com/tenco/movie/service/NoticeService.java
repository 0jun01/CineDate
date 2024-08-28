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
public class NoticeService {

	@Autowired
	private final NoticeRepository noticeRepository;
	
	/**
	 * 공지사항 목록 요청
	 * @return
	 */
	
	@Transactional
	public List<Notice> readNoticeList(int page, int size) {
		List<Notice> noticeListEntity = null;
		
		int limit = size;
		int offset = (page - 1) * size;
		
		try {
			noticeListEntity = noticeRepository.findAll(limit, offset);
		} catch (DataAccessException e) {
			throw new DataDeliveryException("잘못된 처리입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException("알 수 없는 오류", HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		return noticeListEntity;
	}
	
	
	/**
	 * 공지사항 상세보기
	 * @return
	 */
	public Notice readNoticeDetail(int id) {
		Notice notice = null;
		
		 try {
	            notice = noticeRepository.findById(id);
	        } catch (DataAccessException e) {
	            throw new DataDeliveryException("잘못된 처리입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	        } catch (Exception e) {
	            throw new RedirectException("알 수 없는 오류", HttpStatus.SERVICE_UNAVAILABLE);
	        }
		
		return notice;
	}
	
	
	public int countNotice() {
		int totals = 0;
		
		totals = noticeRepository.countNotice();
		
		if(totals == 0 ) {
			throw new DataDeliveryException("카운터 에러 발생", HttpStatus.BAD_REQUEST);
		}
		
		
		
		return totals;
	}
	
	
	
}