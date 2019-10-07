package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/JoinProcess")
public class JoinProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private Connection con;
    private PreparedStatement pstmt;
    
    String driver ="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String uid="scott";
	String upw="tiger";
	
	private String id, pw, name, phone1, phone2, phone3, gender;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		name = request.getParameter("name");
		phone1 = request.getParameter("phone1");
		phone2 = request.getParameter("phone2");
		phone3 = request.getParameter("phone3");
		gender = request.getParameter("gender");
		
		// 여기서 파라미터로 넘어온 값들 체크
		// 이후 정상이면 다음 진행
		
		String query = " insert into member values(?, ?, ?, ?, ?)";
		
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url,uid,upw);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, phone1+"-"+phone2+"-"+phone3);
			pstmt.setString(5, gender);
			
			int updateCount = pstmt.executeUpdate();
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			
			if(updateCount ==1) 
			{
				System.out.println("insert success");
				writer.println("[{\"result\":\"ok\",\"desc\":\"none\"}]");
				
			}
			
			writer.close();
			
			
		}catch (Exception e) 
		{
			e.printStackTrace();
		}finally {
			try
			{
				if(pstmt != null)pstmt.close();
				if(con !=null)con.close();
			}
			catch (Exception e) {}
		}
	}
}
