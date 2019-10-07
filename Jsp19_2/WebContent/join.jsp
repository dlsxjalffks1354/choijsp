<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지.</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	function form_check()
	{
		submit_ajax();
	}
	
	function submit_ajax()
	{
		var queryString =$("#Join").serialize();
		$.ajax({
			url: '/Jsp19_2-Ajax/JoinProcess',
			type:'POST',
			data: queryString,
			dataType: 'json',
			success:function(json)
			{
				var results=eval(json);
				if(results[0].result=="ok")
					{
					alert("정상 가입 되었습니다.")
					window.location.replace("login.jsp");
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
	<form id="Join" >
		아이디 : <input type="text" name="id" size="10"><br>
		비밀번호 : <input type="text" name="pw" size="10"><br>
		이름 : <input type="text" name="name" size="10"><br>
		전화 번호 : <select name ="phone1">
			<option value="010">010</option>
			<option value="016">016</option>
			<option value="017">017</option>
			<option value="018">018</option>
			<option value="019">019</option>
			<option value="011">011</option>
		</select> -
		<input type="text" name= "phone2" size="5"> -
		<input type="text" name= "phone3" size="5"> <br>
		 
		 성별구분 : <input type="radio" name="gender" value="man">남 &nbsp;
		 			<input type="radio" name="gender" value="woman">여 <br>
		 <input type="button" value="회원가입" onclick="form_check();">
		 <input type="reset" value="취소">
	</form>
</body>
</html>