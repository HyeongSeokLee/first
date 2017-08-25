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
	
	

}
