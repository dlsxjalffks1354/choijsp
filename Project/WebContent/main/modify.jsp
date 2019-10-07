<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.study.jsp.sign.MemberDTO" %>
<%@page import="com.study.jsp.sign.MemberDAO" %>
<%
	if(session.getAttribute("ValidMem") == null){
	response.sendRedirect("../main/main2.member");
	}
	%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../title/header.jsp" %>
<meta charset="UTF-8">
  <style>
      
  	.site-heading{
  	text-align:center;
  	}
  </style>
<title>정보수정</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
function form_check() 
{
	
	if($('#pw').val().length == 0)
	{
		alert("비밀번호는 필수 사항입니다.");
		$('#pw').focus();
		return;
	}
	
	if($('#pw').val() != $('#pw_check').val())
	{
		alert("비밀번호가 일치하지 않습니다.");
		$('#pw').focus();
		return;
	}
	
	if($('#eMail').val().length == 0)
	{
		alert("메일은 필수 사항입니다.");
		$('#eMail').focus();
		return;
	}
	if($('#Nickname').val().length == 0)
	{
		alert("메일은 필수 사항입니다.");
		$('#Nickname').focus();
		return;
	}

	submit_ajax();
}

function submit_ajax()
{
	var queryString =$("#modify").serialize();
		$.ajax({
			url: '/Project/ModifyProcess',
			type:'POST',
			data: queryString,
			dataType: 'text',
			success:function(json)
			{
			var result=JSON.parse(json);
			if(result.code=="success")
			{
			alert(result.desc)
			window.location.replace("../main/main2.member");

			}
			else 
			{
			alert(result.desc);
			}
		}
	});
}
</script>
</head>
<body>
	<%@ include file="../title/navigation.jsp"  %>

	<%
	String id = (String)session.getAttribute("id");
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = dao.getMember(id);
	%>
    <header class="masthead" style="background-image: url('../img/img2.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col">
            <div class="site-heading">
              <h1>Modify</h1>
              <span class="subheading" >회원 정보 수정 페이지 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
    <hr>
   <div class="container">

  	<div class="col-lg-auto">
   <div class="jumbotron" style="padding-top: 20px;">

  	
	<form id="modify">
	<h3 style="text-align: center;"> 회원 정보 수정 화면 </h3>
	<div class="form-group">
		아이디 : <%=dto.getId() %><br>
	</div>
	<div class="form-group">
		비밀번호 : <input type="password" id="pw" name="pw" size="20"><br>
	</div>
	<div class="form-group">
		비밀번호 확인 :<input type="password" id="pw_check" name="pw_check"  size="20"><br>
	</div>
	<div class="form-group">
		이름 :<%=dto.getName() %><br>
	</div>
	<div class="form-group">
		닉네임 : <input type="text" id="Nickname" name="Nickname" size="20" value="<%=dto.getNickname()%>"><br>
	</div>
	<div class="form-group">
		메일 : <input type="text" id="eMail" name="eMail" size="20"value="<%=dto.geteMail() %>"><br>
	</div>
	<div class="form-group">
		주소 : <input type="text" id="address" name="address" size="50"value="<%=dto.getAddress() %>"><br>
	</div>
	<div class="form-group">
		<input type="button" class="btn btn-primary form-control" value="수정" onclick="form_check();"> 
	</div>
	<div class="form-group">							 
		<input type="reset" class="btn btn-primary form-control" value="취소" onclick="javascript:window.location='../main/main2.member'">
	</div>
	</form>
	</div>
	</div>
	</div>
	<%@ include file="../title/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  
	
</body>
</html>