package com.tenco.movie.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.dto.TossHistoryDTO;

@Mapper
public interface PaymentHistoryRepository {

	int insertTossHistory(TossHistoryDTO historyDTO);

	// 검색
	TossHistoryDTO searchPaymentHistory(@Param("id") int id);

	// 삭제
	int cancelTossPayment(@Param("paymentKey") String paymentKey, @Param("oderId") String oderId);

	// 취소 기록
	int insertCancelTossPaymenHistory(TossHistoryDTO historyDTO);

	// 프로필 현재 보유 콘 개수
	int findConByPrincipal(int principalId);

	// 업데이트 콘 개수
	int updateCon(@Param("principalId") int principalId, @Param("amount") int amount);

}