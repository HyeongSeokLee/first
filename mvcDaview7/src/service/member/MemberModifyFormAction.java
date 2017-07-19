package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import dto.Member;
import service.CommandProcess;

public class MemberModifyFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();	
		String id = (String) session.getAttribute("id");//session체크
		MemberDao dao = MemberDao.getInstance();
		Member member = dao.memberInfo(id);
		request.setAttribute("member", member);
		request.setAttribute("id", id);
		return "member/memberModifyForm.jsp";
	}

}
