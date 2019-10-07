<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.study.jsp.sign.*" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	if(session.getAttribute("ValidMem") == null){		
		response.sendRedirect("../main/main2.member");
	}


	String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
	String nickname = (String)session.getAttribute("nickname");
	String manager = (String)session.getAttribute("manager");
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = dao.getMember(id);
		
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
      <style>
      
  	.site-heading{
  	text-align:center;
  	}
  	</style>
  	
 <script>

function form_check() {
	if($('#id').val().length == 0 )
	{
		alert("아이디는 필수 사항입니다.");
		$('#id').focus();
		return;
	}
	
	if($('#pw').val().length == 0)
	{
		alert("비밀번호는 필수 사항입니다.");
		$('#pw').focus();
		return;
	}
	
	submit_ajax();
	
	}
function submit_ajax()
{
	var queryString =$("#reg_frm").serialize();
	$.ajax({
		url: '/Project/WithdrawProcess',
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
  
<%@ include file="../title/header.jsp" %>
<title>회원탈퇴</title>
</head>
<body>
	<%@ include file="../title/navigation.jsp"  %>
	
	
	<header class="masthead" style="background-image: url('../img/img2.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
         <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>Withdraw</h1>
              <span class="subheading" >회원탈퇴 페이지입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

	<div class="container">
		<div class="col-lg-auto justify-content-center"></div>
		<div class="col-lg-auto">
			<!-- 점보트론 -->
			<div class="jumbotron" style="padding-top: 20px;">
				<!-- 개인 정보를 숨기면서 post형태로 전송 -->
				<form id= "reg_frm">
					<h3 style="text-align: center;">회원탈퇴</h3>
						<h6 style="text-align: center;">탈퇴하시려면 아이디와 비밀번호를 다시 입력해 주십시오</h6>
						<br>
					<div class="form-group">
						아이디 : <input type="text" readonly class="form-control-plaintext" size="10" id="id" name="id"  value="<%=dto.getId() %>"><br>
					</div>
					<div class="form-group">
						비밀번호 : <input type = "password" name = "pw" id="pw" size ="10"><br>
					</div>					
					<div class="form-group">
						<input type="button" class="btn btn-primary form-control"  value="탈퇴" onclick="form_check();"> 
						
						<input type="button" class="btn btn-primary form-control" value = "취소" onclick = "location.href='main.jsp'">
					</div>
				</form>
			</div>
		</div>
	</div>
	
<%@ include file="../title/footer.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
</body>
</html>