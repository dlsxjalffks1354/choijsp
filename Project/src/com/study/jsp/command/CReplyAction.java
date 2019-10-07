package com.study.jsp.command;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.study.jsp.dao.CDao;
import com.study.jsp.dto.CDto;

public class CReplyAction implements CCommand{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		// 파라미터를 가져온다.
				int cnum = Integer.parseInt(request.getParameter("cnum"));
				int cboard = Integer.parseInt(request.getParameter("cboard"));
				String cid = request.getParameter("cid");
				String cnickname = request.getParameter("cnickname");
				String ccontent = request.getParameter("ccontent");

				CDao dao = CDao.getInstance();
				
				CDto dto = new CDto();	
				dto.setCnum(dao.getSeq());	// 시퀀스를 가져와 세팅한다
				dto.setCboard(cboard);
				dto.setCid(cid);
				dto.setCnickname(cnickname);
				dto.setCcontent(ccontent);
				dto.setCparent(cnum);  // 부모댓글의 글번호를 세팅
				
				boolean result = dao.insertComment(dto);
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				// 정상적으로 댓글을 저장했을경우 1을 전달한다.
				if(result) out.println("1");
				else out.println("0");
				
				out.close();
				
				return null;
	}	
}
