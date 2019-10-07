<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
	if(session.getAttribute("ValidMem") == null){
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
	<script src = "http://code.jquery.com/jquery-1.7.js"></script>	
    <title>자료실</title>

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
              <h1>Reference</h1>
              <span class="subheading" >자료실 페이지입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
    
    
	<!--  게시판 리스트 폼 -->
	<table width="500" cellpadding="0" cellspacing="0"border="1" class="table">
		<tr class="table-primary">
			<td>번호</td>
			<td>글쓴이</td>
			<td>제목</td>
			<td>작성시간</td>
			<td>조회수</td>
			<td>좋아요</td>
		</tr>
		
		<c:forEach items = "${rlist}" var = "dto">
		<tr>
			<td>${dto.bId}</td>
			<td>${dto.bName}</td>
			<td>
				<c:forEach begin = "1" end = "${dto.bIndent}">-</c:forEach>
				<a href = "content_view.rf?bId=${dto.bId}">${dto.bTitle}</a>
				<c:if test = "${dto.bNew eq true}">
					<span style = "color:red">- new</span>
				</c:if>		
			</td>
			<td>${dto.bDate}</td>
			<td>${dto.bHit}</td>
			<td>${dto.bLike}</td>
		</tr>
		</c:forEach>
		<!-- <tr>		
			<td colspan = "5"><a href = "write_view.do">글작성</a></td>
		</tr> -->		
		<tr>			
			<td colspan = "6">
			<nav>
				<ul class="pagination pg-blue justify-content-center">
					<!-- 처음 -->
					<c:choose>
						<c:when test="${(page.curPage -1) < 1}">
							<li class="page-item disabled"><a class="page-link disabled" href="#">First</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="list.rf?page=1">First</a></li>
						</c:otherwise>
					</c:choose>
					<!-- 이전 -->
					<c:choose>
						<c:when test="${(page.curPage -1) < 1}">
							<li class="page-item disabled"><a class="page-link disabled" href="list.rf?page=${page.curPage -1}">Previous</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="list.rf?page=${page.curPage -1}" >Previous</a></li>			
						</c:otherwise>
					</c:choose>
			
					<!-- 개별페이지 -->
					<c:forEach var= "fEach" begin="${page.startPage}" end= "${page.endPage}" step="1">
					<c:choose>
						<c:when test="${page.curPage == fEach}">
							<li class="page-item active"><a class="page-link" href="#">${fEach}</a></li> &nbsp; &nbsp;
						</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="list.rf?page=${fEach}">${fEach}</a></li> &nbsp;						
					</c:otherwise>
					</c:choose>
					</c:forEach>
			
					<!-- 다음 -->
					<c:choose>
						<c:when test='${(page.curPage + 1) > page.totalPage}'>
							<li class="page-item disabled"><a class="page-link disabled" href="list.rf?page=${page.curPage -1}">Next</a></li>
						</c:when>
						<c:otherwise>	
							<li class="page-item"><a class="page-link" href="list.rf?page=${page.curPage + 1}">Next</a></li>
						</c:otherwise>
					</c:choose>
			
					<!-- 끝 -->
					<c:choose>
						<c:when test='${page.curPage == page.totalPage}'>
							<li class="page-item disabled"><a class="page-link disabled" href="#" onclick = "prev();">Last</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="list.rf?page=${page.totalPage}">Last</a></li>
						</c:otherwise>
					</c:choose>
				</ul>				
			</nav>
			</td>		
		</tr>
	</table>
	
	<!-- 검색 -->
	<div align="left">
		<div class = "container align-items-center">
		<form class = "form-inline align-item-center" method="post" action="search.rf">
			<div class="form-group col-lg-autor">
				<select name = "searchoption" class="form-control">
    				<option value="title" selected>글제목</option>
    				<option value="writer">글쓴이</option>
    				<option value="content">글내용</option>
				</select>
				<input type = "search" name ="search" class = "form-control mr-sm-5" placeholder = "검색" aria-label="search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
			</div>
		</form>		
		<div align = "right" >
			<button type="button" class = "btn btn-primary" onclick ="location.href='write_view.rf'">글쓰기</button>
		</div>
	</div>
			
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
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>