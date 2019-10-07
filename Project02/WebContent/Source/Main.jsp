<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<%@ include file="../include/Head.jsp" %>
  </head>

  <body>

    <%@ include file="../include/navigation.jsp"  %>

    <!-- Page Header -->
    <header class="masthead" style="background-image: url('../Resources/img/main_bg.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>Home</h1>
              <span class="subheading">자동차 관리 및 정보제공을 위한 홈페이지입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
        
          <div class="post-preview">
            <a href="../Source/Post.jsp">
              <h2 class="post-title">
                카테고리 선택
              </h2>
              <h3 class="post-subtitle">
                카테고리 선택합니다.
              </h3>
            </a>
          </div>
          <hr>
          
          <div class="post-preview">
            <a href="../Source/About.jsp">
              <h2 class="post-title">
                사이트 정보
              </h2>
              <h3 class="post-subtitle">
                이 사이트에 대한 정보를 제공합니다.
              </h3>
            </a>
          </div>
          <hr>
          
          <div class="post-preview">
            <a href="../Source/Contact.jsp">
              <h2 class="post-title">
                고객센터
              </h2>
              <h3 class="post-subtitle">
                운영진에게 메일을 발송합니다.
              </h3>
            </a>
          </div>
          <hr>
          
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
