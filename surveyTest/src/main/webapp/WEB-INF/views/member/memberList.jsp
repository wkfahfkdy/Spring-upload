<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 목록 보기</title>
<script>
	function fileDown(path, file) {
		
		frm.filePath.value = path;
		frm.fileName.value = file;
		frm.submit();
		
	}
</script>
</head>
<body>
	<div align="center">
		<div>
			<h1>회원목록</h1>
		</div>
		<div>
			<form id="frm" name="frm" action="fileDown.do" method="post">
				<table border="1">
					<tr>
						<th width="100">이메일</th>
						<th width="100">이름</th>
						<th width="100">사진</th>
						<th width="100">첨부 파일</th>
					</tr>
					<c:forEach items="${members }" var="member">
						<tr onmouseover="this.style.background='yellow'" onmouseout="this.style.background='white'">
							<td>${member.email }</td>
							<td>${member.name }</td>
							<td>${member.fileName}</td>
							<td align="center">
								<c:if test="${!empty member.fileUuid }">
									<img src="resources/img/file.png" width="20px" height="20px" onclick="fileDown('${member.fileUuid}', '${member.fileName}')">
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<div>
					<input type="hidden" id="filePath" name="filePath">
					<input type="hidden" id="fileName" name="fileName">
				</div>
			</form>
		</div>
	</div>
</body>
</html>