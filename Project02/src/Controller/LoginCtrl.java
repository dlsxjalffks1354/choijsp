package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.MemberDAO;

public class LoginCtrl extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		MemberDAO dao = new MemberDAO();
		
		String uid = req.getParameter("id");
		String upw = req.getParameter("pwd");
		
		HttpSession session = req.getSession();
			
		if(dao.isMember(uid, upw)==true)
		{
			// 회원인증이 완료된 경우
			
			// 로그인처리 : 전송된 값을 이용하여 session영역에 속성 생성
			session.setAttribute("USER_ID", uid);
			session.setAttribute("USER_PWD", upw);
			
			resp.sendRedirect("../Source/Main.jsp");
		}
		else
		{
			// 회원인증에 실패한 경우 : BbsLogin.jsp로 forward
			req.setAttribute("ERROR_MSG", "아이디 혹은 비밀번호를 확인하세요.");
			req.getRequestDispatcher("../Source/Login.jsp").forward(req, resp);
		}
	}
}
