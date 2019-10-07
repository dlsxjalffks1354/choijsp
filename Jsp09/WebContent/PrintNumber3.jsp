<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String max = request.getParameter("max");
		
		
		if(max != null)
		{
			int maxValue = Integer.parseInt(max);
		
			for ( int i=0; i<maxValue; i++) 
			{
			out.println(i + "<br>");
			}
		}
		else 
		{
			out.println("<H1>맥스 파라미터를 설정하시오</h1>");
		}
	%>
</body>
</html>