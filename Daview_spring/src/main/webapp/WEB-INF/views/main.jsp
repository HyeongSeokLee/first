<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" type="text/css">
<title>Insert title here</title>

<script type="text/javascript">
function choice(o) { 
	   var v = o.value; 
	   document.frm.b_part.value = v;
	   document.frm.submit(); 
} 

function selectCount(){ 
			var aa = document.getElementById('aa').value;
			var selectValue = document.getElementById('countList').value;
			location.href="main?countList="+selectValue+aa;
}
</script>
<style type="text/css">

p a{
	color: grey;
	font-size: 1em;
	font-weight: bold;
}
a:hover{
	color:black;
}
a{
	text-decoration: none;
}
</style>

</head>
<body>
<c:if test="${empty m_email }">
<jsp:include page="board/head.jsp" /><br>	
</c:if>

<c:if test="${!empty m_email}">
		<div id="header1">
			<a href="main"><img src="/resources/images/DaView_C.jpg"></a>
		</div>
		<div id="headerbox">
		<center>${m_nick }님 환영합니다.<br>
		<div style="margin-top:10px;">
		<a href="/member/logoutPro"><img src="/resources/images/DaView_Logout3.jpg"></a>
		<a href="/member/memberModifyForm"><img src="/resources/images/DaView_MyPage3.jpg"></a>
		</div>
		</center>
		</div>
</c:if>	

	<div id="border">
		<h1>게시판</h1>

	<!-- 검색창 폼 -->
	<div id="border_table">
	<div id="serach_wrap_board" class="search_wrap_board_style">
		<center>
			<form action="main.do" method="get">
				<table id="search_t" class="search_table_style">
					<tr>
						<td><select name="find_field" id="find_field" style="width: 70px; height: 23px;">
								<option value="b_subject"<c:if test = "${find_field eq 'b_subject'}">selected</c:if>>제목</option>
								<option value="title&text"<c:if test = "${find_field eq 'title&text'}">selected</c:if>>제목+내용</option>
								<option value="b_content"<c:if test = "${find_field eq 'b_content'}">selected</c:if>>내용</option>
						</select></td>
						<td><input type="text" name="find_name" id="find_name" onfocus="search()"  size="14" style="width: 300px; height: 16px;"
							<c:if test="${!empty find_name}">value=${find_name}</c:if> />
							<input type = "submit" value="검색" id="input_search" />
							<input type = "button" value = "글쓰기" id = "write" onclick = "location='writeForm?pageNum=${curPage}'">
						</td>
					</tr>
				</table>
			</form>
		</center>
	</div>
	
	<table><tr height=10></table>

	<!-- 게시글 목록 테이블 -->
			<center>
				<table border=1 align="center" bgcolor="#0096FF" >
					<tr>
						<th style="width: 100px;"><font color="white">번호</font></th>
						<th>
							<select name="b_part" style="width:100px; height:23px;" onchange="choice(this);">
								<option value="전체"<c:if test="${b_part eq '%'}">selected</c:if>>전체</option>
								<option value="가전" <c:if test="${b_part eq '가전' }">selected</c:if>>가전</option>
								<option value="게임"<c:if test="${b_part eq '게임' }">selected</c:if>>게임</option>
								<option value="숙박"<c:if test="${b_part eq '숙박' }">selected</c:if>>숙박</option>
								<option value="식품"<c:if test="${b_part eq '식품' }">selected</c:if>>식품</option>
								<option value="책"<c:if test="${b_part eq '책' }">selected</c:if>>책</option>
							</select>
							<form name="frm" action="main" method="get">
								<input type="hidden" name="b_part" />
							</form>
						</th>
						<th style="width: 350px;"><font color="white">제목</font></th>
						<th style="width: 130px;"><font color="white">닉네임</font></th>
						<th style="width: 130px;"><font color="white">평점</font></th>
						<th style="width: 100px;"><font color="white">조회수</font></th>
						<th style="width: 100px;"><font color="white">작성일자</font></th>
					</tr>

					<c:if test="${total==0}">데이터가 없습니다.</c:if>
					<c:if test="${!empty list}">
						<c:forEach var="l" items="${list }">

						<tr>
							<td align="center"><font color="white">${l.b_num}</font></td>
							<td align="center"><font color="white">${l.b_part }</font></td>
							<td>
								<a href = "view?b_num=${l.b_num }&pageNum=${curPage}">
								<font color="white">${l.b_subject }</font></a>
								
								<!-- 해당 게시글에 달린 댓글 갯수 -->
								<font color="white">[${l.c_count }]</font> 
						 
							<c:if test="${l.b_rc>20 }">
									<img src='/resources/images/hot.gif'>
							</c:if> 
							</td>
			
					<td align="center"><font color="white">
 								${l.m_nick}</font></td> 
						<td align="center">
						<c:if test="${l.b_star eq '1' }">
							<img src="/resources/images/star_1.jpg">
						</c:if>
						<c:if test="${l.b_star eq '2' }">
							<img src="/resources/images/star_2.jpg">
						</c:if>
						<c:if test="${l.b_star eq '3' }">
							<img src="/resources/images/star_3.jpg">
						</c:if>
						<c:if test="${l.b_star eq '4' }">
							<img src="/resources/images/star_4.jpg">
						</c:if>
						<c:if test="${l.b_star eq '5' }">
							<img src="/resources/images/star_5.jpg">
						</c:if>																													
						</td>
						<td align="center"><font color="white">${l.b_rc }</font></td>
						<td align="center"><font color="white">${l.date }</font></td>
						</tr>
						</c:forEach>
					</c:if>
				</table>
			
				

		<!-- 글목록 테이블 끝(페이징) -->		
	
			<p>
				<c:if test="${curPage!=1 && curPage!=0}">
					<a href="main?curPage=1&${x}">[처음]</a>
				</c:if>
				<c:if test="${startPage > countList}">
					<a href="main?curPage=${startPage - 1}${x}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<c:if test="${curPage eq i}">
						<span style="color:black; font-weight: bold;">${i}</span>
					</c:if>
					<c:if test="${curPage != i}">
					<a href="main?curPage=${i}${x}">${i}</a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage <totalPage}">
					<a href="main?curPage=${startPage + countList}${x}">[다음]</a>
				</c:if>
				<c:if test="${curPage != totalPage}">
					<a href="main?curPage=${totalPage}${x}">[끝]</a>
				</c:if>
			</p>
		</center>
	</div>




		<div style="text-align: center;">
		<select id ="countList" onchange="javascript:selectCount()">
			<option value="#" >-게시글 수-</option>
			<option value="10">10개씩 보기</option>
			<option value="15">15개씩 보기</option>
			<option value="20">20개씩 보기</option>
			<option value="30">30개씩 보기</option>
		</select>
		<input type="hidden" value="${x}" id="aa">
		</div>

	</div>
</body>
</html>
