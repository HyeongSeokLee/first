package com.daview.dto;

import java.util.Date;

public class BoardDto {
	private int b_num;
	private String b_subject;
	private String b_content;
	private int b_rc;
	private Date b_reg_date;
	private String b_part;
	private String m_nick;
	private String b_star;
	private int b_c_total;
	private int b_flag;
	private int b_notice;
	
	
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
	public String getM_nick() {
		return m_nick;
	}
	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}
	public String getB_star() {
		return b_star;
	}
	public void setB_star(String b_star) {
		this.b_star = b_star;
	}
	public int getB_c_total() {
		return b_c_total;
	}
	public void setB_c_total(int b_c_total) {
		this.b_c_total = b_c_total;
	}
	public int getB_flag() {
		return b_flag;
	}
	public void setB_flag(int b_flag) {
		this.b_flag = b_flag;
	}
	public int getB_notice() {
		return b_notice;
	}
	public void setB_notice(int b_notice) {
		this.b_notice = b_notice;
	}
	
	
}
