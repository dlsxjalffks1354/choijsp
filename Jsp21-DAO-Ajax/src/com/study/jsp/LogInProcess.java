package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LogInProcess")
public class LogInProcess extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
			request.setCharacterEncoding("UTF-8");
			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberDAO dao = MemberDAO.getInstance();
			MemberDTO dto = new MemberDTO();
			
			dto.setId(id);
			dto.setPw(pw);
			
			String json_data= "";
			if(dao.confirmId(dto.getId()) != MemberDAO.MEMBER_EXISTENT)
			{
				json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 존재하지 않습니다.\"}";
			}
			else
			{
				int ri = dao.userCheck(id, pw);
				if(ri==MemberDAO.MEMBER_LOGIN_SUCCESS)
				{
					dto=dao.getMember(id);
					String name = dto.getName();
					HttpSession session = request.getSession();
					session.setAttribute("id", dto.getId());
					session.setAttribute("name", dto.getName());
					session.setAttribute("ValidMem","yes");
					json_data ="{\"code\":\"success\", \"desc\":\"로그인에 성공하셧습니다. 환영합니다.\"}";
				}
				else
				{
					json_data = "{\"code\":\"fail\", \"desc\":\"아이디 혹은 비밀번호가 일치 하지 않습니다.\"}";
				}
			}
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println(json_data);
			writer.close();	
			
	}
}
