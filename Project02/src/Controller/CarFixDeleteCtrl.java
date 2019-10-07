package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.FixDAO;


public class CarFixDeleteCtrl extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		String parts_name = req.getParameter("name");
		String id = req.getParameter("id");
		
		FixDAO dao = new FixDAO();
		
		int affected = dao.deleteFix(parts_name);
		
		dao.dbClose();
		
		if(affected==1)
		{
			System.out.println("삭제 성공");
			req.getRequestDispatcher("/Source/CarInfo?id=" + id).forward(req, resp);
		}
		else
		{
			System.out.println("삭제 실패");
		}
	}
}
