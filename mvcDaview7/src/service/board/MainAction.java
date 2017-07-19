package service.board;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.BoardDao;
import dao.MemberDao;
import dto.Board;
import service.CommandProcess;

public class MainAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		BoardDao dao = BoardDao.getInstance();
		MemberDao m_dao=MemberDao.getInstance();
		

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String nick="";
		if(id==null || id.isEmpty()){id="";}
		else 
			nick=m_dao.getNick(id);
			//////아이디랑 닉네임은 세션으로 받아오고
		
		String b_part = request.getParameter("b_part");
		if(b_part==null||b_part.isEmpty()||b_part.equals("전체")){b_part="%";}


		String find_field = request.getParameter("find_field");  //검색 유형
		if( find_field==null){
			find_field="";
		}
		String find_name = request.getParameter("find_name"); //검색 내용
		if(find_name==null){
			find_name="";
		}		
		
		String pg = request.getParameter("curPage"); 		
		String x ="&find_field="+find_field+"&find_name="+find_name+"&b_part="+b_part; 
			
		 
		int curPage=1;
		if(pg!=null){ 
			curPage = Integer.parseInt(pg);  
		}
		
		int totalCount=0;
		if(b_part==null||b_part.isEmpty()){
			totalCount = dao.getTotal(find_field,find_name); // ssssssssssssss
			System.out.println("getTotal메소드 실행결과"+totalCount);
		}else{
			totalCount = dao.getTotal(find_field,find_name,b_part);
		}
		
		System.out.println("totalCount::"+totalCount);
		
		int countList = 10; //1페이지당 10개의 게시글
		if(request.getParameter("countList")!=null){  //카운트리스트를 받았다면
			session.setAttribute("countList", request.getParameter("countList")); //세션에추가
		}
		if(session.getAttribute("countList")!=null){
			countList = Integer.parseInt((String)session.getAttribute("countList"));
		}
		
		System.out.println("카운트리스트는"+countList);
		int countPage = 10; //10페이지씩 보기로 나타냄
		int totalPage = totalCount/countList;//총 게시글 수에서 게시글 갯수로 나눔 
		if(totalCount%countList>0){ //나머지가 존재한다면 ++해줌
			totalPage++;
		}
		
		System.out.println("totalPage::"+totalPage);
		if(totalPage<curPage){
			curPage = totalPage;  
		}
		int startPage=((curPage-1)/10)*10+1;//11부터 11~20이니깐
		int endPage = startPage+countPage-1; //startPage로부터 countPage만큼
		if(endPage>totalPage){
			endPage=totalPage; //끝을 재설정
		}
		int startList=countList*(curPage-1)+1;
		int endList=startList+countList-1;
		
		System.out.println(find_field+":"+find_name);
		ArrayList<Board> list = dao.getList(startList,endList,find_field,find_name,b_part);
		System.out.println("getList메소드 실행결과");		
		
		
		
		request.setAttribute("list", list);
		request.setAttribute("find_field", find_field);
		request.setAttribute("find_name", find_name);
		request.setAttribute("total", totalCount);
		request.setAttribute("pageCount", curPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("curPage", curPage);
		request.setAttribute("startRow", startList);
		request.setAttribute("endRow", endList);
		request.setAttribute("PAGESIZE", countPage);
		request.setAttribute("countList", countList);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("x", x);
		
		
		request.setAttribute("id", id);
		request.setAttribute("nick", nick);
		request.setAttribute("b_part", b_part);
		
		return "Da/main.jsp";
	
		/*--------------------내껴랑 연동할거야*/
		
		
		
		
	
		
		
		
		
			
			
			
			
			
		/*
	
		
		


		<!---------------------------------------------------------------------------------------------------------------->

		<table id="tab" align=center>
		<tr id="head"><th>No</th><th>Title</th><th>Writer</th><th>Reg_date</th><th>Views</th><th>Votes</th></tr>
		<%
			for(int i=0;i<list.size();i++){
				if(currentDay.equals(sdf.format(list.get(i).getReg_date()))){
					sdf3 = sdf4;
				}else{
					sdf3 = sdf5;
				}
		  countComments(list.get(i).getNo()) 
				out.print("<tr><td>"+list.get(i).getNo()
						+"</td><td id='title'><a href='showBoard.jsp?no="+list.get(i).getNo()+"'>"+list.get(i).getTitle()
						+"</a></td><td><a href='users/show_user/showUserPro.jsp?writer="+list.get(i).getWriter()+"'>"+list.get(i).getWriter()
						+"</td><td>"+sdf3.format(list.get(i).getReg_date())
						+"</td><td>"+list.get(i).getViews()
						+"</td><td>"+list.get(i).getVotes()
						+"</td></tr>");
			}
		%>
		<!---------------------------------------------------------------------------------------------------------------->

		</table>
		<div id="list">
		<%if(curPage!=1 && curPage!=0){ %>
		<a href="bulletin_board.jsp?curPage=1<%=x%>">처음</a>

		<%}
			int pre = ((curPage-1)/countPage)*10;
			if(pre>0){ 
		%>
		<a href="bulletin_board.jsp?curPage=<%=pre%><%=x%>">이전</a>

		<%} 
			for(int i=startPage;i<=endPage;i++){
			%><a href="bulletin_board.jsp?curPage=<%=i%><%=x%>"> <%
				if(i==curPage){
					%></a><b><%=i%> </b><%
				}else{
					%><%=i%></a><%
				}
			
			
		   }
			int next = ((curPage-1)/countPage+1)*10+1;
		%>
		<% if(curPage!=totalPage&&endPage-startPage+1==countPage){ %>
		<a href="bulletin_board.jsp?curPage=<%=next%><%=x%>">다음</a>
		<%}if(curPage!=totalPage){ %>
		<a href="bulletin_board.jsp?curPage=<%=totalPage %><%=x%>">끝</a>
		<%} %>
	
		<form  action="bulletin_board.jsp" method="get">
		<table align="center">
		<tr><td>
			<select name="searchType">
				<option value="title&text">제목+내용</option>
				<option value="title" selected="selected">제목</option>
				<option value="text">내용</option>
				<option value="writer">작성자</option>
			</select>
		</td><td><input type="text" name="searchParam"></td>
		<td><input type="submit" value="검색"></td></tr>
		</table>
		</form>


		</div>
		</body>
		</html>*/
		
		
		
		
		
		
		
	}

}
