<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 대여 사이트</title>
</head>
<body>
<div  style = "border: solid black; width : 950px; height : 800px; margin: 0 auto;" >

	<h2 align = "center">도서 대여 프로그램</h3>
	<div align = "center">환영 합니다.</div>
	<table border = "2" style = "width :900px; height : 700px; margin: 0 auto;">
		<tr>
			<th>
				<a href = "<c:url value = "/"/>">로그인</a>
			</th>
			<th>
				<a href="<c:url value = "/bookList.do"/>">도서목록</a>
			</th>
		</tr>
		<tr>
