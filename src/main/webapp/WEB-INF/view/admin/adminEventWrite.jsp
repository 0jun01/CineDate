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
					<h3 class="mb-0">이벤트 글쓰기</h3>
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
						<form action="/adminEventSend" method="post" enctype="multipart/form-data">
							<div class="card-header">
								<div style="display: flex; flex-direction: row;">
									<input type="text" class="card-title col-9" placeholder="제목을 입력하세요" name="title" id="title"> &nbsp;&nbsp;&nbsp;
									<div class="col-md-4 card-title">

										<input type="date" name="releaseDate" id="releaseDate" value="" max="9999-12-31" class="col-md-4"> ~ <input type="date" name="endDate" id="endDate" value=""
											max="9999-12-31" class="col-md-4">
									</div>
								</div>
								<br>
								<div>
									파일 : <input type="file"name="file" accept="jpeg, png, gif, jpg"/>
								</div>

							</div>
							<div class="card-body">
								<img class="img-box" name="img-box">
							</div>
							<!-- /.card-body -->
							<div class="card-footer">
								<button type="submit" class="btn btn-success float-end" onclick="javascript : alert('이벤트가 등록되었습니다')">글쓰기완료</button>
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