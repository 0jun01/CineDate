<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp" %>

<div id="wrap">
    <div id="in--wrap">
        <div class="notice--list">
            <h1 class="section--title">예약 내역</h1>
            <c:choose>
                <c:when test="${not empty myreservations}">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>영화 제목</th>
                                <th>상영 날짜</th>
                                <th>상영 시간</th>
                                <th>예약된 좌석</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="reservation" items="${myreservations}">
                                <tr>
                                    <td>${reservation.movieTitle}</td>
                                    <td>${reservation.showDate}</td>
                                    <td>${reservation.showTime}</td>
                                    <td>${reservation.reservedSeat}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="jumbotron display-4">
                        <h5>예약 내역이 없습니다.</h5>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>