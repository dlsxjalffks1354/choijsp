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
              <h1>Contact</h1>
              <span class="subheading">
              	사용중에 문제나 문의가 있으신 분은 작성해주시기 바랍니다.
              </span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <p>아래를 작성하면 저희에게 메일이 오게 됩니다.<br/>
              	빠른시일내에 답변해 드리겠습니다.
          </p>
          <!-- Contact Form - Enter your email address on line 19 of the mail/contact_me.php file to make this form work. -->
          <!-- WARNING: Some web hosts do not allow emails to be sent through forms to common mail hosts like Gmail or Yahoo. It's recommended that you use a private domain email address! -->
          <!-- To use the contact form, your site must be on a live web host with PHP! The form will not work locally! -->
          <form name="sentMessage" id="contactForm" action="<c:url value='/Source/Mail'/>" novalidate>
            
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>이름</label>
                <input type="text" class="form-control" placeholder="이름" id="name" name="name"
                		required data-validation-required-message="Please enter your name."/>
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>메일주소</label>
                <input type="email" class="form-control" placeholder="고객님의 메일 주소" id="email" name="email"
                		 required data-validation-required-message="Please enter your email address.">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group col-xs-12 floating-label-form-group controls">
                <label>비밀번호</label>
                <input type="password" class="form-control" placeholder="고객님의 메일 비밀번호" id="phone" name="phone"
                		required data-validation-required-message="Please enter your password.">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>내용</label>
                <textarea rows="5" class="form-control" placeholder="내용" id="message" name="message"
                		required data-validation-required-message="Please enter a message."></textarea>
                <p class="help-block text-danger"></p>
              </div>
            </div>
            
            <br>
            
            <div id="success"></div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary" id="sendMessageButton">Send</button>
            </div>
            
          </form>
          
        </div>
      </div>
    </div>

    <hr>

    <%@ include file="../include/Footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="../Resources/vendor/jquery/jquery.min.js"></script>
    <script src="../Resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="../Resources/js/jqBootstrapValidation.js"></script>
    <script src="../Resources/js/contact_me.js"></script>

    <!-- Custom scripts for this template -->
    <script src="../Resources/js/clean-blog.min.js"></script>

  </body>

</html>
