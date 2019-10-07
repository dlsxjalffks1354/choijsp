package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.NDao;

public class NWriteCommand implements NCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String nName = request.getParameter("nName");
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		String nUser = request.getParameter("nUser");
		
		
		NDao dao = new NDao();
		dao.write(nName,nTitle,nContent,nUser);
		
	}

}
