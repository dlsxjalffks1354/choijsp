package com.study.jsp.command;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dto.BDto;
import com.study.jsp.dao.BDao;
import com.study.jsp.dao.MDao;
import com.study.jsp.dto.MDto;
import com.study.jsp.PageInfo.BPageInfo;


public class MContentCommand2 implements MCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String Id = request.getParameter("bId");
		System.out.println(Id);
		MDao dao = new MDao();
		BDto dto = dao.contentView2(Id);
		
		
		int nPage = 1;
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		}catch(Exception e) {			
		}
		
		BPageInfo pinfo = dao.userArticle(nPage, Id);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		ArrayList<BDto> dtos = dao.Articlelist(nPage, Id);
		request.setAttribute("mlist", dtos);
		
		request.setAttribute("content_view", dto);
	}
	
}

