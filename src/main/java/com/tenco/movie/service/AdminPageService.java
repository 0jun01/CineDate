package com.tenco.movie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.dto.NoticeWriterDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.handler.exception.RedirectException;
import com.tenco.movie.repository.interfaces.AdminRepository;
import com.tenco.movie.repository.model.Notice;
import com.tenco.movie.repository.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminPageService {

	@Autowired
	
	private final AdminRepository adminRepository;
	
	@Transactional
	public void createNotice(NoticeWriterDTO dto) {
		int result = 0;
		
		try {
			result = adminRepository.insert(dto.toWrite());
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
			result = adminRepository.updateById(dto.reWrite(id));
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
		Notice notice = adminRepository.findById(id);
		
		if(notice == null) {
			throw new DataDeliveryException("존재하지 않는 게시글 입니다", HttpStatus.BAD_REQUEST);
		}
		return notice;
	}
	
	
	@Transactional
	public void deleteNotice(int id) {
		
		int result = 0;
		
		try {
			result = adminRepository.deleteById(id);
		} catch (DataAccessException e) {
			throw new DataDeliveryException("잘못된 요청입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException("알 수 없는 오류 입니다", HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result == 0) {
			throw new DataDeliveryException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public List<Notice> readNoticePage(int page, int size){
		List<Notice> list = new ArrayList<>();
		int limit = size;
		int offset = (page - 1) * size;
		list = adminRepository.pageCount(limit, offset);
		return list;
	}
	
	@Transactional
	public List<Notice> searchNoticePage(String search, int page, int size){
		List<Notice> list = new ArrayList<>();
		int limit = size;
		int offset = (page - 1) * size;
		list = adminRepository.findByName(search, limit, offset);
		return list;
	}
	
	public int countNoticeAll() {
		return adminRepository.countNoticeAll();
	}
	
	public int countNotice(String search) {
		return adminRepository.countNotice(search);
	}
	
	//공지
	//----------------------------------------------
	//회원정보
	
	@Transactional
	public List<User> selectAllUser(){
		List<User> userEntity = null;
		
		userEntity = adminRepository.findExceptPW();
		
		return userEntity;
		
	}
	
	
}
