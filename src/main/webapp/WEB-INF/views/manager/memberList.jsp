<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%@ include file = "../manager/top.jsp" %>
<td align = "center" colspan = "5">
<div align="center">
<div style="text-align: right;">

	  <form action="SearchMember.do" method="get">
		    <input type="text" name="word" placeholder="이름">
		    <input type="submit" value="검색">
	  </form>
</div>
<font size="4" color="blue"><b>회원목록</b></font><br>
<table border="1" width="100%" style = "text-align:center;">
	<tr bgcolor="sky-blue">
		<th>프로필</th>
		<th>이름</th>
		<th>닉네임</th>
		<th>이메일</th>
		<th>회원가입일</th>
		<th>핸드폰 번호</th>
		<th>집 번호</th>
		<th>납부여부</th>
	</tr>
	
		<c:choose>
			<c:when test="${empty memberList && empty searchMemberList}">
				<tr>
					<td colspan = "8"><b>등록된 회원이 없습니다 ㅠㅠ</b></td>
				</tr>
			</c:when>
			<c:when test="${not empty memberList}">
				<c:forEach var = "member" items = "${memberList}">
				<tr>
					<td width="60px" height="55px"><img alt="프로필사진" src="img/${member.n_profile}" width="60px" height="60px"></td>
					<td>${member.n_name}</td>
					<td>${member.n_nick}</td>
					<td>${member.n_Email}</td>
					<td>
						<fmt:parseDate var="parsedDate2" value="${member.n_regdate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
						<fmt:formatDate value="${parsedDate2}" pattern = "yyyy년 MM월 dd일 HH시 mm분"/>
					</td>
					<td>${member.n_phone1}</td>
					<td>${member.n_phone2}</td>
					<td>${member.n_lend}</td>
				</tr>
				</c:forEach>
			</c:when>
			
			<c:when test="${not empty searchMemberList}">
				<c:forEach var = "smember" items = "${searchMemberList}">
				<tr>
					<td width="60px" height="55px"><img alt="프로필사진" src="img/${smember.n_profile}" width="60px" height="60px"></td>
					<td>${smember.n_name}</td>
					<td>${smember.n_nick}</td>
					<td>${smember.n_Email}</td>
					<td>
						<fmt:parseDate var="sparsedDate2" value="${smember.n_regdate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
						<fmt:formatDate value="${sparsedDate2}" pattern = "yyyy년 MM월 dd일 HH시 mm분"/>
					</td>
					<td>${smember.n_phone1}</td>
					<td>${smember.n_phone2}</td>
					<td>${smember.n_lend}</td>
				</tr>
				</c:forEach>
			</c:when>
			
		</c:choose>
</table>
</div>

<!-- 페이징 -->
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
		</c:if>
		&nbsp;&nbsp; <a href = "m_main.do"><button>메인으로</button></a>
</div>

</td>
<%@ include file = "../bottom.jsp"%>