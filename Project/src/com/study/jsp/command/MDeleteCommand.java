package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.MDao;

public class MDeleteCommand implements MCommand 
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String Id = request.getParameter("Id");
		MDao dao = new MDao();
		dao.delete(Id);
	}
}
