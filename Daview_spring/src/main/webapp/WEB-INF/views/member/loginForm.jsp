<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<jsp:include page="head_1.jsp"/>
<center>
		<h2>로그인</h2>
</center>
	<form name="LoginForm" action="loginPro.do" method="post">
		<table border=1 align="center" bgcolor="#0096FF">
			<tr><th><font color="white">이메일</font></th><td><input type="email" name="m_email" required></td></tr>
			<tr><th><font color="white">비밀번호</font></th><td><input type="password" name="m_pw" required></td></tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="로그인" >
					<input type="button" value="회원가입" onclick="location.href='insertMember.do'">
				</td>
			</tr>
	</table>
	</form>
</body>
</html>