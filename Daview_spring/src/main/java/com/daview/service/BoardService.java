package com.daview.service;

import java.util.List;

import com.daview.dto.Criteria;

import com.daview.dto.BoardDto;


public interface BoardService {

	public List<BoardDto> listSearch(Criteria cri) throws Exception;
	public int listSearchCount(Criteria cri) throws Exception;
	public BoardDto getBoardView(int b_num) throws Exception;
	public int deleteBoard(int b_num) throws Exception;
	public int createBoard(BoardDto dto) throws Exception;
	public int updateBoard(BoardDto dto) throws Exception;
}
