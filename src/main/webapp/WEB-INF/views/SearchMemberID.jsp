<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반회원 아이디 찾기</title>
</head>
<body>
<div align="center">
		<hr color="green" width="300">
		<h2>아 이 디 찾 기</h2>
		<hr color="green" width="300">
		<form name="f" method="post">
			<table border="1" width="500">
				<tr>
					<th>이름</th>
					<td><input type="text" name="n_name"></td>
				</tr>			
				<tr>
					<th>주민번호</th>
					<td><input type="text" name="n_ssn1"> -
					<input type="password" name="n_ssn2"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="조회">
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>