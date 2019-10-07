<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/Head.jsp" %>
<script type="text/javascript">
	function formCheck()
	{
		var f = document.memInfo;

		if(f.pass.value=="")
		{
			alert("비밀번호를 입력하세요.");
			f.pass.focus();

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
              <h1>Delete</h1>
              <span class="subheading">회원정보 탈퇴 페이지 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">

         <form name="memInfo" method="post" action="<c:url value="/Source/MemberDelete"/>"
         	onsubmit="return formCheck();">
	        	<table class="table table-striped table-bordered">
	        		<colgroup>
						<col width="35%" />
						<col width="65%" />
				</colgroup>
	         		<tr>
	         			<th style="vertical-align: middle;">아이디</th>
	         			<td>
	         				<input type="text" class="form-control" placeholder="id"
		                			name="id" value="${param.id }" readonly />
	         			</td>
	         		</tr>
	         		<tr>
	         			<th style="vertical-align: middle;">비밀번호</th>
	         			<td>
	         				<input type="password" class="form-control" placeholder="password"
		                			name="pass" />
	         			</td>
	         		</tr>
            </table>
            <br>

            <div id="success"></div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary">Delete</button>
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
