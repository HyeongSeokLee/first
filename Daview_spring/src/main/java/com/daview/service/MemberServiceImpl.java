package com.daview.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.daview.dao.MemberDao;
import com.daview.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDao dao;
	
	@Override
	public int insertMember(MemberDto md) throws Exception {
		return dao.insertMember(md);
	}

	@Override
	public int nickChk(String m_nick) throws Exception {
		return dao.nickChk(m_nick);
	}

	@Override
	public int userChk(MemberDto md) throws Exception {
		return dao.userChk(md);
	}

	@Override
	public String getM_Nick(String m_email) throws Exception {
		return dao.getM_Nick(m_email);
	}
	
}
