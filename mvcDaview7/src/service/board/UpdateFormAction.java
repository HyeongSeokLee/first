package service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dao.MemberDao;
import dto.Board;
import service.CommandProcess;

public class UpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDao dao = BoardDao.getInstance();
		Board board = dao.getBoard(b_num);
		MemberDao m_dao = MemberDao.getInstance();
		String nick = m_dao.getNick(board.getM_id());
		
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("nick",nick);
		
		return "Da/updateForm.jsp";
	}

}
