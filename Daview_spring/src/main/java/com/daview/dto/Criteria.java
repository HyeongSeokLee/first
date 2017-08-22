package com.daview.dto;

public class Criteria {
	private int page; //현재 페이지
	private int perPageNum;//페이지 범위 ex) 1~10  
	
	public Criteria(){
		this.page=1;
		this.perPageNum = 10;
	}
	
	public void setPage(int page){
		if(page <=0){
			this.page = 1;
			return;
		}
		
		this.page=page;
	}
	
	public void setPerPageNum(int perPageNum){
		if(perPageNum<=0|| perPageNum >100){
			this.perPageNum=10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPage(){
		return this.page;
	}
	
	public int getPageStart(){
		return(this.page-1)*perPageNum;
	}
	
	public int getPerPageNum(){
		return this.perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria[page="+page+", "+"perPageNum="+ perPageNum+"]";
	}
	
	
}
