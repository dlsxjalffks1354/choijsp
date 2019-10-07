<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<%

if(session.getAttribute("manager") == null) {
	response.sendRedirect("../main/main2.member");
}

%>
	<%@ include file="../title/header.jsp" %>
<script type="text/javascript">
	function Check()
	{
		alert("관리자가 아닙니다.");
		
		location.href = "../main/login.jsp";
	}
</script>
<meta charset="UTF-8">
  <style>
      
  	.site-heading{
  	text-align:center;
  	}
  	.post-preview{
  	text-align:center;
  	}
  	
  </style>
<title>프로젝트</title>
</head>
<body>
	<%
	String id = (String)session.getAttribute("id");
	String name =(String)session.getAttribute("name");
	String Nickname =(String)session.getAttribute("Nickname");
	String manager =(String)session.getAttribute("manager");
	%>
	
	
	<%@ include file="../title/navigation.jsp"  %>

	
	<!-- 페이지 헤더 -->
    <header class="masthead" style="background-image: url('../img/img2.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
         <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>Manager</h1>
              <span class="subheading" >관리자 관리 화면 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
    
  <div class="container">
  	<div class="row">
    	<div class="col">
    	
    	<%if(session.getAttribute("manager")==null) {%>
     		<a href="javascript: Check();">
     		<div class="post-preview">
              <h2 class="post-title">
                회원조회
              </h2>
               <h3 class="post-subtitle">
                회원조회 페이지로<br>이동합니다.
              </h3>
             </div>
            </a>
   			<hr>

 
        <% }else { %>
        <div class="post-preview">
             <a href="../manager/list.member?page=1">
              <h2 class="post-title">
                회원조회
              </h2>
               <h3 class="post-subtitle">
                회원조회 페이지로<br>이동합니다.
              </h3>
             </a>
              	<table width="200" cellpadding="0" cellspacing="0"border="1" class="table">
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
				</table>
				
        </div>
   		
        <% } %>
   	</div>
   	<div class="col">
        <a href="../manager/list2.member?page=1">
        <h2 class="post-preview">
          게시글 관리
         </h2>
        <h3 class="post-preview">
            게시글 관리 페이지로<br>이동합니다.
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
				<c:forEach items="${mlist2}" var="dto">
							<tr>
								<td>${dto.bId}</td>
								<td>${dto.bName}</td>
								<td>
								<a href="../manager/content_view2.member?bId=${dto.bId}">
								<c:forEach begin="1"  end="${dto.bIndent}">-</c:forEach>
								${dto.bTitle}
								</a>
								</td>
								<td>${dto.bDate}</td>
								<td>${dto.bHit}</td>
							</a>
							</tr>	
							
				</c:forEach>
				</table>
				</div>
   	
    </div>
    <div class="row" >
    <div class="col">
            <a href="../manager/list3.member?page=1">
             	 	<h2 class="post-preview">
                		댓글 관리
              		</h2>
               		<h3 class="post-preview">
                		댓글 관리 페이지로<br>이동합니다.
              		</h3>
              </a>
              			<table width="200" cellpadding="0" cellspacing="0"border="1" class="table">
						<tr class="table-primary">
							<td>번호</td>
							<td>작성자</td>
							<td>게시글 제목</td>
							<td>날짜</td>
							<td>댓글 내용</td>
						</tr>
						<c:forEach items="${clist}" var="dto">
							<tr>
							<td>${dto.cnum}</td>
							<td>${dto.cid}</td>
							<td>
							<a href = "content_view.member?bId=${dto.cboard}">${dto.ptitle}</a>	
							</td>
							<td>${dto.cDate}</td>
							<td>${dto.ccontent}</td>
							</tr>	
							
						</c:forEach>
						</table>
				</div>
    	<div class="col">
             <a href="../notice/list.nc?page=1">
             	 	<h2 class="post-preview">
                		공지사항
              		</h2>
               		<h3 class="post-preview">
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
  		</div>
  				<div class="row" >
 				 <div class="col-sm" >
      	 				<table width="200" cellpadding="0" cellspacing="0"border="1" class="table">
      	 					<a href="../reference/list.rf?page=1">
        					<h2 class="post-preview">
         						자료실
        					</h2>
        					<h3 class="post-preview">
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
       			 <div class="col">
      		<a href="../free/list.bbs?page=1">
              <h2 class="post-preview">
               	자유게시판
              </h2>
               <h3 class="post-preview">
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
<hr>
<%@ include file="../title/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
</body>
</html>