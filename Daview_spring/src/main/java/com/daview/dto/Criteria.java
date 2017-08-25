package com.daview.dto;

public class Criteria {
	private int page; //현재 페이지
	private int perPageNum;//페이지 범위 ex) 1~10  
	private String searchType;//(제목,내용,작성자 등등 검색조건)
	private String keyword; //찾을 내용
	private String b_part; //분류 명
	

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getB_part() {
		return b_part;
	}

	public void setB_part(String b_part) {
		this.b_part = b_part;
	}

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
