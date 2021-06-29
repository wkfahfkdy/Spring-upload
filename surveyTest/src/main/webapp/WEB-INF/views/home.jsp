<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<div><h1>HOME</h1></div>
		<c:if test = "${!empty email}">
			<div>
				<h1>${member.email } ${message }</h1>
			</div>
		</c:if>
		<div>
			<a href="survey">설문</a> <br>
			<a href="memberList.do?state=Y">회원목록</a> <br>
			<a href="member/memberInsert">회원가입</a> <br>
			
		</div>
	</div>
</body>
</html>