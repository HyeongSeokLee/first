package service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import service.CommandProcess;

public class DelProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String pageNum = request.getParameter("pageNum");
		String b_pw = request.getParameter("b_pw");
		
		BoardDao dao = BoardDao.getInstance();
		
		int passChk=dao.getPassword(b_num, b_pw);//글 번호와 비밀번호 확인 변수 1이면 맞음
		int delBoardChk=dao.delBoard(b_num);//삭제 여부 확인 변수 1이면 삭제 된 것임


		request.setAttribute("b_num", b_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("passChk", passChk);
		request.setAttribute("delBoardChk", delBoardChk);
		
		return "Da/delPro.jsp";
	}

}
