<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	if(session.getAttribute("ValidMem") ==null)
	{
%>
	<<jsp:forward page="login.do"/>
<%
	}

	String name =(String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
	
	<h1><%=name %>님 안녕하세요</h1>
	<form action="logoutOk.do" method="post">
		<input type="submit" value="로그아웃">&nbsp;&nbsp;&nbsp;
		<input type="button" value="정보수정"
				onclick="javascript:window.location='modify.jsp'">
	
	</form>
</body>
</html>