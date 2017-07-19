<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<style>
body {
	text-align: center;
}
 
td {
	background-color: "yellow"
}
.star_rating {font-size:0; letter-spacing:-4px;}
.star_rating button {
    font-size:22px;
    margin-left:10px;
    color:white;
    text-decoration:none;
    background-color: #0096FF;
    border: none;
    outline: none;
}
.star_rating button.on {color:yellow;}
</style>
 
<title>글 쓰기</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.js"></script>
<script type="text/javascript">
	function writeSave() {
		if (!writeForm.b_pw.value) {
			alert('비밀번호를 입력하세요');
			writeForm.b_pw.focus();
			return false;
		}
		if (!writeForm.b_pw2.value) {
			alert('비밀번호 확인을 입력하세요');
			writeForm.b_pw2.focus();
			return false;
		}
		if (writeForm.b_pw.value != writeForm.b_pw2.value) {
			alert('비밀번호가 일치하지 않습니다.');
			writeForm.b_pw.value = "";
			writeForm.b_pw2.value = "";
			writeForm.b_pw.focus();
			return false;
		}
		return true;
	}
 
	$(document).ready(function() {
		$( ".star_rating button" ).click(function() {
			document.getElementById('b_star').value=$(this).val();
		    $(this).parent().children("button").removeClass("on");
		    $(this).addClass("on").prevAll("button").addClass("on");
		    return false;
		});
	});
</script>
</head>
<body>
<c:if test="${empty id}">
		<script>
			alert('로그인 후 이용하세요!');
			window.location.href='main.do';
		</script>
</c:if>
<jsp:include page="head_1.html"/> 
	<center>
		<h2>글쓰기</h2>
	</center>
	<form action="writePro.do?pageNum=${pageNum }" name="writeForm"
		onsubmit="return writeSave()" method="post">
		<input type="hidden" name="b_num" value="${b_num }">
		<!-- <input type="hidden" name="b_star"> -->
		<!-- 브라우저에는 안보임, 데이터 전송 -->
		<table border=1 align="center" bgcolor="#0096FF" width="550">
			<tr>
				<td align=right colspan=2><a href="main.do"><font color="white">글목록</font></a></td>
			</tr>
			<tr>
				<th><font color="white">제목</font></th>
				<td><input type="text" size="60" maxlength="50"
					name="b_subject" required></td>
			</tr>
			<tr><th><font color="white">작성자</font></th><td><font color="white">${nick }</font></td></tr>
			<tr>
				<th><font color="white">구분</font></th>
				<td><select name="b_part" style="width: 100px; height: 23px;">
							<option value="전체">전체</option>
							<option value="가전">가전</option>
							<option value="게임">게임</option>
							<option value="숙박">숙박</option>
							<option value="식품">식품</option>
							<option value="책">책</option>
							<option value="육아">육아</option>
				</select></td>
			<tr>
				<th><font color="white">내용</font></th>
				<td><textarea rows="13" cols="60" name="b_content"></textarea></td>
			</tr>
			<tr>
				<th><font color="white">별 선택</font></th>
				<td>
					<p class="star_rating">
						<input type = "hidden" name="b_star" id="b_star" value ="3">
						<button class="on" type="button" value="1">★</button>
						<button class="on" type="button" value="2">★</button>
						<button class="on" type="button" value="3">★</button>
						<button type="button" value="4">★</button>
						<button type="button" value="5">★</button>
					</p>
				</td>
			</tr>
			<tr>
				<th><font color="white">비밀번호</font></th>
				<td><input type="password" size=15 maxlength="12" name="b_pw"></td>
			</tr>
			<tr>
				<th><font color="white">비밀번호 확인</font></th>
				<td><input type="password" size=15 maxlength="12" name="b_pw2"></td>
			</tr>
			<tr>
				<td colspan=2><input type="submit" value="글쓰기"> <input
					type="reset" value="다시작성"> <input type="button"
					value="목록보기" onclick="window.location='main.do'"></td>
			</tr>
		</table>
	</form>
</body>
</html>