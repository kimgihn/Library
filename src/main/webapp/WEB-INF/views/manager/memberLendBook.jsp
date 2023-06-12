<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ include file = "top.jsp"%>
<td align = "center" colspan = "5">

 	<c:if test="${lend ne '미납'}">
        <font size="4" color="green"><b>${name}님의 도서대여 목록</b></font><br>
  	</c:if>
   <c:if test="${lend eq '미납'}">
        <font size="4" color="red"><b>${name}님의 미반납도서 목록</b></font><br>
	</c:if>

		<table border="1" width="100%" style = "text-align:center;">
			<tr bgcolor="sky-blue">
				<th>도서</th>
				<th>도서코드</th>
				<th>도서명</th>
				<th>랜탈 가격</th>
				<th>지은이</th>
				<th>도서등록일</th>
				<th>도서소개</th>
			</tr>
			<tr>
				<c:choose>		
					<c:when test="${empty MemberLendBook}">
						<tr>
							<td colspan="9" width = "300px" height = "300px">
							<font size="20"><b>빌려간 도서가 없습니다.</b></font>
							</td>
						</tr>
					</c:when>
					<c:when test = "${not empty MemberLendBook}">
						<c:forEach var="book" items="${MemberLendBook}">
							<tr>
								<td width="60px" height="55px"><img alt="책사진" src="img/${book.b_imgName}" width="60px" height="55px"></td>
								<td>${book.b_code}</td>
								<td>
								<a href="<c:url value = "/bookDetail.do"/>?b_no=${book.b_no}">${book.b_name}</a>
								</td>
								<td>${book.b_price}</td>
								<td>${book.b_writer}</td>
								<td>
									<fmt:parseDate var="parsedDate" value="${book.b_regdate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
									<fmt:formatDate value="${parsedDate}" pattern = "yyyy년 MM월 dd일 HH시 mm분"/>
								</td>
								<td>${book.b_intro}</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>				
			</tr>
		</table>
			
<!-- 페이징  -->		
	<div align = "center">
	<c:if test="${startPageGroup > 1}">
				<a href="${url}pageNo=1"> <span>[처음]</span> </a>
			</c:if>
			<c:if test="${startPageGroup > 1}">
				<a href="${url}pageNo=${startPageGroup - 1}"> <span>&laquo;</span> </a>
			</c:if>
			<c:forEach begin="${startPageGroup}" end="${endPageGroup}" step="1" var="i">
				<a href="${url}pageNo=${i}"> 
					<c:choose>
						<c:when test="${pageNo == i}"> <b>[${i}].</b> </c:when>
						<c:otherwise> ${i}. </c:otherwise>
					</c:choose>
				</a>
			</c:forEach>
			<c:if test="${endPageGroup < pageCount}">
				<a href="${url}pageNo=${endPageGroup + 1}"> <span>&raquo;</span> </a>
			</c:if>
			<c:if test="${endPageGroup < pageCount}">
				<a href="${url}pageNo=${pageCount}"> <span>[끝]</span> </a>
			</c:if> &nbsp;&nbsp; <a href = "m_main.do"><button>메인으로</button></a>
	</div>		



</td>
<%@ include file = "../bottom.jsp"%>