package com.daview.dto;

public class PageMaker {
	
	private int totalCount;			// 전체 게시글 수
	private int startPage;  		// 페이징 범위 시작페이지
	private int endPage;			// 페이징 범위 끝 페이지
	private int endendPage;			// 맨 마지막 페이지
	private boolean prev;			// 이전 (on/off)
	private boolean next;			// 다음 (on/off)
	private int displayPageNum = 10;// 보여질 페이지 수
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		int endendPage = (int)Math.ceil((double)totalCount/displayPageNum);
		setEndendPage(endendPage);
		calcData();
	}
	
	public int getEndendPage() {
		return endendPage;
	}
	public void setEndendPage(int endendPage) {
		this.endendPage = endendPage;
	}
	private void calcData(){
		
		this.endPage = (int) (Math.ceil(cri.getPage()/
					(double) displayPageNum) * displayPageNum); // 현재페이지를 기준으로 한 끝 페이지,게시글 수 고려X
		
		startPage = (endPage - displayPageNum)+1; //끝 페이지를 기준으로 시작페이지 구함
		
		int tempEndPage = (int) (Math.ceil(totalCount/(double)cri.getPerPageNum())); //총 게시글 수로 끝페이지 고려
		
		if(endPage > tempEndPage){ //협상
			endPage = tempEndPage;
		}
		
		prev = startPage ==1 ? false : true; 
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true; 
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public Criteria getCri() {
		return cri;
	}
	
	
	
	
}
