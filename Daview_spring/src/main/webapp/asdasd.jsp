<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/js/jquery-3.2.1.js"></script>
</head>
<body>
<script>
	$(function(){
		$("#searchBtn").on("click",function(event){
										alert($("#keywordInput").val());					
      	});
		
	});
</script>
<input type="text" name="keyword" id="keywordInput" value="aaa">
<button id="searchBtn"></button>
</body>
</html>