<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty id }"><!-- 로그인 유/무 확인 -->
	<script>
		alert('로그인 후 이용하세요!');
		history.back();
	</script>
</c:if>
<c:if test="${!empty id }"> <!-- 로그인 했을 시 -->
	<c:if test="${result==1 }"><!-- 댓글이 제대로 등록되면 result값은 1 반영 -->
		<script>
			alert('입력 성공하였습니다.');
			location.href='view.do?b_num=${c_board.b_num}&pageNum=${pageNum}';
		</script>
	</c:if>
	
	<c:if test="${result!=1 }">
		alert('입력 실패하였습니다.메세지를 확인하세요.');
		history.back();
	</c:if>
</c:if>
