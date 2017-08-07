<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.n_box { 
	margin: 0; padding: 0; 
}
.n_box li {
  list-style:none;
  position: relative;
}
.n_box a {
  color: white;
  text-decoration: none;
}
.n_box ul{
  border: 1px solid black;
  background-color: #0096FF; 
  display: none;
  position: absolute;
  padding: 0;
  width: 120px;
  z-index: 2;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.js"></script>
<script>
$(function(){
	 	$(document).mouseup(function(e) {
			if ($(e.target).parents('.n_box').length == 0) {
				$('.n_box li').removeClass('expand');
				$('.n_box ul').hide();
			}
		});
		$(".n_box li>a").click(function(){
			var li = $(this).parent();
			var ul = li.parent();
			ul.find('li').removeClass('expand');
			ul.find('ul').not(li.find('ul')).hide();
			li.children('ul').toggle();			
		});
	});
</script>
</head>
<body>
<table border="1" bgcolor="#0096FF">
	<tr><td align="center"><ul class="n_box"><li><a><font color="white">${l.m_nick}</font></a><ul>
    <li><a href="#0">개인정보 보기</a></li>
    <li><a href="#1">개시글 보기</a></li>
    <li><a href="#2">쪽지 보내기</a></li></ul>
    </li></ul>
	</td></tr>
	<tr><td><ul class="n_box"><li><a>${l.m_nick}</a><ul>
    <li><a href="#0">개인정보 보기</a></li>
    <li><a href="#1">개시글 보기</a></li>
    <li><a href="#2">쪽지 보내기</a></li></ul>
    </li></ul>
	</td></tr>
</table>
</body>
</html>