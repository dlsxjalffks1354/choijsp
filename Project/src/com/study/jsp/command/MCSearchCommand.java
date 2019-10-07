package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.study.jsp.PageInfo.BPageInfo;
import com.study.jsp.dao.MDao;
import com.study.jsp.dto.CDto;
import com.study.jsp.dto.PeriodCsearchDto;

public class MCSearchCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int nPage = 1;
		String searchoption = "";
		String dayoption = "";
		String search = "";
		BPageInfo pinfo = null;
		ArrayList<CDto> dtos = null;
		ArrayList<PeriodCsearchDto> dto = null;
		
		try {
			String sPage = request.getParameter("page");
			searchoption = request.getParameter("searchoption");
			dayoption = request.getParameter("dayoption");
			search = request.getParameter("search");		
			nPage = Integer.parseInt(sPage);
		} 
		catch(Exception e) {			
		}
		
		MDao dao = MDao.getInstance();
		
		if(searchoption.equals("term")) {
			//System.out.println("기간검색");
			//System.out.println(nPage + " + " + sOption + " + " + pOption);
			pinfo = dao.SearchCommentPeriod(nPage, dayoption);
			request.setAttribute("page", pinfo);
			search = "nn";
		} else {
			//System.out.println("일반검색");
			pinfo = dao.SearchPageComment(nPage, searchoption, search);
			request.setAttribute("page", pinfo);
		}
		
		
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();	
		session.setAttribute("cpage", nPage);
		session.setAttribute("searchoption", searchoption);
		session.setAttribute("dayoption", dayoption);
		session.setAttribute("search", search);
		
		if(searchoption.equals("term")) {	
			dtos = dao.SearchCommentListPeriod(nPage, dayoption);
			dto = dao.SearchPeriodUserComment(dayoption);
		} else {
			dtos = dao.SearchComment(nPage, searchoption, search);
		}
		
		request.setAttribute("list", dtos);
		request.setAttribute("listUser", dto);
	}
	
}
