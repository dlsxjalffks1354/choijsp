package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.CarDAO;

public class CarInfoDeleteCtrl extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		String car_name = req.getParameter("name");
		String id = req.getParameter("id");
		
		CarDAO dao = new CarDAO();
		
		int affected = dao.deleteCar(car_name);
		
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
