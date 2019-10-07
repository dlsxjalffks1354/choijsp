package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.MemberDAO;
import Model.MemberDTO;

public class MemberDeleteCtrl extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		HttpSession session = req.getSession();
		
		MemberDAO dao = new MemberDAO();
		int affected = dao.deleteMember(id, pass);
		
		dao.dbClose();
		
		if(affected==1)
		{
			System.out.println("삭제성공");
			session.invalidate();
			req.getRequestDispatcher("../Source/Main.jsp").forward(req, resp);
		}
		else
		{
			System.out.println("삭제성공");
			req.getRequestDispatcher("../Source/MemberInfo.jsp").forward(req, resp);
		}
	}
}
