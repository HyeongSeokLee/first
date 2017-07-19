<%@page import="dto.Board"%>
<%@page import="dao.BoardDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	BoardDao dao = BoardDao.getInstance();
	Board board =new Board();
	String   id = "ab";
	for(int i=2000;i<3000;i++){
		
		board.setB_subject(i+"");
		board.setB_content(i+"");
		board.setB_pw("1234");
		board.setB_part("게임");
		board.setB_star("4");
		dao.insert(board,id);
	}
	/*id="cd";
	for(int i=3000;i<4000;i++){
		
		board.setB_subject(i+"");
		board.setB_content(i+"");
		board.setB_pw("1234");
		board.setB_part("숙박");
		board.setB_star("3");
		dao.insert(board,id);
	} */
	/* id="ef";
	for(int i=4000;i<5000;i++){
		
		board.setB_subject(i+"");
		board.setB_content(i+"");
		board.setB_pw("1234");
		board.setB_part("가전");
		board.setB_star("5");
		dao.insert(board,id);
	}  */
	/* id="ik";
	for(int i=5000;i<6000;i++){
		
		board.setB_subject(i+"");
		board.setB_content(i+"");
		board.setB_pw("1234");
		board.setB_part("식품");
		board.setB_star("2");
		dao.insert(board,id);
	}
	System.out.println("완료"); */
%>

</body>
</html>