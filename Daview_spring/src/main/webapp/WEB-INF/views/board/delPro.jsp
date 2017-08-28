<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${result==1 }">
	<script>
		alert('${b_num}번 글이 삭제되었습니다.');
		location.href = '/main';
	</script>
</c:if>
<c:if test="${result==0 }">
	<script>
		alert('로그인 후 이용');
		history.back();
	</script>
</c:if>

