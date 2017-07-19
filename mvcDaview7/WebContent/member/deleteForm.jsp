<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>


<c:if test="${id==null}">
<script>
alert('로그인 후 이용하세요!');
window.location.href='loginForm.do';
</script>
</c:if>
<script>
function chk(){
	var ch = confirm('삭제하시겠습니까?');
	 if(ch==false)
	 return false;
	 else
     return true;
}
</script>
</head>
<body>
	<jsp:include page="../Da/head_1.html"/>
	<center>
		<h2>회원탈퇴</h2>
	</center>
<form action="deletePro.do" method="post" onsubmit="return chk()">
<input type="hidden" name="id" value="${id}">
<table border=1 align="center" bgcolor="#0096FF">
	<tr><th><font color="white">비밀번호</font></th>
	<td><input type="password" name="password" required></td></tr>
	
<tr><td colspan=2 align="center"><input type="submit" value="확인">
<input type="reset" value="다시입력">
<input type="button" value="취소" 
        onclick="javascript:location.href='main.do'"></td></tr>
</table>
</form>
</body>
</html>