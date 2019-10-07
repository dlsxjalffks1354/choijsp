<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script>
	function form_check() {
		oEditor.getById["ir1"].exec("UPDAT_CONTENTS_FIELD", []);
		
		document.modify_form.submit();
	}
</script>
<style>
  	.site-heading
  	{
  	text-align:center;
  	}
  </style>
 <%@ include file="../title/header.jsp" %>
<meta charset="UTF-8">
<title>Jsp 공지사항</title>
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
			<form action = "modify.nc" method="post">
				<input type="hidden" name="nId" value="${content_view.nId}">
				<tr>
					<td>번호 </td>
					<td>${content_view.nId}</td>
				</tr>
				<tr>
					<td>히트 </td>
					<td>${content_view.nHit}</td>
				</tr>
				<tr>
				
					<td>이름 </td>
					<td><input type="text" readonly class="form-control-plaintext" name="nName" id="nName"  value="${content_view.nName}"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" class="form-control-plaintext" name="nTitle" id="nTitle"  value="${content_view.nTitle}"></td>
				</tr>	
				<tr>
					<td>내용</td>
					<td>
						<textarea rows="10" name="nContent" id="summernote" rows="10" cols="100"> ${content_view.nContent}</textarea>
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
						<a href="content_view.nc?nId=${content_view.nId}">취소</a>&nbsp;&nbsp;
						<a href="list.nc?page=<%=session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
					</td>
				</tr>
			</form>
		</table>
</body>
</html>