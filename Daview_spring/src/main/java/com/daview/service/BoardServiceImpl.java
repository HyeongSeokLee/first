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
	public int countListCriteria() throws Exception {
		return dao.countPaging(); 
	}
	@Override
	public List<BoardDto> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}
	
	

}
