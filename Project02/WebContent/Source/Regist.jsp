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
		var f = document.signupFrm;
		
		if(f.id.value=="")
		{
			alert("아이디를 입력하세요.");
			f.id.focus();
			
			return false;
		}
		if(f.pwd.value=="")
		{
			alert("패스워드를 입력하세요");
			f.pwd.focus();
			
			return false;
		}
		if(f.pwd_check.value=="")
		{
			alert("패스워드 확인을 입력하세요");
			f.pwd.focus();
			
			return false;
		}
		if(f.name.value=="")
		{
			alert("이름을 입력하세요");
			f.pwd.focus();
			
			return false;
		}
		if(f.birth.value=="")
		{
			alert("생년월일을 입력하세요");
			f.pwd.focus();
			
			return false;
		}
		if(f.gender.value=="")
		{
			alert("성별을 입력하세요");
			f.pwd.focus();
			
			return false;
		}
		if(f.phone.value=="")
		{
			alert("핸드폰번호를 입력하세요");
			f.pwd.focus();
			
			return false;
		}
		if(f.address.value=="")
		{
			alert("주소를 입력하세요");
			f.pwd.focus();
			
			return false;
		}
		
		if(f.pwd.value != f.pwd_check.value)
		{
			alert("비밀번호와 비밀번호 확인이 같지 않습니다.");
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
              <h1>Join</h1>
              <span class="subheading">회원가입 페이지 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
        
         <form name="signupFrm" method="post" action="<c:url value="/Source/MemberRegist" />"
         	onsubmit="return formCheck();" novalidate>
         	
         	<table class="table table-striped table-bordered">
         		<colgroup>
						<col width="30%" />
						<col width="70%" />
				</colgroup>
         		<tr>
         			<th>아이디</th>
         			<td>
         				<input type="text" class="form-control" placeholder="아이디" name="id"/>
         			</td>
         		</tr>
         		<tr>
         			<th>비밀번호</th>
         			<td>
         				<input type="password" class="form-control" placeholder="비밀번호" name="pwd"/>
         			</td>
         		</tr>
         		<tr>
         			<th>비밀번호 확인</th>
         			<td>
         				<input type="password" class="form-control" placeholder="비밀번호 확인" name="pwd_check"/>
         			</td>
         		</tr>
         		<tr>
         			<th>이름</th>
         			<td>
         				<input type="text" class="form-control" placeholder="이름" name="name"/>
         			</td>
         		</tr>
         		<tr>
         			<th>생년월일</th>
         			<td>
         				<input type="text" class="form-control" placeholder="생년월일" name="birth">
         			</td>
         		</tr>
         		<tr>
         			<th>성별</th>
         			<td>
         				<input type="text" class="form-control" placeholder="성별" name="gender">
         			</td>
         		</tr>
         		<tr>
         			<th>휴대폰</th>
         			<td>
         				<input type="tel" class="form-control" placeholder="핸드폰" name="phone">
         			</td>
         		</tr>
         		<tr>
         			<th>이메일</th>
         			<td>
         				<input type="email" class="form-control" placeholder="이메일" name="email">
         			</td>
         		</tr>
         		<tr>
         			<th>주소</th>
         			<td>
         				<input type="text" class="form-control" placeholder="주소" name="address">
         			</td>
         		</tr>
         	</table>
         	
            <br>
            
            <div id="success"></div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary" id="Join">Join</button>
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
