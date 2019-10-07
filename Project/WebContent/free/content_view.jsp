<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.study.jsp.sign.MemberDTO" %>
<%@page import="com.study.jsp.sign.MemberDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	if(session.getAttribute("ValidMem") == null){
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
  	
  	.comment
  	{
  	text-align:center;
  	margin: auto;
  	}
  	</style>
  	
  	<script type="text/javascript">
  	
  	function writeCmt()
	{
		var form = document.getElementById("writeCommentForm");
		var cboard = form.cboard.value
		var cid = form.cid.value
		var cnickname = form.cnickname.value
		var content = form.ccontent.value;
		
		if(!content)
		{
			alert("내용을 입력하세요.");
			return false;
		}
		else
		{	
			var param="cboard="+cboard+"&cid="+cid+"&cnickname="+cnickname+"&ccontent="+content;	
			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = checkFunc;
			httpRequest.open("POST", "CommentWriteAction.co", true);	
			httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8'); 
			httpRequest.send(param);
		}
	}
  	
	function checkFunc(){
		if(httpRequest.readyState == 4){
			// 결과값을 가져온다.
			var resultText = httpRequest.responseText;
			if(resultText == 1){ 
				document.location.reload(); // 상세보기 창 새로고침
			}
		}
	}
	
	function getXMLHttpRequest(){
		var httpRequest = null;
	
		if(window.ActiveXObject){
			try{
				httpRequest = new ActiveXObject("Msxml2.XMLHTTP");	
			} catch(e) {
				try{
					httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e2) { httpRequest = null; }
			}
		}
		else if(window.XMLHttpRequest){
			httpRequest = new window.XMLHttpRequest();
		}
		return httpRequest;	
	}
	
	 function cmReplyOpen(cnum)
     {
         var userId = '${id}';
         
         if(userId == null){
             alert("로그인후 사용가능합니다.");
             return false;
         }
         else{
             // 댓글 답변창 open
             window.name = "parentForm";
             window.open("Commentreply.co?cnum="+cnum,
                         "replyForm", "width=570, height=350, resizable = no, scrollbars = no");
         }
     }
	 
	 function cmDeleteOpen(cnum){
         var msg = confirm("댓글을 삭제합니다.");    
         if(msg == true){ // 확인을 누를경우
             deleteCmt(cnum);
         }
         else{
             return false; // 삭제취소
         }
     }
	 function cmUpdateOpen(cnum){
         window.name = "parentForm";
         window.open("CommentUpdateFormAction.co?cnum="+cnum,
                     "updateForm", "width=570, height=350, resizable = no, scrollbars = no");
     }


     // 댓글 삭제
     function deleteCmt(cnum)
     {
         var param="cnum="+cnum;
         
         httpRequest = getXMLHttpRequest();
         httpRequest.onreadystatechange = checkFunc;
         httpRequest.open("POST", "CommentDeleteAction.co", true);    
         httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=EUC-KR'); 
         httpRequest.send(param);
     }



	
  	</script>
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
		
		<table width="1000" cellpadding="0" cellspacing="0"border="1" style="margin-left: auto; margin-right: auto;">
		<tr>
			<td>번호</td>
			<td> ${content_view.bId}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td> ${content_view.bHit}</td>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td> ${content_view.bName}</td>
		</tr>
		<tr>
			<td>글 제목</td>
			<td> ${content_view.bTitle}</td>
		</tr>
		
		<tr>
			<td>글 내용</td>
			<td> ${content_view.bContent}</td>
		</tr>
			<tr>
			<td>좋아요</td>
			<td> ${content_view.bLike}</td>
		</tr>
		<tr>
			<td colspan="2">
			<c:choose>
				<c:when test="${id eq content_view.bUser}">
					<a href="modify_view.bbs?bId=${content_view.bId}">수정</a>&nbsp;&nbsp;
					<a href="list.bbs?page=<%=session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
					<a href="delete.bbs?bId=${content_view.bId}">삭제</a>&nbsp;&nbsp;
				</c:when>
				<c:when test="${manager eq 'y'}">
					<a href="modify_view.bbs?bId=${content_view.bId}">수정</a>&nbsp;&nbsp;
					<a href="list.bbs?page=<%=session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
					<a href="delete.bbs?bId=${content_view.bId}">삭제</a>&nbsp;&nbsp;
				</c:when>
				<c:otherwise>
					<a href ="like.bbs?bId=${content_view.bId}">좋아요</a>
					<a href="list.bbs?page=<%=session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
					
				</c:otherwise>
			</c:choose>
			
			</td>
		</tr>
		</table>		
		
		<div id="comment" class="comment">
		<table border="1" bordercolor="lightgray" align="center">	
		
		<!-- 댓글 목록 -->	
		<c:if test="${requestScope.commentList != null}">
		<c:forEach var="comment" items="${requestScope.commentList}">
		
			<tr>
				<!-- 아이디, 작성날짜 -->
				<td width="150">
					<div>
						<c:if test="${comment.cparent > 0}">
                          &nbsp;&nbsp;&nbsp;&nbsp; <!-- 답변글일경우 아이디 앞에 공백을 준다. -->
                           <img src="../img/reply_icon.gif">
                        </c:if>
						${comment.cnickname}<br>
						<font size="2" color="lightgray">${comment.cDate}</font>
					</div>
				</td>
				
				
				<!-- 본문내용 -->
				
				<td width="550">
					<div class="text_wrapper">
						${comment.ccontent}
					</div>
				</td>
				<!-- 버튼 -->
				<td width="100">
					<div id="btn" style="text-align:center;">
						<a href="#" onclick="cmReplyOpen(${comment.cnum})">[답변]</a><br>
					<!-- 댓글 작성자만 수정, 삭제 가능하도록 -->
					  <c:if test="${comment.cid == id}">
                        <a href="#" onclick="cmUpdateOpen(${comment.cnum})">[수정]</a><br>    
                        <a href="#" onclick="cmDeleteOpen(${comment.cnum})" >[삭제]</a>
                    </c:if> 
					<c:if test="${manager != null}">
							<a href="#" onclick="cmUpdateOpen(${comment.cnum})">[수정]</a><br>	
							<a href="#" onclick="cmDeleteOpen(${comment.cnum})">[삭제]</a>
					</c:if>				


					
					</div>
				</td>
			</tr>
			
		</c:forEach>
	</c:if>
		
			<!-- 로그인 했을 경우만 댓글 작성가능 -->
			
			<c:if test="${sessionScope.id !=null}">
			<tr bgcolor="#F5F5F5">
			<form id="writeCommentForm">
				<input type="hidden" name="cboard" value="${content_view.bId}">
				<input type="hidden" name="cid" value="${sessionScope.id}">
				<input type="hidden" name="cnickname" value="${sessionScope.Nickname}">
				<!-- 아이디-->
				<td width="100">
					<div>
						${sessionScope.Nickname}
					</div>
				</td>
				<!-- 본문 작성-->
				<td width="550">
					<div>
						<textarea name="ccontent" rows="4" cols="70" ></textarea>
					</div>
				</td>
				<!-- 댓글 등록 버튼 -->
				<td width="100">
					<div id="btn" style="text-align:center;">
						<p><input type="button" class="btn btn-primary form-control" value="댓글등록" onclick="writeCmt()"></p>	
					</div>
				</td>
			</form>
			</tr>
			</c:if>
	
		</table>
	</div>
		
		
</body>
</html>