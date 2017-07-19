package service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommandProcess;

public class DeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String pageNum = request.getParameter("pageNum");
		
		request.setAttribute("b_num", b_num);
		request.setAttribute("pageNum", pageNum);

		return "Da/deleteForm.jsp";
	}

}
