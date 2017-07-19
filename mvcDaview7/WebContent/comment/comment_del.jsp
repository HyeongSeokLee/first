<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result==1 }"> <!-- 댓글 삭제됐으면 result값 1로 넘어옴 -->
	<script>
		alert('댓글이 삭제되었습니다.');
		location.href='view.do?b_num=${b_num}&pageNum=${pageNum}';
	</script>
</c:if>
<c:if test="${result!=1 }">
	<script>
		alert('댓글삭제 실패하였습니다.');
		location.href='view.do';
	</script>
</c:if>

