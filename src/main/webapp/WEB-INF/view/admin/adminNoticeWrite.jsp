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
					<h3 class="mb-0">공지사항 글쓰기</h3>
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
						<form action="/adminNoticeWrite" method="post">
							<div class="card-header" style="display: flex;flex-direction: row">

								<input type="text" class="card-title col-10" placeholder="제목을 입력하세요" name="title" id="title">
								<div class="col-md-6 card-title">
									<select class="col-md-4" id="category" name ="category" required>
										<option selected disabled value="">선택필수</option>
										<option value="시스템점검">시스템점검</option>
										<option value="행사/이벤트">행사/이벤트</option>
										<option value="제휴이벤트">제휴이벤트</option>
										<option value="극장">극장</option>
										<option value="기타">기타</option>
									</select>
									<div class="invalid-feedback">카테고리를 선택하세요</div>
								</div>

							</div>
							<div class="card-body">
								<textarea class="form-control" rows="20" id = "content" name="content" style="resize: none;" placeholder="내용을 입력하세요"></textarea>
							</div>
							<!-- /.card-body -->
							<div class="card-footer">
								<button type="submit" class="btn btn-success float-end">글쓰기완료</button>
							</div>
							<!-- /.card-footer-->
						</form>
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