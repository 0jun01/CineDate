package com.tenco.movie.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import com.tenco.movie.dto.TossApproveResponse;
import com.tenco.movie.dto.TossHistoryDTO;

@Mapper
public interface PaymentHistoryRepository {
	
	int insertTossHistory(TossHistoryDTO historyDTO);
	

}
