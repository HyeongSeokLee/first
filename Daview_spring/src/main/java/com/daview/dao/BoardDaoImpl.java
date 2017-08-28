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
	@Override
	public BoardDto getBoardView(int b_num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".getBoardView",b_num);
	}
	
	@Override
	public int deleteBoard(int b_num) throws Exception {
		return sqlSession.update(namespace+".deleteBoard",b_num);
	}
	@Override
	public int createBoard(BoardDto dto) throws Exception {
		return sqlSession.insert(namespace+".createBoard",dto);
	}
	@Override
	public int updateBoard(BoardDto dto) throws Exception {
		return sqlSession.update(namespace+".updateBoard",dto);
	}
	
}
