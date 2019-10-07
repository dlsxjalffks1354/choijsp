package com.study.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/outProcess")
public class outProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection con;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
  
    String driver ="oracle.jdbc.driver.OracleDriver";
   	String url="jdbc:oracle:thin:@localhost:1521:xe";
   	String uid="scott";
   	String upw="tiger";
   	
   	
   	private String id, pw, name, phone1, phone2, phone3, gender;
   	
   	HttpSession session;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		actionDo(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		actionDo(request,response);
	}
	
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		String id, pw, name, phone,gender;
		
		
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		name="";
		phone="";
		gender="";
		
		

		try 
		{
			
			Class.forName(driver);
			con =DriverManager.getConnection(url,uid,upw);
			String query = "delete from member where id =? and pw=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			int updateCount = pstmt.executeUpdate();
			
			if(updateCount ==1) 
			{
				System.out.println("out success");
				response.sendRedirect("outResult.jsp");
			}
			else 
			{
			System.out.println("out fail");
			response.sendRedirect("login.jsp");
			}
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				if(resultSet != null) resultSet.close();
				if(pstmt != null)pstmt.close();
				if(con !=null)con.close();
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
				
	}
}
