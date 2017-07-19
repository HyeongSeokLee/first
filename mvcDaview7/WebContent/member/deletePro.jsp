<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty id}">
	<script>
		alert('로그인 후 이용하세요!');
		window.location.href = 'loginForm.do';
	</script>
</c:if>
<c:if test="${result1==0 }">
	<script>
		alert('비밀번호가 틀립니다.');
		history.go(-1);

	</script>
</c:if>
<c:if test="${result2==1}">
	<script>
		alert('탈퇴하였습니다.');
		location.href = 'main.do';
	</script>
</c:if>
<c:if test="${result2!=1}">
	<script>
		alert('탈퇴 실패하였습니다.');
		history.back();
	</script>
</c:if>
