<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>


<script>
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
			url = "/member/nickCheck?m_nick=" + nick;
			wr = window.open(url, "닉네임 중복체크", "width=500,height=250");
			wr.moveTo(x, y);
		}
	}
	function del(email) {
		width = 500;
		height = 250;
		sx = screen.width;
		sy = screen.height;
		x = (sx - width) / 3;
		y = (sy - height) / 4;
		url = "/member/deleteForm?m_email=" + email;
		wr = window.open(url, "삭제 확인", "width=500,height=250");
		wr.moveTo(x, y);
		}
	
	
	function chk() {
		if (modifyForm.m_pw.value != modifyForm.m_pw2.value) {
			alert('비밀번호와 비밀번호확인값이 다릅니다.');
			modifyForm.m_pw.focus();
			modifyForm.m_pw.value = "";
			modifyForm.m_pw2.value = "";
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<jsp:include page="../head/head_1.jsp"/>
	<center>
		<h2>회원정보 수정</h2>
	</center>
	<form name="modifyForm" action="/member/memberModifyPro" onsubmit="return chk()" method="post">
		<table border=1 align="center" bgcolor="#0096FF">
			<tr>
				<th><font color="white">이메일</font></th>
				<td><input type="text" name="m_email" value="${m_email}" readonly="readonly"></td>
			</tr>
			<tr>
				<th><font color="white">비밀번호</font></th>
				<td><input type="password" name="m_pw" required></td>
			</tr>
			<tr>
				<th><font color="white">비밀번호확인</font></th>
				<td><input type="password" name="m_pw2" required></td>
			</tr>
			<tr>
				<th><font color="white">닉네임</font></th>
				<td><input type="text" name="m_nick" value="${m_nick}" required>
					<input type="button" value="중복체크" onclick="javascript:nickChk(modifyForm.m_nick.value)"></td>
			</tr>
			
			<tr>
				<td colspan="4" align="center"><input type="submit" value="수정">
					<input type="button" value="취소" onclick="location.href='/main'">
					<input type="button" value="탈퇴" onclick="del(modifyForm.m_email.value)"></td>
			</tr>
		</table>
	</form>
</body>
</html>