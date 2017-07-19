package service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;
import dao.MemberDao;
import dto.Board;
import service.CommandProcess;

public class WriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();;
		String id = (String) session.getAttribute("id");//session체크
		
		//request로 값 받기
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String b_subject = request.getParameter("b_subject");
		String b_content = request.getParameter("b_content");
		String b_part = request.getParameter("b_part");
		String b_star = request.getParameter("b_star");
		String b_pw = request.getParameter("b_pw");
		String pageNum = request.getParameter("pageNum");
		
		BoardDao dao = BoardDao.getInstance();
		MemberDao m_dao = MemberDao.getInstance();
		
		String nick = m_dao.getNick(id);//닉네임 가져오기
		
		Board board = new Board();
		/*writeForm에서 넘어온 값들 board 담기*/
		board.setB_num(b_num);
		board.setB_subject(b_subject);
		board.setB_content(b_content);
		board.setB_part(b_part);
		board.setB_star(b_star);
		board.setB_pw(b_pw);

		int result = dao.insert(board,id);//입력한 row의 수를 리턴
		
		request.setAttribute("result", result);
		request.setAttribute("nick", nick);
		request.setAttribute("id", id);
		request.setAttribute("pageNum", pageNum);
		return "Da/writePro.jsp";
	}

}
