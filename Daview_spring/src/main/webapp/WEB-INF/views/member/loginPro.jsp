<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${result==1 }">
<script>
     	location.href='../main.do';
</script>     	
</c:if>
<c:if test="${result==2 }">
	<script>
		alert('암호가 틀립니다.');
		history.back();
	</script>
</c:if>
<c:if test="${result==0 }">
	<script>
		alert('회원이 아닙니다.');
		history.go(-1);
	</script>
</c:if>
