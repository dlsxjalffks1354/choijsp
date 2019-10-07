package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.BDao;
import com.study.jsp.dao.RDao;

public class RDeleteCommand implements BCommand 
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
		System.out.println(bId);
		RDao dao = new RDao();
		dao.delete(bId);
	}
}
