<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원 비밀번호</title>
</head>
<body>
<div align = "center">
<c:if test="${empty notFound}">
<font size = "7px"><b>${name}</b>님 의 비밀번호는 <b>${password}</b> 입니다.</font>
 </c:if>
 <c:if test="${not empty notFound}">
 	<font size = "7px">${notFound}</font>
 </c:if>
</div>
</body>
</html>