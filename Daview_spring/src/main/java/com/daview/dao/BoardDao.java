package com.daview.dao;

import java.util.List;
import com.daview.dto.BoardDto;
import com.daview.dto.Criteria;



public interface BoardDao {
	public List<BoardDto> listSearch(Criteria cri) throws Exception;
	public int listSearchCount(Criteria cri) throws Exception;
	
	public BoardDto getBoardView(int b_num) throws Exception;
}
