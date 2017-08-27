<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>

</head>
<body>
	<form action="/member/deletePro" method="post">
		<input type="hidden" name="m_email" value="${m_email}">
		비밀번호<input type="password" name="m_pw">
		<input type="submit" value="삭제">
		<input type="button" value="취소" onclick="window.close()">
	</form>
</body>
</html>