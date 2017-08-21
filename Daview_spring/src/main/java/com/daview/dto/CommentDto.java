package com.daview.dto;

import java.util.Date;

public class CommentDto {
	private int c_num;
	private int b_num;
	private String m_nick;
	private Date c_date;
	private int c_ref;
	private int c_re_step;
	private int c_re_level;
	private String c_content;
	private int c_flag;
	
	
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
	public String getM_nick() {
		return m_nick;
	}
	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}
	public Date getC_date() {
		return c_date;
	}
	public void setC_date(Date c_date) {
		this.c_date = c_date;
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
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public int getC_flag() {
		return c_flag;
	}
	public void setC_flag(int c_flag) {
		this.c_flag = c_flag;
	}
	
}
