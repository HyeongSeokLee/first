<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript">
	function chk() {
		if (!inForm.password.value) {
			alert('비밀번호 입력란을 입력하세요.');
			inForm.password.focus();
			return false;
		}
		if (!inForm.password2.value) {
			alert('비밀번호확인 입력란을 입력하세요.');
			inForm.password.focus();
			return false;
		}
		if (inForm.password.value != inForm.password2.value) {
			alert('비밀번호와 비밀번호확인이 다릅니다.');
			inForm.password.value = "";
			inForm.password2.value = "";
			inForm.password.focus();
			return false;
		}
		return true;
	}
	
	function idChk(id) {
		width = 500;
		height = 250;
		sx = screen.width;
		sy = screen.height;
		x = (sx - width) / 3;
		y = (sy - height) / 4;
		if (!id) {
			alert("아이디를 입력하세요");
			inForm.id.focus();
		} else {
			url = "id_chk.do?id=" + id;
			wr = window.open(url, "아이디 중복체크", "width=500,height=250");
			wr.moveTo(x, y);
		}
	}
	
	function nickChk(nick) {
		width = 500;
		height = 250;
		sx = screen.width;
		sy = screen.height;
		x = (sx - width) / 3;
		y = (sy - height) / 4;
		if (!nick) {
			alert("닉네임 입력하세요");
			inForm.m_nick.focus();
		} else {
			url = "nick_chk.do?nick=" + nick;
			wr = window.open(url, "닉네임 중복체크", "width=500,height=250");
			wr.moveTo(x, y);
		}
	}
	function mailChk(email){
		width = 500;
		height = 250;
		sx = screen.width;
		sy = screen.height;
		x = (sx - width) / 3;
		y = (sy - height) / 4;
		if (!email) {
			alert("이메일을 입력하세요");
			inForm.m_email.focus();
		} else {
			url = "emailCheck.do?email=" + email;
			wr = window.open(url, "이메일 인증", "width=500,height=250");
			wr.moveTo(x, y);
		}
	}
</script>
</head>
<body>
	<jsp:include page="../Da/head_1.html" />
	<center>
		<h2>회원가입</h2>
	</center>
	<form action="insertPro.do" name="inForm" onsubmit="return chk()">
		<table align="center" bgcolor="#0096FF">
			<tr>
				<th><font color="white">아이디</font></th>
				<td>
					<input type="text" name="m_id">
					<input type="button" value="ID중복체크"	onclick="javascript:idChk(inForm.m_id.value)">
				</td>
			</tr>
			<tr>
				<th><font color="white">비밀번호</font></th>
				<td><input type="password" name="m_pw"></td>
			</tr>
			<tr>
				<th><font color="white">비밀번호확인</font></th>
				<td><input type="password" name="m_pw2"></td>
			</tr>
			<tr>
				<th><font color="white">닉네임</font></th>
				<td>
					<input type="text" name="m_nick" required>
					<input type="button" value="중복체크" onclick="javascript:nickChk(inForm.m_nick.value)">
				</td>
			</tr>
			<tr>
			
				<th><font color="white">이메일</font></th>
				<td><input type="email" name="m_email" value="${email }" required>
				<input type="button" value="이메일 인증" onclick="javascript:mailChk(inForm.m_email.value)">
				</td>
			</tr>
			<tr>
			<td>&nbsp;</td>
			<td><c:if test="${email_auth eq 'Y'}">
					인증완료
				</c:if>
			</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="회원가입"> <input type="reset" value="다시 작성">
				</td>
		</table>
	</form>
</body>
</html>