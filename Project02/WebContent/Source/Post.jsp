<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="../include/Head.jsp" %>
<script type="text/javascript">
	function LoginCheck()
	{
		alert("로그인 후 이용하세요.");
		
		location.href = "../Source/Login.jsp";
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
              <h1>Tag</h1>
              <span class="subheading">주제를 선택하세요.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

   <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
        	<%if(session.getAttribute("USER_ID")==null) {%>
        		<div class="post-preview">
            <a href="javascript: LoginCheck();">
              <h2 class="post-title">
                내 차 관리
              </h2>
              <h3 class="post-subtitle">
                내차를 관리합니다.
              </h3>
            </a>
            <p class="post-meta">Posted by
              <a href="#">네이버 백과사전</a>
              on September 24, 2017</p>
          </div>
          <hr>
          
          <div class="post-preview">
            <a href="javascript: LoginCheck();">
              <h2 class="post-title">
                검색
              </h2>
              <h3 class="post-subtitle">
                차량에 관한 네이버 검색을 제공합니다.
              </h3>
            </a>
            <p class="post-meta">Posted by
              <a href="#">네이버 백과사전</a>
              on September 24, 2017</p>
          </div>
          <hr>
          
         <div class="post-preview">
            <a href="javascript: LoginCheck();">
              <h2 class="post-title">
                업데이트 예정
              </h2>
              <h3 class="post-subtitle">
                업데이트 예정입니다...
              </h3>
            </a>
            <p class="post-meta">Posted by
              <a href="#">네이버 백과사전</a>
              on September 24, 2017</p>
          </div>
          <hr>
        	<% }else { %>
        		<div class="post-preview">
            <a href="../Source/CarInfo?id=${sessionScope.USER_ID }">
              <h2 class="post-title">
                내 차 관리
              </h2>
              <h3 class="post-subtitle">
                내차를 관리합니다.
              </h3>
            </a>
            <p class="post-meta">Posted by
              <a href="#">네이버 백과사전</a>
              on September 24, 2017</p>
          </div>
          <hr>
          
          <div class="post-preview">
            <a href="../Source/NaverSearchAPI.jsp">
              <h2 class="post-title">
                검색
              </h2>
              <h3 class="post-subtitle">
                차량에 관한 네이버 검색을 제공합니다.
              </h3>
            </a>
            <p class="post-meta">Posted by
              <a href="#">네이버 백과사전</a>
              on September 24, 2017</p>
          </div>
          <hr>
          
         <div class="post-preview">
            <a href="http://www.encar.com">
              <h2 class="post-title">
                업데이트 예정
              </h2>
              <h3 class="post-subtitle">
                업데이트 예정입니다...
              </h3>
            </a>
            <p class="post-meta">Posted by
              <a href="#">네이버 백과사전</a>
              on September 24, 2017</p>
          </div>
          <hr>
        	<% } %>
          
        </div>
      </div>
    </div>

    <hr>

    <%@ include file="../include/Footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="../Resources/vendor/jquery/jquery.min.js"></script>
    <script src="../Resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Custom scripts for this template -->
    <script src="../Resources/js/clean-blog.min.js"></script>

  </body>

</html>
