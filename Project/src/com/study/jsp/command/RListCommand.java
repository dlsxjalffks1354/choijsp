package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.PageInfo.BPageInfo;
import com.study.jsp.dao.RDao;
import com.study.jsp.dto.RDto;



public class RListCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int nPage = 1;
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		}catch(Exception e) {			
		}
		
		RDao dao = RDao.getInstance();
		BPageInfo pinfo = dao.articlePage(nPage, "reference");
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		ArrayList<RDto> dtos = dao.rlist(nPage);
		request.setAttribute("rlist", dtos);
	}
	
}
