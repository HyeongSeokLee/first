package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBOracleConn {
	public static Connection getConnection() throws Exception {
		Connection conn = null;
/*		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			
			conn = ds.getConnection();
			System.out.print("연결되었습니다.");
		} catch (Exception e) {
			System.out.print("연결에 실패하였습니다.");
		}
		return conn;
	}*/
	String url="jdbc:oracle:thin:@localhost:1521:orcl";
	String id="scott";
	String password="tiger";

	Class.forName("oracle.jdbc.driver.OracleDriver");
	conn=DriverManager.getConnection(url,id,password);
	if(conn!=null){
		System.out.println("연결되었습니다.");
	}
	return conn;
}
}
