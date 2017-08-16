package com.daview.dao;

import com.daview.dto.MemberDto;

public interface MemberDao {
	public int insertMember(MemberDto md);
	public int nickChk(String m_nick);
	public int userChk(MemberDto md);
}
