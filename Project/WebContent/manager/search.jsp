<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

	if(session.getAttribute("manager") == null) {
		response.sendRedirect("../main/main2.member");
	}

%>
	<%
	String searchoption = (String)session.getAttribute("searchoption");
	String search = (String)session.getAttribute("search");
	%> 
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../title/header.jsp" %>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	
<meta charset="UTF-8">
<title>Jsp게시판</title>
  <style> 
  	.site-heading
  	{
  	text-align:center;
  	}
  	td,td
  	{
  	text-align: center;
  	vertical-align: middle;
  	}
  </style>
</head>
<body>
	
	<%@ include file="../title/navigation.jsp"  %>
	
	
	<!-- 페이지 헤더 -->
    <header class="masthead" style="background-image: url('../img/img2.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col">
            <div class="site-heading">
              <h1>User</h1>
              <span class="subheading" >유저관리 페이지 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
    
    
	<table width="500" cellpadding="0" cellspacing="0"border="1" class="table">
			<tr class="table-primary">
			<td>유저 아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>가입일</td>
			<td>사용중인 닉네임</td>
			<td>사용중인 이메일</td>
			<td>주소</td>
		</tr>
		<c:forEach items="${mlist}" var="Dto">
			<tr>
				<td>
				<a href="content_view.member?id=${Dto.id}">${Dto.id}</a>
				</td>
				<td>${Dto.pw}</td>
				<td>${Dto.name}</td>
				<td>${Dto.rDate}</td>
				<td>${Dto.nickname}</td>
				<td>${Dto.eMail}</td>
				<td>${Dto.address}</td>
			</tr>		
		</c:forEach>
		
		<tr>
		<td colspan ="5">
		<!-- 처음 -->
		<nav>
		<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${(page.curPage -1)<1}">
				<li class="page-item disabled"><span class="page-link">처음</span></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="search.nc?page=1&searchoption=<%= searchoption %>&search=<%= search %>">처음</a></li>
			</c:otherwise>
		</c:choose>
		<!-- 이전 -->
		<c:choose>
		<c:when test="${(page.curPage -1)<1}">
			<li class="page-item disabled"><span class="page-link">이전</span>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="search.nc?page=${page.curPage -1}&searchoption=<%= searchoption %>&search=<%= search %>" >이전</a>
		</c:otherwise>
		</c:choose>
		
		<!-- 개별페이지 -->
		<c:forEach var ="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
		<c:choose>
		<c:when test="${page.curPage == fEach}">
		<li class="page-item active">
      	<span class="page-link">
			${fEach}&nbsp;
		</span>
		</c:when>
		
		<c:otherwise>
		<li class="page-item">
		<a class="page-link" href="search.member?page=${fEach}&searchoption=<%= searchoption %>&search=<%= search %>">${fEach}</a>&nbsp;
		</c:otherwise>
		</c:choose>		
		</c:forEach>
		
		<!--  다음 -->
		<c:choose>
		<c:when test = "${(page.curPage +1) > page.totalPage}">
		<li class="page-item disabled"><span class="page-link">다음</span>
		</c:when>
		<c:otherwise>
		<li class="page-item">
		<a class="page-link" href="search.member?page=${page.curPage + 1}&searchoption=<%= searchoption %>&search=<%= search %>">다음</a>
		</c:otherwise>
		</c:choose>
		<!-- 끝 -->
		<c:choose>
		<c:when test= "${page.curPage == page.totalPage}">
		<li class="page-item disabled"><span class="page-link">끝</span>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="search.member?page=${page.totalPage}&searchoption=<%= searchoption %>&search=<%= search %>">끝</a>
		</c:otherwise>
		</c:choose>
		</ul>
		</nav>
		</td>
		</tr>
	</table>
	<div class = "container" align-items-center">
		<form class = "form-inline align-item-center" method="post" action="search.member">
			<div class="form-group col-lg-autor">
				<select name = "searchoption" class="form-control">
    				<option value="id" selected>아이디</option>
    				<option value="name">이름</option>
				</select>
				<input type = "search" name = "search" class = "form-control mr-sm-5" placeholder = "검색" aria-label="search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
			</div>
		</form>	
	</div>
	totalCount : ${page.totalCount}<br>
	listCount : ${page.listCount}<br>	
	totalPage : ${page.totalPage}<br>	
	curPage : ${page.curPage}<br>	
	pageCount : ${page.pageCount}<br>	
	startPage : ${page.startPage}<br>	
	endPage : ${page.endPage}<br>	
	
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <%@ include file="../title/footer.jsp" %>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

  
		
			
</body>
</html>