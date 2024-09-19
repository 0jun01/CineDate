<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--begin::App Main-->
<main class="app-main">
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
	<!--begin::App Content Header-->
	<div class="app-content-header">
		<!--begin::Container-->
		<div class="container-fluid">
			<!--begin::Row-->
			<div class="row">
				<div class="col-sm-6">
					<h3 class="mb-0">대쉬보드</h3>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-end">
						<li class="breadcrumb-item"><a href="/adminMain">메인</a></li>
						<li class="breadcrumb-item active" aria-current="page">대쉬보드</li>
					</ol>
				</div>
			</div>
			<!--end::Row-->
		</div>
		<!--end::Container-->
	</div>
	<div class="app-content">
		<!--begin::Container-->
		<div class="container-fluid">
			<!-- Info boxes -->
			<div class="row">
				<!-- /.col -->
				<div class="col-12 col-sm-6 col-md-3">
					<div class="info-box">
						<span class="info-box-icon text-bg-danger shadow-sm"> <i class="bi bi-hand-thumbs-up-fill"></i>
						</span>
						<div class="info-box-content">
							<span class="info-box-text">리뷰 수</span> <span class="info-box-number">${reviewCount}</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->
				<!-- fix for small devices only -->
				<!-- <div class="clearfix hidden-md-up"></div> -->
				<div class="col-12 col-sm-6 col-md-3">
					<div class="info-box">
						<span class="info-box-icon text-bg-success shadow-sm"> <i class="bi bi-cart-fill"></i>
						</span>
						<div class="info-box-content">
							<span class="info-box-text">판매 수</span> <span class="info-box-number">${sellCount}</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->
				<div class="col-12 col-sm-6 col-md-3">
					<div class="info-box">
						<span class="info-box-icon text-bg-warning shadow-sm"> <i class="bi bi-people-fill"></i>
						</span>
						<div class="info-box-content">
							<span class="info-box-text">회원 수</span> <span class="info-box-number">${memberCount}</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
			<!--begin::Row-->
			<div class="row">
				<div class="col-md-12">
					<div class="card mb-4">
						<div class="card-header">
							<h5 class="card-title">연도별 가입자수 & 예매수</h5>
							<div class="card-tools">
								<button type="button" class="btn btn-tool" data-lte-toggle="card-collapse">
									<i data-lte-icon="expand" class="bi bi-plus-lg"></i> <i data-lte-icon="collapse" class="bi bi-dash-lg"></i>
								</button>
								<div class="btn-group"></div>
								<button type="button" class="btn btn-tool" data-lte-toggle="card-remove">
									<i class="bi bi-x-lg"></i>
								</button>
							</div>
						</div>
						<!-- /.card-header -->
						<div class="card-body">
							<!--begin::Row-->
							<div class="row">
								<div class="col-md-8" style="display: flex;">
									<div class="col-9" style="min-height: 100%">
										<canvas id="myChart"></canvas>
									</div>
									<div class="col-9" style="min-height: 100%; position:">
										<canvas id="genreChart"></canvas>
									</div>

								</div>
								<!-- /.col -->
							</div>
							<!--end::Row-->
						</div>
						<!-- ./card-body -->
						<div class="card-footer">
							<!--begin::Row-->
							<div class="row"></div>
							<!--end::Row-->
						</div>
						<!-- /.card-footer -->
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!--end::Row-->
			<!--begin::Row-->
			<div class="row">
				<!-- Start col -->
				<div class="col-md-8">
					<!--begin::Row-->
					<div class="row g-4 mb-4">

						<div class="col-md-8">
							<!-- USERS LIST -->
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">최근 데이트 가입자</h3>
									<div class="card-tools">
										<span class="badge text-bg-danger"> ${profileCount}명이 가입 </span>
										<button type="button" class="btn btn-tool" data-lte-toggle="card-collapse">
											<i data-lte-icon="expand" class="bi bi-plus-lg"></i> <i data-lte-icon="collapse" class="bi bi-dash-lg"></i>
										</button>
										<button type="button" class="btn btn-tool" data-lte-toggle="card-remove">
											<i class="bi bi-x-lg"></i>
										</button>
									</div>
								</div>
								<!-- /.card-header -->
								<div class="card-body p-0">
									<div class="row text-center m-1">
										<c:forEach var="profileList" items="${profileList}">
											<div class="col-3 p-2">
												<c:choose>
													<c:when test="${profileList.firstUploadFileName != null}">
														<img class="img-fluid rounded-circle" src="/DateProfileIMAGE/${profileList.firstUploadFileName}" onerror="this.onerror = null; this.src='/img/usernone.jpg'" alt="사진"
															style="height: 100px;">
													</c:when>
													<c:otherwise>
														<img class="img-fluid rounded-circle" src="/img/usernone.jpg" alt="User Image">
													</c:otherwise>
												</c:choose>
												<a class="btn fw-bold fs-7 text-secondary text-truncate w-100 p-0" href="#">${profileList.nickName}</a>

											</div>
										</c:forEach>


									</div>
									<!-- /.users-list -->

								</div>
								<!-- /.card-body -->
								<div class="card-footer text-center">
									<a href="/adminProfileList" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">모든 가입자 보기</a>
								</div>
								<!-- /.card-footer -->
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col -->
					</div>
					<!--end::Row-->
					<!--begin::Latest Order Widget-->
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">최근 주문</h3>
							<div class="card-tools">
								<button type="button" class="btn btn-tool" data-lte-toggle="card-collapse">
									<i data-lte-icon="expand" class="bi bi-plus-lg"></i> <i data-lte-icon="collapse" class="bi bi-dash-lg"></i>
								</button>
								<button type="button" class="btn btn-tool" data-lte-toggle="card-remove">
									<i class="bi bi-x-lg"></i>
								</button>
							</div>
						</div>
						<!-- /.card-header -->
						<div class="card-body p-0">
							<div class="table-responsive" style="margin: 10px">
								<table class="table m-0">
									<thead>
										<tr style="text-align: center">
											<th>주문 Id</th>
											<th>주문 아이템</th>
											<th>상황</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach var="history" items="${historyList}">
											<tr style="text-align: center">
												<td><a href="pages/examples/invoice.html" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"> ${history.orderId}</a></td>
												<td>${history.orderName}</td>
												<td style="text-align: center"><c:choose>
														<c:when test="${history.requestedAt == null && history.approvedAt == null}">
															<span class="badge text-bg-warning">승인전</span>
														</c:when>
														<c:when test="${history.approvedAt == null}">
															<span class="badge text-bg-danger">승인중</span>
														</c:when>
														<c:otherwise>
															<span class="badge text-bg-success">승인됨</span>
														</c:otherwise>
													</c:choose></td>
											</tr>

										</c:forEach>

									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.card-body -->
						<div class="card-footer clearfix">
							 <a href="/adminHistory" class="btn btn-sm btn-secondary float-end"> 전체 내역
								보기 </a>
						</div>
						<!-- /.card-footer -->
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
				<div class="col-md-4">
					<!-- Info Boxes Style 2 -->
					<div class="info-box mb-3 text-bg-warning">
						<span class="info-box-icon"> <i class="bi bi-tag-fill"></i>
						</span>
						<div class="info-box-content">
							<span class="info-box-text">등록된 상점 아이템</span> <span class="info-box-number">${itemCount}</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
					<div class="info-box mb-3 text-bg-success">
						<span class="info-box-icon"> <i class="bi bi-heart-fill"></i>
						</span>
						<div class="info-box-content">
							<span class="info-box-text">매칭이 성사된 수</span> <span class="info-box-number" id="totalMatchings"></span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
					<div class="info-box mb-3 text-bg-danger">
						<span class="info-box-icon"> <i class="bi bi-cloud-download"></i>
						</span>
						<div class="info-box-content">
							<span class="info-box-text">예매된 영화 자리 총 갯수</span> <span class="info-box-number">${bookingCount}</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
					<div class="info-box mb-3 text-bg-info">
						<span class="info-box-icon"> <i class="bi bi-chat-fill"></i>
						</span>
						<div class="info-box-content">
							<span class="info-box-text">수익</span> <span class="info-box-number">총 ${sellSum} 원</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
					
					<!-- /.card -->
					<!-- PRODUCT LIST -->
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">새로 추가된 상품</h3>
							<div class="card-tools">
								<button type="button" class="btn btn-tool" data-lte-toggle="card-collapse">
									<i data-lte-icon="expand" class="bi bi-plus-lg"></i> <i data-lte-icon="collapse" class="bi bi-dash-lg"></i>
								</button>
								<button type="button" class="btn btn-tool" data-lte-toggle="card-remove">
									<i class="bi bi-x-lg"></i>
								</button>
							</div>
						</div>
						<!-- /.card-header -->
						<div class="card-body p-0">
							<div class="px-2">
								
								<c:forEach var="conItems" items="${conItems}">
								<div class="d-flex border-top py-2 px-1">
									<div class="col-2" style="margin-right: 10px">
										<img src="${conItems.itemImg}" alt="Product Image" class="img-size-50">
									</div>
									<div class="col-10">
										<a href="javascript:void(0)" class="fw-bold"> ${conItems.name} <span class="badge text-bg-warning float-end"> ${conItems.price} 콘 </span>
										</a>
										<div class="text-truncate">${conItems.itemDesc}</div>
									</div>
								</div>
								<!-- /.item -->
								</c:forEach>
							</div>
						</div>
						<!-- /.card-body -->
						<div class="card-footer text-center">
							<a href="javascript:void(0)" class="uppercase"> 모든 상품 보기 </a>
						</div>
						<!-- /.card-footer -->
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!--end::Row-->
		</div>
		<!--end::Container-->
	</div>
	<!--end::App Content-->
</main>
<!--end::App Main-->

<script>

Chart.register(ChartDataLabels);

function getGenresBookings() {
	const url2 = "http://localhost:8080/genresBookings";
    fetch(url2)
        .then(response => response.json())
        .then(data => {
        	chartGenresBookings(data);
        })
        .catch(error => {
            console.log("error:", error);
        });
}
function chartGenresBookings(data) {
    const genrelabels = data.map(item => item.genre);
    const totalBookings = data.map(item => item.totalBookings);

    var ctx2 = document.getElementById('genreChart').getContext('2d');
    var myChart = new Chart(ctx2, {
        type: 'pie',
        data: {
            labels: genrelabels,
            datasets: [{
                label: '장르별 예매수',
                data: totalBookings,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(208, 112, 251, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(208, 112, 251, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {  // Updated from yAxes to y for newer Chart.js versions
                    beginAtZero: true
                }
            }
        	
       		
    	}
    });
}

function getCountProfileByYear() {
	const url = "http://localhost:8080/CountProfile";
    fetch(url)
        .then(response => response.json())
        .then(data => {
            chartCountProfileByYear(data);
        })
        .catch(error => {
            console.log("error:", error);
        });
}

function chartCountProfileByYear(data) {
    const labels = data.map(item => item.year);
    const count = data.map(item => item.count);

    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: '연도별',
                data: count,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(208, 112, 251, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(208, 112, 251, 1)'
                ],
                borderWidth: 1
                	
            }]
        },
        options: {
            scales: {
                y: {  // Updated from yAxes to y for newer Chart.js versions
                    beginAtZero: true
                }
            }
        }
    });
}

function tatalMatChings() {
    fetch('http://localhost:8080/totalMatching')
        .then(response => response.json())
        .then(data => {
            document.getElementById('totalMatchings').textContent = data.count;
        })
        .catch(error => {
            console.error('총 매칭수 가져오는 중 오류 발생:', error);
            document.getElementById('totalMatchings').textContent = '오류 발생';
        });
}



document.addEventListener('DOMContentLoaded', function() {
	tatalMatChings();
    getCountProfileByYear();
    getGenresBookings(); // 함수 호출 추가
});
</script>


<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>
