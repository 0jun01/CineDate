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
						<form action="/adminEventWrite" method="post" enctype="multipart/form-data">
							<div class="card-header">
								<div style="display: flex; flex-direction: row;">
									<input type="text" class="card-title col-9" placeholder="제목을 입력하세요" name="title" id="title"> &nbsp;&nbsp;&nbsp;
									<div class="col-md-4 card-title">

										<input type="date" name="releaseDate" id="releaseDate" value="" max="9999-12-31" class="col-md-4"> ~ <input type="date" name="endDate" id="endDate" value=""
											max="9999-12-31" class="col-md-4">
									</div>
								</div>


							</div>
							<div class="card-body">
								<label for="mFileOne"> <img id="img_eventimage1" src="/img/Basic.jpg" alt="프로필 사진" onclick="document.getElementById('event_upload_file1').click();" width="40%"
									> <input type="file" id="event_upload_file1" name="mFileOne" title="내용" onchange="previewImage(this, 'img_eventimage1')" style="display: none;">
								</label>
							</div>
							<!-- /.card-body -->
							<div class="card-footer">
								<button type="submit" class="btn btn-success float-end" onclick="javascript : alert('이벤트가 작성되었습니다')">작성 완료</button>
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
<script type="text/javascript">
	function previewImage(input, imgId) {
		var file = input.files[0];
		var img = document.getElementById(imgId);

		if (file) {
			var reader = new FileReader();

			reader.onload = function(e) {
				img.src = e.target.result; // 미리보기 이미지 업데이트
			};

			reader.readAsDataURL(file);
		} else {
			img.src = '/img/Basic.jpg'; // 파일이 선택되지 않은 경우 기본 이미지로 설정
		}
	}
</script>


</div>
</div>
</div>


<!-- end of content.jsp(xxx.jsp) -->
<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>

