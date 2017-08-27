<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<c:if test="${result>0 }">
	<script>
	alert('입력 성공하였습니다.');
	location.href='main.do?pageNum=${pageNum}';
	</script>
</c:if>
<c:if test="${result==0 }">
		<script>
		alert('입력 실패하였습니다.메세지를 확인하세요.');
		location.href='writeForm.do';
		</script>
</c:if>

<%asdasd %>