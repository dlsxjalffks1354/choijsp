package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.study.jsp.dao.NDao;
import com.study.jsp.dto.NDto;
import com.study.jsp.PageInfo.NPageInfo;

public class NSearchCommand implements NCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		int nPage = 1;
		String searchoption = "";
		String search = "";
		
		try {
			String sPage = request.getParameter("page");
			searchoption = request.getParameter("searchoption");
			search = request.getParameter("search");			
			nPage = Integer.parseInt(sPage);
		} 
		catch(Exception e) 
		{			
			
		}
		
		NDao dao = NDao.getInstance();
		NPageInfo pinfo = dao.SearchPage(nPage, searchoption, search);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();	
		session.setAttribute("cpage", nPage);
		session.setAttribute("searchoption", searchoption);
		session.setAttribute("search", search);
		
		ArrayList<NDto> dtos = dao.Search(nPage, searchoption, search);
		request.setAttribute("list", dtos);
	}

}
