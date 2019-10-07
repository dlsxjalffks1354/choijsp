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
import com.study.jsp.command.NContentCommand;
import com.study.jsp.command.NModifyCommand;
import com.study.jsp.command.RContentCommand;
import com.study.jsp.command.RDeleteCommand;
import com.study.jsp.command.RDownloadCommand;
import com.study.jsp.command.RLikeCommand;
import com.study.jsp.command.RListCommand;
import com.study.jsp.command.RModifyCommand;
import com.study.jsp.command.RSearchCommand;
import com.study.jsp.command.RWriteCommand;

@WebServlet("*.rf")
public class RFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RFrontController() 
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
		
		if(com.equals("/reference/write_view.rf"))
		{
			
			System.out.println("여기는 write_view 입니다.");
			viewPage ="../reference/write_view.jsp";
		}else if(com.equals("/reference/write.rf"))
		{
			System.out.println("여기는 write.rf 입니다.");
			command =new RWriteCommand();
			command.execute(request, response);
			viewPage = "list.rf";
		}else if(com.equals("/reference/list.rf"))
		{
			System.out.println("여기는 list 입니다.");
			command = new RListCommand();
			command.execute(request, response);
			viewPage="../reference/list.jsp";
		}else if(com.equals("/reference/content_view.rf"))
		{
			System.out.println("여기는 content_view.rf 입니다.");
			command = new RContentCommand();
			command.execute(request, response);
			viewPage="../reference/content_view.jsp";
		}
		else if(com.equals("/manager/content_view.rf"))
		{
			System.out.println("여기는 content_view.rf 입니다.");
			command = new RContentCommand();
			command.execute(request, response);
			viewPage="../reference/content_view.jsp";
		}else if(com.equals("/reference/modify_view.rf"))
		{
			System.out.println("여기는 modify_view.rf 입니다.");
			command = new RContentCommand();
			command.execute(request, response);
			viewPage="../reference/modify_view.jsp";
		}else if(com.equals("/reference/modify.rf"))
		{
			System.out.println("여기는 modify.rf 입니다.");
			command = new RModifyCommand();
			command.execute(request, response);
			command = new RContentCommand();
			command.execute(request, response);
			viewPage="../reference/content_view.jsp";
		}else if(com.equals("/reference/delete.rf"))
		{
			System.out.println("여기는 delete.rf 입니다.");
			command = new RDeleteCommand();
			System.out.println("삭제 하였습니다.");
			command.execute(request, response);
			System.out.println("확인중입니다.");
			viewPage="list.rf?page="+curPage;
			System.out.println("이동하였습니다.");
		}else if(com.equals("/reference/reply_view.rf"))
		{
			System.out.println("여기는 reply_view.rf 입니다.");
			command = new BReplyViewCommand();
			command.execute(request, response);
			viewPage="../reference/reply_view.jsp";
		}
		else if(com.equals("/reference/reply.rf"))
		{
			System.out.println("여기는 reply.rf 입니다.");
			command = new BReplyCommand();
			System.out.println("답글을 달앗습니다.");
			command.execute(request, response);
			System.out.println("페이지를 이동합니다.");
			viewPage="list.rf?page="+curPage;
		}else if(com.equals("/reference/search.rf")) {
			System.out.println("검색 시도");
			command = new RSearchCommand();
			command.execute(request, response);
			viewPage="../reference/search.jsp";
		}else if(com.equals("/reference/download.rf")) {
			System.out.println("다운로드 시도");
			command = new RDownloadCommand();
			command.execute(request, response);
			return;
		}else if(com.equals("/reference/like.rf")) {
			System.out.println("좋아요를 누르셧습니다.");
			command = new RLikeCommand();
			command.execute(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
