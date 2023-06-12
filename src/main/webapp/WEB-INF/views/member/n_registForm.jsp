<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

    
<%@ include file = "top.jsp"%>
<td align = "center" colspan = "5">
		<form:form  modelAttribute = "cmd" enctype="multipart/form-data">
			<table width="500" style = "text-align: center;" class="outline" border = "1">
  				<tr>
					<td colspan="2" align=center class="m2" bgcolor="lime"><b>일반회원가입</b></td>
 				</tr>
				<tr>
					<td width="150" class="m3">이름</td>
					<td class="m3" align = "left">
						<form:input  path="n_name" placeholder = "이름" class = "box" type = "text" />
						<form:input path="n_lend" type = "hidden" value = "안빌림"/>
					</td>
				</tr>
				
				<tr>
					<td width="150" class="m3">닉네임</td>
					<td class="m3" align = "left">
						<form:input path="n_nick" placeholder = "닉네임" class = "box" type = "text"/>
					</td>
				</tr>
				<tr>
					<td width="150" class="m3">프로필</td>
					<td class="m3" align = "left">
						<input type = "file" name = "profile">
					</td>
				
				</tr>
				<tr>
					<td width="150" class="m3">아이디</td>
					<td class="m3" align = "left">
						<form:input path="n_id" placeholder = "아이디" class = "box" type = "text"/>
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">비밀번호</td>
					<td class="m3" align = "left">
						<form:input path="n_password" placeholder = "비밀번호" class = "box" type = "password"/>
					</td>
				<tr>
					<td width="150" class="m3">비밀번호 확인</td>
					<td class="m3" align = "left">
						<form:input path="n_password2" placeholder = "비밀번호 확인" class = "box" type = "password"/>
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">주민등록번호</td>
					<td class="m3" align = "left">
						<form:input path="n_ssn1" placeholder = "주민번호 앞자리" type = "text"/> - <form:input path="n_ssn2" placeholder = "주민번호 뒷자리" type = "password"/>
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">이메일</td>
					<td class="m3" align = "left">
						<form:input path="n_Email" type = "text" placeholder = "이메일" style="width: 200px;"/>
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">핸드폰 번호</td>
					<td class="m3" align = "left">
						<form:input path="n_phone1" placeholder = "-를 붙여주세요" type = "text"/>
					</td>
  				</tr>
  		
  				<tr>
					<td width="150" class="m3">집 전화번호</td>
					<td class="m3" align = "left">
						<form:input path="n_phone2" placeholder = "-를 붙여주세요" type = "text"/>
					</td>
  				</tr>
  				<tr>
					<td colspan="2" align="center">
						<form:button type="submit">회원가입</form:button>&nbsp;
						<form:button type = "reset">다시작성</form:button>
					</td>
  				</tr>
  			</table>
		</form:form>
<%@ include file = "../bottom.jsp"%>