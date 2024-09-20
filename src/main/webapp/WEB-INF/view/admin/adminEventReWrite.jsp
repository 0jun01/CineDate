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
					<h3 class="mb-0">이벤트 글 수정</h3>
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
						<form action="/adminEventReWrite/${event.id}" method="post" enctype="multipart/form-data">
							<div class="card-header">
								<div style="display: flex; flex-direction: row;">

									<textarea class="card-title col-9" name="title" id="title" style="resize: none">${event.title}</textarea>
									&nbsp;&nbsp;&nbsp;
									<div class="col-md-4 card-title">

										<input type="date" name="releaseDate" id="releaseDate"
											value="<fmt:formatDate pattern="yyyy-MM-dd" value="${event.releaseDate}" />"
											max="9999-12-31" class="col-md-4"> ~ <input type="date" name="endDate" id="endDate"
											value="<fmt:formatDate pattern="yyyy-MM-dd" value="${event.endDate}" />"
											max="9999-12-31" class="col-md-4">
									</div>
								</div>


							</div>
							<div class="card-body">
								<label for="mFileOne"> <img id="img_eventimage1" src="/DateProfileIMAGE/${event.uploadFileName}" alt="프로필 사진"
									onclick="document.getElementById('event_upload_file1').click();"> <input type="file" id="event_upload_file1" name="mFileOne" title="내용"
									onchange="previewImage(this, 'img_eventimage1')" style="display: none;" onerror="this.onerror = null; this.src='/img/usernone.jpg'" alt="사진">
								</label>
							</div>
							<!-- /.card-body -->
							<div class="card-footer">
								<button type="submit" class="btn btn-success float-end" onclick="javascript : alert('이벤트가 수정되었습니다')">수정 완료</button>
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

