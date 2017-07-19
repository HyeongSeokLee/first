package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommandProcess;

@WebServlet(description = "mvc컨트롤러")
public class Controller extends HttpServlet {
	private Map commandMap = new HashMap();

	@Override
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("propertyConfig");
		Properties ps = new Properties();
		FileInputStream f = null;
		try {
			String configFilePath = config.getServletContext().getRealPath(props);
			f = new FileInputStream(configFilePath);
			ps.load(f);
		} catch (IOException e) {
			throw new ServletException(e);
		} finally{
			if(f!=null) try{f.close();}catch(IOException e){}
		}
		Iterator keyIter = ps.keySet().iterator();
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();// ex)/view.do
			String className = ps.getProperty(command);// ex) service.ViewAction
			System.out.println("init command= " + command);
			System.out.println("init classsName= " + className);
			try {
				Class commandClass = Class.forName(className);// 클래스화
				Object commandInstance = commandClass.newInstance();// 클래스에
																	// 인스턴스시킴
				commandMap.put(command, commandInstance);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		requestPro(request,response);
	}

	private void requestPro(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		String view = null;
		CommandProcess com = null;
		try{
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath())==0){
				command=command.substring(request.getContextPath().length());
			}
			 System.out.println("requestPro + command="+command);
			 com=(CommandProcess)commandMap.get(command);//list.do에 해당하는 패키지.클래스 추출
			 view=com.requestPro(request, response);//com이 ListAction이 됨.
			 System.out.println("requestPro + view="+view);
		}catch(Throwable e){}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
