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
import Model.FixDAO;
import Model.FixDTO;

public class CarInfoCtrl extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		System.out.println("id : " + id);
		
		// DB연결을 위한 DAO 호출
		CarDAO dao = new CarDAO();
		List<CarDTO> lists = dao.selectInfo(id);
		
		FixDAO dao2 = new FixDAO();
		List<FixDTO> listsFix = dao2.selectInfo(id);
		
		// 커넥션풀에 자원반납
		dao.dbClose();
		dao2.dbClose();

		// 리퀘스트 영역에 저장하기
		req.setAttribute("lists", lists);	// 결과 레코드셋
		req.setAttribute("listsFix", listsFix);

		// 뷰 호출
		RequestDispatcher dis = req.getRequestDispatcher("../Source/CarInfo.jsp");
		dis.forward(req, resp);
	}
}
