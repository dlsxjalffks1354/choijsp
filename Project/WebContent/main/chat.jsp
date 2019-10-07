<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	if(session.getAttribute("ValidMem") != null){
		String name = (String)session.getAttribute("name");
		String id = (String)session.getAttribute("id");
		String nickname = (String)session.getAttribute("Nickname");
	} else {
%>
	<jsp:forward page = "../main/login.jsp" />
<%	
	}
%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../title/header.jsp" %>
<script type="text/javascript">
</script>
<meta charset="UTF-8">
  <style>
      
  	.site-heading{
  	text-align:center;
  	}
  	.row
  	{
  	text-align:center;
  	}
  	
  	.container{
			height : 310px;
			width : 100%;
			margin : none;
			padding : none;
		}
		.chatfield {
			height : 300px;
			width : 90%;
			overflow: auto; 
			float : left;
			border : 1px solid gray;
		}
		.inputArea{
			height : 100;
			width : 80%;
			overflow: none;
			margin:auto;
			text-align : center;
		}
  	
  </style>
<title>프로젝트</title>
</head>
<body>
	<%@ include file="../title/navigation.jsp"  %>

	
	<!-- 페이지 헤더 -->
    <header class="masthead" style="background-image: url('../img/img2.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
         <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>Chat room</h1>
              <span class="subheading" >채팅방 화면입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

   <div class = "container">
		<!-- Server responses get wriiten here -->
		<div class = "chatfield" id = "messages"></div>
	</div>
	<br>
	<div class = "inputArea">
		<%= (String)session.getAttribute("Nickname") %> : <input type = "text" id ="messageinput" /> <button type = "button" onclick="send();">메시지 전송</button>
	</div>
	
	<!-- Script to utilise the WebSocket -->
	<script type="text/javascript">
		var webSocket;
		var messages = document.getElementById("messages");
		
		$(function() {
			   openSocket();			
		});
		
		function openSocket(){
			// Ensures only one connection is open at a time
			if(webSocket != undefined && webSocket.readyState != WebSocket.CLOSED){
				writeResponse("WebSocket is already opened");
				return;				
			}
			
			// Create a new instance of the websocket
			// webSocket = new WebSocket("ws://localhost:8081/ *ProjectName*/echo");
			webSocket = new WebSocket("ws://localhost:8081/Project/websocketendpoint");
			// Binds functions to the listeners for the websocket.
			webSocket.onopen = function(event){
				// For reasons I can't determine, onopen gets called twice
				// and the first time event.data is undefined.
				// Leave a commnet if you know the answer.
				if(event.date == undefined){
					return;
				}
				writeResponse(event.data);
			};
			
			webSocket.onmessage = function(event){
				writeResponse(event.data);
			};
			
			webSocket.onclose = function(event){
				writeResponse("Connection closed");
			};
		}
		
		function send() {
			var id = "<%= (String)session.getAttribute("Nickname") %>";
			var text = document.getElementById("messageinput").value;
			webSocket.send(id + " : " + text);
		}
		
		function closeSocket(){
			webSocket.close();
		}
		
		function writeResponse(text){
			var strArray = text.split('/');
			for(var i=0; i<strArray.length; i++){
				console.log('str['+i+']: ' + strArray[i]);
			}
			if(strArray[0] == "to"){
				messages.innerHTML += (strArray[1] + "<br />");
			}
			
		}
		
	</script>    
	<%@ include file="../title/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
</body>
</html>