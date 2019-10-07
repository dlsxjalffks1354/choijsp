package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.CarDAO;
import Model.CarDTO;

public class CarInfoModifyCtrl extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		String id = req.getParameter("id");
		String car_name = req.getParameter("name");
		
		CarDAO dao = new CarDAO();
		CarDTO dto = new CarDTO();
		
		dto = dao.selectView(id, car_name);
		
		req.setAttribute("dto", dto);
		
		// 뷰 호출
		RequestDispatcher dis = req.getRequestDispatcher("../Source/CarInfoModify.jsp");
		dis.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		
		// 값 받아오기
		String brand = req.getParameter("brand");
		String car_name = req.getParameter("car_name");
		String car_type = req.getParameter("car_type");
		String year = req.getParameter("year");
		String transmission = req.getParameter("transmission");
		String km = req.getParameter("km");
		String id = req.getParameter("id");
		
		CarDTO dto = new CarDTO();
		
		dto.setBrand(brand);
		dto.setCar_name(car_name);
		dto.setCar_type(car_type);
		dto.setYear(year);
		dto.setTransmission(transmission);
		dto.setKm(km);
		dto.setId(id);
		
		CarDAO dao = new CarDAO();
		int affected = dao.updateCar(dto);
		dao.dbClose();
		
		if(affected==1)
		{
			System.out.println("수정성공");
			
			req.getRequestDispatcher("/Source/CarInfo").forward(req, resp);
		}
		else
		{
			System.out.println("수정실패");
			req.getRequestDispatcher("../Source/CarInfoModify.jsp").forward(req, resp);
		}
		
	}
}