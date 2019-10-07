<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%

	if(session.getAttribute("manager") == null) {
		response.sendRedirect("../main/main2.member");
	}
	String searchoption = (String)session.getAttribute("searchoption");
	String dayoption = (String)session.getAttribute("dayoption");
	String search = (String)session.getAttribute("search");
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
              <h1>User</h1>
              <span class="subheading" >댓글 관리 페이지 입니다</span>
            </div>
          </div>
        </div>
      </div>
    </header>
    
	<table width="500" cellpadding="0" cellspacing="0"border="1" class="table">
		<tr class="table-primary">
			<td>번호</td>
			<td>작성자</td>
			<td>게시글 제목</td>
			<td>날짜</td>
			<td>댓글 내용</td>
		</tr>
		<c:forEach items="${list}" var="Dto">
		<tr>
			<td>${Dto.cnum}</td>
			<td>${Dto.cid}</td>
			<td>
				<a href = "content_view.bbs?bId=${Dto.cboard}">${Dto.ptitle}</a>	
			</td>
			<td>${Dto.cDate}</td>
			<td>${Dto.ccontent}</td>
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
			<li class="page-item"><a class="page-link" href="csearch.member?page=1&searchoption=<%= searchoption %>&dayoption=<%= dayoption %>&search=<%= search %>">처음</a></li>
		</c:otherwise>
		</c:choose>
		<!-- 이전 -->
		<c:choose>
		<c:when test="${(page.curPage -1)<1}">
			<li class="page-item disabled"><span class="page-link">이전</span>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="csearch.memeber?page=${page.curPage -1}&searchoption=<%= searchoption %>&dayoption=<%= dayoption %>&search=<%= search %>">이전</a>
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
		<a class="page-link" href="csearch.member?page=${fEach}&searchoption=<%= searchoption %>&dayoption=<%= dayoption %>&search=<%= search %>">${fEach}</a>&nbsp;
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
		<a class="page-link" href="csearch.member?page=${page.curPage + 1}&searchoption=<%= searchoption %>&dayoption=<%= dayoption %>&search=<%= search %>">다음</a>
		</c:otherwise>
		</c:choose>
		<!-- 끝 -->
		<c:choose>
		<c:when test= "${page.curPage == page.totalPage}">
		<li class="page-item disabled"><span class="page-link">끝</span>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="csearch.member?page==${page.totalPage}&searchoption=<%= searchoption %>&dayoption=<%= dayoption %>&search=<%= search %>">끝</a>
		</c:otherwise>
		</c:choose>
		</ul>
		</nav>
		</td>
		</tr>
	</table>
	<div class = "container align-items-center">
		<form class = "form-inline align-item-center" method="post" action="csearch.member">
			<div class="form-group col-lg-autor">
				<select id = "searchoption" name = "searchoption" class="form-control" onchange = "change()">
    				<option value="id" selected>아이디</option>
    				<option value="name">내용</option>
    				<option value="term">기간</option>
				</select>
				
				<input type = "search" id = "search" name = "search" class = "form-control mr-sm-2" placeholder = "검색" aria-label="search">
				
				<select id = "dayoption" name = "dayoption" class="form-control" style ="display : none">
    				<option value="day" selected>1일</option>
    				<option value="week">1주일</option>
    				<option value="month">1달</option>
    				<option value="3month">3달</option>
    				<option value="6month">6달</option>
				</select>
				
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
			</div>
		</form>
	</div>
	
	<% if(session.getAttribute("dayoption") != null) { %>
		<table class = "table table-bordered table-hover">
		<thead class="table-primary"> 
			<tr>
				<th scope="col">댓글 작성량 순위</th>
				<th scope="col">아이디</th>				
				<th scope="col">닉네임</th>
				<th scope="col">블락여부</th>
				<th scope="col">총 개시글</th>
			</tr>	
		</thead>
		
		<c:forEach items = "${listUser}" var = "dto" varStatus="status">
		<tr>
			<td>${status.count}위</td>
			<td><a href = "content_view.member?id=${dto.id}">${dto.id}</a></td>
			<td>${dto.nickname}</td>
			<td>
				<c:choose>
					<c:when test = "${dto.ban eq 'y'}">
						블락 계정
					</c:when>
					<c:when test = "${dto.ban eq 'n'}">
						일반 계정
					</c:when>
				</c:choose>
			</td>
			<td>${dto.numComment}</td>
		</tr>
		</c:forEach>
	</table>	
	<% } %>		
	<br>
	
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