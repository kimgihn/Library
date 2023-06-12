<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
  
<%@ include file = "top.jsp"%>
	<td align = "center" colspan = "5">
	
	<form:form modelAttribute = "memberUpdate" method = "post" enctype="multipart/form-data" >
	<table border="1" width="500">
		<tr>
			<th colspan = "6" bgcolor="orange" style = "color:blue;">내정보 수정</th>
		</tr>
		<tr>
			<th class="m2">이름</th>
			<td align="left">
			<form:input type ="text " path = "n_name" placeholder = "이름"/>
			</td>
		</tr>
		
		<tr>
			<th class="m2">주민번호</th>
			<td align="left">
			<form:input  type = "text" path = "n_ssn1" placeholder = "앞자리"/> - <form:input  type = "password" path = "n_ssn2" placeholder = "뒷자리"/>
			</td>
		</tr>
		<tr>
			<th class="m2">프로필사진</th>
			<td align="left">
				<img src="img/${member.n_profile}" width="40" height="40">	
				<input type="file" name="newProfile">
			</td>
		</tr>
		<tr>
			<th class="m2">이메일</th>
			<td align="left">
				<form:input  type = "text" path = "n_Email" placeholder = "이메일" style = "width:200px;"/>
			</td>
		</tr>
		<tr>
			<th class="m2">아이디</th>
			<td align="left">
				<form:input  type = "text" path = "n_id" placeholder = "새로운 아이디"/>
			</td>
		</tr>
		<tr>
			<th class="m2">비밀번호</th>
			<td align="left">
				<form:input  type = "password" path = "n_password" placeholder = "새로운 비밀번호"/>
			</td>
		</tr>
		<tr>
			<th class="m2">핸드폰 번호</th>
			<td align="left">
			<form:input  type = "text" path = "n_phone1" placeholder = "-를 붙여주세요"/>
			</td>
		</tr>
		
		<tr>
			<th class="m2">집 전화번호</th>
			<td align="left">
			<form:input  type = "text" path = "n_phone2" placeholder = "-를 붙여주세요"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" class="m1" align = "center">
				<input type="submit" value="정보 수정"/>
				<input type="reset" value="취소">
			</td>
		</tr>			
	</table>
</form:form><br>
	<a href = "n_main.do"><button>메인으로</button></a>
	
	
	
	
	</td>	
<%@ include file = "../bottom.jsp"%>