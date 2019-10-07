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

		if(f.phone.value=="")
		{
			alert("핸드폰 번호를 입력하세요.");
			f.phone.focus();

			return false;
		}

		if(f.email.value=="")
		{
			alert("이메일을 입력하세요.");
			f.email.focus();

			return false;
		}

		if(f.address.value=="")
		{
			alert("주소를 입력하세요.");
			f.address.focus();

			return false;
		}

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
              <h1>Info</h1>
              <span class="subheading">회원정보 확인 페이지 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">

         <form name="memInfo" method="post" action="<c:url value="/Source/MemberModify"/>"
         	onsubmit="return formCheck();">
	        	<table class="table table-striped table-bordered">
	        		<colgroup>
						<col width="35%" />
						<col width="65%" />
				</colgroup>
         		<c:choose>
						<c:when test="${empty dto }">
							<!-- 회원이 없는 경우 -->
							<div class="control-group">
				              <div class="form-group floating-label-form-group controls">
				              	회원이 존재하지 않습니다.
				              </div>
				            </div>
						</c:when>
						<c:otherwise>
						<!-- 회원이 있는 경우 -->
							<tr>
			         			<th style="vertical-align: middle;">아이디</th>
			         			<td>
			         				<input type="text" class="form-control" placeholder="Id"
			         					name="id" value="${dto.id }" readonly/>
			         			</td>
			         		</tr>
							<tr>
			         			<th style="vertical-align: middle;">이름</th>
			         			<td>
			         				<input type="text" class="form-control" placeholder="Name" name="name"
			         					value="${dto.name }" readonly/>
			         			</td>
			         		</tr>
			         		<tr>
			         			<th style="vertical-align: middle;">생년월일</th>
			         			<td>
			         				<input type="text" class="form-control" placeholder="Birth"
				                			name="birth" value="${dto.birth }" readonly/>
			         			</td>
			         		</tr>
			         		<tr>
			         			<th style="vertical-align: middle;">성별</th>
			         			<td>
			         				<input type="text" class="form-control" placeholder="Gender"
				                			name="gender" value="${dto.gender }" readonly />
			         			</td>
			         		</tr>
			         		<tr>
			         			<th style="vertical-align: middle;">핸드폰</th>
			         			<td>
			         				<input type="tel" class="form-control" placeholder="PhoneNumber"
				                			name="phone" value="${dto.phonenumber }" />
			         			</td>
			         		</tr>
			         		<tr>
			         			<th style="vertical-align: middle;">이메일</th>
			         			<td>
			         				<input type="email" class="form-control" placeholder="E-mail"
				                			name="email" value="${dto.email }" />
			         			</td>
			         		</tr>
			         		<tr>
			         			<th style="vertical-align: middle;">주소</th>
			         			<td>
			         				<input type="text" class="form-control" placeholder="Address"
				                			name="address" value="${dto.address }" />
			         			</td>
			         		</tr>
			         		<tr>
			         			<th style="vertical-align: middle;">현재 비밀번호</th>
			         			<td>
			         				<input type="password" class="form-control" placeholder="password"
				                			name="pass" />
			         			</td>
			         		</tr>
			         		<tr>
			         			<th style="vertical-align: middle;">변경할 비밀번호</th>
			         			<td>
			         				<input type="password" class="form-control" placeholder="비밀번호 변경시에만 입력"
				                			name="passModify" />
			         			</td>
			         		</tr>
						</c:otherwise>
				</c:choose>
            </table>
            <br>

            <div id="success"></div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary">Modify</button>
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
