package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import service.CommandProcess;

public class IdChkAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {


		String dbid = request.getParameter("id");
		System.out.println(dbid);
	    MemberDao dao = MemberDao.getInstance();
	    int result = dao.userCheck(dbid);//id조회 결과 1이면 이미 존재, 0이면 id사용가능
	    
	    request.setAttribute("result", result);
	    request.setAttribute("dbid", dbid);
		return "member/id_chk.jsp";
	}

}
