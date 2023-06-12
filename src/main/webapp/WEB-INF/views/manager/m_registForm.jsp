<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
     
<%@ include file = "../top.jsp"%>
<td align = "center" colspan = "2">

<form:form modelAttribute = "cmd">
<table border = "1"width = "60%">
<tr bgcolor="yellow">
<th colspan = "2">관리자 회원가입</th>
</tr>
<tr>
<td align = "left">이름: </td><td><form:input path="m_name" placeholder = "이름"/></td>
</tr>

<tr>
<td align = "left">아이디: </td><td><form:input path="m_id" placeholder = "아이디"/></td>
</tr>

<tr>
<td align = "left">비밀번호: </td><td><form:input type ="password" path="m_password" placeholder = "비밀번호"/></td>
</tr>

<tr>
<td align = "left">비밀번호 확인: </td><td><form:input type = "password" path="m_password2" placeholder = "비밀번호 확인"/></td>
</tr>

<tr>
<td align = "left">닉네임: </td><td><form:input path="m_nick" placeholder = "닉네임"/></td>
</tr>
<tr>
<td colspan = "2" align = "center"><form:button type="submit">회원가입</form:button></td>
</tr>
</table>
</form:form>
</td>

<%@ include file = "../bottom.jsp"%>