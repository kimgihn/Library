<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
<div align="center">
		<hr color="green" width="300">
		<h2>비 밀 번 호 찾 기</h2>
		<hr color="green" width="300">
		<form name="f" method="post">
			<table border="1" width="350" align = "center">
				<tr>
					<th>이름</th>
					<td><input type="text" name="n_name"></td>
				</tr>			
				<tr>
					<th>아이디</th>
					<td><input type="text" name="n_id">
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