package com.daview.dao;

import java.util.List;

import com.daview.dto.BoardDto;
import com.daview.dto.Criteria;



public interface BoardDao {
	public List<BoardDto> listCriteria(Criteria cri) throws Exception;//페이지
	public int countPaging()throws Exception;
}
