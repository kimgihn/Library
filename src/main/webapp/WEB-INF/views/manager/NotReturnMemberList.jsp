<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>      
<%@ include file = "top.jsp"%>
<td align = "center" colspan = "5">

<font size="4" color="red"><b>미납 회원목록</b></font><br>
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

	<tr>
	<c:choose>
			<c:when test="${empty NotReturnMembers}">
				<tr>
					<td colspan="8" width = "300px" height = "300px">
							<font size="20"><b>미납한 회원이 없습니다.</b></font>
					</td>
				</tr>
			</c:when>
			<c:when test="${not empty NotReturnMembers}">
				<c:forEach var = "nmember" items = "${NotReturnMembers}">
				<tr>
					<td width="60px" height="55px"><img alt="프로필사진" src="img/${nmember.n_profile}" width="60px" height="60px"></td>
					<td><a href = "GoToMemberRentalBookInfo.do?n_no=${nmember.n_no}&name=${nmember.n_name}&lend=${nmember.n_lend}">${nmember.n_name}</a></td>
					<td>${nmember.n_nick}</td>
					<td>${nmember.n_Email}</td>
					<td>
						<fmt:parseDate var="parsedDate2" value="${nmember.n_regdate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
						<fmt:formatDate value="${parsedDate2}" pattern = "yyyy년 MM월 dd일 HH시 mm분"/>
					</td>
					<td>${nmember.n_phone1}</td>
					<td>${nmember.n_phone2}</td>
					<td>${nmember.n_lend}</td>
				</tr>
				</c:forEach>
			</c:when>
	</c:choose>
	</tr>
</table>

</td>
<%@ include file = "../bottom.jsp"%>