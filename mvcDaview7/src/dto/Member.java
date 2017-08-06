package dto;

import java.sql.Date;

public class Member {
	private String m_id;
	private String m_pw;
	private String m_email;
	private String m_nick;
	private Date m_reg_date;
	private String email_auth;
	public String getEmail_auth() {
		return email_auth;
	}
	public void setEmail_auth(String email_auth) {
		this.email_auth = email_auth;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_nick() {
		return m_nick;
	}
	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}
	public Date getM_reg_date() {
		return m_reg_date;
	}
	public void setM_reg_date(Date m_reg_date) {
		this.m_reg_date = m_reg_date;
	}
	
}
