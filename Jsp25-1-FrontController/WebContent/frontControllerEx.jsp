<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<a href="insert.do">insert</a>
	<hr />
	<a href="http://localhost:8081<%=request.getContextPath() %>/update.do">update</a>
	<hr />
	<a href="http://localhost:8081/Jsp26-1-FrontController/select.do">select</a>
	<hr />
	<a href ="<%=request.getContextPath()%>/delete.do">delete</a>
</body>
</html>