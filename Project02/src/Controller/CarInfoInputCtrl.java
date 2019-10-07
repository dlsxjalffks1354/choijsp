package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.CarDAO;
import Model.CarDTO;


public class CarInfoInputCtrl extends HttpServlet
{
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
		int affected = dao.insert(dto);
		
		dao.dbClose();
		
		if(affected==1)
		{
			System.out.println("자동차 정보 등록완료");
			req.getRequestDispatcher("/Source/CarInfo").forward(req, resp);
		}
		else
		{
			System.out.println("자동차 정보 등록실패!!");
			req.getRequestDispatcher("../Source/CarInfoInput.jsp").forward(req, resp);
		}
	}
}
