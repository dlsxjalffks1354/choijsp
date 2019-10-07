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
import com.study.jsp.command.BListCommand2;
import com.study.jsp.command.BModifyCommand;
import com.study.jsp.command.BReplyCommand;
import com.study.jsp.command.BReplyViewCommand;
import com.study.jsp.command.BSearchCommand;
import com.study.jsp.command.BWriteCommand;
import com.study.jsp.command.MCommand;
import com.study.jsp.command.MCommentListCommand;
import com.study.jsp.command.MContentCommand;
import com.study.jsp.command.MContentCommand2;
import com.study.jsp.command.MDeleteCommand;
import com.study.jsp.command.MListCommand;
import com.study.jsp.command.MListCommand2;
import com.study.jsp.command.MMSearchCommand;
import com.study.jsp.command.MCommentListCommand;
import com.study.jsp.command.MSearchCommand;
import com.study.jsp.command.MuserSearchCommand;
import com.study.jsp.command.MBanCommand;
import com.study.jsp.command.MCSearchCommand;
import com.study.jsp.command.NCommand;
import com.study.jsp.command.NDeleteCommand;
import com.study.jsp.command.NListCommand;
import com.study.jsp.command.NSearchCommand;
import com.study.jsp.command.RListCommand;

@WebServlet("*.member")
public class MFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MFrontController() 
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
		MCommand command = null;
		NCommand command2 = null;
		BCommand command3 = null;
		BCommand command4 = null;
		MCommand command5 = null;
		BCommand command6 = null;
		String uri =request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		HttpSession session = null;
		session =request.getSession();
		int curPage = 1;
		if(session.getAttribute("cpage")!=null) {
			curPage= (int)session.getAttribute("cpage");
		}
		
		if(com.equals("/manager/content_view.member"))
		{
			System.out.println("여기는 content_view.member 입니다.");
			command = new MContentCommand();
			command.execute(request, response);
			viewPage="../manager/content_view.jsp";
		}
		else if(com.equals("/manager/content_view2.member"))
		{
			System.out.println("여기는 content_view2.member 입니다.");
			command = new MContentCommand2();
			command.execute(request, response);
			viewPage="../manager/content_view2.jsp";
		}
		else if(com.equals("/manager/content_view3.member"))
		{
			System.out.println("여기는 content_view3.member 입니다.");
			command3 = new BContentCommand();
			command3.execute(request, response);
			viewPage="../free/content_view.jsp";
		}
		else if(com.equals("/manager/modify_view.member"))
		{
			System.out.println("여기는 modify_view.member 입니다.");
			command = new MContentCommand();
			command.execute(request, response);
			viewPage="../manager/modify_view.jsp";	
		}else if(com.equals("/manager/list3.member"))
		{
			System.out.println("여기는 list3.member 입니다.");
			command6 = new MCommentListCommand();
			command6.execute(request, response);
			viewPage="../manager/list3.jsp";
		}
		else if(com.equals("/manager/list.member"))
		{
			System.out.println("여기는 list.member 입니다.");
			command = new MListCommand();
			command.execute(request, response);
			viewPage="../manager/list.jsp";
		}
		else if(com.equals("/manager/list2.member"))
		{
			System.out.println("여기는 게시글 관리 페이지 입니다.");
			command3 = new BListCommand2();
			command3.execute(request, response);
			viewPage="../manager/list2.jsp";
		}
		else if(com.equals("/manager/delete.member"))
		{
			System.out.println("여기는 delete.member 입니다.");
			command = new MDeleteCommand();
			System.out.println("삭제 하였습니다.");
			command.execute(request, response);
			System.out.println("확인중입니다.");
			viewPage="list.member?page="+curPage;
		}
		else if(com.equals("/manager/search.member")) {
			System.out.println("검색 시도");
			command = new MSearchCommand();
			command.execute(request, response);
			System.out.println(request.getParameter("결과가 완료되었습니다."));
			viewPage="../manager/search.jsp";
		}
		else if(com.equals("/main/main2.member")) {
			System.out.println("메인메뉴를 불러옵니다.");
			command3 = new BListCommand();
			command3.execute(request, response);
			command2 = new NListCommand();
			command2.execute(request, response);
			command4 = new RListCommand();
			command4.execute(request, response);
			System.out.println(request.getParameter("결과가 완료되었습니다."));
			viewPage="../main/main2.jsp";
		}
		else if(com.equals("/manager/main.member")) {
			System.out.println("관리자 메인메뉴를 불러옵니다.");
			command = new MListCommand();
			command.execute(request, response);
			command2 = new NListCommand();
			command2.execute(request, response);
			command3 = new BListCommand();
			command3.execute(request, response);
			command4 = new RListCommand();
			command4.execute(request, response);
			command5 = new MListCommand2();
			command5.execute(request, response);
			command6 = new MCommentListCommand();
			command6.execute(request, response);
			System.out.println(request.getParameter("결과가 완료되었습니다."));
			viewPage="../manager/main.jsp";
		}else if(com.equals("/manager/ban.member")) {
			System.out.println("사용자 사용정지를 수행합니다.");
			command = new MBanCommand();
			command.execute(request, response);
			return;
		}else if(com.equals("/manager/msearch.member")) {
			System.out.println("기간검색을 수행합니다.");
			command = new MMSearchCommand();
			command.execute(request, response);
			viewPage = "ArticleSearch.jsp";
		}
		else if(com.equals("/manager/usersearch.member")) {
			System.out.println("검색을 수행합니다.");
			command = new MuserSearchCommand();
			command.execute(request, response);
			viewPage = "userSearch.jsp";
		}
		else if(com.equals("/manager/csearch.member")) {
			System.out.println("기간검색을 수행합니다.");
			command6 = new MCSearchCommand();
			command6.execute(request, response);
			viewPage = "CommentSearch.jsp";
		}
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
