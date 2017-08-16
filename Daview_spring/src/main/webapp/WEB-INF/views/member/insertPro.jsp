<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${result>0}">
	<script>
		alert('회원가입에 성공하였습니다.');
		location.href = '../board/main.do';
	</script>
</c:if>

<c:if test="${result<=0}">
	<script>
		alert('회원가입에 실패하였습니다.');
		history.back();
	</script>
</c:if>