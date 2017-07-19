package dto;

import java.sql.Date;

import dao.MemberDao;

public class Comment_board {
 private int c_num;
 private int b_num;
 private String m_id;
 private Date c_date;
 private String c_content;
 private int c_ref;		//답변글끼리 그룹
 private int c_re_step;	//ref내의 순서
 private int c_re_level;	//들여쓰기
 private String m_nick;
 
 
public String getM_nick() {
	return m_nick;
}
public void setM_nick(String id) throws Exception{
	MemberDao m_dao = MemberDao.getInstance();
	this.m_nick = m_dao.getNick(id);
}
public String getM_id() {
	return m_id;
}
public void setM_id(String m_id) {
	this.m_id = m_id;
}
public int getC_num() {
	return c_num;
}
public void setC_num(int c_num) {
	this.c_num = c_num;
}
public int getB_num() {
	return b_num;
}
public void setB_num(int b_num) {
	this.b_num = b_num;
}
public Date getC_date() {
	return c_date;
}
public void setC_date(Date c_date) {
	this.c_date = c_date;
}
public String getC_content() {
	return c_content;
}
public void setC_content(String c_content) {
	this.c_content = c_content;
}
public int getC_ref() {
	return c_ref;
}
public void setC_ref(int c_ref) {
	this.c_ref = c_ref;
}
public int getC_re_step() {
	return c_re_step;
}
public void setC_re_step(int c_re_step) {
	this.c_re_step = c_re_step;
}
public int getC_re_level() {
	return c_re_level;
}
public void setC_re_level(int c_re_level) {
	this.c_re_level = c_re_level;
}
 

 
}
