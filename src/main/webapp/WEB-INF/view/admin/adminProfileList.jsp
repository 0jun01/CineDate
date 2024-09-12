<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
								<form action="/adminProfileList" method="get">
									<div class="col-4 float-end" style="display: flex; flex-direction: row;">

										<c:choose>

											<c:when test="${search != null}">
												<input type="text" class="form-control-sm" id="search" name="search" value="${search}">&nbsp;&nbsp;&nbsp;
												<h3>
													<li class="nav-item">
														<button type="submit" class="nav-link" data-widget="navbar-search">
															<i class="bi bi-search"></i>
														</button>
													</li>
												</h3>
											</c:when>
											<c:otherwise>
												<input type="text" class="form-control-sm" id="search" name="search" placeholder="검색할 닉네임를 입력하세요">&nbsp;&nbsp;&nbsp;
												<h3>
													<li class="nav-item">
														<button type="submit" class="nav-link" data-widget="navbar-search">
															<i class="bi bi-search"></i>
														</button>
													</li>
												</h3>
											</c:otherwise>
										</c:choose>
									</div>
								</form>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<table class="table table-bordered">
									<c:choose>
										<c:when test="${list != null}">
											<table class="table">
												<thead>
													<tr class="align-middle text-center">
														<th style="width: 10px">id</th>
														<th style="width: 170px">회원ID</th>
														<th>닉네임</th>
														<th>자기소개</th>
														<th>con 보유</th>
														<th style="width: 150px">계정상태</th>
														<th style="width: 150px">활성화</th>
														<th style="width: 80px" class="text-center"></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="user" items="${list}">
														<tr class="align-middle text-center">
															<td>${user.id}</td>
															<td>${user.userId}</td>
															<td>${user.nickName}</td>
															<td>${user.introduce}</td>
															<td>${user.con}</td>
															<td>${user.lifeStatus}</td>
															<td>${user.listStatus}</td>

															<td class="text-center">
																<form action="/lifeStatus?id=${user.id}" method="post">
																<c:if test="${user.lifeStatus==0}">
																	<button type="submit" class="btn btn-danger">정지하기</button>
																</c:if>
																<c:if test="${user.lifeStatus==1}">
																	<button type="submit" class="btn btn-danger" style="background-color: blue;">복구하기</button>
																</c:if>
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

										<nav aria-label="...">
											<ul class="pagination" style="justify-content: center">

												<li class="page-item <c:if test='${currentPage == 1}'>disabled</c:if>"><a class="page-link" href="?page=${currentPage - 1}&size=${size}">이전</a></li>
												<c:forEach begin="1" end="${totalPages}" var="page">
													<li class="page-item <c:if test='${page == currentPage}'>active </c:if>" aria-current="page"><a class="page-link" href="?page=${page}&size=${size}">${page}</a></li>
												</c:forEach>
												<li class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>"><a class="page-link" href="?page=${currentPage + 1}&size=${size}">다음</a></li>
											</ul>
										</nav>
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