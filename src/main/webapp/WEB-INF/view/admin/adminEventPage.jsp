<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>

<div class="card card-success card-outline mb-4">
	<main class="app-main">
		<!--begin::App Content Header-->
		<div class="app-content-header">
			<!--begin::Container-->
			<div class="container-fluid">
				<!--begin::Row-->
				<div class="row">
					<div class="col-sm-6">
						<h3 class="mb-0">이벤트</h3>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-end">
							<li class="breadcrumb-item"><a href="/adminMain">홈</a></li>
							<li class="breadcrumb-item active" aria-current="page">이벤트</li>
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
								<form action="/adminEvent" method="post">
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
												<input type="text" class="form-control-sm" id="search" name="search" placeholder="검색할 제목을 입력하세요">&nbsp;&nbsp;&nbsp;
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
										<c:when test="${eventList != null}">
											<table class="table">
												<thead>
													<tr class="align-middle text-center">
														<th style="width: 10px">id</th>
														<th>제목</th>
														<th>기간</th>
														<th>등록일</th>
														<th style="width: 40px" class="text-center">수정</th>
														<th style="width: 40px" class="text-center">삭제</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="event" items="${eventList}">
														<tr class="align-middle text-center">
															<td>${event.id}</td>
															<td><a href="/adminEventDetail/${event.id}">${event.title}</a></td>
															<td style="width: 200px;"><fmt:parseDate value="${event.releaseDate}" var="releaseDate" pattern="yyyy-MM-dd HH:mm:ss.S" /> <fmt:formatDate pattern="yyyy-MM-dd"
																	value="${releaseDate}" /> ~ <fmt:parseDate value="${event.endDate}" var="endDate" pattern="yyyy-MM-dd HH:mm:ss.S" /> <fmt:formatDate pattern="yyyy-MM-dd"
																	value="${endDate}" /></td>
															<td style="width: 120px;"><fmt:parseDate value="${event.createdAt}" var="createdAt" pattern="yyyy-MM-dd HH:mm:ss.S" /> <fmt:formatDate pattern="yyyy-MM-dd"
																	value="${createdAt}" /></td>
															<td style="width: 90px;" class="text-center">
																<form action="/adminEventReWrite/${event.id}" method="get">
																	<button class="btn btn-warning">수정</button>
																</form>
															</td>

															<td style="width: 90px;" class="text-center">
																<form action="/adminEventDelete/${event.id}" method="get">
																	<button type="submit" class="btn btn-danger">삭제</button>
																</form>
															</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>

										</c:when>
										<c:otherwise>
											<div class="jumbotron display-4">
												<h5>게시된 이벤트가 없습니다.</h5>
											</div>
										</c:otherwise>
									</c:choose>
								</table>
							</div>
							<!-- /.card-body -->



							<div class="card-footer clearfix">
								<form action="/adminEventWrite" method="get">
									<button class="btn btn-primary btn-sm clearfix text-bg-dark float-end">글쓰기</button>
								</form>
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