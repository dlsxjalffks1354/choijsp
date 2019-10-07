package com.study.jsp.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.NCommand;
import com.study.jsp.command.NContentCommand;
import com.study.jsp.command.NDeleteCommand;
import com.study.jsp.command.NListCommand;
import com.study.jsp.command.NModifyCommand;
import com.study.jsp.command.NSearchCommand;
import com.study.jsp.command.NWriteCommand;

@WebServlet("*.nc")
public class NFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public NFrontController() 
    {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		
		String viewPage= null;
		NCommand command = null;
		
		String uri =request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		HttpSession session = null;
		session =request.getSession();
		int curPage = 1;
		if(session.getAttribute("cpage")!=null) {
			curPage= (int)session.getAttribute("cpage");
		}
		
		if(com.equals("/notice/write_view.nc"))
		{
			System.out.println("여기는 write_view 입니다.");
			viewPage ="../notice/write_view.jsp";
		}else if(com.equals("/notice/write.nc"))
		{
			System.out.println("여기는 write.nc 입니다.");
			command =new NWriteCommand();
			command.execute(request, response);
			viewPage = "list.nc?page=1";
		}else if(com.equals("/notice/list.nc"))
		{
			System.out.println("여기는 list 입니다.");
			command = new NListCommand();
			command.execute(request, response);
			viewPage="../notice/list.jsp";
		}else if(com.equals("/notice/content_view.nc"))
		{
			System.out.println("여기는 content_view.nc 입니다.");
			command = new NContentCommand();
			command.execute(request, response);
			viewPage="../notice/content_view.jsp";
		}else if(com.equals("/notice/modify_view.nc"))
		{
			System.out.println("여기는 modify_view.nc 입니다.");
			command = new NContentCommand();
			command.execute(request, response);
			viewPage="../notice/modify_view.jsp";
		}else if(com.equals("/notice/modify.nc"))
		{
			System.out.println("여기는 modify.nc 입니다.");
			command = new NModifyCommand();
			command.execute(request, response);
			command = new NContentCommand();
			command.execute(request, response);
			viewPage="../notice/content_view.jsp";
		}else if(com.equals("/notice/delete.nc"))
		{
			System.out.println("여기는 delete.nc 입니다.");
			command = new NDeleteCommand();
			System.out.println("삭제 하였습니다.");
			command.execute(request, response);
			System.out.println("확인중입니다.");
			viewPage="list.nc?page="+curPage;
			System.out.println("이동하였습니다.");
		}else if(com.equals("/notice/search.nc")) {
			System.out.println("검색 시도");
			command = new NSearchCommand();
			command.execute(request, response);
			System.out.println(request.getParameter("결과가 완료되었습니다."));
			viewPage="../notice/search.jsp";
		}
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
