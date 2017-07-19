package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dto.Board;
import jdbc.DBOracleConn;


public class BoardDao {
	private static BoardDao instance;

	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}

	// board 전체 건수
	public int getTotal() throws SQLException {
		int total = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			rs.next();
			total = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("getTotal()오류:" + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return total;
	}

	
	// 
	public List<Board> selectList(int startRow, int endRow, String find_field, 
			String find_name, String b_part)
			// find_filed: id/제목 find_name:검색내용
	throws SQLException {
		List<Board> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;


		find_name = "%" + find_name + "%"; // dao에서 select시 like조건으로 변경 "%%"
											// "%홍%"
		
		if(b_part==null||b_part=="") b_part="%";
		String sql1 = "";
		
		/*페이징 
		 * rownum 이용 :: rownum은 가상의 열값이기때문에 1부터가 아니면 값을 출력하지 못함
		 * rownum을 실제 테이블의 열로 만들기 위해 서브쿼리를 이용 
		 * */
		String sql0 = "select rn,b_num,b_subject,b_content,b_rc,b_reg_date,b_part,member.m_id id,b_star from "
				+ "(select rownum rn, a.* from (select * from board order by b_num desc)a)b, member "
				+ " where  b.m_id=member.m_id and b_part like '"+b_part+"'";
		
		
		if (find_field.equals("id"))
			sql1 = " and member.m_id like ? ";
		else if (find_field.equals("b_subject"))
			sql1 = " and b_subject like ? ";
		else
			sql1 = " and (member.m_id like ? or b_subject like ?) ";

		String sql = sql0 + sql1 + " and (rn between ? and ?) ";
		
		System.out.println(sql);

		try {
			conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (find_field.equals("id") || find_field.equals("b_subject")) {
				pstmt.setString(1, find_name);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			} else {
				pstmt.setString(1, find_name);
				pstmt.setString(2, find_name);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
			}
			System.out.println("sql=" + sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board bd = new Board(); // bean객체 생성

				bd.setB_num(rs.getInt("b_num")); // 속성값 저장
				bd.setB_subject(rs.getString("b_subject"));
				bd.setB_content(rs.getString("b_content"));
				bd.setB_rc(rs.getInt("b_rc"));
				bd.setB_reg_date(rs.getDate("b_reg_date"));
				bd.setB_part(rs.getString("b_part"));
				bd.setM_id(rs.getString("id"));
				bd.setB_star(rs.getString("b_star"));
				bd.setM_nick(rs.getString("id"));
				bd.setC_count(rs.getInt("b_num"));

				list.add(bd); // 한번 다 돌때마다 list에 저장

			}
		} catch (Exception e) {
			System.out.println("selectList()오류:" + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return list;
	}
	
	//게시글 가져오기
	public Board getBoard(int num) throws SQLException {
		Board bd = new Board();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			readCount(num);
			conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement("select * from board where b_num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bd.setB_num(rs.getInt("b_num")); // 속성값 저장
				bd.setB_subject(rs.getString("b_subject"));
				bd.setB_content(rs.getString("b_content"));
				bd.setB_rc(rs.getInt("b_rc"));
				bd.setB_reg_date(rs.getDate("b_reg_date"));
				bd.setB_part(rs.getString("b_part"));
				bd.setB_star(rs.getString("b_star"));
				bd.setM_id(rs.getString("m_id"));
			}

		} catch (Exception e) {
			System.out.println("getBoard()오류:" + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return bd;
	}
	
	// 조회건수 출력메소드
	public int readCount(int b_num) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement("update board set b_rc=b_rc+1 where b_num=?");
			pstmt.setInt(1, b_num);
			result = pstmt.executeUpdate();
			

		} catch (Exception e) {
			System.out.println("readCount()오류:" + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}
	
	// 게시글 등록
		public int insert(Board board,String id) throws SQLException {
			
			int result = 0;
			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			int number = 0;
			String sql = "";
			try {
				conn = DBOracleConn.getConnection();
				pstmt = conn.prepareStatement("select max(b_num) from board");
				rs = pstmt.executeQuery();
				if (rs.next())
					number = rs.getInt(1) + 1;// 새글번호=(현재 글번호 + 1)
				else
					number = 1;// 최초로 글을 쓰는 경우
				pstmt.close();// select쿼리 닫기

				sql = "insert into board(b_num,b_subject,b_content,b_pw,b_reg_date,b_part,m_id,b_star)" + "values(?,?,?,?,sysdate,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, number);
				pstmt.setString(2, board.getB_subject());
				pstmt.setString(3, board.getB_content());
				pstmt.setString(4, board.getB_pw());
				pstmt.setString(5, board.getB_part());
				pstmt.setString(6, id);
				pstmt.setString(7, board.getB_star());
				result = pstmt.executeUpdate();

			} catch (Exception e) {
				System.out.println("insert()메소드오류:" + e.getMessage());
			} finally {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			}
			return result;
		}
	
	// 등록한 글번호에 해당하는 패스워드가 맞는지 확인
	public int getPassword(int b_num, String b_pw) throws SQLException {
		int result = -1;// 패스워드가 틀리면 -1, 맞으면 1
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement("select b_pw from board where b_num=?");
			pstmt.setInt(1, b_num);
			rs = pstmt.executeQuery();
			System.out.println(b_pw);
			if (rs.next()) {
				if (b_pw.equals(rs.getString(1))) {
					result = 1; // 비밀번호가 맞으면 1
				} else
					result = -1; // 틀리면 -1
			}

		} catch (Exception e) {
			System.out.println("getPassword()오류:" + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}
	
	// 글 수정
	public int update(Board board) throws SQLException {
		int result = -1;// update결과는 정상 update는 update한 row수
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update board set b_subject=?,b_content=?,b_part=?,b_star=? where b_num=?";
		String b_part = board.getB_part();
		if(b_part==null||b_part.equals(" ")) b_part="%"; 
		try {
			conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_subject());
			pstmt.setString(2, board.getB_content());
			pstmt.setString(3, board.getB_part());
			pstmt.setString(4, board.getB_star());
			pstmt.setInt(5, board.getB_num());
			result = pstmt.executeUpdate();// 정상 update결과는 1
			
			System.out.println(board.getB_subject());

		} catch (Exception e) {
			System.out.println("update()오류:" + e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}
	// 글 삭제
		public int delBoard(int b_num) throws Exception {
			int result = -1;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "delete from board where b_num=?";
			try {
				conn = DBOracleConn.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, b_num);
				result = pstmt.executeUpdate();

			} catch (Exception e) {
				System.out.println("delBoard()오류:" + e.getMessage());
			} finally {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			}
			return result;
		}
		
		//b_part 선택 안했을 시 전체 게시글 갯수를 가져오는 메소드
		public int getTotal(String find_field,String find_name){
			ResultSet rs=null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			if(find_field.equals("")||find_name.equals("")){
				sql="select count(*) from board";
			}else if(find_field.equals("b_subject")){
				sql="select count(*) from board where b_subject like '%"+find_name+"%'";
			}else if(find_field.equals("b_content")){
				sql="select count(*) from board where b_content like'%"+find_name+"%'";
			}else if(find_field.equals("title&text")){
				sql="select count(*) from board where b_subject like '%"+find_name+"%' or b_content like'%"+find_name+"%'";
			}
			int result=0;
			try{
				conn = DBOracleConn.getConnection();
				pstmt=conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()){
					result=rs.getInt(1);
				}
			}catch (Exception e) {
				System.out.println(e.getMessage()); 
			}finally {
				try{if(rs!=null)rs.close();}catch (Exception e) {	}
				try{if(pstmt!=null)pstmt.close();}catch (Exception e) {	}
				try{if(conn!=null)conn.close();}catch (Exception e) {	}
				
			}
			return result;
		}
		
		//b_part 구분 선택 시 해당하는  게시글의 갯수만 가져오는 메소드
		public int getTotal(String find_field,String find_name,String b_part){
			ResultSet rs=null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			if(find_field.equals("")||find_name.equals("")){
				sql="select count(*) from board where b_part like '"+b_part+"'";
			}else if(find_field.equals("b_subject")){
				sql="select count(*) from board where b_subject like '%"+find_name+"%' and b_part like '"+b_part+"'";
			}else if(find_field.equals("b_content")){
				sql="select count(*) from board where b_content like'%"+find_name+"%'";
			}else if(find_field.equals("title&text")){
				sql="select count(*) from board where b_subject like '%"+find_name+"%' or b_content like'%"+find_name+"%' and b_part like '"+b_part+"'";
			}
			int result=0;
			try{
				conn = DBOracleConn.getConnection();
				pstmt=conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()){
					result=rs.getInt(1);
				}
			}catch (Exception e) {
				System.out.println(e.getMessage()); 
			}finally {
				try{if(rs!=null)rs.close();}catch (Exception e) {	}
				try{if(pstmt!=null)pstmt.close();}catch (Exception e) {	}
				try{if(conn!=null)conn.close();}catch (Exception e) {	}
				
			}
			return result;
		}

	
		public ArrayList<Board> getList(int startPage,int endPage,String find_field,String find_name,String b_part){
			ArrayList<Board> al = new ArrayList<>();
			PreparedStatement pstmt = null;
			Connection conn=null;
			ResultSet rs = null;
			String sql = null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA); 
			SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
			SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");	
			String currentDay=sdf2.format(new Date());
			
			if(find_field.equals("")||find_name.equals("")){
				System.out.println("기본검색을 실시");
				sql = "select * "
				       	+  "from(select rownum as rnum,b.* "
		            	+ "from (select b_num,b_subject,b_content,b_rc,b_reg_date,b_part,m_id ,b_star  "
		                    	+ "from board  "
		                       + " where b_part like ? order by b_num desc)b "
		              + ")  "
		              	+ "where rnum between ? and ?";	
			}else if(find_field.equals("title&text")){
				find_name = "'%"+find_name+"%'";
				System.out.println("두개이상");
				sql = "select * "
				       	+  "from(select rownum as rnum,b.* "
		            	+ "from (select b_num,b_subject,b_content,b_rc,b_reg_date,b_part,m_id ,b_star  "
		                    	+ "from board  "
		                       + " where  b_part like ? and ( b_subject like "+find_name+" or b_content like"+find_name+")"
		                       		+ " order by b_num desc)b"
		              + ")  "
		              	+ "where rnum between ? and ?";		
			}else{
				find_name = "'%"+find_name+"%'";
				System.out.println("조건검색을 실시");
				sql = "select * "
				       	+  "from(select rownum as rnum,b.* "
		            	+ "from (select b_num,b_subject,b_content,b_rc,b_reg_date,b_part,m_id ,b_star  "
		                    	+ "from board  "
		                       + " where b_part like ? and ( "+find_field+" like "+find_name+") order by b_num desc)b"
		              + ")  "
		              	+ "where rnum between ? and ? ";	
			}

			try{
				conn = DBOracleConn.getConnection();
				pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, b_part);
					pstmt.setInt(2, startPage);
					pstmt.setInt(3, endPage);
					
				System.out.println("sql::"+sql);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Board board = new Board();
					board.setB_num(rs.getInt("b_num"));
					board.setB_part(rs.getString("b_part"));
					board.setB_subject(rs.getString("b_subject"));
					board.setB_rc(rs.getInt("b_rc"));
					board.setB_star(rs.getString("b_star"));
					Date date = rs.getTimestamp("b_reg_date");
					board.setM_nick(rs.getString("m_id"));
					board.setC_count(rs.getInt("b_num"));
					
					
					if(currentDay.equals(sdf.format(date))){
						board.setDate(sdf3.format(date));
					}else{
						board.setDate(sdf4.format(date));
					}
					
					al.add(board);
	
				}
			}catch(Exception e){
				System.out.println("오류~"+e.getMessage());
			}finally{
				try{if(rs!=null)rs.close();}catch(Exception e){System.out.println(e.getMessage());}
				try{if(pstmt!=null)pstmt.close();}catch(Exception e){System.out.println(e.getMessage());}
				try{if(conn!=null)conn.close();}catch(Exception e){System.out.println(e.getMessage());}
			}
			return al;
		}
}
