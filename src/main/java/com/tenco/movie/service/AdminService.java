package com.tenco.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.dto.NoticeWriterDTO;
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
	
	@Transactional
	public void createNotice(NoticeWriterDTO dto) {
		int result = 0;
		
		try {
			result = noticeRepository.insert(dto.toWrite());
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException("잘못된 요청입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			throw new RedirectException("알 수 없는 오류 입니다", HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		if(result == 0) {
			throw new DataDeliveryException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@Transactional
	public void reCreateNotice(NoticeWriterDTO dto, int id) {
		
		int result = 0;
		
		try {
			result = noticeRepository.updateById(dto.reWrite(id));
		} catch (DataAccessException e) {
			throw new DataDeliveryException("잘못된 요청입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException("알 수 없는 오류 입니다", HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result == 0) {
			throw new DataDeliveryException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public Notice findById(Integer id) {
		Notice notice = noticeRepository.findById(id);
		
		if(notice == null) {
			throw new DataDeliveryException("존재하지 않는 게시글 입니다", HttpStatus.BAD_REQUEST);
		}
		return notice;
	}
	
	@Transactional
	public void deleteNotice(int id) {
		
		int result = 0;
		
		try {
			result = noticeRepository.deleteById(id);
		} catch (DataAccessException e) {
			throw new DataDeliveryException("잘못된 요청입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException("알 수 없는 오류 입니다", HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result == 0) {
			throw new DataDeliveryException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	
	
}
