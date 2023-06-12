<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서대여 프로그램</title>
</head>
<body>
<div  style = "border: solid black; width : 950px; height : 800px; margin: 0 auto;" >

	<c:choose>
	<c:when test="${not empty mgr}">
	<h2 align = "center">도서 대여 프로그램(관리자)</h2>
		<div align = "center" style = "color:red;"><b> 관리자 ${mgr.m_nick} 님 환영합니다. | 현재 대여한 도서는 ${mgr.r_count}개 </b></div>
		<table border = "1" style = "width :900px; height : 700px; margin: 0 auto;">
			<tr bgcolor="#85BEFF">
				<th>
				<a href = "GoToMemberList.do">전체회원목록</a>
				</th>
				<th>
				<a href = "GoToRentalMemberList.do">도서대여 회원보기</a>
				</th>
				<th>
				<a href = "GoToNotReturnMemberList.do">미납 회원보기</a>
				</th>
				<th>
					<a href="RegistBook.do">도서등록</a>
				</th>
				<th>
					<a href="bookList.do">도서목록</a>
				</th>
			</tr>
			<tr>
	</c:when>
	
	<c:when test="${empty mgr}">
	<h2 align = "center">도서 대여 프로그램</h2>
		<div align = "center">관리 잘하시길 빌겠습니다.</div>
		<table border = "1" style = "width :900px; height : 700px; margin: 0 auto;">
			<tr>
				<th>관리자 회원가입</th>
			</tr>
			<tr>
	</c:when>
	</c:choose>	