package com.study.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.CDao;
import com.study.jsp.dto.CDto;


public class CommentUpdateAction implements CCommand
{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		   
		  // 파라미터를 가져온다.
        int cnum = Integer.parseInt(request.getParameter("cnum"));
        String ccontent = request.getParameter("ccontent");
        
        CDao dao = CDao.getInstance();
        
        CDto dto = new CDto();
        dto.setCnum(cnum);
        dto.setCcontent(ccontent);
        
        boolean result = dao.updateComment(dto);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // 정상적으로 댓글을 수정했을경우 1을 전달한다.
        if(result) out.println("1");
        
        out.close();
        
        return null;
    }
}
