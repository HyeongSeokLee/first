package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Member;
import jdbc.DBOracleConn;

public class MemberDao {
	private static MemberDao instance;

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	public int insertMember(Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;		
		String sql = "insert into member(m_id,m_pw,m_email,m_nick,m_reg_date) values(?,?,?,?,sysdate)";
		try{
			conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getM_id());
			pstmt.setString(2, member.getM_pw());
			pstmt.setString(3, member.getM_email());
			pstmt.setString(4, member.getM_nick());
			result = pstmt.executeUpdate();
		}catch(Exception e){System.out.println("insertMember()오류:"+e.getMessage());			
		}finally{
			try{
			if(pstmt!=null) pstmt.close(); if(conn!=null) conn.close();
		}catch(Exception e){}
		}
		
		return result;
	}
	
	public int userCheck(String id) {
		ResultSet rs = null;
		Connection conn=null;
		PreparedStatement pstmt = null;
		String sql = "select m_id from member where m_id=?";
		int result = -1;// 1:회원, 0:비회원, 2:패스워드 실패
		try {
			 conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);// 매개변수로 입력된 id를 세팅
			rs = pstmt.executeQuery();
			if (rs.next()) {// 결과가 있으면(id가 존재한다는 의미)
				result = 1;
			} else
				result = 0;// 비회원
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt != null)pstmt.close();
				if(conn!=null)conn.close();
				} catch (Exception e) {}
		}
		return result;
	}
	
	public int userCheck(String id,String password) {
		ResultSet rs = null;
		Connection conn=null;
		PreparedStatement pstmt = null;
		String sql = "select m_id,m_pw from member where m_id=?";
		int result = -1;// 1:회원, 0:비회원, 2:패스워드 실패
		try {
			 conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);// 매개변수로 입력된 id를 세팅
			rs = pstmt.executeQuery();
			if (rs.next()) {// 결과가 있으면(id가 존재한다는 의미)
				String dbpassword = rs.getString("m_pw");
				if(password.equals(dbpassword))
				result = 1;
				else if(!password.equals(dbpassword))
				result = 2;
			} else
				result = 0;// 비회원
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				System.out.println(result);
				if(rs!=null) rs.close();
				if(pstmt != null)pstmt.close();
				if(conn!=null)conn.close();
				} catch (Exception e) {}
		}
		return result;
	}
	
	public int nickCheck(String nick) {
		ResultSet rs = null;
		Connection conn=null;
		PreparedStatement pstmt = null;
		String sql = "select m_nick from member where m_nick=?";
		int result = -1;// 1:회원, 0:비회원, 2:패스워드 실패
		try {
			 conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nick);// 매개변수로 입력된 id를 세팅
			rs = pstmt.executeQuery();
			if (rs.next()) {// 결과가 있으면(id가 존재한다는 의미)
				result = 1;
			} else
				result = 0;// 비회원
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt != null)pstmt.close();
				if(conn!=null)conn.close();
				} catch (Exception e) {}
		}
		return result;
	}
	
	public Member memberInfo(String id) {
		Member member = null;// meber변수 선언
		PreparedStatement pstmt = null;
		Connection conn=null;
		ResultSet rs =null;
		String sql = "select * from member where m_id=?";
		try {// 오라클 DB연결 콘넥션인 DBOrcleConn.getConnection()메소드 호출(static)
			 conn = DBOracleConn.getConnection();// Oracle DB연
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			 rs = pstmt.executeQuery();
			while (rs.next()) {// 출력결과가 1건인 경우 if(rs.next())와 while(rs.next())둘
								// 다 가능
				member = new Member();
				member.setM_id(rs.getString(1));
				member.setM_email(rs.getString(3));
				member.setM_nick(rs.getString(4));
				member.setM_reg_date(rs.getDate("m_reg_date"));
			}
		} catch (Exception e) {
			System.out.println("오류~" + e.getMessage());
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt != null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {}
		}
		return member;
	}
	
	public int updateMember(Member member) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update member set m_pw=?,m_nick=?,m_email=? where m_id=?";
		try{
			conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,member.getM_pw());
			pstmt.setString(2, member.getM_nick());
			pstmt.setString(3, member.getM_email());
			pstmt.setString(4, member.getM_id());
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("updateMember()오류:"+e.getMessage());
		}finally{
			try{
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			}catch(Exception e){}			
		}
		
		return result;
	}
	
	public String getNick(String id) throws SQLException {
		String nick="";
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement("select m_nick from member where m_id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				nick=rs.getString("m_nick");
			}

		
			
		} catch (Exception e) {
			System.out.println("getNick()오류:" + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return nick;
	}
	
	public int delMember(String id){
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from member where m_id=?";
		try{
			conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("delMember()오류:"+e.getMessage());
		}finally{
			try{
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			}catch(Exception e){}			
		}		
		return result;
	}
	
	//해당 댓글 작성자 닉네임 가져오기
		public String getNick(int c_num) throws SQLException {
			String nick="";
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBOracleConn.getConnection();
				pstmt = conn.prepareStatement("select m_nick from member mb,comment_board cb where mb.m_id=cb.m_id and c_num=?");
				pstmt.setInt(1, c_num);
				rs = pstmt.executeQuery();

				if(rs.next()) {
					nick=rs.getString("m_nick");
				}
			} catch (Exception e) {
				System.out.println("getNick()오류:" + e.getMessage());
			} finally {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			}
			return nick;
		}
}
