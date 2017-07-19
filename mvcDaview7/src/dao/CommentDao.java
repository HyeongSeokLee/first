package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Comment_board;
import jdbc.DBOracleConn;


public class CommentDao {
	private static CommentDao instance;
	public static CommentDao getInstance(){
		if(instance==null){instance = new CommentDao();}
		return instance;
	}
	// 댓글 가져오기
	public Comment_board getComment_board(int b_num) throws SQLException {
		Comment_board cb = new Comment_board();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement("select * from comment_board where b_num=?");
			pstmt.setInt(1, b_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cb.setC_num(rs.getInt("c_num"));
				cb.setB_num(rs.getInt("b_num")); // 속성값 저장
				cb.setM_id(rs.getString("m_id"));
				cb.setC_content(rs.getString("c_content"));
				cb.setC_date(rs.getDate("c_date"));
				cb.setC_ref(rs.getInt("c_ref"));
				cb.setC_re_step(rs.getInt("c_re_step"));
				cb.setC_re_level(rs.getInt("c_re_level"));
			}
			System.out.println("c_num "+cb.getC_num());
			System.out.println("m_id "+cb.getM_id());
			System.out.println("b_num "+cb.getB_num());
			System.out.println("content "+cb.getC_content());
			System.out.println("date "+cb.getC_date());
			System.out.println("ref "+cb.getC_ref());
			System.out.println("step "+cb.getC_re_step());
			System.out.println("level "+cb.getC_re_level());
			
		} catch (Exception e) {
			System.out.println("getComment_board()오류:" + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return cb;
	}
	
	public Comment_board getComment(int c_num){
		Comment_board comment = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBOracleConn.getConnection();
			pstmt = conn.prepareStatement("select * from comment_board where c_num=?");
			pstmt.setInt(1, c_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comment = new Comment_board();
				comment.setC_num(rs.getInt("c_num"));
				comment.setB_num(rs.getInt("b_num")); // 속성값 저장
				comment.setM_id(rs.getString("m_id"));
				comment.setC_content(rs.getString("c_content"));
				comment.setC_date(rs.getDate("c_date"));
				comment.setC_ref(rs.getInt("c_ref"));
				comment.setC_re_step(rs.getInt("c_re_step"));
				comment.setC_re_level(rs.getInt("c_re_level"));
			}
			System.out.println("c_num "+comment.getC_num());
			System.out.println("m_id "+comment.getM_id());
			System.out.println("b_num "+comment.getB_num());
			System.out.println("content "+comment.getC_content());
			System.out.println("date "+comment.getC_date());
			System.out.println("ref "+comment.getC_ref());
			System.out.println("step "+comment.getC_re_step());
			System.out.println("level "+comment.getC_re_level());
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println("getComment_board()오류:" + e.getMessage());
		}
		return comment;
	}
	
	//댓글 등록
		public int insert(Comment_board c_board) throws SQLException {
			int result = 0;
			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;

			int b_num = c_board.getB_num();
			int c_num = c_board.getC_num();			//댓글번호
			int c_ref = c_board.getC_ref();			//새댓글번호(새댓글인 경우 num = ref, 답변댓글일 경우 num != ref)
			int c_re_step = c_board.getC_re_step();	//답변댓글의 순서
			int c_re_level = c_board.getC_re_level();	//같은 답변글 내에서의 순서
			int number = 0;
			String sql = "";

			try {
				conn = DBOracleConn.getConnection();
				pstmt = conn.prepareStatement("select max(c_num) from comment_board");
				rs = pstmt.executeQuery();
				if(rs.next()) number = rs.getInt(1) + 1;	//새댓글 번호 = {현재 댓글번호 + 1}
				else number = 1;							//최초로 댓글을 쓰는 경우
				pstmt.close();								//select 쿼리 닫기
				
				if(c_num != 0){								//답변댓글인 경우
					sql = "update comment_board set c_re_step=c_re_step+1 where c_ref=? and c_re_step>?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, c_ref);
					pstmt.setInt(2, c_re_step);
					pstmt.executeUpdate();
					c_re_step = c_re_step + 1;
					c_re_level = c_re_level + 1;
					pstmt.close();
				}else { 							// 새댓글 인 경우
					c_ref = number;					// 새댓글인 경우 새댓글=c_ref
					c_re_step = 0; 					//
					c_re_level = 0;

				}									//새댓글인 경우
					sql = "insert into comment_board(c_num,b_num,m_id,c_date,c_ref,c_re_step,c_re_level,c_content) "
							+ " values(?,?,?,sysdate,?, ?, ?, ?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, number);
					pstmt.setInt(2, b_num);
					pstmt.setString(3, c_board.getM_id());
					pstmt.setInt(4, c_ref);					//number == c_ref //수정
					pstmt.setInt(5, c_re_step);
					pstmt.setInt(6, c_re_level);
					pstmt.setString(7, c_board.getC_content());
					result = pstmt.executeUpdate();
					
					System.out.println("c_num "+ c_board.getC_num());
					System.out.println("m_id "+c_board.getM_id());
					System.out.println("b_num "+c_board.getB_num());
					System.out.println("content "+c_board.getC_content());
					System.out.println("date "+c_board.getC_date());
					System.out.println("ref "+c_board.getC_ref());
					System.out.println("step "+c_board.getC_re_step());
					System.out.println("level "+c_board.getC_re_level());
					
					
			} catch (Exception e) {
				System.out.println("Comment_insert()메소드 오류 : " + e.getMessage());
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
	
		public List<Comment_board> selectList(int b_num)
				throws SQLException {
			List<Comment_board> list = new ArrayList<>();
			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			


			String sql="select c_content,c_date,c_num,cb.m_id m_id,c_ref,c_re_step,c_re_level from comment_board cb, board bd,member mb where "
					+" cb.b_num=bd.b_num and cb.m_id=mb.m_id and cb.b_num="+b_num+" order by c_ref desc, c_re_step";
			
			try {
				conn = DBOracleConn.getConnection();
				pstmt = conn.prepareStatement(sql);

//				System.out.println("sql=" + sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Comment_board cb = new Comment_board(); // bean객체 생성
					cb.setC_num(rs.getInt("c_num"));
					cb.setC_content(rs.getString("c_content"));
					cb.setC_date(rs.getDate("c_date"));
					cb.setM_id(rs.getString("m_id"));
					cb.setC_re_step(rs.getInt("c_re_step"));
					cb.setC_re_level(rs.getInt("c_re_level"));
					cb.setC_ref(rs.getInt("c_ref"));
					cb.setM_nick(rs.getString("m_id"));
					
					list.add(cb); // 한번 다 돌때마다 list에 저장
					System.out.println(rs.getInt("c_num"));
					System.out.println(rs.getString("c_content"));
					System.out.println(rs.getDate("c_date"));
					System.out.println(rs.getString("m_id"));
					System.out.println(rs.getInt("c_ref"));
					System.out.println(rs.getInt("c_re_step"));
					System.out.println(rs.getInt("c_re_level"));
				}
			} catch (Exception e) {
				System.out.println("comment_selectList()오류:" + e.getMessage());
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
	
	//댓글삭제
		public int delComment(int c_num) throws Exception {
			int result = -1;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "delete from comment_board where c_num=?";
			try {
				conn = DBOracleConn.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, c_num);
				result = pstmt.executeUpdate();

			} catch (Exception e) {
				System.out.println("delComment()오류:" + e.getMessage());
			} finally {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			}
			return result;
		}
		
		//게시글에 달린 댓글 갯수
		public int c_Count(int b_num) throws SQLException {
			Comment_board cb = new Comment_board();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result=0;
			try {
				conn = DBOracleConn.getConnection();
				pstmt = conn.prepareStatement("select count(*) from comment_board where b_num=?");
				pstmt.setInt(1, b_num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result=rs.getInt(1);
				}
				
			} catch (Exception e) {
				System.out.println("getComment_board()오류:" + e.getMessage());
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
}
