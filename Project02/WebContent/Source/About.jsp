<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>

    <%@ include file="../include/Head.jsp" %>

  </head>

  <body>

    <%@ include file="../include/navigation.jsp" %>

    <!-- Page Header -->
    <header class="masthead" style="background-image: url('../Resources/img/main_bg.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <div class="page-heading">
              <h1>About</h1>
              <span class="subheading">자동차 관리 및 정보제공을 위한 홈페이지입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <p>이 홈페이지는 자동차 관리가 주 목적인 페이지 이며, <br/> 자기 자신의 차량을 먼저 등록한 후 이용할 수 있습니다.</p>
          <p>또한, 이 홈페이지는 자기 차량의 정비 내역, 소모품 교환 주기 등을 알려주며, <br/>
          	새차 및 중고차 구매, 타던 자동차 판매를 돕기도 합니다.</p>
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
