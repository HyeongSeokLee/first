package dto;

import java.util.Date;

import dao.CommentDao;
import dao.MemberDao;

public class Board {
	private int b_num;
	private String b_subject;
	private String b_content;
	private int b_rc;
	private String b_pw;
	private Date b_reg_date;
	private String b_part;
	private String m_id;
	private String b_star;
	private String m_nick; //닉네임 얻기위함 (테이블에는 따로 추가 x)
	private int c_count; //댓글 달린 갯수
	private String date;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getC_count() {
		return c_count;
	}
	public void setC_count(int b_num) throws Exception{
		CommentDao c_dao = CommentDao.getInstance();
		this.c_count = c_dao.c_Count(b_num);
	}
	public String getM_nick() {
		return m_nick;
	}
	public void setM_nick(String id) throws Exception{
		MemberDao m_dao = MemberDao.getInstance();
		this.m_nick = m_dao.getNick(id);
	}
	public String getB_star() {
		return b_star;
	}
	public void setB_star(String b_star) {
		this.b_star = b_star;
	}
	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	public String getB_subject() {
		return b_subject;
	}
	public void setB_subject(String b_subject) {
		this.b_subject = b_subject;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public int getB_rc() {
		return b_rc;
	}
	public void setB_rc(int b_rc) {
		this.b_rc = b_rc;
	}
	public String getB_pw() {
		return b_pw;
	}
	public void setB_pw(String b_pw) {
		this.b_pw = b_pw;
	}
	public Date getB_reg_date() {
		return b_reg_date;
	}
	public void setB_reg_date(Date b_reg_date) {
		this.b_reg_date = b_reg_date;
	}
	public String getB_part() {
		return b_part;
	}
	public void setB_part(String b_part) {
		this.b_part = b_part;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	
	
}
