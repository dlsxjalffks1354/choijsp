<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

	function LoginCheck()
	{
		alert("로그인 후 이용하세요.");
		
		location.href = "../main/login.jsp";
	}
</script>


<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>

<!-- 구글로그인 -->
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="402062581041-0mm7h0ilbhk7q0fe877840tquusi4kf1.apps.googleusercontent.com">
<!-- Navigation -->

	<style>
	.nav-item dropdown 
	{
  	background: blue;
  	float: right;
	}
	</style>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="../main/main2.member">Jsp 게시판</a>
       	<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
  		</button>
         <div class="collapse navbar-collapse" id="navbarNavDropdown">
    		<ul class="navbar-nav ml-auto">
     		 <li class="nav-item active">
              <a class="nav-link" href="../main/main2.member">
              메인 페이지
              <span class="sr-only">(current)</span>
              </a>
            </li>
            <% if(session.getAttribute("id")==null) { %>
            <li class="nav-item active">
             <a class="nav-link" href="javascript: LoginCheck();">
             공지사항
             </a>
             </li>
            <li class="nav-item active">
             <a class="nav-link" href="javascript: LoginCheck();">
             자유게시판
             </a>
             </li>
             <li class="nav-item active">
             <a class="nav-link" href="javascript: LoginCheck();">
             자료실
             </a>
             </li>
             <li class="nav-item active">
             <a class="nav-link" href="javascript: LoginCheck();">
             채팅방
             </a>
             </li>
             <% }else {%>
                         <li class="nav-item active">
             <a class="nav-link" href="../notice/list.nc?page=1">
             공지사항
             </a>
             </li>
            <li class="nav-item active">
             <a class="nav-link" href="../free/list.bbs?page=1">
             자유게시판
             </a>
             </li>
             <li class="nav-item active">
             <a class="nav-link" href="../reference/list.rf?page=1">
             자료실
             </a>
             </li>
             <li class="nav-item active">
             <a class="nav-link" href="../main/chat.jsp">
             채팅방
             </a>
             </li>
             <%} %>
            <% if(session.getAttribute("id")==null) { %>
            <ul class="nav navbar-nav navbar-right">
            <li class="nav-item dropdown" >
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	메뉴
        	</a>
        	 <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="../main/gokosmo.jsp">찾아오시는길</a>
              	<a class="dropdown-item" href="../main/login.jsp">로그인</a>
              	<a class="dropdown-item" href="../main/join.jsp">회원가입</a>
            </div>
            </li>
            <% } else{
            if(session.getAttribute("manager")==null) {%>
            <li class="nav-item dropdown">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	<%=session.getAttribute("Nickname")%>님 환영합니다.
        	</a>
        	 <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="../main/gokosmo.jsp">찾아오시는길</a>
              <a class="dropdown-item" href="../main/modify2.jsp">개인정보수정</a>
	          <a class="dropdown-item" href="../main/Logout.jsp">로그아웃</a>
	           <a class="dropdown-item" href="../main/withdraw.jsp">회원탈퇴</a>
	          </div>
            </li>
            <%}else {%>
            <li class="nav-item dropdown">
        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	관리담당자님 환영합니다.
        	</a>
        	<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="../main/gokosmo.jsp">찾아오시는길</a>
              <a class="dropdown-item" href="../main/modify2.jsp">개인정보수정</a>
              <a class="dropdown-item" href="../manager/main.member">관리자페이지</a>
	          <a class="dropdown-item" href="../main/Logout.jsp">로그아웃</a>
	           </div>
            </li>
            <%}} %>
          </ul>
        </div>
    </nav>