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
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap-theme.min.css">
<script type="text/javascript" src="/resources/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
<c:if test="${empty m_email }">
<jsp:include page="head/head.jsp" /><br>	
</c:if>

<c:if test="${!empty m_email}">
<jsp:include page="head/head2.jsp" /><br>
</c:if>	


	<!-- 게시글 목록 테이블 -->

<table class="table table-hover">
	<thead>
	<tr>
		<th>번호</th>
		<th>
		분류
			<!-- 수정중 	
			<div class="btn-group">
					<button type="button" class="btn btn-primary">전체</button>
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">가전</a></li>
						<li><a href="#">게임</a></li>
						<li><a href="#">숙박</a></li>
						<li><a href="#">식품</a></li>
						<li><a href="#">책</a></li>
					</ul>
			</div> -->
		</th>
		<th> 제목</th><th> 닉네임</th><th> 평점</th><th> 조회수</th><th> 작성일자</th>
	</tr>
	</thead>
	<c:if test="${empty list}">데이터가 없습니다.</c:if>
	<c:if test="${!empty list}">
		<c:forEach items="${list }" var="l" >

			<tr>
				<td>${l.b_num}</td>
				<td>${l.b_part }</td>
				<td><a href="/board/view?b_num=${l.b_num }">
						${l.b_subject }
					</a> [${l.b_c_total }]
				</td>
				<td>${l.m_nick}</td>
				<td><c:if test="${l.b_star eq '1' }">
						<img src="/resources/images/star_1.jpg">
					</c:if> <c:if test="${l.b_star eq '2' }">
						<img src="/resources/images/star_2.jpg">
					</c:if> <c:if test="${l.b_star eq '3' }">
						<img src="/resources/images/star_3.jpg">
					</c:if> <c:if test="${l.b_star eq '4' }">
						<img src="/resources/images/star_4.jpg">
					</c:if> <c:if test="${l.b_star eq '5' }">
						<img src="/resources/images/star_5.jpg">
					</c:if></td>
				<td>${l.b_rc }</td>
						
				<!-- 오늘날짜면 시간으로 나오게하는 부분인데 여기서 처리함, 나중에 수정할 수도 있음  -->
				<jsp:useBean id="now" class="java.util.Date" />
				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />  	
				<fmt:formatDate value="${l.b_reg_date }" pattern="yyyy-MM-dd" var="someday"/>			
				<c:if test="${today eq someday }">
					<td><fmt:formatDate pattern="HH:mm" value="${l.b_reg_date }"/></td>
				</c:if>
				<c:if test="${today ne someday }">
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${l.b_reg_date }"/></td>
				</c:if>

			</tr>
		</c:forEach>
	</c:if>
</table>

	
	
	
	<div class="text-center">
		<ul class="pagination">
		<c:if test="${pageMaker.cri.page!=1}">
				<li><a href="main${pageMaker.makeSearch(1) }">처음</a></li>
			</c:if>
			
  		<c:if test="${pageMaker.prev }">
				<li><a href="main${pageMaker.makeSearch(pageMaker.startPage -1)}">이전</a></li>
			</c:if>
			
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="i">
				<c:if test="${pageMaker.cri.page eq i}">
					  <li class="active"><a>${i}</a></li>
				</c:if>
				<c:if test="${pageMaker.cri.page ne i}">
					<li><a href="main${pageMaker.makeSearch(i)}">${i}</a></li>
				</c:if>
			</c:forEach>

 		 <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
				<li><a href="main${pageMaker.makeSearch(pageMaker.endPage +1) }">다음</a></li>
			</c:if>
			
 			<c:if test="${pageMaker.endendPage!=pageMaker.cri.page}">
				 <li><a href="main${pageMaker.makeSearch(pageMaker.endendPage) }">끝</a></li>
			</c:if>
		</ul>
</div>

			



		<%-- 수정중 
		<div style="text-align: center;">
		<select id ="countList" onchange="javascript:selectCount()">
			<option value="#" >-게시글 수-</option>
			<option value="10">10개씩 보기</option>
			<option value="15">15개씩 보기</option>
			<option value="20">20개씩 보기</option>
			<option value="30">30개씩 보기</option>
		</select>
		<input type="hidden" value="${x}" id="aa">
		</div> --%>
		
		<div class="box-body">
			<select name="searchType" id="searchT">
				<option value="t" <c:out value="${cri.searchType eq 't'?'selected':''}"/>>
				제목
				</option>
				<option value="c" <c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
				내용
				</option>
				<option value="tc" <c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
				제목+내용
				</option>
				<option value="w" <c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
				작성자
				</option>
				
			</select>
			<input type="text" name="keyword" id="keywordInput" value="${cri.keyword}">
			<button id="searchBtn">검색</button>
		</div>
		
		<input type="button" onclick="location.href='/board/writeForm'" value="자자자자자ㅏ자작성">
		
		
		
	</div>
	
<script>
	$(function(){
		$("#searchBtn").on("click",function(event){
										self.location="main"
										+ "${pageMaker.makeQuery(1)}"
										+ "&searchType="
										+ $("#searchT option:selected").val()					
										+ "&keyword=" + encodeURIComponent($("#keywordInput").val());
										
      	});
		
	});
</script>
</body>
</html>
