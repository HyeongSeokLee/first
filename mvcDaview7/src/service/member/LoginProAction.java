package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import service.CommandProcess;

public class LoginProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
	    String id = request.getParameter("id");
	    String pass = request.getParameter("password");
	    
	    //1정상 2패스워드 비정상 0 비회원
	    MemberDao dao = MemberDao.getInstance();
	    int result = dao.userCheck(id,pass);
	    if(result==1){
	    	HttpSession session = request.getSession();
	    	session.setAttribute("id", id);
	    }
	    
	    request.setAttribute("result", result);
		return "member/loginPro.jsp";
	}

}
