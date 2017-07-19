package service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dto.Board;
import service.CommandProcess;

public class UpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		request.setCharacterEncoding("utf-8");
		
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String b_subject = request.getParameter("b_subject");
		String b_content = request.getParameter("b_content");
		String b_part = request.getParameter("b_part");
		String b_star = request.getParameter("b_star");
		String b_pw = request.getParameter("b_pw");
		String pageNum = request.getParameter("pageNum");
		
		
		Board board = new Board();
		
/*		updateForm에서 넘어온 값들 board에 담기*/
		board.setB_num(b_num);
		board.setB_subject(b_subject);
		board.setB_content(b_content);
		board.setB_part(b_part);
		board.setB_star(b_star);
		board.setB_pw(b_pw);
		BoardDao dao = BoardDao.getInstance();
		
		
		int isTrue = dao.getPassword(board.getB_num(), board.getB_pw());//글 비번과 같은지 확인
		int check = dao.update(board);//글 수정 맞으면 1

		request.setAttribute("check",check);
		request.setAttribute("isTrue", isTrue);
		request.setAttribute("pageNum", pageNum);

	
		return "Da/updatePro.jsp";
	}

}
