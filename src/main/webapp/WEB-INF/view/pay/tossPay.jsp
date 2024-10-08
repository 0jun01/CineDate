<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>결제하기</title>
<!-- 토스페이먼츠 결제창 SDK 추가 -->
<script src="https://js.tosspayments.com/v1/payment"></script>
</head>
<body>
	<script>
    // ------ 클라이언트 키로 객체 초기화 ------
    var clientKey = 'test_ck_AQ92ymxN34g2qm2pkpxK8ajRKXvd';
    var tossPayments = TossPayments(clientKey);
    
    // 서버에서 전달받은 데이터
    var amount = ${amount}; // 결제 금액 (숫자형)
    var orderId = `${orderId}`; // 주문 ID (문자열형)
    var orderName = `${orderName}`; // 주문명 (문자열형)
    var customerName = `${customerName}`; // 구매자 이름 (문자열형)

    // ------ 결제창 띄우기 ------
    tossPayments.requestPayment('간편결제', { // 결제수단 파라미터 (카드, 계좌이체, 가상계좌, 휴대폰 등) 
      amount: amount, // 결제 금액
      orderId: orderId, // 주문 ID
      orderName: orderName, // 주문명
      customerName: customerName, // 구매자 이름
      successUrl: 'http://192.168.0.46:8080/toss/success', // 결제 성공 시 이동할 페이지
      failUrl: 'http://192.168.0.46:8080/home', // 결제 실패 시 이동할 페이지
    })
    .catch(function (error) {
      if (error.code === 'USER_CANCEL') {
        // 결제 고객이 결제창을 닫았을 때 에러 처리
        console.error('User canceled payment:', error);
      } else if (error.code === 'INVALID_CARD_COMPANY') {
        // 유효하지 않은 카드 코드에 대한 에러 처리
        console.error('Invalid card company:', error);
      } else {
        // 기타 에러 처리
        console.error('Payment error:', error);
      }
    });
  </script>
</body>
</html>
