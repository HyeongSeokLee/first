package service.c_board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDao;
import dao.MemberDao;
import dto.Comment_board;
import service.CommandProcess;

public class CommentDapAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

	request.setCharacterEncoding("utf-8");
	HttpSession session=request.getSession();
	String id = (String) session.getAttribute("id");//session체크

	/*view에서 대댓글시 넘어온 값들 parameter로 받기*/
	int c_num = Integer.parseInt(request.getParameter("c_num"));
	int b_num = Integer.parseInt(request.getParameter("b_num"));
	String m_id = id;
	int c_ref = Integer.parseInt(request.getParameter("c_ref"));
	int c_re_step = Integer.parseInt(request.getParameter("c_re_step"));
	int c_re_level = Integer.parseInt(request.getParameter("c_re_level"));
	String c_content=request.getParameter("c_content");
	String pageNum = request.getParameter("pageNum");
	System.out.println("c_re_step볼래"+c_re_step);


	 
	MemberDao m_dao = MemberDao.getInstance();
	String c_nick=m_dao.getNick(id);//현재 접속한 아이디의 닉네임표시
	

	request.setAttribute("c_num", c_num);
	request.setAttribute("b_num", b_num);
	request.setAttribute("m_id", m_id);
	request.setAttribute("c_ref", c_ref);
	request.setAttribute("c_re_step", c_re_step);
	request.setAttribute("c_re_level", c_re_level);
	request.setAttribute("c_content", c_content);
	request.setAttribute("id", id);
	request.setAttribute("c_nick", c_nick);
	request.setAttribute("pageNum", pageNum);

	return "comment/comment_dap.jsp";
	}

}
