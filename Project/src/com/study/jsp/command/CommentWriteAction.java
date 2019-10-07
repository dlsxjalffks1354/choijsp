package com.study.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.CDao;
import com.study.jsp.dto.CDto;


public class CommentWriteAction implements CCommand
{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		CDao dao = CDao.getInstance();
		CDto comment = new CDto();
		
		// 파리미터 값을 가져온다.
		int cboard = Integer.parseInt(request.getParameter("cboard"));
		String cid = request.getParameter("cid");
		String cnickname = request.getParameter("cnickname");
		String ccontent = request.getParameter("ccontent");
		
		comment.setCnum(dao.getSeq());	// 댓글 번호는 시퀀스값으로
		comment.setCboard(cboard);
		comment.setCid(cid);
		comment.setCnickname(cnickname);
		comment.setCcontent(ccontent);
		
		boolean result = dao.insertComment(comment);

		if(result){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("1");
			out.close();
		}
			
		return null;
		
	}
}
