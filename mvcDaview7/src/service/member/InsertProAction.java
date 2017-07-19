package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import dto.Member;
import service.CommandProcess;

public class InsertProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		Member member = new Member();
		MemberDao dao = MemberDao.getInstance();
		
		member.setM_email(request.getParameter("m_email"));
		member.setM_id(request.getParameter("m_id"));
		member.setM_nick(request.getParameter("m_nick"));
		member.setM_pw(request.getParameter("m_pw"));
	    int result = dao.insertMember(member);
	    
	    request.setAttribute("result", result);
		
		return "member/insertPro.jsp";
	}

}
