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
	
	@Override
	public int countPaging() throws Exception {
		return sqlSession.selectOne(namespace+".countPaging");
	}
	private static final String namespace = 
			"com.daview.mapper.BoardMapper";
	@Override
	public List<BoardDto> listCriteria(Criteria cri) throws Exception {
		return sqlSession.selectList(namespace+".listCriteria",cri);
	}

	
}
