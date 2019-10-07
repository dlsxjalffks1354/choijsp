package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.PageInfo.BPageInfo;
import com.study.jsp.PageInfo.MPageInfo;
import com.study.jsp.dao.MDao;
import com.study.jsp.dto.MDto;

public class MListCommand implements MCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		int nPage= 1;
		try {
			String sPage =request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		}catch(Exception e) {
			
		}
		MDao dao = MDao.getInstance();
		BPageInfo pinfo = dao.userPage(nPage);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		ArrayList<MDto> dtos = dao.mlist(nPage);
		request.setAttribute("mlist", dtos);
	}

}
