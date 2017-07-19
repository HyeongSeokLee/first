package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;
import dao.CommentDao;
import dao.MemberDao;
import dto.Board;
import dto.Comment_board;
import service.CommandProcess;

public class ViewAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");//session체크

	 	int b_num = Integer.parseInt(request.getParameter("b_num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDao dao = BoardDao.getInstance();

		Board board = dao.getBoard(b_num);
		MemberDao m_dao = MemberDao.getInstance();
		String nick = m_dao.getNick(board.getM_id());
		

		
		String c_nick=m_dao.getNick(id);//현재 접속한 아이디의 닉네임표시
		
		//댓글 관련 객체생성
		CommentDao c_dao = CommentDao.getInstance();
		Comment_board cob= c_dao.getComment_board(b_num);
		
		int c_num = 0;
		String cm_id = id; 
		int cb_num = b_num;
		int c_ref = cob.getC_ref();
		int c_re_step =cob.getC_re_step();
		int c_re_level = cob.getC_re_level();
		
		
		List<Comment_board> list = c_dao.selectList(b_num);
		
		request.setAttribute("list", list);//해당 게시글에 달린 댓글들 정보 담겨져있음
		request.setAttribute("board", board); //클릭한 게시글에 대한 정보 담겨져있는 board
		request.setAttribute("nick", nick);//현재 접속한 아이디의 닉네임 넘김
		request.setAttribute("cob",cob);
		
		request.setAttribute("c_num",c_num );
		request.setAttribute("m_id",id );
		request.setAttribute("b_num",cb_num);
		request.setAttribute("c_ref",c_ref );
		request.setAttribute("c_re_step", c_re_step);
		request.setAttribute("c_re_level", c_re_level);
		request.setAttribute("pageNum", pageNum);
		
		request.setAttribute("id", id);
		request.setAttribute("nick", c_nick);
	  
		return "Da/view.jsp";
	}

}
