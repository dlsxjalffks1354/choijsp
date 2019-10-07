package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.MemberDAO;
import Model.MemberDTO;

public class MemberModifyCtrl extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		String id = req.getParameter("id");
		
		dto = dao.selectView(id);
		
		req.setAttribute("dto", dto);
		
		// 뷰 호출
		RequestDispatcher dis = req.getRequestDispatcher("../Source/MemberModify.jsp");
		dis.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String passModify = req.getParameter("passModify");
		String phonenumber = req.getParameter("phone");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		
		MemberDTO dto = new MemberDTO();
		
		dto.setId(id);
		dto.setPass(pass);
		dto.setPassModify(passModify);
		dto.setPhonenumber(phonenumber);
		dto.setEmail(email);
		dto.setAddress(address);
		
		MemberDAO dao = new MemberDAO();
		int affected = dao.updateMember(dto);
		
		dao.dbClose();
		
		if(affected==1)
		{
			System.out.println("수정성공");
			
			req.getRequestDispatcher("/Source/MemberInfo").forward(req, resp);
		}
		else
		{
			System.out.println("수정실패");
			req.getRequestDispatcher("../Source/MemberModify.jsp").forward(req, resp);
		}
	}
}
