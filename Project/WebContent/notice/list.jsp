<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	if(session.getAttribute("ValidMem") == null){
%>
		<jsp:forward page = "../main/main.jsp" />
	<%
	}
	%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../title/header.jsp" %>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

 
<meta charset="UTF-8">
<title>공지사항</title>
  <style> 
  	.site-heading
  	{
  	text-align:center;
  	}
  	td{
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
              <h1>Notice</h1>
              <span class="subheading" >공지사항 페이지 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
    
    
	<table width="500" cellpadding="0" cellspacing="0"border="1" class="table">
		<tr class="table-primary">
			<td>번호</td>
			<td>글쓴이</td>
			<td>제목</td>
			<td>작성시간</td>
			<td>조회수</td>
		</tr>
		<c:forEach items="${nlist}" var="dto">
		<tr>
			<td>${dto.nId}</td>
			<td>${dto.nName}</td>
			<td>
				<c:forEach begin="1"  end="${dto.nIndent}">-</c:forEach>
				<a href="content_view.nc?nId=${dto.nId}">${dto.nTitle}</a>
				<c:if test = "${dto.nNew eq true}">
               	<span style = "color:red">- new</span>
            	</c:if>  
			</td>
				
				<td>${dto.nDate}</td>
				<td>${dto.nHit}</td>
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
			<li class="page-item"><a class="page-link" href="list.nc?page=1">처음</a></li>
		</c:otherwise>
		</c:choose>
		<!-- 이전 -->
		<c:choose>
		<c:when test="${(page.curPage -1)<1}">
			<li class="page-item disabled"><span class="page-link">이전</span>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="list.nc?page=${page.curPage-1}">이전</a>
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
		<a class="page-link" href="list.nc?page=${fEach}">${fEach}</a>&nbsp;
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
		<a class="page-link" href="list.nc?page=${page.curPage + 1}">다음</a>
		</c:otherwise>
		</c:choose>
		<!-- 끝 -->
		<c:choose>
		<c:when test= "${page.curPage == page.totalPage}">
		<li class="page-item disabled"><span class="page-link">끝</span>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href= "list.nc?page=${page.totalPage}">끝</a>
		</c:otherwise>
		</c:choose>
		</ul>
		</nav>
		</td>
		</tr>
	</table>
	<div class = "container align-items-center">
		<form class = "form-inline align-item-center" method="post" action="search.nc">
			<div class="form-group col-lg-autor">
				<select name = "searchoption" class="form-control">
    				<option value="title" selected>글제목</option>
    				<option value="content">내용</option>
    				
				</select>
				<input type = "search" name = "search" class = "form-control mr-sm-5" placeholder = "검색" aria-label="search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
			</div>
		</form>	
	</div>
	<%if(session.getAttribute("manager")!=null) { %>
	<div align = "right" >
		<button type="button" class = "btn btn-primary" onclick ="location.href='write_view.nc'">공지사항 작성</button>
	</div>
	<%} %>
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
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

  
		
			
</body>
</html>