<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	if(session.getAttribute("ValidMem") != null){
		String name = (String)session.getAttribute("name");
		String id = (String)session.getAttribute("id");
		String nickname = (String)session.getAttribute("Nickname");
	} else {
%>
	<jsp:forward page = "../main/login.jsp" />
<%	
	}
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../title/header.jsp" %>
<script type="text/javascript">
</script>
<meta charset="UTF-8">
  <style>
      
  	.site-heading{
  	text-align:center;
  	}
  	.row
  	{
  	text-align:center;
  	}
  	
  </style>
<title>프로젝트</title>
</head>
<body>
	<%@ include file="../title/navigation.jsp"  %>

	
	<!-- 페이지 헤더 -->
    <header class="masthead" style="background-image: url('../img/img2.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
         <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>HOME</h1>
              <span class="subheading" >메인 화면 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    
	<%@ include file="../title/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
</body>
</html>