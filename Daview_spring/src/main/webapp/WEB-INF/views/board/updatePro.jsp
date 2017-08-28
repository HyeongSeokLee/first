<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${result==1 }">

	<script>
		alert('게시글 수정완료 하였습니다.');
		location.href = '/main';
	</script>

</c:if>
<c:if test="${result!=1}">
	<script>
		alert('수정 오류');
		history.back();
	</script>
</c:if>