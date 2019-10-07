<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
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
			url: '/Jsp19_2-Ajax/LogInProcess',
			type:'POST',
			data: queryString,
			dataType: 'json',
			success:function(json)
			{
				var results=eval(json);
				if(results[0].result=="ok")
					{
					alert("로그인 되었습니다.")
					window.location.replace("loginResult.jsp");
					}
				else 
				{
					alert(results[0].desc);
				}
			}
		});
	}
</script>
</head>
<body>
	<form id="Login">
		아이디 : <input type="text" name="id" size="10"><br>
		비밀번호 : <input type="text" name="pw" size="10"><br>
	 <input type="button" value="로그인" onclick="form_check();"/>
	</form>
	
</body>
</html>