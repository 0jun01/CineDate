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
	<!--begin::App Content-->
	<div class="app-content">
		<!--begin::Container-->
		<div class="container-fluid">
			<!--begin::Row-->
			<div class="row">
				<div class="col-12">
					<!-- Default box -->
					<div class="card">

						<div class="card-header">
							<span class="card-title col-5" id="title" style="resize: none; border-style: none">${event.title}</span>
							<span class="input-group-text col-3 float-end" id="date"> <fmt:parseDate
									value="${event.releaseDate}" var="releaseDate" pattern="yyyy-MM-dd HH:mm:ss.S" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${releaseDate}" /> ~ <fmt:parseDate
									value="${event.endDate}" var="endDate" pattern="yyyy-MM-dd HH:mm:ss.S" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${endDate}" />
							</span>


						</div>
						<div class="card-body">
							<img class="form-control" id="content" style="resize: none; border-style: none" src="/DateProfileIMAGE/${event.uploadFileName}" width="50%">
						</div>
						<!-- /.card-body -->
						<div class="card-footer">
							<form action="/adminEventDelete" method="get">
								<button type="submit" class="btn btn-danger float-end">삭제</button>
							</form>
							<form action="/adminEventReWrite/${event.id}" method="get">
								<button type="submit" class="btn btn-warning float-end">수정</button>
							</form>
						</div>
						<!-- /.card-footer-->

					</div>
					<!-- /.card -->

				</div>
			</div>
			<!--end::Row-->
		</div>
		<!--end::Container-->
	</div>
	<!--end::App Content-->
</main>
<!--end::App Main-->



</div>
</div>
</div>
<!-- end of content.jsp(xxx.jsp) -->


<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>