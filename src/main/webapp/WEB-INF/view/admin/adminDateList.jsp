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
						<h3 class="mb-0">등록된 데이트 신청 목록</h3>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-end">
							<li class="breadcrumb-item"><a href="/adminMain">홈</a></li>
							<li class="breadcrumb-item active" aria-current="page">데이트 신청 목록</li>
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
								<form action="/adminMemberList" method="post">
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
												<input type="text" class="form-control-sm" id="search" name="search" placeholder="검색할 프로필을 입력하세요">&nbsp;&nbsp;&nbsp;
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
										<c:when test="${profileList != null}">
											<table class="table">
												<thead>
													<tr class="align-middle text-center">
														<th style="width: 5%">Num.</th>
														<th style="width: 5%">회원ID</th>
														<th style="width: 10%">닉네임</th>
														<th style="width: 20%">자기소개</th>
														<th style="width: 20%">사진1</th>
														<th style="width: 20%">사진2</th>
														<th style="width: 20%" class="text-center">슈퍼리스트</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="user" items="${profileList}">
														<tr class="align-middle text-center text-truncate">
															<td>${user.id}</td>
															<td>${user.userId}</td>
															<td>${user.nickName}</td>
															<td><c:choose>
																	<c:when test="${fn:length(user.introduce) gt 20}">
																		<c:out value="${fn:substring(user.introduce, 0, 19)}...">
																		</c:out>
																	</c:when>
																	<c:otherwise>
																		<c:out value="${user.introduce}">
																		</c:out>
																	</c:otherwise>
																</c:choose></td>

															<td><img style="width: 100px; height: 100px" class="img-fluid" src="/DateProfileIMAGE/${user.firstUploadFileName}" onerror="this.onerror = null; this.src='/img/usernone.jpg'" alt="사진"></td>
															<td><img style="width: 100px; height: 100px" class="img-fluid" src="/DateProfileIMAGE/${user.secondUploadFileName}" onerror="this.onerror = null; this.src='/img/usernone.jpg'" alt="사진"></td>
															
															
															<td class="text-center">
															<c:choose>
															<c:when test="${user.listStatus == 1}">
																<form action="/superlistChange/${user.id}" method="get">
																	<button type="submit" class="btn btn-warning">슈퍼리스트 해제</button>
																</form>
																</c:when>
																<c:otherwise>
																<form action="/superlistChange/${user.id}" method="get">
																	<button type="submit" class="btn btn-success">슈퍼리스트 등록</button>
																</form>
																</c:otherwise>
																</c:choose>
															</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>

										</c:when>
										<c:otherwise>
											<div class="jumbotron display-4">
												<h5>게시글이 없습니다</h5>
											</div>
										</c:otherwise>
									</c:choose>
								</table>
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