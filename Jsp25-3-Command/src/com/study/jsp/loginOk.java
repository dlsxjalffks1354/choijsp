package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class loginOk implements Service  
{
	
    public loginOk() 
    {
       
    }

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("loginOk");
		request.setCharacterEncoding("UTF-8");
		String id =request.getParameter("id");
		String pw =request.getParameter("pw");
		
		
		MemberDTO dto =new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		
		MemberDAO dao = MemberDAO.getInstance();
	 	int checkNum = dao.userCheck(id, pw);
	 	
	 	response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
	 	
	 	if(checkNum == -1)
	 	{
	 		writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("       alert(\"아이디가 존재하지 않습니다.\");");
			writer.println("     histroy.back();");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
	 	}
	 	else if(checkNum == 0)
	 	{
	 		writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("       alert(\"비밀번호가 틀립니다.\");");
			writer.println("     history.back();");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
	 	}
	 	else if(checkNum == 1)
	 	{
	 		MemberDTO dto2 = dao.getMember(id);
	 		String name = dto2.getName();
	 		HttpSession session = request.getSession();
	 		session.setAttribute("id", id);
	 		session.setAttribute("name", name);
	 		session.setAttribute("ValidMem", "yes");
	 		
	 		writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("       alert(\"로그인성공 환영합니다.\");");
			writer.println("    document.location.href=\"main.jsp\";");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
	 	}	
	}
}
