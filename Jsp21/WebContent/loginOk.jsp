<%@page import="com.study.jsp.MemberDTO" %>
<%@page import="com.study.jsp.MemberDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <% 
 	request.setCharacterEncoding("UTF-8");
 
 	String id = request.getParameter("id");
 	String pw = request.getParameter("pw");
 	
 	MemberDAO dao = MemberDAO.getInstance();
 	int checkNum = dao.userCheck(id, pw);
 	
 	if(checkNum == -1)
 	{
 %>
 		<script language="javascript">
 			alert("아이디가 존재하지 않습니다.");
 			history.go(-1);
 		</script>
 <%
 	} else if(checkNum == 0)
 	{
 %>
 
 	<script language="javascript">
 			alert("비밀번호가 틀립니다.");
 			history.go(-1);
 		</script>
 <%
 	}
 	else if(checkNum == 1)
 	{
 		MemberDTO dto = dao.getMember(id);
 		
 		String name = dto.getName();
 		session.setAttribute("id", id);
 		session.setAttribute("name", name);
 		session.setAttribute("ValidMem", "yes");
 		response.sendRedirect("main.jsp");
 	}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 확인.</title>
</head>
<body>

</body>
</html>