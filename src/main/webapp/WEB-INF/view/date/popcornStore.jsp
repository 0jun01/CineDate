<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- start of content.jsp(xxx.jsp) -->
<div>
    <form action="/date/popcornStore" method="POST">
        <p>팝콘을 선택하세요:</p>
        <label>
            <input type="radio" name="popcorn" value="1" />
            1개
        </label><br/>
        <label>
            <input type="radio" name="popcorn" value="10" />
            10개
        </label><br/>
        <label>
            <input type="radio" name="popcorn" value="30" />
            30개
        </label><br/>
        <label>
            <input type="radio" name="popcorn" value="50" />
            50개
        </label><br/>
        <label>
            <input type="radio" name="popcorn" value="100" />
            100개
        </label><br/>
        <button type="submit" value="제출" >
        제출
        </button>
    </form>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>