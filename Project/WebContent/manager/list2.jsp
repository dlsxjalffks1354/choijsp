<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*,java.text.SimpleDateFormat,java.util.Date"%>
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
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>게시판 리스트</title>
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
              <h1>게시글 관리</h1>
              <span class="subheading" >게시글 관리 페이지 입니다.</span>
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
			<td>좋아요</td>
			<td>쓰여진곳</td>
			
		</tr>
		<c:forEach items="${blist2}" var="dto">
		<tr>
			<td>${dto.bId}</td>
			<td>${dto.bName}</td>
			<td>
				<c:forEach begin="1"  end="${dto.bIndent}">-</c:forEach>
				<a href="content_view2.member?bId=${dto.bId}">${dto.bTitle}</a>
				<c:if test = "${dto.bNew eq true}">
               	<span style = "color:red">- new</span>
            	</c:if>  
			</td>
				<td>${dto.bDate}</td>
				<td>${dto.bHit}</td>
				<td>${dto.bLike}</td>
				<td>	
				<c:choose>
					<c:when test = "${dto.type eq 'reference'}">
						자료실
					</c:when>
					<c:when test = "${dto.type eq 'free'}">
						자유 게시판
					</c:when>
					<c:when test = "${dto.type eq 'notice'}">
						공지사항
					</c:when>
				</c:choose>
				</td>
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
			<li class="page-item"><a class="page-link" href="list2.memberpage=1">처음</a></li>
		</c:otherwise>
		</c:choose>
		<!-- 이전 -->
		<c:choose>
		<c:when test="${(page.curPage -1)<1}">
			<li class="page-item disabled"><span class="page-link">이전</span>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="list2.member?page=${page.curPage-1}">이전</a>
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
		<a class="page-link" href="list2.member?page=${fEach}">${fEach}</a>&nbsp;
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
		<a class="page-link" href="list2.member?page=${page.curPage + 1}">다음</a>
		</c:otherwise>
		</c:choose>
		<!-- 끝 -->
		<c:choose>
		<c:when test= "${page.curPage == page.totalPage}">
		<li class="page-item disabled"><span class="page-link">끝</span>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href= "list.bbs?page=${page.totalPage}">끝</a>
		</c:otherwise>
		</c:choose>
		</ul>
		</nav>
		</td>
		</tr>
	</table>
	<div class = "container align-items-center">
		<form class = "form-inline align-item-center" method="post" action="msearch.member">
			<div class="form-group col-lg-autor">
				<select id = "searchoption" name = "searchoption" class="form-control" onchange = "change()">
    				<option value="title" selected>제목</option>
    				<option value="writer">작성자</option>
    				<option value="content">내용</option>
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
    <%@ include file="../title/footer.jsp" %>
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
  
		
			
</body>
</html>