package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.PageInfo.NPageInfo;
import com.study.jsp.dao.NDao;
import com.study.jsp.dto.NDto;

public class NListCommand implements NCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		int nPage= 1;
		try {
			String sPage =request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		}catch(Exception e) {
			
		}
		NDao dao = NDao.getInstance();
		NPageInfo pinfo = dao.articlePage(nPage);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		ArrayList<NDto> dtos = dao.nlist(nPage);
		request.setAttribute("nlist", dtos);
	}

}
