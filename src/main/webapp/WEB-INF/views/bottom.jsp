<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
	</tr>
	
<c:choose>
<c:when test="${empty mgr && empty member}">
<tr>
			<td colspan = "3" align="center">도서 대여 프로그램 만든이 : 김민석</td>
</tr>
</c:when>
<c:when test="${not empty mgr}">
	<td colspan = "5" align="center" bgcolor = "#85BEFF">
		<form action = "logout.do" method = "post">
			<button type = "submit" style = "border: 0; background-color: transparent;"><img alt="로그아웃" src="img/logout.png" width = "80px" height = "80px"></button>
		</form>	
	</td>
</c:when>

<c:when test="${not empty member}">
<td colspan = "4" align = "center" bgcolor="orange">
	<form action = "logout.do" method = "post">
		<button type = "submit" style = "border: 0; background-color: transparent;"><img alt="로그아웃" src="img/memberLogout.png" width = "80px" height = "80px"></button>
	</form>	
</td> 
</c:when>

</c:choose>
	</table>
</div>

</body>
</html>