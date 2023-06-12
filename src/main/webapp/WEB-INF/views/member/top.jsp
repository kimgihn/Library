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
<script type="text/javascript">
	function checkMemDel(){
		var MemberDel = window.confirm("정말 회원 탈퇴를 하시겠습니까?")
		if(MemberDel){
			document.MemDel.submit()
		}
	}
</script>

<form name = "MemDel" action="DeleteMember.do" method = "post"></form>


<div  style = "border: solid black; width : 950px; height : 800px; margin: 0 auto;" >

	<h2 align = "center">도서 대여 프로그램</h3>
	<c:choose>
	<c:when test="${empty member}">
		<div align = "center">저의 사이트에 회원가입을 해주셔서 감사합니다.</div>
	</c:when>
	<c:when test="${not empty member}">
		<div align = "center">
		<img alt="프로필" src="img/${member.n_profile}" width="40px" height="30px">&nbsp;
		${member.n_nick} 님 환영합니다.
		</div>
	</c:when>
	</c:choose>
	<table border = "1" style = "width :900px; height : 600px; margin: 0 auto;">
		<c:choose>
		<c:when test="${empty member}">
			<tr>
				<th>
					<a href = "<c:url value = "/"/>">로그인</a>
				</th>
				<th>
					<a href="bookList.do">도서목록</a>
				</th>
			</tr>
		</c:when>
		<c:when test="${not empty member}">
			<tr bgcolor="orange">
				<th>
					<a href="bookList.do">도서목록</a>
				</th>
				<th>
					<a href="GotoMyselfInfo.do">내정보</a>
				</th>
				<th>
					<a href="javascript:checkMemDel()">회원탈퇴</a>
				</th>
				<th><a href = "GotoLendBook.do">빌린 도서 정보</a></th>
			</tr>
		</c:when>
		</c:choose>
		<tr>
