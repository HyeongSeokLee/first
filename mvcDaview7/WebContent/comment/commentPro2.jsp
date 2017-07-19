<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty id}">
	<script>
		alert('로그인 후 이용하세요!');
		history.back();
	</script>
</c:if>
<c:if test="${result==1 }">
	<script>
		alert('입력 성공하였습니다.');
		opener.parent.location='view.do?b_num=${c_board.b_num}&pageNum=${pageNum}';
		window.close();
	</script>
</c:if>
<c:if test="${result!=1 }">
	<script>
		alert('다시 입력해주세요.');
		history.back();
	</script>
</c:if>
