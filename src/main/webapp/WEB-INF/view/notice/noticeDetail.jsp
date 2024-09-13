<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div id="wrap">
    <div id="in--wrap">
        <div class="notice--list--detail">
        <c:choose>
            <c:when test="${notice != null}">
                <h1 class="notice--detail--title">${notice.title}</h1>
                <div class="notoce--detail">
                    <div class="notice--detail--category">${notice.category}</div>
                    <div class="notice--detail--time">${notice.timestampToString()}</div>
                    <div class="notice--detail--views">조회수: ${notice.viewCount}</div> 
                    <div class="list--btn btn">
                        <a href="/notice"><img src="/img/list_icon_gray.png" width="100%"></a>
                    </div>
                </div>
                <div class="notice--detail--content">${notice.content}</div>
            </c:when>
            <c:otherwise>
                <div class="jumbotron display-4">
                    <h5>해당 공지사항이 존재하지 않습니다.</h5>
                </div>
            </c:otherwise>
        </c:choose>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
