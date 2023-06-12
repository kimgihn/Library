<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "top.jsp" %>    

<script type="text/javascript">
	function r_click(){
		document.f.action = "GoToRegist.do"
	}
	function m_login(){
		document.f.action = "GoToLogin.do"
	}
	function n_searchID(){
		var IsSearchAlt = window.confirm("관리자는 아이디/비밀번호찾기를 하실수없습니다. 관리자는 개발자에게 문의해주세요!! 개발자 전화번호:010-2477-9026")
		if(IsSearchAlt){
			window.open("searchID.do","","width=620, height=400")
			document.f.action = "main.do";
			document.f.method = "get"
	        document.f.submit();
		}else{
			document.f.action = "main.do";
			document.f.method = "get"
	        document.f.submit();
		}	
	}
	function n_searchPW(){
		window.open("searchPW.do","","width=620, height=400")
		document.f.action = "main.do";
		document.f.method = "get"
        document.f.submit();
	}	
</script>
		
<td colspan="3" width="500px" height ="500px" align = "center">	
<br>
<p>

<img src="LoginImg/tm_login.gif" width=100 height="13" border="0" ALT="회원 로그인">

<form name="f" method="post">
	<table width="60%" align="center" height="120">
		<tr>
			<td align="right" width="30%">
				<img src="LoginImg/id01.gif"width="28" height="11" border="0" alt="아이디">&nbsp;&nbsp;
			</td>
			<td width="40%">

				<input type="text" name="id" placeholder = "아이디" value = "${remember}">			
			</td>
			<td rowspan="2" width="30%" valign="middle">
				
					<button onclick = "m_login()" style = "border : 0; background-color: transparent;"><img src="LoginImg/bt_login.gif" border="0" alt="로그인"  tabindex="3"></button>&nbsp;&nbsp;<br>
				
				<nobr>
					<input type="checkbox" name="saveId">
					<font face="굴림" size="2">아이디 기억하기</font>
				</nobr>
			</td>
		</tr>
		<tr>
			<td align="right">
				<img src="LoginImg/pwd.gif" width="37" height="11" alt="비밀번호">
			</td>
			<td>
				<input type="password" name="pw" placeholder = "비밀번호">
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<button onclick="r_click()" style = "border :0; background-color: transparent;"><img src="LoginImg/bt_join.gif" width="60" height="22" alt="회원가입"></button>
 				<button onclick="n_searchID()" style = "border: 0; background-color: transparent;"><img src="LoginImg/bt_search_id.gif" width="60" height="22" alt="아이디 찾기"></button>
					<button onclick = "n_searchPW()" style = "border:0; background-color: transparent;"><img src="LoginImg/bt_search_pw.gif" width="60" height="22" alt="비밀번호 찾기"></button>		
			</td>
		</tr>
	</table>
	<br>
	<img alt="관리자 로고" src="LoginImg/m_Logo.png" width = "40" height = "40"><br>
	<input type = "password" name = "m_code"><br>
	<p><font  size = "2" color = "red">관리자 회원가입을 원하시거나 관리자로 로그인을 하고싶으시면 <br>개발자에게 받은 특정 코드를 입력하시고 실행해주세요</font></p>
</form>
</td>			

<%@ include file = "bottom.jsp" %>    




