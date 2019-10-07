<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.study.jsp.sign.MemberDTO" %>
<%@page import="com.study.jsp.sign.MemberDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  	</style>
<meta charset="UTF-8">
</head>
<body>
		<%@ include file="../title/navigation.jsp"  %>
		
		 <header class="masthead" style="background-image: url('../img/img2.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-lg-9 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>Write</h1>
              <span class="subheading">글내용 페이지 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
    
    <%
	String id = (String)session.getAttribute("id");
    String manager = (String)session.getAttribute("manager");
	%>
	<%
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = dao.getMember(id);
	%>
		
		<table width="500" cellpadding="0" cellspacing="0"border="1" style="margin-left: auto; margin-right: auto;">
		<tr>
			<td>번호</td>
			<td> ${content_view.nId}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td> ${content_view.nHit}</td>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td> ${content_view.nName}</td>
		</tr>
		<tr>
			<td>글 제목</td>
			<td> ${content_view.nTitle}</td>
		</tr>
		
		<tr>
			<td>글 내용</td>
			<td> ${content_view.nContent}</td>
		</tr>
		<tr>
			<td colspan="2">
			<c:choose>
				<c:when test="${id eq content_view.nUser}">
					<a href="modify_view.nc?nId=${content_view.nId}">수정</a>&nbsp;&nbsp;
					<a href="list.nc?page=<%=session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
					<a href="delete.nc?nId=${content_view.nId}">삭제</a>&nbsp;&nbsp;
					<a href="reply_view.nc?nId=${content_view.nId}">답변</a>&nbsp;&nbsp;
				</c:when>
				<c:when test="${manager eq 'y'}">
					<a href="modify_view.nc?nId=${content_view.nId}">수정</a>&nbsp;&nbsp;
					<a href="list.nc?page=<%=session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
					<a href="delete.nc?nId=${content_view.nId}">삭제</a>&nbsp;&nbsp;
					<a href="reply_view.bbs?bId=${content_view.nId}">답변</a>&nbsp;&nbsp;
				</c:when>
				<c:otherwise>
					<a href="list.nc?page=<%=session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
				</c:otherwise>
			</c:choose>
			
			
			
			</td>
		</tr>
		</table>
		
		
</body>
</html>