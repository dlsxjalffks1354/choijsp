<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	if(session.getAttribute("manager") == null) {
		response.sendRedirect("../main/main2.member");
	}

%>   

<!DOCTYPE html>
<html>
<head>
<style>
  	.site-heading
  	{
  	text-align:center;
  	}
  </style>
 <%@ include file="../title/header.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<%@ include file="../title/navigation.jsp"  %>
		
		<!-- 페이지 헤더 -->
    <header class="masthead" style="background-image: url('../img/img2.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-lg-9 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>Modify</h1>
              <span class="subheading">회원정보 수정페이지 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
		<table width="500" cellpadding="0" cellspacing="0"border="1" style="margin-left: auto; margin-right: auto;">
			<form action = "modify.member" method="post">
			<tr>
				<td>유저아이디</td>
				<td><input type="text" class="form-control-plaintext" name="Id" id="Id"  value="${content_view.id}"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" class="form-control-plaintext" name="Pw" id="Pw"  value="${content_view.pw}"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" class="form-control-plaintext" name="Name" id="Name"  value="${content_view.name}"> </td>
			</tr>
		
			<tr>
				<td>사용중인 닉네임</td>
				<td><input type="text" class="form-control-plaintext" name="Nickname" id="Nickname"  value="${content_view.nickname}"></td>
			</tr>
			<tr>
				<td>사용중인 이메일</td>
				<td><input type="text" class="form-control-plaintext" name="eMail" id="eMail"  value="${content_view.eMail}"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" class="form-control-plaintext" name="Address" id="Address"  value="${content_view.address}"> </td>
			</tr>
				
			<tr>
				<td colspan="2">
					<input type="submit" value="수정">
					<a href="content_view.member?Id=${content_view.id}">취소</a>&nbsp;&nbsp;
					<a href="list.member?page=<%=session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
				</td>
			</tr>
			</form>
		</table>
		<%@ include file="../title/footer.jsp" %>
</body>
	
</html>