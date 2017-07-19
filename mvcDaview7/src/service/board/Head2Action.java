package service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import service.CommandProcess;

//로그인 됐을 경우의 head 부분
public class Head2Action implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//HttpSession session = request.getSession();
		String id= request.getParameter("id");
		MemberDao m_dao=new MemberDao();
		String nick = m_dao.getNick(id);
	
		request.setAttribute("id", id);
		request.setAttribute("nick", nick);
		
		return "Da/head2.jsp"; 
	}

}
