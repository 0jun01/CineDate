<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div style="margin:100px;">
    <h1> 안녕하세요.</h1>
    <h1> CineDate 시네데이트입니다.</h1>
    <br>
        <p> 아래 코드를 회원가입 창에서 입력해주세요.</p>
    <br>

    <div align="center" style="border:1px solid black; font-family:verdana;">
    	<h3 style="color:blue"> 회원가입 인증 코드 입니다.</h3>
        <div style="font-size:130%">${code}</div>
    </div>
    <br/>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>