package com.study.jsp.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.BCommand;
import com.study.jsp.command.BContentCommand;
import com.study.jsp.command.BDeleteCommand;
import com.study.jsp.command.BListCommand;
import com.study.jsp.command.BModifyCommand;
import com.study.jsp.command.BReplyCommand;
import com.study.jsp.command.BReplyViewCommand;
import com.study.jsp.command.BWriteCommand;

@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BFrontController() 
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
		BCommand command = null;
		
		String uri =request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		HttpSession session = null;
		session =request.getSession();
		int curPage = 1;
		if(session.getAttribute("cpage")!=null) {
			curPage= (int)session.getAttribute("cpage");
		}
		
		if(com.equals("/write_view.do"))
		{
			System.out.println("여기는 write_view 입니다.");
			viewPage ="write_view.jsp";
		}else if(com.equals("/write.do"))
		{
			System.out.println("여기는 write.do입니다.");
			command =new BWriteCommand();
			command.execute(request, response);
			viewPage = "list.do";
		}else if(com.equals("/list.do"))
		{
			System.out.println("여기는 list 입니다.");
			command = new BListCommand();
			command.execute(request, response);
			viewPage="list.jsp";
		}else if(com.equals("/content_view.do"))
		{
			System.out.println("여기는 content_view.do 입니다.");
			command = new BContentCommand();
			command.execute(request, response);
			viewPage="content_view.jsp";
		}else if(com.equals("/modify_view.do"))
		{
			System.out.println("여기는 modify_view.do 입니다.");
			command = new BContentCommand();
			command.execute(request, response);
			viewPage="modify_view.jsp";
		}else if(com.equals("/modify.do"))
		{
			System.out.println("여기는 modify.do 입니다.");
			command = new BModifyCommand();
			command.execute(request, response);
			
			
			command = new BContentCommand();
			command.execute(request, response);
			viewPage="content_view.jsp";
		}else if(com.equals("/delete.do"))
		{
			System.out.println("여기는 delete.do 입니다.");
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage="list.do?page="+curPage;
		}else if(com.equals("/reply_view.do"))
		{
			System.out.println("여기는 reply_view.do 입니다.");
			command = new BReplyViewCommand();
			command.execute(request, response);
			viewPage="reply_view.jsp";
		}
		else if(com.equals("/reply.do"))
		{
			System.out.println("여기는 reply.do 입니다.");
			command = new BReplyCommand();
			command.execute(request, response);
			viewPage="list.do?page="+curPage;
		}
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
