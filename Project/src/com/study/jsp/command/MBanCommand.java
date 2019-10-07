package com.study.jsp.command;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.MDao;

public class MBanCommand implements MCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = null;
		session = request.getSession();
		String bUser = request.getParameter("Id");
		
		MDao dao = new MDao();
		
		String result = dao.ban(bUser);	
		
		//System.out.println("c : " + result);
		
		if(result == "y") {
			try {
				System.out.println("밴 성공");
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out = response.getWriter();
			
				out.println("<html><head></head><body>");
				out.println("<script languange = \"javascript\">");
				out.println("	alert(\"밴하셨습니다.\")");
				out.println("	document.location.href=\"../manager/content_view.member?id="+ bUser +"\";");
				out.println("</script>");
				out.println("</body></html>");
				out.close();
				
			} catch(Exception e) {}
		} else if(result == "n") {
			try {
				System.out.println("밴 해제 성공");
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out = response.getWriter();
			
				out.println("<html><head></head><body>");
				out.println("<script languange = \"javascript\">");
				out.println("	alert(\"밴이 해제되었습니다.\")");
				out.println("	document.location.href=\"../manager/content_view.member?id="+ bUser +"\";");
				out.println("</script>");
				out.println("</body></html>");
				out.close();
				
			} catch(Exception e) {}
		} else {
			try {
				System.out.println("밴 실패");
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out = response.getWriter();
		
				out.println("<html><head></head><body>");
				out.println("<script languange = \"javascript\">");
				out.println("	alert(\"밴하는중 오류가 발생했습니다.\")");
				out.println("	document.location.href=\"../manager/content_view.member?id="+ bUser +"\";");
				out.println("</script>");
				out.println("</body></html>");
				out.close();
			
			} catch(Exception e) {}
		}
	}
}
