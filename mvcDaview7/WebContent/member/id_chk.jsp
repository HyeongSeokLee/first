<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${result==1}">
		<script>
			opener.document.forms[0].id.value = '';
			alert('이미 존재하는 아이디입니다.');
			self.close();
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert('${dbid}은(는) 사용 가능합니다.');
			self.close();
		</script>
	</c:otherwise>
</c:choose>


