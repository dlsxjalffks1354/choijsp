package com.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/ServletInitParam")
public class ServletInitParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletInitParam() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");
		
		String id =getInitParameter("id");
		String pw =getInitParameter("pw");
		String path =getInitParameter("path");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter(); 
		writer.println("<html><head></head><body>");
		writer.println("아이디 : " + id + "<br>");
		writer.println("비밀번호 : " +pw + "<br>" );
		writer.println("path : " + path);
		writer.println("</body></html>");
		
		writer.close();
	}

}
