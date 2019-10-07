<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% if(session.getAttribute("ValidMem") !=null) { %>
	<jsp:forward page="main.jsp"></jsp:forward>
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery.js"></script>
<script>
function form_check() 
	{
	
	submit_ajax();
	}
	
	function submit_ajax()
	{
	var queryString =$("#Login").serialize();
	$.ajax({
		url: '/Jsp21-DAO-Ajax/LogInProcess',
		type:'POST',
		data: queryString,
		dataType: 'text',
		success:function(json)
		{
			var result=JSON.parse(json);
			if(result.code=="success")
				{
				alert(result.desc)
				window.location.replace("main.jsp");

				}
			else 
			{
				alert(result.desc);
			}
		}
	});
}

</script>

<title>로그인 페이지.</title>
</head>
<body>
	<form id= "Login">
		아이디 : <input type="text" name="id"
				value="<%if(session.getAttribute("id")!=null)
						out.println(session.getAttribute("id"));	
					%>"><br>
		비밀번호 : <input type= "password" name="pw"><br><p>
		<input type="button" value="로그인" onclick="form_check();"/> &nbsp;&nbsp;
		<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'">			
	</form>
</body>
</html>