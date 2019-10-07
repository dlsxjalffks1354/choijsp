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
import com.study.jsp.command.BSearchCommand;
import com.study.jsp.command.CCommand;
import com.study.jsp.command.CDeleteCommand;
import com.study.jsp.command.CReplyAction;
import com.study.jsp.command.CReplyCommand;
import com.study.jsp.command.CReplyCommandFormAction;
import com.study.jsp.command.CommentUpdateAction;
import com.study.jsp.command.CommentUpdateFormAction;
import com.study.jsp.command.CommentWriteAction;

@WebServlet("*.co")
public class CFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CFrontController() 
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
		CCommand command = null;
		
		String uri =request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		HttpSession session = null;
		session =request.getSession();
		int curPage = 1;
		if(session.getAttribute("cpage")!=null) {
			curPage= (int)session.getAttribute("cpage");
		}
		
		if(com.equals("/free/CommentWriteAction.co"))
		{
			System.out.println("여기는 CommentWriteAction.co 입니다.");
			command =new CommentWriteAction();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			viewPage="../free/content_view.jsp";
			return;
			
		}else if(com.equals("/free/Commentreply.co")) {
			System.out.println("답글페이지 이동");
			command = new CReplyCommand();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			viewPage="../free/CommentReplyFrom.jsp";
			
		}else if(com.equals("/free/CommentReplyAction.co")) 
		{
			System.out.println("답글을 작성하였습니다.");
			command = new CReplyAction();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}else if(com.equals("/free/CommentDeleteAction.co")) 
		{
			System.out.println("답글을 삭제하였습니다.");
			command = new CDeleteCommand();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}else if(com.equals("/free/CommentUpdateFormAction.co")) 
		{
			System.out.println("CommentUpdateForm 실행");
			command = new CommentUpdateFormAction();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			viewPage = "../free/CommentUpdateForm.jsp";
		}else if(com.equals("/free/CommentUpdateAction.co")) 
		{
			System.out.println("CommentUpdateAction 실행");
			command = new CommentUpdateAction();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}else if(com.equals("/reference/CommentWriteAction.co"))
		{
			System.out.println("여기는 CommentWriteAction.co 입니다.");
			command =new CommentWriteAction();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			viewPage="../reference/content_view.jsp";
			return;
			
		}else if(com.equals("/reference/Commentreply.co")) {
			System.out.println("답글페이지 이동");
			command = new CReplyCommand();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			viewPage="../reference/CommentReplyFrom.jsp";
			
		}else if(com.equals("/reference/CommentReplyAction.co")) 
		{
			System.out.println("답글을 작성하였습니다.");
			command = new CReplyAction();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}else if(com.equals("/reference/CommentDeleteAction.co")) 
		{
			System.out.println("답글을 삭제하였습니다.");
			command = new CDeleteCommand();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}else if(com.equals("/manager/CommentDeleteAction.co")) 
		{
			command = new CDeleteCommand();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("답글을 삭제하였습니다.");
		}
		
		else if(com.equals("/reference/CommentUpdateFormAction.co")) 
		{
			System.out.println("CommentUpdateForm 실행");
			command = new CommentUpdateFormAction();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			viewPage = "../reference/CommentUpdateForm.jsp";
		}else if(com.equals("/reference/CommentUpdateAction.co")) 
		{
			System.out.println("CommentUpdateAction 실행");
			command = new CommentUpdateAction();
			try {
				command.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
