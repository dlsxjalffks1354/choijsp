package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.FixDAO;
import Model.FixDTO;

public class CarFixModifyCtrl extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		String id = req.getParameter("id");
		String parts_name = req.getParameter("name");
		
		FixDAO dao = new FixDAO();
		FixDTO dto = new FixDTO();
		
		dto = dao.selectView(id, parts_name);
		
		req.setAttribute("dto", dto);
		
		// 뷰 호출
		RequestDispatcher dis = req.getRequestDispatcher("../Source/CarFixModify.jsp");
		dis.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		
		// 값 받아오기
		String day = req.getParameter("day");
		String parts_cnt = req.getParameter("parts_cnt");
		String parts_money = req.getParameter("parts_money");
		String fix_money = req.getParameter("fix_money");
		String tax = req.getParameter("tax");
		String money = req.getParameter("money");
		String shop_name = req.getParameter("shop_name");
		String pay = req.getParameter("pay");
		String gita = req.getParameter("gita");
		
		String parts_name = req.getParameter("parts_name");
		String user_id = req.getParameter("id");
		
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
		int affected = dao.updateFix(dto);
		dao.dbClose();
		
		if(affected==1)
		{
			System.out.println("수정성공");
			
			req.getRequestDispatcher("/Source/CarInfo").forward(req, resp);
		}
		else
		{
			System.out.println("수정실패");
			req.getRequestDispatcher("../Source/CarFixModify.jsp").forward(req, resp);
		}
		
	}
}