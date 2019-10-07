package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.MemberDAO;
import Model.MemberDTO;

public class RegistCtrl extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		
		//String driver = "com.mysql.jdbc.Driver";
		//String url = "jdbc:mysql://localhost:3306/KOSMO";
		
		// 나머지 파라미터를 객체를 통해 받음
		String id = req.getParameter("id");
		String pass = req.getParameter("pwd");
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		String gender = req.getParameter("gender");
		String phonenumber = req.getParameter("phone");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPass(pass);
		dto.setName(name);
		dto.setBirth(birth);
		dto.setGender(gender);
		dto.setPhonenumber(phonenumber);
		dto.setEmail(email);
		dto.setAddress(address);
		System.out.println(dto.getId());
		
		MemberDAO dao = new MemberDAO();
		int affected = dao.insert(dto);
		
		dao.dbClose();
		
		if(affected==1)
		{
			System.out.println("가입성공!");
			req.getRequestDispatcher("../Source/Main.jsp").forward(req, resp);
		}
		else
		{
			System.out.println("가입실패");
		}
	}
}
