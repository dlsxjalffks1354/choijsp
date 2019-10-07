package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.CarDAO;
import Model.CarDTO;
import Model.FixDAO;
import Model.FixDTO;

public class CarFixCtrl extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		req.setCharacterEncoding("UTF-8");
		
		String user_id = req.getParameter("id");
		System.out.println(user_id);
		
		String day = req.getParameter("day");
		String parts_name = req.getParameter("parts_name");
		String parts_cnt = req.getParameter("parts_cnt");
		String parts_money = req.getParameter("parts_money");
		String fix_money = req.getParameter("fix_money");
		String tax = req.getParameter("tax");
		String money = req.getParameter("money");
		String shop_name = req.getParameter("shop_name");
		String pay = req.getParameter("pay");
		String gita = req.getParameter("gita");
		
		FixDTO dto = new FixDTO();
		
		dto.setDay(day);
		dto.setParts_name(parts_name);
		dto.setParts_cnt(parts_cnt);
		dto.setParts_money(parts_money);
		dto.setFix_money(fix_money);
		dto.setTax(tax);
		dto.setMoney(money);
		dto.setShop_name(shop_name);
		dto.setPay(pay);
		dto.setGita(gita);
		dto.setUser_id(user_id);
		
		FixDAO dao = new FixDAO();
		int affected = dao.insert(dto);
		
		dao.dbClose();
		
		if(affected==1)
		{
			System.out.println("수리내역 등록완료");
			//req.getRequestDispatcher("/Source/MyCarFix").forward(req, resp);
			req.getRequestDispatcher("/Source/CarInfo").forward(req, resp);
		}
		else
		{
			System.out.println("수리내역 등록실패!!");
			req.getRequestDispatcher("../Source/CarFixInput.jsp").forward(req, resp);
		}
	}
}
