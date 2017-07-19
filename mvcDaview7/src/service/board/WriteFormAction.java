package service.board;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import service.CommandProcess;

public class WriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");// session체크

		System.out.println("writeFormAction id::" + id);

		MemberDao m_dao = new MemberDao();
		String nick = m_dao.getNick(id);

		String b_star = "";
		int b_num = 0;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("b_num", b_num);
		request.setAttribute("id", id);
		request.setAttribute("nick", nick);

		return "Da/writeForm.jsp";
	}

}
