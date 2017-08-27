package com.daview.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daview.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = 
			"com.daview.mapper.MemberMapper";
	//회원 등록
	@Override
	public int insertMember(MemberDto md) {
		int result=sqlSession.insert(namespace + ".insertMember", md);
		return result;
	}

	//닉네임 중복 체크
	@Override
	public int nickChk(String m_nick) {
		int result=sqlSession.selectOne(namespace+".nickChk",m_nick);
		
		return result;
	}
	
	//로그인 처리를위한 유저 확인
	@Override
	public int userChk(MemberDto md) {
		int result=sqlSession.selectOne(namespace+".userChk", md);

		return result;
	}
	
	
	@Override
	public String getM_Nick(String m_email) {
		String m_nick= sqlSession.selectOne(namespace+".getM_Nick", m_email);
		return m_nick;
	}

	@Override
	public int updateMember(MemberDto dto) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace+".updateMember", dto);
	}

	@Override
	public int deleteMember(MemberDto dto) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace+".deleteMember", dto);
	}
	
	
}
