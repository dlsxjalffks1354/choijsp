package com.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns= {"/ServletInitParam2"},
			initParams= {@WebInitParam(name="id",value="abcdef"),
			@WebInitParam(name="pw",value="1234"),
			@WebInitParam(name="path",value="C:\\java\\workspace")}
		)
public class ServletInitParam2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletInitParam2() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
