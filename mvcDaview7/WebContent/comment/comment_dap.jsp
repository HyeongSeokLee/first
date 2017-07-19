<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty id }">
	<script>
		alert('로그인 후 이용하세요!');
		window.close();
	</script>
</c:if>

<!DOCTYPE html>
<script type="text/javascript">
window.onload = function(){  //윈도우 창이 열리면 실행 
var form = document.frm;   
form.c_content.focus(); //폼에 있는 textarea에 커서 이동시키기
}
</script>
<html>
<head>
<meta charset="UTF-8">
<title>Daview</title>
</head>
<body>
<form action="commentPro2.do" method="post" name="frm">
	<table align="center">
	<input type = "hidden" name = "c_num" value = ${c_num }>
	<input type = "hidden" name = "b_num" value = ${b_num }>
	<input type = "hidden" name = "m_id" value = ${m_id }>
	<input type="hidden" name="c_ref" value=${c_ref }>
	<input type="hidden" name="c_re_step" value=${c_re_step }>
	<input type="hidden" name="c_re_level" value=${c_re_level }>
	<input type="hidden" name="pageNum" value=${pageNum }>
		<tr>
			<td text-align="center" width="100px">
			${c_nick }</td>
			<td><textarea name="c_content" rows="4" cols="70"></textarea></td>
			<td>
			 	<input type="submit" value="댓글등록" style="height:60px;">
			</td>
		</tr>
 
	</table>
</form>
</body>
</html>