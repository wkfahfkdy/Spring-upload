<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<div align = "center">
		<div><h1>로그인</h1></div>
		<div>
			<form action = "loginConfirm.do" method = "post">
				<div>
					<table border = "1">
						 <tr>
						 	<th width = "150">이메일</th>
						 	<td width = "300">
							 	<input type = "text" id = "email" name = "email">
						 	</td>
						 </tr>
						 <tr>
						 	<th width = "150">비밀번호</th>
						 	<td width = "300">
							 	<input type = "password" id = "password" name = "password">
						 	</td>
						 </tr>
						 <tr>
						 	<td colspan = "2" style = "text-align : center;">
								<input type = "submit" value ="로그인">
						 	</td>
						 </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>