<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
    
<%@ include file = "top.jsp"%>
	<td align = "center" colspan = "5">
		<br>
		<img alt="프로필" src="img/${member.n_profile}" width="150px" height="150px">
		<div align = "left" style = "width : 230px; height: 350px;">
		<p>이름 : ${member.n_name} &nbsp;&nbsp; <a href = "memberUpdate.do"><b>정보 수정</b></a></p> 
		<p>주민번호 : ${member.n_ssn1} - ${member.n_ssn2}
		<p>이메일  : ${member.n_Email}</p>
		<p>닉네임 : ${member.n_nick}</p>
		<p>아이디 : ${member.n_id}</p>
		<p>페스워드 : ${member.n_password}</p>
		<p>가입일 : 
				<fmt:parseDate var="parsedDate" value="${member.n_regdate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
				<fmt:formatDate value="${parsedDate}" pattern = "yyyy년 MM월 dd일 HH시 mm분"/>
		</p>
		<p>핸드폰 번호: ${member.n_phone1}</p>
		<p>집 전화번호 : ${member.n_phone2}</p>
		</div>
		<a href = "n_main.do"><button>메인으로</button></a>
	</td>	
<%@ include file = "../bottom.jsp"%>