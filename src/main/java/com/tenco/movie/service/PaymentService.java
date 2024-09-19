package com.tenco.movie.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Calendar;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.movie.dto.TossApproveResponse;
import com.tenco.movie.dto.TossHistoryDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.interfaces.PaymentHistoryRepository;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	@Autowired
	private final PaymentHistoryRepository historyRepository;

	@Transactional
	public String cancelPaymentHistory(int id) throws IOException, InterruptedException {

		TossHistoryDTO tossHistoryDTO = historyRepository.searchPaymentHistory(id);

		String uri = "https://api.tosspayments.com/v1/payments/" + tossHistoryDTO.getPaymentKey() + "/cancel";
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)) // paymentKey
				// 시크릿 키를 Basic Authorization 방식으로 base64를 이용하여 인코딩하여 꼭 보내야함
				.header("Authorization", "Basic dGVzdF9za19lcVJHZ1lPMXI1UDdFZ0RLd05KYlZRbk4yRXlhOg==")
				.header("Content-Type", "application/json")
				.method("POST", HttpRequest.BodyPublishers.ofString("{\"cancelReason\":\"고객이 취소를 원함\"}")).build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

		historyRepository.insertCancelTossPaymenHistory(tossHistoryDTO);

		historyRepository.cancelTossPayment(tossHistoryDTO.getPaymentKey(), tossHistoryDTO.getOderId());

		return response.body();
	}

	/**
	 * 토스 주문 OrderID 생성
	 * 
	 * @return
	 */
	public String getOderId() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);
		Random rd1 = new Random();
		Random rd2 = new Random();
		int rd11 = rd1.nextInt(100);
		int rd22 = rd2.nextInt(100);

		String yStr = Integer.toString(y);
		String mStr = Integer.toString(m);
		String dStr = Integer.toString(d);
		String rd1Str = Integer.toString(rd11);
		String rd2Str = Integer.toString(rd22);

		return mStr + rd1Str + yStr + rd2Str + dStr;
	}

	@Transactional
	public int insertTossHistory(TossApproveResponse response, int principalId) {

		int result = 0;
		System.out.println(response);
		TossHistoryDTO dto = TossHistoryDTO.builder().paymentKey(response.getPaymentKey()).userId(principalId)
				.oderId(response.getOrderId()).oderName(response.getOrderName()).amount(response.getTotalAmount())
				.method(response.getMethod()).requestedAt(response.getRequestedAt())
				.approvedAt(response.getApprovedAt()).build();

		result = historyRepository.insertTossHistory(dto);

		if (result != 1) {
			throw new DataDeliveryException(Define.ERROR_PAYMENT_FAILED, HttpStatus.BAD_REQUEST);

		}
		return result;

	}

}
