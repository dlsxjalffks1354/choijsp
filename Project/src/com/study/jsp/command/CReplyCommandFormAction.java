package com.study.jsp.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.study.jsp.dao.CDao;
import com.study.jsp.dto.CDto;

public class CReplyCommandFormAction implements CCommand
{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// 부모글의 글번호를 가져온다.
		int cnum = Integer.parseInt(request.getParameter("cnum"));

		CDao dao = CDao.getInstance();
		CDto dto = dao.getComment(cnum);
		
		// 댓글 정보를 request에 세팅한다.
		request.setAttribute("comment", dto);
		
		forward.setRedirect(false);
		forward.setNextPath("../free/CommentReplyFrom.jsp");
		
		return forward;
	}
}