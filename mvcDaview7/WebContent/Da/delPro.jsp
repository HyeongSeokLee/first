<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:if test="${passChk==1 }">
	<c:if test="${delBoardChk>0 }">
		<script>
			alert('${b_num}번 글이 삭제되었습니다.');
			location.href='main.do?pageNum=${pageNum}';
		</script>
	</c:if>
</c:if>
<c:if test="${passChk!=1 }">
	<script>
		alert('비밀번호가 틀렸습니다.');
		history.back();
	</script>
</c:if>    


<!-- 	if(dao.getPassword(b_num, b_pw)==1){ //글 번호와 비밀번호 확인
		if(dao.delBoard(b_num)>0){//비밀번호가 맞으면 삭제
			out.print("<script>");
			out.print("alert('"+b_num+"번 글이 삭제되었습니다.');");
			out.print("location.href='main.jsp?pageNum="+pageNum+"';");
			out.print("</script>");
		}else{//삭제 실패시 뒤로
			out.print("<script>");
			out.print("alert('삭제 실패하였습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
	}else{//비밀번호가 틀리면 뒤로
		out.print("<script>");
		out.print("alert('비밀번호가 틀렸습니다.');");
		out.print("history.back();");
		out.print("</script>");
		
	} -->
     
