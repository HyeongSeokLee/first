<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE><html><head>

<script type="text/javascript">
function openDap(a,b,c,d,e,f){
	width=400;
	height=300;
	sx = screen.width;
	sy = screen.height;
	x = (sx-width)/3;
	y = (sy-height)/4;
	
	url = "comment_dap.do?c_num="+a+"&b_num="+b+"&pageNum="+c+"&c_ref="+d+"&c_re_step="+e+"&c_re_level="+f;
	wr = window.open(url,"view","width=800,height=200");
	wr.moveTo(x,y);
}
</script>
<style type="text/css">
	textarea{
		resize: vertical;
	}
	.commentList {
		border-collapse:collapse;
	}
</style>
<title>글 내용 보기</title>
</head>
<body>
<jsp:include page="../head/head_1.jsp"/>
	<center>
		<h2>제목 : ${board.b_subject }</h2>
		<h4>작성자 : ${board.m_nick }</h4>
	</center>
<form>
	
<table border=1 align="center" bgcolor="#0096FF" width="500">
<tr height="30"><th width="20%"><font color="white">글번호</font></th><td align="center"><font color="white">${board.b_num }</font></td></tr>
<tr height="30"><th><font color="white">구&nbsp;&nbsp;&nbsp;분</font></th><td align="center"><font color="white">${board.b_part }</font></td></tr>
<tr height="30"><th><font color="white">평&nbsp;&nbsp;&nbsp;점</font></th><td align="center">
							<c:if test="${board.getB_star() eq '1'}">
								<img src="/resources/images/star_1.jpg">
							</c:if>
							<c:if test="${board.getB_star() eq '2'}">
								<img src="/resources/images/star_2.jpg">
							</c:if>
							<c:if test="${board.getB_star() eq '3'}">
								<img src="/resources/images/star_3.jpg">
							</c:if>
							<c:if test="${board.getB_star() eq '4'}">
								<img src="i/resources/images/star_4.jpg">
							</c:if>
							<c:if test="${board.getB_star() eq '5'}">
								<img src="/resources/images/star_5.jpg">
							</c:if>																												

						</td></tr>
<tr height="30"><th><font color="white">글내용</font></th><td><font color="white">${board.b_content }</font></td></tr>
<tr height="30"><th><font color="white">작성일</font></th><td align="center"><font color="white"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.b_reg_date }"/></font></td></tr>
<tr><th>조회수</th><td>${board.b_rc }</td></tr>
<tr height="30"><td colspan=4 align="center">
<input type="button" value="글 수정" onclick="location.href='/board/updateForm?b_num=${board.b_num}'" />
<input type="button" value="글 삭제" onclick="location.href='/board/delPro_get?m_nick=${board.m_nick }&b_num=${board.b_num}'" />
<input type="button" value="글 목록" onclick="location.href='/main'" />

</td></tr></table>
</form>
 
 
 
 <%-- 

	<center>
		<h3>의견 작성</h3>
	</center>
<!-- 댓글 목록 보여주는 테이블 -->
<table border=1 align="center" width="700" class="commentList">
<c:if test = "${!empty list}">
					<!-- 글 목록이 있으면 -->
					<c:forEach var="l" items="${list }">
					<tr>
						<td width="150px" style=text-align:left>
						<c:if test="${l.c_re_level>0 }">	
							<!-- level이 0보다 크면 답변글 -->
								<img src='/resources/images/level.gif' width='${l.c_re_level*10 }' height='5'>
								<!-- 답변글의 구분을 10칸씩 표시 -->
								<img src='/resources/images/re.gif'>
								<!-- 답변 표시 --> 
						</c:if>
						${l.m_nick }</td>
						<td width="300px">${l.c_content }</td>
						<td width="110px">${l.c_date }</td>
						<td width="60px" text-align="center">
						
						<input type="button" value="답글 달기" onclick="openDap(${l.c_num },${board.b_num},${pageNum},${l.c_ref },${l.c_re_step },${l.c_re_level })"><br>
		
						<c:if test="${l.m_nick eq nick }">
						<a href="#"  onclick="location.href='comment_del.do?c_num=${l.c_num }&b_num=${board.b_num}&pageNum=${pageNum}'" 					
						 style="text-decoration:none">[삭제]</a></td>
						</c:if>
						</tr>
						
					<tr>	

					</c:forEach>		
	</c:if>
</table>
<!-- 댓글 작성 폼 -->
<form action="commentPro.do" method="post">
	<input type = "hidden" name = "c_num" value = ${c_num }>
	<input type = "hidden" name = "b_num" value = ${b_num }>
	<input type = "hidden" name = "m_id" value = ${m_id }>
	<input type="hidden" name="c_ref" value=${c_ref }>
	<input type="hidden" name="c_re_step" value=${c_re_step }>
	<input type="hidden" name="c_re_level" value=${c_re_level }>
	<input type="hidden" name="pageNum" value=${pageNum }>
	<table align="center">
		<tr>
			<td text-align="center" width="200px">
				<c:if test="${empty id }">
					<h3>guest</h3>
				</c:if>
				<c:if test="${!empty id }">
					${nick }
				</c:if>
			</td>
			<td><textarea name="c_content" rows="4" cols="68"></textarea></td>
			<td>
			 	<input type="submit" value="댓글등록" style="height:60px;">
			</td>
		</tr>
 
	</table>
</form>  
 --%>
</body>
</html>