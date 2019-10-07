<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script>
	function form_check() {
		oEditor.getById["ir1"].exec("UPDAT_CONTENTS_FIELD", []);
		
		document.modify_form.submit();
	}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<table width="500" cellpadding="0" cellspacing="0"border="1">
			<form action = "modify.do" method="post">
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
					<td><input type="text" name="bName" value="${content_view.bName}"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="bTitle" value="${content_view.bTitle}"></td>
				</tr>	
				<tr>
					<td>내용</td>
					<td>
						<textarea rows="10" name="bContent" id="ir1" rows="10" cols="100"> ${content_view.bContent}</textarea>
					<script type="text/javascript">
					var oEditors = [];
					nhn.husky.EZCreator.createInIFrame({
   					oAppRef: oEditors,
   					elPlaceHolder: "ir1",
    				sSkinURI: "./naver-editor/SmartEditor2Skin.html",
    				fCreator: "createSEditor2"
					});
</script>
					</td>
					
				</tr>
				<tr>
					<td colspan="2"> <input type="submit" value="수정"> &nbsp;&nbsp;
						<a href="content_view.do?bId=${content_view.bId}">취소</a>&nbsp;&nbsp;
						<a href="list.do?page=<%=session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
					</td>
				</tr>
			</form>
		</table>
</body>
</html>