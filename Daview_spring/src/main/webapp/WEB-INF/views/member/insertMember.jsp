<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	function nickChk(m_nick) {
		width = 500;
		height = 250;
		sx = screen.width;
		sy = screen.height;
		x = (sx - width) / 3;
		y = (sy - height) / 4;
		if (!m_nick) {
			alert("닉네임 입력하세요");
			inForm.m_nick.focus();
		} else {
			url = "nickCheck.do?m_nick="+ m_nick;
			wr = window.open(url, "닉네임 중복체크", "width=500,height=250");
			wr.moveTo(x, y);
		}
	}
	//인증코드를 입력할 팝업창 생성
	function mailChk(email,address){
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
			url = "emailCheck.do?email=" + email+"&address="+address;
			wr = window.open(url, "이메일 인증", "width=500,height=250");
			wr.moveTo(x, y);
		}
	}
	
	function addressView(val){
		var frm = document.inForm;
		frm.address.value=val;
	}
</script>
</head>
<body>
	<jsp:include page="head_1.jsp" />
	<center>
		<h2>회원가입</h2>
	</center>
	<form action="insertPro.do" name="inForm" method="post" onsubmit="return chk()">
		<table align="center" bgcolor="#0096FF">
			<tr>
			
				<th><font color="white">이메일</font></th>
				<td><input type="text" name="m_email" value="${email }" required>
				@<input type="text" name="address" value="${address }" required>
				<select name="select_address" onchange="addressView(this.value);">
					<option value="">직접입력</option>
					<option value="naver.com">네이버</option>
					<option value="hanmail.net">다음</option>
					<option value="nate.com">네이트</option>
					<option value="gmail.com">구글(gmail)</option>
				</select>
				
				<input type="button" value="이메일 인증" onclick="javascript:mailChk(inForm.m_email.value,inForm.address.value)">
				</td>
			</tr>
			<tr>
			<tr>
				<td>&nbsp;</td>
				<td><c:if test="${email_auth eq 'Y'}">
					인증완료
				</c:if></td>
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
				<td><input type="text" name="m_nick" required> <input
					type="button" value="중복체크"
					onclick="javascript:nickChk(inForm.m_nick.value)"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="회원가입"> <input type="reset" value="다시 작성"></td>
		</table>
	</form>
</body>
</html>