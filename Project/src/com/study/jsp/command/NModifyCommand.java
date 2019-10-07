package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.NDao;
import com.study.jsp.dto.NDto;

public class NModifyCommand implements NCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String nId = request.getParameter("nId");
		String nName = request.getParameter("nName");
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		
		NDao dao = new NDao();
		dao.modify(nId,nName,nTitle,nContent);
		
	}
}
