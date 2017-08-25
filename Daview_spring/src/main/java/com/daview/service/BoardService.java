package com.daview.service;

import java.util.List;

import com.daview.dto.Criteria;

import com.daview.dto.BoardDto;


public interface BoardService {

	public List<BoardDto> listSearch(Criteria cri) throws Exception;
	public int listSearchCount(Criteria cri) throws Exception;
}
