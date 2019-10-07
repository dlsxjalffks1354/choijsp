package com.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PostMethod")
public class PostMethod extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostMethod() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("HelloWorld~~");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer =response.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1>HelloWorld~~</h1>");
		writer.println("</body>");
		writer.println("</html");
		
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer =response.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("POST 방식입니다. <br>따라서 doPost 메서드가 호출되었습니다.");
		writer.println("</body>");
		writer.println("</html");
		writer.close();
	}

}
