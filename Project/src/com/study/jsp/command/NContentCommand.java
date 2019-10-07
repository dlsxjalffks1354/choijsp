package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.NDao;
import com.study.jsp.dto.NDto;

public class NContentCommand implements NCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String nId = request.getParameter("nId");
		NDao dao = new NDao();
		NDto dto = dao.contentView(nId);
		
		request.setAttribute("content_view", dto);
	}

}
