<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		int num1;
		int num2;
		String sam;
	%>
	
	<%
	request.setCharacterEncoding("UTF-8");
	
	String str= request.getParameter("num1");
	String str2= request.getParameter("num2");
	String sam =request.getParameter("sam");
	
	num1=Integer.parseInt(str);
	num2=Integer.parseInt(str2);
	
	if(sam.equals("plus"))
	{
		out.println("두 입력값의 합은"+(num1+num2) +"입니다.");
	}
	
	if(sam.equals("minus"))
	{
		out.println("두 입력값의 차는"+(num1-num2) +"입니다.");
	}
	if(sam.equals("multi"))
	{
		out.println("두 입력값의 곱는"+(num1*num2) +"입니다.");
	}
	if(sam.equals("div"))
	{
		out.println("두 입력값의 몫는"+(num1/num2) +"입니다.");
	}
	%>
</body>
</html>