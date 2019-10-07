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
<script src="http://code.jquery.com/jquery.js"></script>
<meta charset="UTF-8">
<style>
      
  	.site-heading{
  	text-align:center;
  	}
  </style>
<script>
function form_check() 
{
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
	var queryString =$("#modify2").serialize();
		$.ajax({
			url: '/Project/ModifyProcess2',
			type:'POST',
			data: queryString,
			dataType: 'text',
			success:function(json)
			{
			var result=JSON.parse(json);
			if(result.code=="success")
			{
			alert(result.desc)
			window.location.replace("../main/modify.jsp");
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
	%>
	<%
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

	<form id="modify2">
		<h3 style="text-align: center;"> 회원 정보 수정 화면 </h3>
		<h6 style="text-align: center;"> 정보 수정을 하기 전에 비밀번호를 입력해주세요.</h6>
		<br>
		<div class="form-group">
     		아이디 : <input type="text" readonly class="form-control-plaintext" size="10" name="id" value="<%=dto.getId() %>"><br>
   		 </div>
		<div class="form-group" style="text-align: center;">
			비밀번호 : <input type="password" id="pw" name="pw" size="50"><br>
		</div>
		<div class="form-group">
			<input type="button" class="btn btn-primary form-control" value="입력" onclick="form_check();"> 
		</div>
		<div class="form-group">							 
		<input type="reset" class="btn btn-primary form-control" value="취소" onclick="javascript:window.location='../main/main2.member'">
		</div>	
	</form>
	</div>
	</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   
</body>
</html>