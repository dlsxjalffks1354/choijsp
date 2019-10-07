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


@WebServlet("/WithdrawProcess")
public class Withdrawprocess extends HttpServlet {
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
		System.out.println(id);
		String json_data= "";
		if(dao.confirmId(dto.getId()) != MemberDAO.MEMBER_EXISTENT)
		{
			json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 존재하지 않습니다.\"}";
		}
		else
		{
			int ri = dao.userCheck(id, pw);
			if(ri==MemberDAO.MEMBER_WITHDRAW_SUCCESS)
			{
				dto=dao.deletemember(id, pw);
				HttpSession session = request.getSession();
				session.invalidate();
				json_data ="{\"code\":\"success\", \"desc\":\"회원탈퇴에 성공하였습니다 다음에 또 이용해주세요 \"}";
			}
			else
			{
				json_data = "{\"code\":\"fail\", \"desc\":\"비밀번호가 일치 하지 않습니다.\"}";
			}
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();	
}
}

