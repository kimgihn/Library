<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<%@ include file = "top.jsp"%>
<td align = "center" colspan = "5">

<form:form method="post" enctype="multipart/form-data" modelAttribute="book">
	<table border="1" width="400">
		<tr>
			<th bgcolor="orange" colspan="5">Regist Book</th>
		</tr>
		<tr>
			<th class="m2">도서명</th>
			<td align="left"><form:input path="b_name"/></td>
		</tr>
		<tr>
			<th class="m2">도서코드</th>
			<td align="left"><form:input path="b_code"/></td>
		</tr>
		<tr>
			<th class="m2">도서가격</th>
			<td align="left"><form:input path="b_price"/></td>
		</tr>
		<tr>
			<th class="m2">도서 이미지</th>
			<td align="left"><input type="file" name="file"/></td>
		</tr>
		<tr>
			<th class="m2">지은이</th>
			<td align="left"><form:input path="b_writer"/></td>
		</tr>
		<tr>
			<th class="m2">수량</th>
			<td align="left"><form:input path="b_amount"/> 개</td>
		</tr>
		<tr>
			<th class="m2">책 종류</th>
			<td align="left"><form:input path="b_intro"/></td>
		</tr>
		<tr>
			<th class = "m2">도서 소개</th>
			<td align="left"><form:textarea path="b_content"/></td>
		</tr>
		
		<tr>
			<td colspan="2" class="m1" align = "center">
				<form:button type="submit" value="도서등록">도서등록</form:button>
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>
</form:form><br>
<a href ="m_main.do"><button>메인으로</button></a>
</td>
<%@ include file = "../bottom.jsp"%>