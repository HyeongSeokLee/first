package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import service.CommandProcess;

public class DeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");// session에서 체크한 id
		
		
		if (id == "" || id == null) {// 로그인 하여 session정보를 세팅 후 조회..
			return "member/deletePro.jsp";
		}
		
		String dbid = request.getParameter("id");// 회원 id를 파라미터로 받음
		String dbpass = request.getParameter("password");
		MemberDao dao = MemberDao.getInstance();
		/* 비밀번호 확인 */
		int result1 = dao.userCheck(dbid, dbpass);
		int result2 = dao.delMember(dbid);
		
		request.setAttribute("id", id);
		request.setAttribute("result1", result1);
		request.setAttribute("result2", result2);

		return "member/deletePro.jsp";
	}

}
