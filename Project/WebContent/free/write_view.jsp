<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.study.jsp.sign.MemberDTO" %>
<%@page import="com.study.jsp.sign.MemberDAO" %>
<%
	if(session.getAttribute("ValidMem") == null){
	response.sendRedirect("../main/main2.member");
	}
	%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    
    <%@ include file="../title/header.jsp" %>
<meta charset="UTF-8">
	<style>
      
  	.site-heading
  	{
  	text-align:center;
  	}
  </style>
<title>글쓰기</title>
</head>
<body>
	<%@ include file="../title/navigation.jsp"  %>
	<!-- 페이지 헤더 -->
    <header class="masthead" style="background-image: url('../img/img2.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-lg-9 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>Write</h1>
              <span class="subheading">글쓰기 페이지 입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
    
    
	<%
	String Nickname =(String)session.getAttribute("Nickname");
	String id = (String)session.getAttribute("id");
	%>
	<%
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = dao.getMember(id);
	%>

	<table width="500" cellpadding="0" cellspacing="0"border="1" style="margin-left: auto; margin-right: auto;">
		<form action="write.bbs" method="post">
		<tr>
			<td> 글쓴이 </td>
			<td> 
			 <input type="text" readonly class="form-control-plaintext" name="bName" id="staticEmail"  value="<%=dto.getNickname() %>">
			</td>
		</tr>
		<tr>
			<td> 글 제목 </td>
			<td> 
			<input type="text" name="bTitle" size="50" > 
			</td>
		</tr>
		<tr>
			<td> 내용 </td>
			<td> <textarea name="bContent" id="summernote" rows="10" cols="100"></textarea>
			<script>
     					$('#summernote').summernote({
     						options : {disableDragAndDrop: false},
     						lang : 'ko-KR',
        					height: 500,
        					toolbar: [
        						['style', ['style']],
        						['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
        						['fontface', ['fontname']],
        						['textsize', ['textsize']],
        						['color', ['color']],
        						['alignment', ['ul', 'ol', 'paragraph', 'lineheight']],
        						['height', ['height']],
        						['table', ['table']],
        						['insert', ['insert']],        						
        					]
     					 });
    		</script>
            </td>
		</tr>
		 <input type="hidden" name="bUser" id="bUser" value="<%=dto.getId() %>">
		<tr>
			<td colspan="2">
				<input type="submit" value="입력"> &nbsp;&nbsp;
				<a href="list.bbs">목록보기</a>
			</td>
		</tr>
	</form>
</table>
</body>
</html>