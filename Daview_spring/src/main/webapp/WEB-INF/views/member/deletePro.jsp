<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${result==0}">
	<script>
		alert('비밀번호가 틀립니다.');
		history.go(-1);

	</script>
</c:if>
<c:if test="${result==1}">
	<script>
		alert('탈퇴하였습니다.');
		opener.parent.location="/main";
		window.close();
	</script>
</c:if>

