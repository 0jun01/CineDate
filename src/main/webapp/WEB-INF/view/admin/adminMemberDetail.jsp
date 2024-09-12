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
					<h3 class="mb-0">회원정보 수정</h3>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-end">
						<li class="breadcrumb-item"><a href="/adminMain">홈</a></li>
						<li class="breadcrumb-item active" aria-current="page">회원정보 수정</li>
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
			<div class="row g-4">
				<!--begin::Col-->
				<div class="col-12">
					<div class="callout callout-info">개인정보 보호를 위해 아이디, 비밀번호는 변경할수 없습니다</div>
				</div>
				<!--end::Col-->
				<!--begin::Col-->
				<div class="col-md-6">
					<!--begin::Quick Example-->

					<div class="card card-info card-outline mb-4">
						<!--begin::Header-->
						<div class="card-header">
							<div class="card-title">${member.name}님의회원정보</div>
						</div>
						<!--end::Header-->
						<!--begin::Form-->
						<form class="needs-validation" novalidate action="/adminMemberDetail/${event.id}" method="get">
							<!--begin::Body-->
							<div class="card-body">
								<!--begin::Row-->
								<div class="row g-3">
									<!--begin::Col-->
									<div class="col-md-6">
										<label for="userId" class="form-label">아이디(변경불가)</label> <input type="text" class="form-control" id="userId" value="${member.loginId}" required readonly="readonly">
										<div class="valid-feedback">Looks good!</div>
									</div>
									<!--end::Col-->
									<!--begin::Col-->
									<div class="col-md-6">
										<label for="password" class="form-label">비밀번호(변경불가)</label> <input type="text" class="form-control" id="password"
											value="<c:choose><c:when test="${fn:length(member.password) gt 1}"><c:out value ="${fn:substring(member.loginId, 0, 0)}********"></c:out>
										</c:when>
										<c:otherwise>
										${member.password}</c:otherwise>
										</c:choose>"
											required readonly="readonly">


									</div>
									<!--end::Col-->
									<!--begin::Col-->
									<div class="col-md-6">
										<label for="name" class="form-label">이름</label>
										<div class="input-group has-validation">
											<input type="text" class="form-control" id="name" value="${member.name}" required>
											<div class="invalid-feedback">이름이 공백이면 안됩니다</div>
										</div>
									</div>
									<!--end::Col-->
									<!--begin::Col-->
									<div class="col-md-6">
										<label for="phoneNum" class="form-label">휴대폰번호</label> <input type="text" class="form-control" id="phoneNum" value="${member.phoneNum}" required>
										<div class="invalid-feedback">휴대폰 번호가 공백이면 안됩니다</div>
									</div>
									<!--end::Col-->
									<!--begin::Col-->
									<div class="col-md-12">
										<label for="email" class="form-label">이메일</label>
										<div class="input-group mb-3">
											<input type="text" class="form-control" id="email" value="${member.email}" required="required">
										</div>


										<div class="invalid-feedback">이메일이 공백이면 안됩니다</div>
									</div>
									<!--end::Col-->
									<!--begin::Col-->
									<p style="margin: 0">
									<div class="col-md-6">
										<label for="date" class="form-label">생일</label> <input type="date" class="form-control" id="date" value="${member.birthDay}" required> &nbsp;&nbsp;
										<div class="invalid-feedback">생일이 공백이면 안됩니다</div>
									</div>

									<div class="col-md-6">
										<label for="gender" class="form-label">성</label> <select class="form-select" id="gender" required >
											<c:choose>
											<c:when test=""
											<option>...</option>
										</select>
										<div class="invalid-feedback">Please select a valid state.</div>
									</div>
									<!--end::Col-->
									<!--begin::Col-->
									<div class="col-12">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value="" id="invalidCheck" required> <label class="form-check-label" for="invalidCheck"> 고객의 동의를 받고
												수정했습니다 </label>
											<div class="invalid-feedback">반드시 체크해 주세요</div>
										</div>
									</div>
									<!--end::Col-->


								</div>
								<!--end::Row-->
							</div>
							<!--end::Body-->
							<!--begin::Footer-->
							<div class="card-footer">
								<button class="btn btn-info" type="submit">회원정보 변경</button>

							</div>
							<!--end::Footer-->
						</form>

						<!--end::Form-->
						<!--begin::JavaScript-->
						<script>
                                    // Example starter JavaScript for disabling form submissions if there are invalid fields
                                    (() => {
                                        "use strict";

                                        // Fetch all the forms we want to apply custom Bootstrap validation styles to
                                        const forms =
                                            document.querySelectorAll(".needs-validation");

                                        // Loop over them and prevent submission
                                        Array.from(forms).forEach((form) => {
                                            form.addEventListener(
                                                "submit",
                                                (event) => {
                                                    if (!form.checkValidity()) {
                                                        event.preventDefault();
                                                        event.stopPropagation();
                                                    }

                                                    form.classList.add("was-validated");
                                                },
                                                false
                                            );
                                        });
                                    })();
                                </script>


						<!--end::JavaScript-->
					</div>
					<!--end::Form Validation-->
				</div>
				<!--end::Col-->
			</div>
			<!--end::Row-->
		</div>
		<!--end::Container-->
	</div>
	<!--end::App Content-->
</main>
<!--end::App Main-->
<!--begin::Footer-->



<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>