<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("ValidMem") == null){
		response.sendRedirect("../main/main2.member");
	}
%>    

<!DOCTYPE html>
<html>
<head>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<style>
      
  	.site-heading
  	{
  	text-align:center;
  	}
  </style>
 <%@ include file="../title/header.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
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
              <span class="subheading">글 수정 페이지입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
		<table width="500" cellpadding="0" cellspacing="0"border="1" style="margin-left: auto; margin-right: auto;">
			<form action = "modify.bbs" method="post">
				<input type="hidden" name="bId" value="${content_view.bId}">
				<tr>
					<td>번호 </td>
					<td>${content_view.bId}</td>
				</tr>
				<tr>
					<td>히트 </td>
					<td>${content_view.bHit}</td>
				</tr>
				<tr>
				
					<td>이름 </td>
					<td><input type="text" readonly class="form-control-plaintext" name="bName" id="bName"  value="${content_view.bName}"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" class="form-control-plaintext" name="bTitle" id="bTitle"  value="${content_view.bTitle}"></td>
				</tr>	
				<tr>
					<td>내용</td>
					<td>
					<textarea name="bContent" id="summernote" rows="10" cols="100">${content_view.bContent}</textarea>
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
				<tr>
					<td colspan="2"> <input type="submit" value="수정"> &nbsp;&nbsp;
						<a href="content_view.bbs?bId=${content_view.bId}">취소</a>&nbsp;&nbsp;
						<a href="list.bbs?page=<%=session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
					</td>
				</tr>
			</form>
		</table>
		
</body>
</html>