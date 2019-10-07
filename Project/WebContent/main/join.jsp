<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../title/header.jsp" %>
<meta charset="UTF-8">
<title>회원가입 페이지.</title>
 <style>
      
  	.site-heading{
  	text-align:center;
  	}
  </style>
<script src="http://code.jquery.com/jquery.js"></script>
<script>

function form_check() {
	if($('#id').val().length == 0 )
	{
		alert("아이디는 필수 사항입니다.");
		$('#id').focus();
		return;
	}
	
	if($('#id').val().length < 4)
	{
		alert("아이디는 4글자 이상이어야 합니다.");
		$('#id').focus();
		return;
	}
	
	if($('#pw').val().length == 0)
	{
		alert("비밀번호는 필수 사항입니다.");
		$('#pw').focus();
		return;
	}
	
	if($('#pw').val() != $('#pw_check').val())
	{
		alert("비밀번호가 일치하지 않습니다.");
		$('#pw').focus();
		return;
	}
	
	if($('#name').val().length == 0)
	{
		alert("이름은 필수 사항입니다.");
		$('#name').focus();
		return;
	}
	if($('#Nickname').val().length == 0)
	{
		alert("닉네임은 필수 사항입니다.");
		$('#Nickname').focus();
		return;
	}
	
	if($('#eMail').val().length == 0)
	{
		alert("메일은 필수 사항입니다.");
		$('#eMail').focus();
		return;
	}
	
	submit_ajax();
	
	}
	
function submit_ajax()
{
	var queryString =$("#reg_frm").serialize();
	$.ajax({
		url: '/Project/JoinProcess',
		type:'POST',
		data: queryString,
		dataType: 'text',
		success:function(json)
		{
			var result=JSON.parse(json);
			if(result.code=="success")
				{
				alert(result.desc)
				window.location.replace("../main/main2.member");
				}
			else 
			{
				alert(result.desc);
			}
		}
	});
}

function audio(){
	var rand = Math.random();
	var url = 'captchaAudio.capt';
	$.ajax({
		url: url,
		type: 'POST',
		dataType: 'text',
		data : 'rand=' + rand,
		async: false,
		success: function(resp){
			var uAgent = navigator.userAgent;
			var soundUrl = 'captchaAudio.capt?rand=' + rand;
			if(uAgent.indexOf('Trident') > -1 || uAgent.indexOf('MSIE') > -1){
				winPlayer(soundUrl);
			} else if (!!document.createElement('audio').canPlayType){
				try {
					new Audio(SoundUrl).play();
				} catch (e){
					winPlayer(soundUrl);
				}
			}else {
				window.open(soundUrl, '', 'width=1, height =1');
			}
		}
	});
}

function refreshBtn(type){
	var rand = Math.random();
	var url = "captchaImg.capt?rand=" + rand;
	$('#captchaImg').attr("src", url);
}

function winPlayer(objUrl){
	$('#captchaAudio').html('<vgsound src="'+objUrl+'">');
}

function chkAnswer(){
	var url = 'Answercheck.capt';
	$.ajax({
		url: url,
		type: 'POST',
		dataType: 'text',
		data : "answer=" + document.getElementById("captchaAnswer").value,
		async: false,
		success: function(data){
			    				
			if(data == 1){
				alert("회원가입을 진행해 주세요");
				document.getElementById("joinButton").disabled = false;
			}    				
			else {
				alert("캡차 문자를 다시 확인해 주십시오");
			}
		}
	});    		
}


</script>


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
              <h1>Join</h1>
              <span class="subheading">회원가입 페이지입니다..</span>
            </div>
          </div>
        </div>
      </div>
    </header>
    
    <div class="container">

  	<div class="col-lg-auto">
  	
  	<div class="jumbotron" style="padding-top: 20px;">
    
    
	<form id= "reg_frm">
	<h3 style="text-align: center;"> 사이트 회원가입 </h3>
	<div class="form-group">
		아이디 <input type="text" class="form-control" id="id" name="id" size="20"><br>
	</div>
	<div class="form-group">
		비밀번호 <input type="password" class="form-control"  id="pw"name="pw" size="20"><br>
	</div>
	<div class="form-group">
		비밀번호 확인 <input type="password" class="form-control" id="pw_check"name="pw_check" size="20"><br>
	</div>
	<div class="form-group">
		이름 <input type="text" class="form-control" id="name" name="name" size="20"><br>
	</div>
	<div class="form-group">
		닉네임 <input type="text" class="form-control" id="Nickname" name="Nickname" size="20"><br>
	</div>
	<div class="form-group">
		EMail <input type="text" class="form-control" id="eMail" name="eMail" size="50"><br>
	</div>
	<div class="form-group">
		주소 <input type="text" class="form-control" id="address" name="address" size="50"><br><p>
	</div>
	<div class = "captcha" align="center">
		<div class = "form-group">
			<img id = "captchaImg" title="캡차 이미지" src = "captchaImg.capt" alt ="캡차 이미지" />
			<div id="captchaAudio" style = "display:none;"></div>
		</div>
		<div class = "form-group">
			<a onclick = "refreshBtn()" class = "refreshBtn">
			<input type = "button" value = "새로고침" />
			</a>
			<a onclick = "audio()" class = "refreshBtn">
			<input type = "button" value = "음성듣기" />
			</a>
		</div>
		<div class = "form-group">
			<input type = "text" name = "captchaAnswer" id = "captchaAnswer" class = "from-control" placeholder = "보안문자를 입력하세요" >
			</div>
				<div>
				<a onclick = "chkAnswer()">
				<input type = "button" value = "입력" />
				</a>
				</div>					
	</div>
	
	<br>
	<br>
	<br>
	
	<div class="form-group">
		 <input type="button" id="joinButton" class="btn btn-primary form-control" value="회원가입" onclick="form_check()" disabled>
	</div>
	</form>
	 </div>
 	</div>
	</div>
	
	<%@ include file="../title/footer.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
</body>
</html>