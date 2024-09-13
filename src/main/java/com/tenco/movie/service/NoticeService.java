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
import com.tenco.movie.utils.Define;

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
			throw new DataDeliveryException(Define.INVALID_INPUT, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		return noticeListEntity;
	}
	
	
	/**
	 * 공지사항 상세보기
	 * @return
	 */
	@Transactional
    public Notice readNoticeDetail(int id) {
        Notice notice = null;
        
        try {
            notice = noticeRepository.findById(id);
            if (notice != null) {
                noticeRepository.incrementViewCount(id); // 조회수 증가
            }
        } catch (DataAccessException e) {
            throw new DataDeliveryException(Define.INVALID_INPUT, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
        }
        
        return notice;
    }

    public int countNotice() {
        int totals = 0;
        
        totals = noticeRepository.countNotice();
        
        if(totals == 0 ) {
            throw new DataDeliveryException(Define.NOT_FOUND_PREVIOUS, HttpStatus.BAD_REQUEST);
        }

        return totals;
    }
	
	
	
}