<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/Head.jsp" %>
<script type="text/javascript">
	function checkLogin()
	{
		var f = document.loginFrm;
		
		if(f.id.value=="")
		{
			alert("아이디를 입력하셔야 합니다.");
			f.id.focus();
			
			return false;
		}
		if(f.pwd.value=="")
		{
			alert("패스워드를 입력하셔야 합니다.");
			f.pwd.focus();
			
			return false;
		}
	}
</script>
</head>  
<body>
    <%@ include file="../include/navigation.jsp" %>

    <!-- Page Header -->
    <header class="masthead" style="background-image: url('../Resources/img/main_bg.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>Login</h1>
              <span class="subheading">로그인 페이지 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
        
         <form name="loginFrm" method="post" action="<c:url value="/Source/Login" />"
				onsubmit="return checkLogin();" novalidate>
         	<div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>아이디</label>
                <input type="text" class="form-control" placeholder="ID" name="id"/>
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>비밀번호</label>
                <input type="password" class="form-control" placeholder="Password" name="pwd"/>
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <br/>
            
            <span style="color: red; font-size: 1em;">
            	<%=request.getAttribute("ERROR_MSG")==null ? "" : request.getAttribute("ERROR_MSG") %>
            </span>
            <br/>
            <br/>
            
            <div id="success"></div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary" id="Send">Send</button>
            </div>
          </form>
         
        </div>
      </div>
    </div>

    <hr>

    <!-- Footer -->
    <%@ include file="../include/Footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="../Resources/vendor/jquery/jquery.min.js"></script>
    <script src="../Resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Custom scripts for this template -->
    <script src="../Resources/js/clean-blog.min.js"></script>

  </body>

</html>
