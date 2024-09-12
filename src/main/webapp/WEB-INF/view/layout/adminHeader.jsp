<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>

<title>Dashboard</title>
<!--begin::Primary Meta Tags-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="title" content="AdminLTE | Dashboard v2">
<meta name="author" content="ColorlibHQ">
<meta name="description" content="AdminLTE is a Free Bootstrap 5 Admin Dashboard, 30 example pages using Vanilla JS.">
<meta name="keywords"
	content="bootstrap 5, bootstrap, bootstrap 5 admin dashboard, bootstrap 5 dashboard, bootstrap 5 charts, bootstrap 5 calendar, bootstrap 5 datepicker, bootstrap 5 tables, bootstrap 5 datatable, vanilla js datatable, colorlibhq, colorlibhq dashboard, colorlibhq admin dashboard">
<!--end::Primary Meta Tags-->
<!--begin::Fonts-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fontsource/source-sans-3@5.0.12/index.css" integrity="sha256-tXJfXfp6Ewt1ilPzLDtQnJV4hclT9XuaZUKyUvmyr+Q="
	crossorigin="anonymous">
<!--end::Fonts-->
<!--begin::Third Party Plugin(OverlayScrollbars)-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/overlayscrollbars@2.3.0/styles/overlayscrollbars.min.css" integrity="sha256-dSokZseQNT08wYEWiz5iLI8QPlKxG+TswNRD8k35cpg="
	crossorigin="anonymous">
<!--end::Third Party Plugin(OverlayScrollbars)-->
<!--begin::Third Party Plugin(Bootstrap Icons)-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.min.css" integrity="sha256-Qsx5lrStHZyR9REqhUF8iQt73X06c8LGIUPzpOhwRrI="
	crossorigin="anonymous">
<!--end::Third Party Plugin(Bootstrap Icons)-->
<!--begin::Required Plugin(AdminLTE)-->
<link rel="stylesheet" href="/css/adminlte.css">
<link rel="stylesheet" href="/css/common.css">
<!--end::Required Plugin(AdminLTE)-->
<!-- apexcharts -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/apexcharts@3.37.1/dist/apexcharts.css" integrity="sha256-4MX+61mt9NVvvuPjUWdUdyfZfxSB1/Rf9WtqRHgG5S0="
	crossorigin="anonymous">
</head>
<body class="layout-fixed sidebar-expand-lg bg-body-tertiary">
	<!--begin::App Wrapper-->
	<div class="app-wrapper">
		<!--begin::Header-->
		<nav class="app-header navbar navbar-expand bg-body">
			<!--begin::Container-->
			<div class="container-fluid">
				<!--begin::Start Navbar Links-->
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" data-lte-toggle="sidebar" href="#" role="button"> <i class="bi bi-list"></i>
					</a></li>
					<li class="nav-item d-none d-md-block"><a href="/adminMain" class="nav-link">홈</a></li>
					<li class="nav-item d-none d-md-block"><a href="#" class="nav-link">고객문의현황</a></li>
				</ul>
				<!--end::Start Navbar Links-->
				<!--begin::End Navbar Links-->
				<ul class="navbar-nav ms-auto">
					<!--begin::Navbar Search-->
					<li class="nav-item"><a class="nav-link" data-widget="navbar-search" href="#" role="button"> <i class="bi bi-search"></i>
					</a></li>
					<!--end::Navbar Search-->
					<!--begin::Messages Dropdown Menu-->

					
					<!--end::Notifications Dropdown Menu-->
					<!--begin::Fullscreen Toggle-->
					<li class="nav-item"><a class="nav-link" href="#" data-lte-toggle="fullscreen"> <i data-lte-icon="maximize" class="bi bi-arrows-fullscreen"></i> <i
							data-lte-icon="minimize" class="bi bi-fullscreen-exit" style="display: none;"></i>
					</a></li>
					<!--end::Fullscreen Toggle-->
					<!--begin::User Menu Dropdown-->
					<li class="nav-item dropdown user-menu"><a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"> <img src="/img/user2-160x160.jpg"
							class="user-image rounded-circle shadow" alt="User Image"> <span class="d-none d-md-inline">${user.loginId} 관리자님</span>
					</a>
						<ul class="dropdown-menu dropdown-menu-lg dropdown-menu-end">
							<!--begin::User Image-->
							<li class="user-header text-bg-primary"><img src="/img/user2-160x160.jpg" class="rounded-circle shadow" alt="User Image">
								<p>
									${user.loginId} <small>2024년 9월 가입</small>
								</p></li>
							<!--end::User Image-->
							<!--begin::Menu Body-->
							
							<!--end::Menu Body-->
							<!--begin::Menu Footer-->
							<li class="user-footer"><a href="/logout" class="btn btn-default btn-flat float-end">로그아웃</a></li>
							<!--end::Menu Footer-->
						</ul></li>
					<!--end::User Menu Dropdown-->
				</ul>
				<!--end::End Navbar Links-->
			</div>
			<!--end::Container-->
		</nav>
		<!--end::Header-->
		<!--begin::Sidebar-->
		<aside class="app-sidebar bg-body-secondary shadow" data-bs-theme="dark">
			<!--begin::Sidebar Brand-->
			<div class="sidebar-brand">
				<!--begin::Brand Link-->
				<a href="/adminMain" class="brand-link"> <!--begin::Brand Image--> <img src="/img/CineDateLogo.png" alt="CineDate Logo"
					class="brand-image opacity-75 shadow"> <!--end::Brand Image--> <!--begin::Brand Text--> <span class="brand-text fw-light">CineDate 관리페이지</span> <!--end::Brand Text-->
				</a>
				<!--end::Brand Link-->
			</div>
			<!--end::Sidebar Brand-->
			<!--begin::Sidebar Wrapper-->
			<div class="sidebar-wrapper">
				<nav class="mt-2">
					<!--begin::Sidebar Menu-->
					<ul class="nav sidebar-menu flex-column" data-lte-toggle="treeview" role="menu" data-accordion="false">
						<li class="nav-item"><a href="#" class="nav-link active"> <i class="nav-icon bi bi-speedometer"></i>
								<p>
									대쉬보드 <i class="nav-arrow bi bi-chevron-right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="/adminMain" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>대쉬보드</p>
								</a></li>
							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i class="nav-icon bi bi-clipboard-fill"></i>
								<p>
									관리 <i class="nav-arrow bi bi-chevron-right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="/adminNotice" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>공지사항</p>
								</a></li>
								<li class="nav-item"><a href="/adminEvent" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>이벤트</p>
								</a></li>
								<li class="nav-item"><a href="/adminMemberList" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>회원정보</p>
								</a></li>
								<li class="nav-item"><a href="/adminProfileList" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>프로필 정보</p>
								</a></li>
								
								
							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i class="nav-icon bi bi-box-seam-fill"></i>
								<p>
									박스오피스 <span class="nav-badge badge text-bg-secondary me-3">3</span> <i class="nav-arrow bi bi-chevron-right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="./layout/unfixed-sidebar.html" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>일간 박스오피스</p>
								</a></li>
								<li class="nav-item"><a href="./layout/fixed-sidebar.html" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>주간 박스오피스</p>
								</a></li>
								<li class="nav-item"><a href="./layout/fixed-complete.html" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>월간 박스오피스</p>
								</a></li>
								<li class="nav-item"><a href="./layout/sidebar-mini.html" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>준비중</p>
								</a></li>
								<li class="nav-item"><a href="./layout/collapsed-sidebar.html" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>
											준비중 <small>+ ...</small>
										</p>
								</a></li>
								<li class="nav-item"><a href="./layout/logo-switch.html" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>
											아 암튼준비중임 <small>+ ...</small>
										</p>
								</a></li>
								<li class="nav-item"><a href="./layout/layout-rtl.html" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>.....</p>
								</a></li>
							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i class="nav-icon bi bi-tree-fill"></i>
								<p>
									히스토리 <i class="nav-arrow bi bi-chevron-right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="/adminHistory" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>결제내역</p>
								</a></li>
								<li class="nav-item"><a href="./UI/timeline.html" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>결제top10</p>
								</a></li>
								<li class="nav-item"><a href="/adminCancelHistory" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>결제 취소 내역</p>
								</a></li>
							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i class="nav-icon bi bi-pencil-square"></i>
								<p>
									데이팅현황 <i class="nav-arrow bi bi-chevron-right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="./forms/general.html" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>데이트현황</p>
								</a></li>
							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i class="nav-icon bi bi-table"></i>
								<p>
									문의페이지 <i class="nav-arrow bi bi-chevron-right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="./tables/simple.html" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
										<p>Simple Tables</p>
								</a></li>
							</ul></li>
						
					</ul>
					<!--end::Sidebar Menu-->
				</nav>
			</div>
			<!--end::Sidebar Wrapper-->
		</aside>
		<!--end::Sidebar-->