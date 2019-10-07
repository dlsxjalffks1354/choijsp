package com.study.jsp.sign;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ModifyProcess")
public class ModifyProcess extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
			
			boolean modify=true;
		    HttpSession session = request.getSession();
		    String id = (String)session.getAttribute("id");      
		    String pw = request.getParameter("pw");
		    String name = request.getParameter("name");
		    String Nickname = request.getParameter("Nickname");
		    String email = request.getParameter("eMail");
		    String address = request.getParameter("address");
	
			String json_data= "";
			
			if(modify)
			{
				MemberDAO dao = MemberDAO.getInstance();
				MemberDTO dto = new MemberDTO();
			
				dto.setId(id);
				dto.setPw(pw);
				dto.setName(name);
				dto.setNickname(Nickname);
				dto.seteMail(email);
				dto.setAddress(address);
			
			
				int ri =dao.updateMember(dto);
			
				  if(ri == 1) 
				  {
			       json_data = "{\"code\":\"success\", \"desc\":\"회원정보 수정이 완료되었습니다.\"}";
			       session.setAttribute("Nickname", dto.getNickname());
			      } else 
			      {
			        json_data = "{\"code\":\"fail\", \"desc\":\"회원정보 수정을 실패했습니다.\"}";
			      }
			 } 
			else 
			{
			  json_data = "{\"code\":\"fail\", \"desc\":\"회원정보 수정을 실패했습니다.\"}";
			}

			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println(json_data);
			writer.close();	
			}
}
