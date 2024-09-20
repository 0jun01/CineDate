<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>

<main class="app-main">
	<!--begin::App Content Header-->
	<div class="app-content-header">
		<!--begin::Container-->
		<div class="container-fluid">
			<!--begin::Row-->
			<div class="row">
				<div class="col-sm-6">
					<h3 class="mb-0">고객 결제 내역</h3>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-end">
						<li class="breadcrumb-item"><a href="/adminMain">Home</a></li>
						<li class="breadcrumb-item active" aria-current="page">Timeline</li>
					</ol>
				</div>
			</div>
			<!--end::Row-->
		</div>
		<!--end::Container-->
	</div>
	<!--end::App Content Header-->
	<!--begin::App Content-->
	<div class="app-content">
		<!--begin::Container-->
		<div class="container-fluid">
			<!-- Timelime example  -->
			<div class="row">
				<div class="col-md-12">
					<!-- The time line -->

					<div class="timeline">
						<!-- timeline time label -->
						<c:forEach var="historytime" items="${historyTimeLine}">
							<div class="time-label" >
								<span class="text-bg-danger">${historytime.approvedAt}</span>
							</div>
							<!-- /.timeline-label -->
							<!-- timeline item -->
							<c:forEach var="history" items="${historyList}">
								<div>
									<c:choose>
									<c:when test="${history.dateToDate() == historytime.approvedAt}">
									<i class="timeline-icon bi bi-chat-text-fill text-bg-primary"></i>
									<div class="timeline-item">
										<span class="time"> <i class="bi bi-clock-fill"></i> ${history.dateToTime()}
										</span>
										<h3 class="timeline-header">
											<a href="#">${history.id} 결제 내역</a>
										</h3>
										<div class="timeline-body">
										id : ${history.id} <br>
										결제 key : ${history.paymentKey} <br>
										결제한 아이디 : ${history.userId} <br>
										주문 번호 : ${history.orderId} <br>
										주문 내역 : ${history.orderName} <br>
										금액 : ${history.amount} 원<br>
										결제 방법 : ${history.method} <br>
										요청 시간 : ${history.requestedAt} <br>
										승인 시간 : ${history.approvedAt}
										
										</div>
										<div class="timeline-footer">
											<a class="btn btn-primary btn-sm">자세히보기</a> 
											<form action="/cancel" method="POST">
											<button class="btn btn-danger btn-sm" name="payId" value="${history.id}" disabled="disabled">환불</button>
											</form>
										</div>
									</div>
									</c:when>
									
									</c:choose>
								</div>
								<!-- END timeline item -->
							</c:forEach>
						</c:forEach>
					
						<!-- END timeline item -->
					
						<div>
							<i class="timeline-icon bi bi-clock-fill text-bg-secondary"></i>
						</div>
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!--end::Container-->
	</div>
	<!--end::App Content-->
</main>
<!--end::App Main-->
<!--begin::Footer-->

</div>
</div>
</div>
<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>