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
import com.study.jsp.command.BLikeCommand;
import com.study.jsp.command.BListCommand;
import com.study.jsp.command.BModifyCommand;
import com.study.jsp.command.BReplyCommand;
import com.study.jsp.command.BReplyViewCommand;
import com.study.jsp.command.BSearchCommand;
import com.study.jsp.command.BWriteCommand;
import com.study.jsp.command.MCommand;
import com.study.jsp.command.MCommentListCommand;
import com.study.jsp.command.NContentCommand;
import com.study.jsp.command.NModifyCommand;
import com.study.jsp.command.RLikeCommand;

@WebServlet("*.bbs")
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
		MCommand command2 =null;
		
		String uri =request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		HttpSession session = null;
		session =request.getSession();
		int curPage = 1;
		if(session.getAttribute("cpage")!=null) {
			curPage= (int)session.getAttribute("cpage");
		}
		
		if(com.equals("/free/write_view.bbs"))
		{
			System.out.println("여기는 write_view 입니다.");
			viewPage ="../free/write_view.jsp";
		}else if(com.equals("/free/write.bbs"))
		{
			System.out.println("여기는 write.bbs 입니다.");
			command =new BWriteCommand();
			command.execute(request, response);
			viewPage = "list.bbs?page=1";
		}else if(com.equals("/free/list.bbs"))
		{
			System.out.println("여기는 list 입니다.");
			command = new BListCommand();
			command.execute(request, response);
			viewPage="../free/list.jsp";
		}else if(com.equals("/free/content_view.bbs"))
		{
			System.out.println("여기는 content_view.bbs 입니다.");
			command = new BContentCommand();
			command.execute(request, response);
			viewPage="../free/content_view.jsp";
		}
		else if(com.equals("/manager/content_view.bbs"))
		{
				System.out.println("여기는 content_view.bbs 입니다.");
				command = new BContentCommand();
				command.execute(request, response);
				viewPage="../free/content_view.jsp";
		}
		else if(com.equals("/free/modify_view.bbs"))
		{
			System.out.println("여기는 modify_view.bbs 입니다.");
			command = new BContentCommand();
			command.execute(request, response);
			viewPage="../free/modify_view.jsp";
		}else if(com.equals("/free/modify.bbs"))
		{
			System.out.println("여기는 modify.bbs 입니다.");
			command = new BModifyCommand();
			command.execute(request, response);
			command = new BContentCommand();
			command.execute(request, response);
			viewPage="../free/content_view.jsp";
		}else if(com.equals("/free/delete.bbs"))
		{
			System.out.println("여기는 delete.bbs 입니다.");
			command = new BDeleteCommand();
			System.out.println("삭제 하였습니다.");
			command.execute(request, response);
			System.out.println("확인중입니다.");
			viewPage="list.bbs?page="+curPage;
			System.out.println("이동하였습니다.");
			
		}
		else if(com.equals("/manager/delete.bbs"))
		{
			System.out.println("여기는 delete.bbs 입니다.");
			command = new BDeleteCommand();
			System.out.println("삭제 하였습니다.");
			command.execute(request, response);
			System.out.println("확인중입니다.");
			viewPage="list2.member?page="+curPage;
			System.out.println("이동하였습니다.");
		}
		
		else if(com.equals("/free/reply_view.bbs"))
		{
			System.out.println("여기는 reply_view.bbs 입니다.");
			command = new BReplyViewCommand();
			command.execute(request, response);
			viewPage="../free/reply_view.jsp";
		}
		else if(com.equals("/free/reply.bbs"))
		{
			System.out.println("여기는 reply.bbs 입니다.");
			command = new BReplyCommand();
			System.out.println("답글을 달앗습니다.");
			command.execute(request, response);
			System.out.println("페이지를 이동합니다.");
			viewPage="list.bbs?page="+curPage;
		}else if(com.equals("/free/search.bbs")) {
			System.out.println("검색 시도");
			command = new BSearchCommand();
			command.execute(request, response);
			viewPage="../free/search.jsp";
		}else if(com.equals("/free/like.bbs")) {
			System.out.println("좋아요를 누르셧습니다.");
			command = new BLikeCommand();
			command.execute(request, response);
			return;
		}
		
		else if(com.equals("/manager/list.bbs")) {
			System.out.println("댓글 관리 페이지로 이동합니다.");
			command2 = new MCommentListCommand();
			command2.execute(request, response);
			viewPage="../manager/list3.jsp";
		}
		else if(com.equals("/manager/content_view.bbs"))
		{
			System.out.println("여기는 content_view.bbs 입니다.");
			command = new BContentCommand();
			command.execute(request, response);
			viewPage="../free/content_view.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
