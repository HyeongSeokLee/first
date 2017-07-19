package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import service.CommandProcess;

public class NickChkAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String dbnick = request.getParameter("nick");
	    MemberDao dao = MemberDao.getInstance();
	    int result = dao.nickCheck(dbnick);//id조회 결과 1이면 이미 존재, 0이면 id사용가능
	    request.setAttribute("result", result);
	    request.setAttribute("dbnick", dbnick);
		return "member/nick_chk.jsp";
	}

}
