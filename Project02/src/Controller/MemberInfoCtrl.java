package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.MemberDAO;
import Model.MemberDTO;

public class MemberInfoCtrl extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		String id = req.getParameter("id");
		
		dto = dao.selectView(id);
		
		req.setAttribute("dto", dto);
		
		// 뷰 호출
		RequestDispatcher dis = req.getRequestDispatcher("../Source/MemberInfo.jsp");
		dis.forward(req, resp);
	}
}
