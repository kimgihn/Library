<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원 아이디</title>
</head>
<body>

<div align = "center">
<c:if test="${not empty SearchMember}">
 	<font size = "7px"><b>${SearchMember.n_name}</b>님 의 아이디는 <b>${SearchMember.n_id}</b> 입니다.</font>
 </c:if>
 <c:if test="${empty SearchMember}">
 	<font size = "7px">${notFound}</font>
 </c:if>
</div>

</body>
</html>
