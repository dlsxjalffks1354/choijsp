<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<body>
	<%= session.getAttribute("name") %>님의 회원정보 수정이 정상 처리 되었습니다.
	
	<br>
	<input type="button" value="로그아웃" onclick ="location.href='login.jsp'"> <br>
	<input type="button" value="정보수정" onclick ="location.href='modify.jsp'"> <br>
	<input type="button" value="시작페이지" onclick ="location.href='startpage.jsp'"> <br>
</body>
</html>