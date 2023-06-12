<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  


<c:choose>   
<c:when test="${empty mgr && empty member}">
   <%@ include file="../top.jsp"%>
</c:when>
<c:when test="${not empty mgr}">
	<%@ include file = "../manager/top.jsp" %>
</c:when>
<c:when test="${not empty member}">
	<%@ include file = "../member/top.jsp" %>
</c:when>
</c:choose> 

<script type="text/javascript">


	function checkDel(b_no, b_imgName){
		var isDel = window.confirm("정말로 삭제하시겠습니까?")
		if (isDel){
			document.del.b_no.value = b_no
			document.del.b_imgName.value = b_imgName
			document.del.submit()
		}
	}
	function checkLend(b_no, b_name){
		var isLen = window.confirm("대여하고 반납하고싶으시면 반납버튼을 꼭 눌러준다음에 반납해주세요!!!!")
		if(isLen){
			document.len.b_no.value = b_no
			document.len.b_name.value= b_name
			document.len.submit()
		}
	}
</script>

<c:if test = "${not empty alt}">
<<script type="text/javascript">
	alert("${alt}")
</script>
</c:if>

<td align = "center" colspan = "5">
<div align="center">

	<div style="text-align: right;">
	  <form action="search.do" method="get">
	    <input type="text" name="word" placeholder="제목">
	    <input type="submit" value="검색">
	  </form>
	</div>
									
	<font size="4" color="green"><b>도서목록</b></font><br>
	<table border="1" width="100%" style = "text-align:center;">
		<tr bgcolor="#00FFC0">
			<th>도서</th>
			<th>도서코드</th>
			<th>도서명</th>
			<th>랜탈 가격</th>
			<th>지은이</th>
			<th>도서등록일</th>
			<th>수량</th>
			<th>도서종류</th>
		<c:if test="${not empty mgr}">	
			<th>수정|삭제</th>
		</c:if>	
		<c:if test="${not empty member}">
			<th>빌리기</th>
		</c:if>
		</tr>
<c:choose>		
	<c:when test="${empty bookList && empty searchList}">
		<tr>
			<td colspan="9">등록된 도서가 없습니다.</td>
		</tr>
	</c:when>
	<c:when test = "${not empty bookList}">
		<c:forEach var="book" items="${bookList}">
			<tr>
				<td width="60px" height="55px"><img alt="책사진" src="img/${book.b_imgName}" width="60px" height="55px"></td>
				<td>${book.b_code}</td>
				<td><a href = "<c:url value = "/bookDetail.do"/>?b_no=${book.b_no}"><font size="2px">${book.b_name}</font></a></td>
				<td>${book.b_price}</td>
				<td>${book.b_writer}</td>
				<td>
					<fmt:parseDate var="parsedDate" value="${book.b_regdate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
					<fmt:formatDate value="${parsedDate}" pattern = "yyyy년 MM월 dd일 HH시 mm분"/>
				</td>
				<td>${book.b_amount}개</td>
				<td>${book.b_intro}</td>
			<c:if test="${not empty mgr}">
				<td>	
				<a href="updateBook.do?no=${book.b_no}&bimage2=${book.b_imgName}">수정</a> |
				<a href="javascript:checkDel('${book.b_no}','${book.b_imgName}')">삭제</a>
				</td>
			</c:if>
			   <c:if test="${not empty member}">
			            <td>
			               <a href="javascript:checkLend('${book.b_no}','${book.b_name}')">대여</a> |
			               <a href="returnBook.do?bookNo=${book.b_no}">반납</a>
			            </td>
			   </c:if>
			</tr>
		</c:forEach>
	</c:when>
	<c:when test="${not empty searchList}">
		<c:forEach var="sbook" items="${searchList}">
			<tr>
				<td width="60px" height="55px"><img alt="책사진" src="img/${sbook.b_imgName}" width="60px" height="55px"></td>
				<td>${sbook.b_code}</td>
				<td><a href = "<c:url value = "/bookDetail.do"/>?b_no=${sbook.b_no}">${sbook.b_name}</a></td>
				<td>${sbook.b_price}</td>
				<td>${sbook.b_writer}</td>
				<td>
					<fmt:parseDate var="parsedDate" value="${sbook.b_regdate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
					<fmt:formatDate value="${parsedDate}" pattern = "yyyy년 MM월 dd일 HH시 mm분"/>
				</td>
				<td>${sbook.b_amount}개</td>
				<td>${sbook.b_intro}</td>
				<c:if test="${not empty mgr}">
					<td>	
					<a href="updateBook.do?no=${sbook.b_no}&bimage2=${sbook.b_imgName}">수정</a> |
					<a href="javascript:checkDel('${sbook.b_no}','${sbook.b_imgName}')">삭제</a>
					</td>
				</c:if>	
				
			<c:if test="${not empty member}">
			            <td>
			               <a href="javascript:checkLend('${sbook.b_no}','${sbook.b_name}')">대여</a>|
			               <a href="returnBook.do?BookNo=${sbook.b_no}">반납</a>
			            </td>
			   </c:if>
			</tr>
		</c:forEach>
	</c:when>
	
</c:choose>			
	</table>
</div>

<form name="del" action="deleteBook.do" method="post">
	<input type="hidden" name="b_no">
	<input type="hidden" name="b_imgName">
</form>

<form name = "len" action = "rental.do" method = "post">
	<input type = "hidden" name = "b_no">
	<input type = "hidden" name = "b_name">
</form>


<!-- 페이징 -->
<div align = "center">

<c:if test="${startPageGroup > 1}">
			<a href="${url}pageNo=1"> <span>[처음]</span> </a>
		</c:if>
		<c:if test="${startPageGroup > 1}">
			<a href="${url}pageNo=${startPageGroup - 1}"> <span>&laquo;</span> </a>
		</c:if>
		<c:forEach begin="${startPageGroup}" end="${endPageGroup}" step="1" var="i">
			<a href="${url}pageNo=${i}"> 
				<c:choose>
					<c:when test="${pageNo == i}"> <b>[${i}].</b> </c:when>
					<c:otherwise> ${i}. </c:otherwise>
				</c:choose>
			</a>
		</c:forEach>
		<c:if test="${endPageGroup < pageCount}">
			<a href="${url}pageNo=${endPageGroup + 1}"> <span>&raquo;</span> </a>
		</c:if>
		<c:if test="${endPageGroup < pageCount}">
			<a href="${url}pageNo=${pageCount}"> <span>[끝]</span> </a>
		</c:if>
		&nbsp;&nbsp;
	<c:choose>
		<c:when test="${not empty mgr}">
			<a href = "<c:url value = "m_main.do"/>"><button>메인으로</button></a>
		</c:when>
		<c:when test = "${empty mgr && empty member}">
			<a href = "<c:url value = "/"/>"><button>메인으로</button></a>	
		</c:when>
		<c:when test="${not empty member}">
			<a href = "n_main.do"><button>메인으로</button></a>
		</c:when>
	</c:choose>		
</div>
</td>
<%@ include file = "../bottom.jsp"%>