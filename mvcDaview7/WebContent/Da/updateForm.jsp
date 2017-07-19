<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html><html><head><meta charset=UTF-8">
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
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.js"></script>
<script type="text/javascript">
function chk(){
	if(!document.forms[0].b_pw.value){
		alert('비밀번호를 입력하세요');
		frm.b_pw.focus();
		return false;
	}
	var b_num = document.forms[0].b_num.value;
	
	var b_pw=document.forms[0].b_pw.value;
	window.location.href='BoardCheck.do?b_num='+b_num+'&b_pw='+b_pw; 
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

<title>글 내용 수정</title>
</head>
<body>
	<jsp:include page="head_1.html"/>
	<center>
		<h2>글 내용 수정</h2>
	</center>
<form action="updatePro.do" name="writeForm" method="post" onsubmit="return chk()">
<input type="hidden" name="b_num" value="${board.b_num }">
<input type="hidden" name="pageNum" value="${pageNum }">
	<table border=1 align="center" bgcolor="#0096FF" width="500">
<tr height="30"><th><font color="white">작성자</font></th><td><label><font color="white">${nick}</font></label></td></tr>
<tr height="30"><th><font color="white">글제목</font></th><td><input type="text" name="b_subject" value="${board.b_subject }" size="55"></td></tr>
<tr><th><font color="white">구분</font></th>				
<td><select name="b_part" style="width: 100px; height: 23px;">
	<option value="전체">전체</option>
	<option value="가전">가전</option>
	<option value="게임">게임</option>
	<option value="숙박">숙박</option>
	<option value="식품">식품</option>
	<option value="책">책</option>
	<option value="육아">육아</option>
				</select></td></tr>
<tr height="30"><th><font color="white">글내용</font></th><td><textarea rows="13" cols="55" name="b_content">${board.b_content }</textarea></td></tr>
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
<tr height="30"><th><font color="white">비밀번호</font></th><td><input type="password" name="b_pw"></td></tr>


<tr height="30"><td colspan=4 align="center">
<input type="submit" value="글 수정"  >
<input type="reset" value="다시 입력">
<input type="button" value="목록보기" onclick="location.href='main.do?pageNum=${pageNum }'">
</td></tr>
</table>
</form>
</body>
</html>