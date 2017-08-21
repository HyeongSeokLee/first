package com.daview.service;

import com.daview.dto.MemberDto;

public interface MemberService {

	public int insertMember(MemberDto md) throws Exception;

	public int nickChk(String m_nick) throws Exception;


	public int userChk(MemberDto md) throws Exception;

	public String getM_Nick(String m_email) throws Exception;
}
