<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function chk(){
	if(!delForm.b_pw.value){
		alert('비밀번호를 입력하세요');
		delForm.b_pw.focus();
		return false;
	}
	return true;
}
</script>
<title>글 삭제</title>
</head>
<body>
</head><body>
	<jsp:include page="../head/head_1.jsp"/>
	<center>
		<h2>글 삭제</h2>
	</center>
<form action="delPro.do" name="deleteForm" method="post" onsubmit="return chk()">
<table border=1 align="center" bgcolor="#0096FF" width="300">
	<tr height=30><th><font color="white">비밀번호를 입력해주세요</font></th></tr>
	<tr height=30><td align="center"><font color="white">비밀번호 : </font><input type="password" name="b_pw" >
	<input type="hidden" name="b_num" value="${b_num }">
	<input type="hidden" name="pageNum" value="${pageNum }"></td></tr>
	<tr height=30><td align="center">
		<input type="submit" value="글삭제">
		<input type="button" value="글목록" 
			onclick="location.href='main.do?pageNum=${pageNum}'">
		</td></tr>	
		<%asdasd %>
</table>
</form>
</body>
</html>