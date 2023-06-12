<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>      
    
<c:choose>   
<c:when test="${empty mgr && empty member}">
   <%@ include file="../top.jsp"%>
</c:when>
<c:when test="${not empty mgr}">
	<%@ include file = "../manager/top.jsp" %>
</c:when>
<c:when test="${not empty member}">
	<%@ include file = "../member/top.jsp" %>
</c:when>
</c:choose> 

<td align = "center" colspan = "5">
<div align="center">
<table border="1" width="80%">
	<caption><b><font size = "5" color="green">도서상세보기</font></b></caption>
		<tr>
			<th width="15%" class="m2" bgcolor="#00FFC0">도서명</th>
			<td width="35%" align="center"><b>${DetailBook.b_name}</b></td>
			<th width="15%" class="m2" bgcolor="#00FFC0">지은이</th>
			<td width="35%" align="center"><b>${DetailBook.b_writer}</b></td>
		</tr>
		<tr>
			<th width="15%" class="m2" bgcolor="#00FFC0">도서이미지</th>
			<td align="left" colspan="3">
				<img alt="책사진" src="img/${DetailBook.b_imgName}" width="35%">
				<!-- 절대 경로로 들어왔으면 절대경로로 이미지경로로 맞춰줘야 한다.-->
			</td>
		</tr>
		<tr>
			<th width="15%" class="m2" bgcolor="#00FFC0">도서수량</th>
			<td width="35%" align="center">${DetailBook.b_amount}권</td>
			<th width="15%" class="m2" bgcolor="#00FFC0">도서가격</th>
			<td width="35%" align="center">${DetailBook.b_price}</td>
		</tr>
		<tr>
			<th width="15%" class="m2" bgcolor="#00FFC0">도서 등록일</th>
			<td width="35%" align="center">
				<fmt:parseDate var="parsedDate" value="${DetailBook.b_regdate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
				<fmt:formatDate value="${parsedDate}" pattern = "yyyy년 MM월 dd일 HH시 mm분"/>
			</td>
			<th width="15%" class="m2" bgcolor="#00FFC0">도서 코드</th>
			<td width="35%" align="center">${DetailBook.b_code}</td>
		</tr>
		<tr>
			<th width="15%" class="m2" bgcolor="#00FFC0">도서 줄거리</th>
			<td align="center" colspan="3">
				<textarea name="pcontents" rows="5" cols="50" readOnly style = "width: 600px;" >${DetailBook.b_content}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4" class="m1" align="center" >
				<c:choose>
					<c:when test="${not empty mgr}">
						<a href = "<c:url value = "/m_main.do"/>"><button>메인으로</button></a>
					</c:when>
					<c:when test = "${empty mgr && empty member}">
						<a href = "<c:url value = "/main.do"/>"><button>메인으로</button></a>	
					</c:when>
					<c:when test="${not empty member}">
						<a href = "n_main.do"><button>메인으로</button></a>
					</c:when>
				</c:choose>		
			</td>
		</tr>
	</table>
</div>
</td>
<%@ include file = "../bottom.jsp"%>