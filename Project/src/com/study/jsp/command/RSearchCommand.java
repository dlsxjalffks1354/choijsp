package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.BDao;
import com.study.jsp.dao.RDao;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.RDto;
import com.study.jsp.PageInfo.BPageInfo;

public class RSearchCommand implements BCommand {
	
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
		
		RDao dao = RDao.getInstance();
		BPageInfo pinfo = dao.SearchPage(nPage, searchoption, search);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();	
		session.setAttribute("cpage", nPage);
		session.setAttribute("searchoption", searchoption);
		session.setAttribute("search", search);
		
		ArrayList<RDto> dtos = dao.Search(nPage, searchoption, search);
		request.setAttribute("rlist", dtos);
	}

}
