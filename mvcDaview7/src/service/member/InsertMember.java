package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommandProcess;

public class InsertMember implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String email_auth = request.getParameter("email_auth");
		
		if(email==null){email="";}
		if(email_auth==null){email_auth="N";}
		
		request.setAttribute("email", email);
		request.setAttribute("email_auth", email_auth);
		
		return "member/insertMember.jsp";
	}

}
