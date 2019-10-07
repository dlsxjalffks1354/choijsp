<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id = (String)session.getAttribute("id");
	String name =(String)session.getAttribute("name");
	String Nickname =(String)session.getAttribute("Nickname");
	String manager =(String)session.getAttribute("manager");
	String ban  =(String)session.getAttribute("ban");
%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../title/header.jsp" %>
<script type="text/javascript">
	function LoginCheck()
	{
		alert("로그인 후 이용하세요.");
		
		location.href =  "../main/main2.member";
	}
	function ReadyCheck()
	{
		alert("준비중입니다.");
		
		location.href = "../main/main2.member";
	}
</script>
<meta charset="UTF-8">
  <style>
      
  	.site-heading{
  	text-align:center;
  	}
  	.row
  	{
  	text-align:center;
  	}
  	
  </style>
<title>프로젝트</title>
</head>
<body>

	<%@ include file="../title/navigation.jsp"  %>
	
	
	<!-- 페이지 헤더 -->
    <header class="masthead" style="background-image: url('../img/img2.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
         <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>HOME</h1>
              <span class="subheading" >메인 화면 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    
  	<div class="container">
    		<%if(session.getAttribute("id")==null) {%>
    		<div class="row" >
     		<a href="javascript: LoginCheck();">
     			<div class="col">
     				<table width="300" cellpadding="0" cellspacing="0"border="1" class="table">
              		<h2 class="post-title">
                			공지사항
              		</h2>
               		<h3 class="post-subtitle">
                		공지사항 페이지로<br>이동합니다.
              		</h3>
              		</table>
              			<table width="300" cellpadding="0" cellspacing="0"border="1" class="table">
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
								${dto.nTitle}
								</td>
								<td>${dto.nDate}</td>
								<td>${dto.nHit}</td>
							</tr>		
						</c:forEach>
       				
						</table>	
              		<br>
              		<br>
             	</div>
            </a>
            <hr>
    			<div class="col" >
    			
    		 	<a href="javascript: LoginCheck();">
    		 	<table width="300" cellpadding="0" cellspacing="0"border="1" class="table">
              				<h2 class="post-title">
               					자유게시판
              				</h2>
              			 	<h3 class="post-subtitle">
              				  자유게시판 페이지로<br>이동합니다.
              				</h3>
              	</table>
              		<table width="300" cellpadding="0" cellspacing="0"border="1" class="table">
						<tr class="table-primary">
							<td>번호</td>
							<td>글쓴이</td>
							<td>제목</td>
							<td>작성시간</td>
							<td>조회수</td>
						</tr>
						<c:forEach items="${blist}" var="dto">
							<tr>
								<td>${dto.bId}</td>
								<td>${dto.bName}</td>
								<td>
								<c:forEach begin="1"  end="${dto.bIndent}">-</c:forEach>
								${dto.bTitle}
								</td>
								<td>${dto.bDate}</td>
								<td>${dto.bHit}</td>
							</tr>		
						</c:forEach>
					</table>
				</a>
				</div>
				</div>
				<div class="w-100"></div>
				<div class="row" >
					<div class="col-sm" >
					
      	 				<a href="javascript: LoginCheck();">
      	 				<table width="300" cellpadding="0" cellspacing="0"border="1" class="table">
        					<h2 class="post-title">
         						자료실
        					</h2>
        					<h3 class="post-subtitle">
       							자료실 페이지로<br> 이동합니다.
        					</h3>
        				</table>
        				<table width="300" cellpadding="0" cellspacing="0"border="1" class="table">
						<tr class="table-primary">
							<td>번호</td>
							<td>글쓴이</td>
							<td>제목</td>
							<td>작성시간</td>
							<td>조회수</td>
						</tr>
						<c:forEach items="${rlist}" var="dto">
							<tr>
								<td>${dto.bId}</td>
								<td>${dto.bName}</td>
								<td>
								<c:forEach begin="1"  end="${dto.bIndent}">-</c:forEach>
								${dto.bTitle}
								</td>
								<td>${dto.bDate}</td>
								<td>${dto.bHit}</td>
							</tr>		
						</c:forEach>
					</table>
        				</a>
       				</div>
       			<div class="col-sm" >
      	 				<a href="javascript: LoginCheck();">
      	 					<table width="300" cellpadding="0" cellspacing="0"border="1" class="table">
        					<h2 class="post-title">
         						야구게시판
        					</h2>
        					<h3 class="post-subtitle">
       							야구게시판 페이지로<br> 이동합니다.
        					</h3>
        					</table>
        				</a>
       			</div>
       			</div>	
        <% }else { %>
      	<div class="row" >
      		<div class="col">
             <a href="../notice/list.nc?page=1">
             	 	<h2 class="post-title">
                		공지사항
              		</h2>
               		<h3 class="post-subtitle">
                		공지사항 페이지로<br>이동합니다.
              		</h3>
              </a>
              			<table width="200" cellpadding="0" cellspacing="0"border="1" class="table">
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
								<a href="../notice/content_view.nc?nId=${dto.nId}">
								<c:forEach begin="1"  end="${dto.nIndent}">-</c:forEach>
								${dto.nTitle}
								</a>
								</td>
								<td>${dto.nDate}</td>
								<td>${dto.nHit}</td>
							</a>
							</tr>	
							
						</c:forEach>
						</table>
				</div>
			<div class="col">
      		<a href="../free/list.bbs?page=1">
              <h2 class="post-title">
               	자유게시판
              </h2>
               <h3 class="post-subtitle">
                자유게시판 페이지로<br>이동합니다.
              </h3>
              </a>
              <table width="200" cellpadding="0" cellspacing="0"border="1" class="table">
						<tr class="table-primary">
							<td>번호</td>
							<td>글쓴이</td>
							<td>제목</td>
							<td>작성시간</td>
							<td>조회수</td>
						</tr>
						<c:forEach items="${blist}" var="dto">
							<tr>
								<td>${dto.bId}</td>
								<td>${dto.bName}</td>
								<td>
								<a href="../free/content_view.bbs?bId=${dto.bId}">
								<c:forEach begin="1"  end="${dto.bIndent}">-</c:forEach>
								${dto.bTitle}
								</td>
								<td>${dto.bDate}</td>
								<td>${dto.bHit}</td>
							</tr>		
						</c:forEach>
					</table>
				</div>
   		</div>
			<div class="row" >
				<div class="col-sm" >
      	 				<table width="200" cellpadding="0" cellspacing="0"border="1" class="table">
      	 					<a href="../reference/list.rf?page=1">
        					<h2 class="post-title">
         						자료실
        					</h2>
        					<h3 class="post-subtitle">
       							자료실 페이지로<br> 이동합니다.
        					</h3>
        					</a>
        				</table>
        				 <table width="200" cellpadding="0" cellspacing="0"border="1" class="table">
						<tr class="table-primary">
							<td>번호</td>
							<td>글쓴이</td>
							<td>제목</td>
							<td>작성시간</td>
							<td>조회수</td>
						</tr>
						<c:forEach items="${rlist}" var="dto">
							<tr>
								<td>${dto.bId}</td>
								<td>${dto.bName}</td>
								<td>
								<a href="../reference/content_view.rf?bId=${dto.bId}">
								<c:forEach begin="1"  end="${dto.bIndent}">-</c:forEach>
								${dto.bTitle}
								</td>
								<td>${dto.bDate}</td>
								<td>${dto.bHit}</td>
							</tr>		
						</c:forEach>
					</table>
       			 </div>
       			<div class="col-sm" >
      	 					<table width="200" cellpadding="0" cellspacing="0"border="1" class="table">
      	 					<a href="javascript: ReadyCheck();">
        					<h2 class="post-title">
         						야구게시판
        					</h2>
        					<h3 class="post-subtitle">
       							야구게시판 페이지로<br> 이동합니다.
        					</h3>
        					</a>
        					</table>
       			</div>
       			 
       		</div>
        	<hr>
       </div>
        	<% } %>
<%@ include file="../title/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
</body>
</html>