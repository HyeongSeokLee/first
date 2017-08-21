package com.daview.dto;

import java.util.Date;

public class BlameDto {

	private int blame_id;
	private int blame_type;
	private int target_id;
	private int target_type;
	private String target_nick;
	private String accuser_nick;
	private Date blame_date;
	
	
	public int getBlame_id() {
		return blame_id;
	}
	public void setBlame_id(int blame_id) {
		this.blame_id = blame_id;
	}
	public int getBlame_type() {
		return blame_type;
	}
	public void setBlame_type(int blame_type) {
		this.blame_type = blame_type;
	}
	public int getTarget_id() {
		return target_id;
	}
	public void setTarget_id(int target_id) {
		this.target_id = target_id;
	}
	public int getTarget_type() {
		return target_type;
	}
	public void setTarget_type(int target_type) {
		this.target_type = target_type;
	}
	public String getTarget_nick() {
		return target_nick;
	}
	public void setTarget_nick(String target_nick) {
		this.target_nick = target_nick;
	}
	public String getAccuser_nick() {
		return accuser_nick;
	}
	public void setAccuser_nick(String accuser_nick) {
		this.accuser_nick = accuser_nick;
	}
	public Date getBlame_date() {
		return blame_date;
	}
	public void setBlame_date(Date blame_date) {
		this.blame_date = blame_date;
	}
	
}
