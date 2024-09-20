<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>

<!--begin::App Main-->
<main class="app-main">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
							<span class="info-box-text">리뷰 수</span> <span class="info-box-number">41,410</span>
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
							<span class="info-box-text">판매 수</span> <span class="info-box-number">760</span>
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
							<span class="info-box-text">총 회원가입수</span> <span class="info-box-number" id="totalMembersCount">Loading...</span>
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
							<h5 class="card-title">Monthly Recap Report</h5>
							<div class="card-tools">
								<button type="button" class="btn btn-tool" data-lte-toggle="card-collapse">
									<i data-lte-icon="expand" class="bi bi-plus-lg"></i> <i data-lte-icon="collapse" class="bi bi-dash-lg"></i>
								</button>
								<div class="btn-group">
									<div class="dropdown-menu dropdown-menu-end" role="menu">
										<a href="#" class="dropdown-item">Action</a> <a href="#" class="dropdown-item">Another action</a> <a href="#" class="dropdown-item"> Something else here </a> <a
											class="dropdown-divider"></a> <a href="#" class="dropdown-item">Separated link</a>
									</div>
								</div>
								<button type="button" class="btn btn-tool" data-lte-toggle="card-remove">
									<i class="bi bi-x-lg"></i>
								</button>
							</div>
						</div>
						<!-- /.card-header -->
						<div class="card-body">
							<!--begin::Row-->
							<div class="row">
								<div class="col-md-8">
									<p class="text-center">
										<strong>년도 별 DateService 가입자 수</strong>
									</p>
									<!-- canvas -->
									<canvas id="myChart" width="400" height="230" style="width: 400; height: 230"></canvas>
									<canvas id="genreChart" style="width: 400px; height: 230px"></canvas>
								</div>
								<!-- /.col -->
								<div class="col-md-4">
									<!-- Info Boxes Style 2 -->

									<!-- /.info-box -->
									<div class="info-box mb-3 text-bg-success">
										<span class="info-box-icon"> <i class="bi bi-heart-fill"></i>
										</span>
										<div class="info-box-content">
											<span class="info-box-text">매칭이 성사된수</span> 
											<span class="info-box-number" id="totalMatchings"></span>
										</div>
										<!-- /.info-box-content -->
									</div>
									<!-- /.info-box -->
									<div class="info-box mb-3 text-bg-danger">
										<span class="info-box-icon"> <i class="bi bi-cloud-download"></i>
										</span>
										<div class="info-box-content">
											<span class="info-box-text">Downloads</span> <span class="info-box-number">114,381</span>
										</div>
										<!-- /.info-box-content -->
									</div>
									<!-- /.info-box -->
									<div class="info-box mb-3 text-bg-info">
										<span class="info-box-icon"> <i class="bi bi-chat-fill"></i>
										</span>
										<div class="info-box-content">
											<span class="info-box-text">Direct Messages</span> <span class="info-box-number">163,921</span>
										</div>
										
										<!-- /.info-box-content -->
									</div>
									<!-- /.info-box -->
									<!-- USERS LIST -->
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">Date Profile 최신 가입자들 예정</h3>
									<div class="card-tools">
										<span class="badge text-bg-danger"> 8명이 가입 예정 </span>
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
										<div class="col-3 p-2">
											<img class="img-fluid rounded-circle" src="img/user1-128x128.jpg" alt="User Image"> <a class="btn fw-bold fs-7 text-secondary text-truncate w-100 p-0" href="#">
												Alexander Pierce </a>
										</div>
										<div class="col-3 p-2">
											<img class="img-fluid rounded-circle" src="img/user1-128x128.jpg" alt="User Image"> <a class="btn fw-bold fs-7 text-secondary text-truncate w-100 p-0" href="#">
												Norman </a>
										</div>
										<div class="col-3 p-2">
											<img class="img-fluid rounded-circle" src="img/user7-128x128.jpg" alt="User Image"> <a class="btn fw-bold fs-7 text-secondary text-truncate w-100 p-0" href="#">
												Jane </a>
										</div>
										<div class="col-3 p-2">
											<img class="img-fluid rounded-circle" src="img/user6-128x128.jpg" alt="User Image"> <a class="btn fw-bold fs-7 text-secondary text-truncate w-100 p-0" href="#">
												John </a>
										</div>
										<div class="col-3 p-2">
											<img class="img-fluid rounded-circle" src="img/user2-160x160.jpg" alt="User Image"> <a class="btn fw-bold fs-7 text-secondary text-truncate w-100 p-0" href="#">
												Alexander </a>
										</div>
										<div class="col-3 p-2">
											<img class="img-fluid rounded-circle" src="img/user5-128x128.jpg" alt="User Image"> <a class="btn fw-bold fs-7 text-secondary text-truncate w-100 p-0" href="#">
												Sarah </a>
										</div>
										<div class="col-3 p-2">
											<img class="img-fluid rounded-circle" src="img/user4-128x128.jpg" alt="User Image"> <a class="btn fw-bold fs-7 text-secondary text-truncate w-100 p-0" href="#">
												Nora </a>
										</div>
										<div class="col-3 p-2">
											<img class="img-fluid rounded-circle" src="img/user3-128x128.jpg" alt="User Image"> <a class="btn fw-bold fs-7 text-secondary text-truncate w-100 p-0" href="#">
												Nadia </a>
										</div>
									</div>
									<!-- /.users-list -->
								</div>
								<!-- /.card-body -->
								<div class="card-footer text-center">
									<a href="/adminProfileList" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">전체 유저 리스트</a>
								</div>
								<!-- /.card-footer -->
							</div>
							<!-- /.card -->
								</div>
								<!-- /.col -->
							</div>
							<!--end::Row-->
						</div>
						<!-- ./card-body -->

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

						<div class="col-md-6">
							
						</div>
						<!-- /.col -->
					</div>
					<!--end::Row-->
					<!--begin::Latest Order Widget-->
					<!-- /.card -->
				</div>
				<!-- /.col -->

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
        type: 'bar',
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
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(255, 159, 64, 1)'
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
                label: '',
                data: count,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(255, 159, 64, 1)'
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
function updateTotalMembers() {
    fetch('http://localhost:8080/totalProfiles')
        .then(response => response.json())
        .then(data => {
            // 서버에서 반환된 회원 수를 총회원수 <span>에 업데이트
            document.getElementById('totalMembersCount').textContent = data.count;
        })
        .catch(error => {
            console.error('총 회원 수를 가져오는 중 오류 발생:', error);
            document.getElementById('totalMembersCount').textContent = '오류 발생';
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
    updateTotalMembers();
    tatalMatChings();
    getCountProfileByYear();
    getGenresBookings(); // 함수 호출 추가
});
</script>


<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>
