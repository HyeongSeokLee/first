package com.daview.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.daview.dao.BoardDao;
import com.daview.dto.BoardDto;
import com.daview.dto.Criteria;

@Service
public class BoardServiceImpl implements BoardService{
	@Inject
	private BoardDao dao;
	

	@Override
	public List<BoardDto> listSearch(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearch(cri);
	}
	@Override
	public int listSearchCount(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchCount(cri);
	}
	@Override
	public BoardDto getBoardView(int b_num) throws Exception {
		// TODO Auto-generated method stub
		return dao.getBoardView(b_num);
	}
	@Override
	public int deleteBoard(int b_num) throws Exception {
		return dao.deleteBoard(b_num);
	}
	@Override
	public int createBoard(BoardDto dto) throws Exception {
		return dao.createBoard(dto);
	}
	@Override
	public int updateBoard(BoardDto dto) throws Exception {
		return dao.updateBoard(dto);
	}
	
	

}
