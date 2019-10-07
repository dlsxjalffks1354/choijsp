package com.study.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.CDao;
import com.study.jsp.dto.CDto;


public class CDeleteCommand implements CCommand
{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		   
        int cnum = Integer.parseInt(request.getParameter("cnum"));
        
        CDao dao = CDao.getInstance();
        boolean result = dao.deleteComment(cnum);
        
        response.setContentType("text/html;charset=euc-kr");
        PrintWriter out = response.getWriter();
 
        // 정상적으로 댓글을 삭제했을경우 1을 전달한다.
        if(result) out.println("1");
        
        out.close();
        return null;
    }
}
