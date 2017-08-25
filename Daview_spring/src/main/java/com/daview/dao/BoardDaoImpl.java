package com.daview.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daview.dto.BoardDto;
import com.daview.dto.Criteria;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Inject
	SqlSession sqlSession;
	private static final String namespace = 
			"com.daview.mapper.BoardMapper";
	
	@Override
	public List<BoardDto> listSearch(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".listSearch",cri);
	}
	@Override
	public int listSearchCount(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".listSearchCount",cri);
	}

	
}
