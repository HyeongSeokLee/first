<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${isTrue==1 }">
	<c:if test="${check>0}">
		<script>
			alert('게시글 수정완료 하였습니다.');
			location.href = 'main.do?pageNum=${pageNum}';
		</script>
	</c:if>
</c:if>
<c:if test="${isTrue!=1}">
	<script>
		alert('패스워드가 틀립니다.');
		history.back();
	</script>
</c:if>
