package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import dto.Member;
import service.CommandProcess;

public class MemberModifyProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Member member = new Member();
		member.setM_email(request.getParameter("m_email"));
		member.setM_id(request.getParameter("m_id"));
		member.setM_nick(request.getParameter("m_nick"));
		member.setM_pw(request.getParameter("m_pw"));
			
	    MemberDao dao = MemberDao.getInstance();
		int result = dao.updateMember(member);
		request.setAttribute("result", result);

		return "member/memberModifyPro.jsp";
	}

}
