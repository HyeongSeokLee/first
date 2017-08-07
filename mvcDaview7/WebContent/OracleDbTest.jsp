<%@page import="jdbc.DBOracleConn"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Connection conn = null;
try{  
	conn = DBOracleConn.getConnection();
	out.print("<h3>연결되었습니다.</h3>");
}catch(Exception e){
	out.print("<h3>연결에 실패하였습니다.</h3>");
}
%>
		