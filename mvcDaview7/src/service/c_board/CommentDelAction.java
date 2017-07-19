package service.c_board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDao;
import dao.MemberDao;
import service.CommandProcess;

public class CommentDelAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		request.setCharacterEncoding("utf-8");

		int c_num = Integer.parseInt(request.getParameter("c_num"));
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String pageNum = request.getParameter("pageNum");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemberDao m_dao = MemberDao.getInstance();
		CommentDao c_dao = CommentDao.getInstance();
		
		String nick = m_dao.getNick(id); //접속된 아이디의 닉네임 가져오기

		
		int result=c_dao.delComment(c_num);//삭제 유/무 확인 1이면 삭제

		request.setAttribute("id", id);
		request.setAttribute("b_num", b_num);
		request.setAttribute("result", result);
		request.setAttribute("pageNum", pageNum);

		return "comment/comment_del.jsp";
	}

}
