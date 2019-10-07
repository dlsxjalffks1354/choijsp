<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%

	if(session.getAttribute("manager") == null) {
		response.sendRedirect("../main/main2.member");
	}

%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../title/header.jsp" %>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<meta charset="UTF-8">
  <style> 
  	.site-heading
  	{
  	text-align:center;
  	}
  	td{
  	text-align: center;
  	vertical-align: middle;
  	}
  	
  	.footer
  	{
  	align:center;
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
              <h1>User Search</h1>
              <span class="subheading" >유저 검색 결과 페이지 입니다</span>
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
			<td>금지상태</td>
		</tr>
		<c:forEach items="${list}" var="Dto">
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
				<td>
				<c:choose>
					<c:when test = "${Dto.ban eq 'y'}">
						정지된 계정
					</c:when>
					<c:when test = "${Dto.ban eq 'n'}">
						일반 계정
					</c:when>
				</c:choose></td>
			</tr>		
		</c:forEach>
		
		
		<tr>
		<td colspan ="10">
		<!-- 처음 -->
		<nav>
		<ul class="pagination justify-content-center">
			<c:choose>
		<c:when test="${(page.curPage -1)<1}">
			<li class="page-item disabled"><span class="page-link">처음</span></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="list.member?page=1">처음</a></li>
		</c:otherwise>
		</c:choose>
		<!-- 이전 -->
		<c:choose>
		<c:when test="${(page.curPage -1)<1}">
			<li class="page-item disabled"><span class="page-link">이전</span>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="list.member?page=${page.curPage-1}">이전</a>
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
		<a class="page-link" href="list.member?page=${fEach}">${fEach}</a>&nbsp;
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
		<a class="page-link" href="list.member?page=${page.curPage + 1}">다음</a>
		</c:otherwise>
		</c:choose>
		<!-- 끝 -->
		<c:choose>
		<c:when test= "${page.curPage == page.totalPage}">
		<li class="page-item disabled"><span class="page-link">끝</span>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href= "list.member?page=${page.totalPage}">끝</a>
		</c:otherwise>
		</c:choose>
		</ul>
		</nav>
		</td>
		</tr>
	</table>
	<div class = "container align-items-center">
		<form class = "form-inline align-item-center" method="post" action="usersearch.member">
			<div class="form-group col-lg-autor">
				<select id = "searchoption" name = "searchoption" class="form-control" onchange = "change()">
    				<option value="id" selected>아이디</option>
    				<option value="nickname">닉네임</option>
				</select>
				
				<input type = "search" id = "search" name = "search" class = "form-control mr-sm-2" placeholder = "검색" aria-label="search">
				
				
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
			</div>
		</form>
	</div>
	
	<script>
		function change(){
			var option = document.getElementById("searchoption");
			
			var selectedOptionValue = option.options[option.selectedIndex].value;
			console.log(selectedOptionValue);
			if(selectedOptionValue == "term"){
				document.getElementById("search").style.display = "none";
				document.getElementById("dayoption").style.display = "block";
			} else{
				document.getElementById("search").style.display = "block";
				document.getElementById("dayoption").style.display = "none";
			}
		}
	</script>
	
	
	totalCount : ${page.totalCount}<br>
	listCount : ${page.listCount}<br>	
	totalPage : ${page.totalPage}<br>	
	curPage : ${page.curPage}<br>	
	pageCount : ${page.pageCount}<br>	
	startPage : ${page.startPage}<br>	
	endPage : ${page.endPage}<br>	
	
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <div class="footer">
    <%@ include file="../title/footer.jsp" %>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

  
		
			
</body>
</html>