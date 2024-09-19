<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- header.jsp -->
    <%@ include file="/WEB-INF/view/layout/header.jsp" %>
    
        <!-- start of content.jsp(xxx.jsp) -->
	
	<h1>이곳은 매칭 리스트 입니다.</h1>
	<c:choose>
		<c:when test="${list != null}">
			<table>
			  <tr>
			    <th>사진</th>
			    <th>닉네임</th>
			    <th>자기 소개</th>
			    <th>상태</th>
			  </tr>
			  
			  <c:forEach var="list"  items="${list}">
			  <tr>
			    <td><img class="m--profile list--profile" alt="" src="/image/${list.firstUploadFileName}" style="width: 100px; height: 100px;"></td>
				<td>${list.nickName}</td>
				<td>${list.introduce}</td>
				<td>
				<c:if test="${list.status == 0}">
					<h3>응답대기중</h3>
				</c:if>
							  	
				<c:if test="${list.status == 1}">
					<button>메세지보내기</button>
				</c:if>			  	
				
				<c:if test="${list.status == 2}">
					<h3>거절 ㅠㅠ</h3>
				</c:if>			  	
				
				</td>
				
				
			  </tr>
			  </c:forEach>
			  
			  
			</table>

		
		</c:when>
		
		<c:otherwise>
			<h2>매칭된 사람이 없습니다.</h2>
		</c:otherwise>
				
	</c:choose>
	
	
	<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
</body>
</html>