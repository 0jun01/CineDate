<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>
    
    
    <div class="card card-warning card-outline mb-4">
	<main class="app-main">
		<!--begin::App Content Header-->
		<div class="app-content-header">
			<!--begin::Container-->
			<div class="container-fluid">
				<!--begin::Row-->
				<div class="row">
					<div class="col-sm-6">
						<h3 class="mb-0">회원목록</h3>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-end">
							<li class="breadcrumb-item"><a href="/adminMain">홈</a></li>
							<li class="breadcrumb-item active" aria-current="page">회원목록</li>
						</ol>
					</div>
				</div>
				<!--end::Row-->
			</div>
			<!--end::Container-->
		</div>
		<!--end::App Content Header-->
		<div class="app-content">
			<!--begin::Container-->
			<div class="container-fluid">
				<!--begin::Row-->
				<div class="row">
					<div class="col-md-12">
						<div class="card mb-4">
							<div class="card-header">
								<h3 class="card-title"></h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<table class="table table-bordered">
									<c:choose>
										<c:when test="${userList != null}">
											<table class="table">
												<thead>
													<tr class="align-middle text-center">
														<th style="width: 10px">id</th>
														<th style="width: 100px">회원ID</th>
														<th>이름</th>
														<th style="width: 150px">이메일</th>
														<th style="width: 100px">전화번호</th>
														<th>생년월일</th>
														<th style="width: 50px">성별</th>
														<th style="width: 100px" class="text-center">수정</th>
														<th style="width: 120px" class="text-center">삭제</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="user" items="${userList}">
														<tr class="align-middle text-center">
															<td>${user.id}</td>
															<td>${user.loginId}</td>
															<td>${user.name}</td>
															<td>${user.email}</td>
															<td>${user.phoneNum}</td>
															<td>${user.birthDay}</td>
															<td>${user.gender}</td>
															<td class="text-center">
																<form action="#" method="get">
																	<button class="btn btn-warning">수정</button>
																</form>
															</td>

															<td style="width: 100px;" class="text-center">
															<form action="#" method="get">
																<button type="submit" class="btn btn-danger">강제탈퇴</button>
																</form>
															</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>

										</c:when>
										<c:otherwise>
											<div class="jumbotron display-4">
												<h5>가입한 사람이 없습니다</h5>
											</div>
										</c:otherwise>
									</c:choose>
									</div>
									<!-- /.card-body -->



									<div class="card-footer clearfix">
										
										<ul class="pagination pagination-sm m-0" style="justify-content: center">
											<li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
											<li class="page-item"><a class="page-link" href="#">1</a></li>
											<li class="page-item"><a class="page-link" href="#">2</a></li>
											<li class="page-item"><a class="page-link" href="#">3</a></li>
											<li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
										</ul>
									</div>
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
									<!--begin::Footer-->

									</div>
									</div>
									</div>
									<!-- end of content.jsp(xxx.jsp) -->
    
<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>