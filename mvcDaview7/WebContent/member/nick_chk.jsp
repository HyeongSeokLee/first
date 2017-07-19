<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${result ==1}">
		<script>
			opener.document.forms[0].id.value = '';
			alert('이미 존재하는 닉네임입니다.');
			window.close();
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert('${dbnick}(은)는 사용 가능합니다.');
			window.close();
		</script>
	</c:otherwise>
</c:choose>
