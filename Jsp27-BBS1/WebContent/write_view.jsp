<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>


	<table width="500" cellpadding="0" cellspacing="0"border="1">
		<form action="write.do" method="post">
		<tr>
			<td> 이름 </td>
			<td> <input type="text" name="bName" size="50"> </td>
		</tr>
		<tr>
			<td> 제목 </td>
			<td> <input type="text" name="bTitle" size="50"> </td>
		</tr>
		<tr>
			<td> 내용 </td>
			<td> <textarea name="bContent" id="summernote" rows="10"></textarea>
		
			 <script>
                	 $('#summernote').summernote({
                	       options : {disableDragAndDrop:false},
                	       lang: 'ko-KR',
                	       height :500,
                	       toolbar: [
                	    	['style', ['style']],
                	    	['style', ['bold', 'italic', 'underline','strikethrough','clear']],
                	    	['fontface', ['fontname']],
                	    	['textsize',['fontsize']],
                	    	['color', ['color']],
                	    	['alignment', ['ul', 'ol','paragraph','lineheight']],
                	    	['height', ['height']],
                	    	['table', ['table']],
                	    	[]
                	       ]
                	      });
            </script>
            
 
            </td>
		</tr>
		
		<tr >
			<td colspan="2">
				<input type="submit" value="입력"> &nbsp;&nbsp;
				<a href="list.do">목록보기</a>
			</td>
		</tr>
	</form>
</table>
</body>
</html>