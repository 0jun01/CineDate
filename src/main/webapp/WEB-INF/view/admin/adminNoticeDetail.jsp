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
					<h3 class="mb-0">공지사항</h3>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-end">
						<li class="breadcrumb-item"><a href="/adminMain">홈</a></li>
						<li class="breadcrumb-item active" aria-current="page">공지사항</li>
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
							<span class="card-title col-5" id="title" style="resize: none; border-style: none">${notice.title}</span>
							<span class="input-group-text col-2 float-end" id="category">${notice.category}
							</span>


						</div>
						<div class="card-body">
							<textarea class="form-control" rows="20" id="content" name="content" style="resize: none; border-style: none" readonly="readonly">${notice.content}</textarea>
						</div>
						<!-- /.card-body -->
						<div class="card-footer">
							<form action="/adminNoticeDelete/${notice.id}" method="get">
								<button type="submit" class="btn btn-danger float-end">삭제</button>
							</form>
							<form action="/adminNoticeReWrite/${notice.id}" method="get">
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