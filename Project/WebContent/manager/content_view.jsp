<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.study.jsp.sign.MemberDTO" %>
<%@page import="com.study.jsp.sign.MemberDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%

if(session.getAttribute("manager") == null) {
	response.sendRedirect("../main/main2.member");
}

%>
	
	
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
    <script src="https://cdn.ckeditor.com/4.12.1/standard/ckeditor.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    
    <%@ include file="../title/header.jsp" %>
    
    <style>
      
  	.site-heading
  	{
  	text-align:center;
  	}
  	
  	h2
  	{
  	text-align:center;
  	}
  	</style>
<meta charset="UTF-8">
</head>
<body>
		<%@ include file="../title/navigation.jsp"  %>
		
		 <header class="masthead"style="background-image: url('../img/img2.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-lg-9 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>User</h1>
              <span class="subheading">사용자 정보페이지입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
    	
		<table width="800" cellpadding="0" cellspacing="0"border="1" style="margin-left: auto; margin-right: auto;">
		<tr>
			<td>유저아이디</td>
			<td> ${content_view.id}</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td> ${content_view.pw}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td> ${content_view.name}</td>
		</tr>
		<tr>
			<td>가입일</td>
			<td> ${content_view.rDate}</td>
		</tr>
		
		<tr>
			<td>사용중인 닉네임</td>
			<td> ${content_view.nickname}</td>
		</tr>
		<tr>
			<td>사용중인 이메일</td>
			<td> ${content_view.eMail}</td>
		</tr>
		<tr>
			<td>주소</td>
			<td> ${content_view.address}</td>
		</tr>
		<tr>
			<td>총 게시글</td>
			<td> ${content_view.mContent}</td>
		</tr>
		<tr>
			<td>총 댓글</td>
			<td> ${content_view.mReply}</td>
		</tr>
		<tr>
			<td>사용 정지</td>
			
			<td><c:choose>
				<c:when test="${content_view.ban eq 'n'}"> 
				일반계정
				</c:when>
				<c:otherwise>
				정지된 계정
				</c:otherwise>
				</c:choose>
			</td>
				
		</tr>
		<tr>
			<td colspan="2">
			<c:choose>
				<c:when test="${content_view.ban eq 'n'}">
					<a href="modify_view.member?Id=${content_view.id}">수정</a>&nbsp;&nbsp;
					<a href="list.member?page=<%=session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
					<a href="ban.member?Id=${content_view.id}">로그인 정지</a>&nbsp;&nbsp;
					<a href="delete.member?Id=${content_view.id}">회원삭제</a>&nbsp;&nbsp;
				</c:when>
				<c:otherwise>
					<a href="modify_view.member?Id=${content_view.id}">수정</a>&nbsp;&nbsp;
					<a href="list.member?page=<%=session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
					<a href="ban.member?Id=${content_view.id}">로그인 정지 해제</a>&nbsp;&nbsp;
					<a href="delete.member?Id=${content_view.id}">회원삭제</a>&nbsp;&nbsp;
				</c:otherwise>
			</c:choose>
				
			
			
			</td>
		</tr>
		</table>
		
			<h2 align:center>작성 게시글</h2>
	<!--  유저 게시글 폼 -->
	<table width="500" cellpadding="0" cellspacing="0"border="1" class="table">
		<tr class="table-primary">
			<td>번호</td>
			<td>글쓴이</td>
			<td>제목</td>
			<td>작성시간</td>
			<td>조회수</td>
			<td>좋아요</td>
			<td>게시판</td>
		</tr>
		<c:forEach items="${mlist}" var="dto">
		<tr>
			<td>${dto.bId}</td>
			<td>${dto.bName}</td>
			<td>
				<c:forEach begin="1"  end="${dto.bIndent}">-</c:forEach>
				<c:choose>
					<c:when test = "${dto.type eq 'reference'}">
						<a href="content_view.rf?bId=${dto.bId}">${dto.bTitle}</a>
					</c:when>
					<c:when test = "${dto.type eq 'free'}">
						<a href="content_view.bbs?bId=${dto.bId}">${dto.bTitle}</a>
					</c:when>
				</c:choose>
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
				</c:choose>
				</td>
		</tr>		
		</c:forEach>
		<!-- <tr>		
			<td colspan = "5"><a href = "write_view.do">글작성</a></td>
		</tr> -->		
		<tr>			
			<td colspan = "12">
			<nav>
				<ul class="pagination pg-blue justify-content-center">
					<!-- 처음 -->
					<c:choose>
						<c:when test="${(page.curPage -1) < 1}">
							<li class="page-item disabled"><a class="page-link disabled" href="#">처음으로</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="content_view.member?id=${content_view.id}&page=1">First</a></li>
						</c:otherwise>
					</c:choose>
					<!-- 이전 -->
					<c:choose>
						<c:when test="${(page.curPage -1) < 1}">
							<li class="page-item disabled"><a class="page-link disabled" href="content_view.member??id=${content_view.id}&page=${page.curPage -1}">이전</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="content_view.member??id=${content_view.id}&page=${page.curPage -1}" >Previous</a></li>			
						</c:otherwise>
					</c:choose>
			
					<!-- 개별페이지 -->
					<c:forEach var= "fEach" begin="${page.startPage}" end= "${page.endPage}" step="1">
					<c:choose>
						<c:when test="${page.curPage == fEach}">
							<li class="page-item active"><a class="page-link" href="#">${fEach}</a></li> &nbsp; &nbsp;
						</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="content_view.member?id=${content_view.id}&page=${fEach}">${fEach}</a></li> &nbsp;						
					</c:otherwise>
					</c:choose>
					</c:forEach>
			
					<!-- 다음 -->
					<c:choose>
						<c:when test='${(page.curPage + 1) > page.totalPage}'>
							<li class="page-item disabled"><a class="page-link disabled" href="content_view.member?id=${content_view.id}&page=${page.curPage +1}">Next</a></li>
						</c:when>
						<c:otherwise>	
							<li class="page-item"><a class="page-link" href="content_view.member?id=${content_view.id}&page=${page.curPage + 1}">다음</a></li>
						</c:otherwise>
					</c:choose>
			
					<!-- 끝 -->
					<c:choose>
						<c:when test='${page.curPage == page.totalPage}'>
							<li class="page-item disabled"><a class="page-link disabled" href="#" onclick = "prev();">Last</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="content_view.member?id=${content_view.id}&page=${page.totalPage}">Last</a></li>
						</c:otherwise>
					</c:choose>
				</ul>				
			</nav>
			</td>		
		</tr>
	</table>
		
		
		
		
</body>
</html>