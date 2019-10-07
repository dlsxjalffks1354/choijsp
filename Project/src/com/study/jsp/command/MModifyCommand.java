package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.MDao;


public class MModifyCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String Id = request.getParameter("Id");
		String Pw = request.getParameter("Pw");
		String Name = request.getParameter("Name");
		String Nickname  = request.getParameter("Nickname");
		String eMail = request.getParameter("eMail");
		String Address = request.getParameter("Address");
		
		
		MDao dao = new MDao();
		dao.modify(Id,Pw,Name,Nickname,eMail,Address);
		
	}
}
