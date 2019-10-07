<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand" href="../Source/Main.jsp">CarPur</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" 
	        data-target="#navbarResponsive" aria-controls="navbarResponsive" 
	        aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="../Source/Main.jsp">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="../Source/Post.jsp">Tag</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="../Source/About.jsp">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="../Source/Contact.jsp">Contact</a>
            </li>
            <% if(session.getAttribute("USER_ID")==null) { %>
	            <li class="nav-item">
	              <a class="nav-link" href="../Source/Regist.jsp">Regist</a>
	            </li>
	            <li class="nav-item">
	              <a class="nav-link" href="../Source/Login.jsp">Login</a>
	            </li>
            <% }else { %>
	            <li class="nav-item">
	              <a class="nav-link" href="../Source/MemberInfo?id=${sessionScope.USER_ID }">개인정보수정</a>
	            </li>
	            <li class="nav-item">
	              <a class="nav-link" href="../Source/Logout.jsp">Logout</a>
	            </li>
            <%} %>
          </ul>
        </div>
      </div>
    </nav>