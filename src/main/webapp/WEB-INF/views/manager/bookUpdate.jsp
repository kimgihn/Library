<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<%@ include file = "top.jsp"%>
<td align = "center" colspan = "5">

<form:form action="updateBook.do" method = "post" modelAttribute = "updateBook" enctype="multipart/form-data">
	<table border="1" width="500">
		<tr>
			<th colspan = "6" bgcolor="sky-blue" style = "color:blue;">도서 수정</th>
		</tr>
		<tr>
			<th class="m2">도서명</th>
			<td align="left">
			<form:input path="b_name" placeholder = "도서명"/>
			<form:input type = "hidden" path="b_no" value = "${no}"/>
			</td>
		</tr>
		
		<tr>
			<th class="m2">도서코드</th>
			<td align="left"><form:input path="b_code" placeholder = "도서코드"/></td>
		</tr>
		
		<tr>
			<th class="m2">도서이미지</th>
			<td align="left">
				<img src="img/${bimage2}" width="40" height="40">	
				<input type="file" name="bimage">
				<input type="hidden" name="bimage2" value="${bimage2}"/>
			</td>
		</tr>
		<tr>
			<th class="m2">도서수량</th>
			<td align="left"><form:input path="b_amount" placeholder = "수량"/>개</td>
		</tr>
		<tr>
			<th class="m2">랜탈가격</th>
			<td align="left"><form:input path="b_price" placeholder = "가격"/></td>
		</tr>
		
		<tr>
			<th class="m2">도서 장르</th>
			<td align="left"><form:input path="b_intro" placeholder = "장르"/></td>
		</tr>
		
		<tr>
			<th class = "m2">도서 소개</th>
			<td align="left"><form:textarea path="b_content" placeholder = "도서 소개"/></td>
		</tr>
		
		<tr>
			<td colspan="2" class="m1" align = "center">
				<input type="submit" value="도서수정">
				<input type="reset" value="취소">
			</td>
		</tr>			
	</table>
</form:form>

</td>
<%@ include file = "../bottom.jsp"%>