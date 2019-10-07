<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!
    	String name,id,pw;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<%
		name = (String)session.getAttribute("name");
		id = (String)session.getAttribute("id");
		pw = (String)session.getAttribute("pw");
	
	%>
	
	<%=name %>님 안녕하세요 <br><p>
	
	<input type="button" value="회원정보 수정" onclick ="location.href='modify.jsp'"> <br>
	<input type="button" value="회원탈퇴" onclick ="location.href='out.jsp'"> <br>
</body>
</html>