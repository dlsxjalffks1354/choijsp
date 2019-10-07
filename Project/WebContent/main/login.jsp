<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	if(session.getAttribute("ValidMem") != null){
	response.sendRedirect("../main/main2.member");
	} 
	else if(session.getAttribute("id") != null)
	{
		response.sendRedirect("../main/main2.member");
	}
%>

<!DOCTYPE html>
<html>
<head>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<%@ include file="../title/header.jsp" %>
<meta charset="UTF-8">
  <style>
  	.btnArea .btnNaver{display:block;margin:10px 0 0;border:0;font-size:13px;color:#fff;text-align:center;height:34px;line-height:33px;padding:0 0 0 45px;background:#26c826 url(http://img.echosting.cafe24.com/skin/mobile_ko_KR/member/ico_btn_naver.png) no-repeat 13px 0;background-size:33px 33px;-moz-box-sizing:border-box;box-sizing:border-box}

	.btnArea .btnFacebook{display:block;margin:10px 0 0;border:0;font-size:13px;color:#fff;text-align:center;height:34px;line-height:33px;padding:0 0 0 45px;background:#3b5998 url(http://img.echosting.cafe24.com/skin/mobile_ko_KR/member/ico_btn_facebook.png) no-repeat 13px 0;background-size:32px 33px;-moz-box-sizing:border-box;box-sizing:border-box}

	.btnArea .btnGoogle{display:block;margin:10px 0 0;border:0;font-size:13px;color:#fff;text-align:center;height:34px;line-height:33px;padding:0 0 0 45px;background:#df4a32 url(http://img.echosting.cafe24.com/skin/mobile_ko_KR/member/ico_btn_google.png) no-repeat 12px 1px;background-size:33px 33px;-moz-box-sizing:border-box;box-sizing:border-box}

	.btnArea .btnKakao{display:block;margin:10px 0 0;border:0;font-size:13px;color:#3c1e1e;text-align:center;height:34px;line-height:33px;padding:0 0 0 45px;background:#ffeb00 url(http://img.echosting.cafe24.com/skin/mobile/member/ico_btn_kakao.png) no-repeat 11px 1px;background-size:auto 33px;-moz-box-sizing:border-box;box-sizing:border-box}		
      
  	.site-heading{
  	text-align:center;
  	}
  	.form-Login {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: auto;
	}
  </style>
<title>로그인 페이지.</title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script src="http://code.jquery.com/jquery.js"></script>	
 <script>
function form_check() 
	{
	if($('#id').val().length == 0 )
	{
		alert("아이디는 필수 사항입니다.");
		$('#id').focus();
		return;
	}
	
	
	if($('#pw').val().length == 0)
	{
		alert("비밀번호는 필수 사항입니다.");
		$('#pw').focus();
		return;
	}
	
	submit_ajax();
	}
	
	function submit_ajax()
	{
	var queryString =$("#Login").serialize();
	$.ajax({
		url: '/Project/LogInProcess',
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
 	Kakao.init('454fd9437050c9fc610e801df8aeceac');
	function loginWithKakao() {
        // 로그인 창을 띄웁니다.
        Kakao.Auth.login({
          success: function(authObj) {
            //alert(JSON.stringify(authObj));
            signIn(authObj);
          },
          fail: function(err) {
            alert(JSON.stringify(err));
          }
        });
      };
      function signIn(authObj) {
          //console.log(authObj);
          Kakao.API.request({
              url: '/v2/user/me',
              success: function(res) {
                  console.log(res);
                  console.log(res.id);
                  var pname = res.properties.nickname;
                  var pid = res.id;
                $.ajax({
                  data: {id:pid,name:pname},
                  url: 'login.sns',
                  type: 'post',
                  dataType: 'text',
                  success: function (json) {
                     var result = JSON.parse(json);
                     if(result.code == "success"){
                        /* alert(result.desc); */
                        signOut2();
                        window.location.replace("../main/main2.member");
                     }else{
                        alert(result.desc);
                     }
                  }
               });
                  $('#login2').css('display', 'none');
                    $('#logout2').css('display', 'block');
                  $('#upic2').attr('src', res.properties.thumbnail_image );
                    $('#uname').html('[ ' + res.properties.Nickname + ' ]');
               }
           })
     }

      function signOut2() {
         Kakao.Auth.logout(function () {
            $('#login3').css('display', 'block');
            $('#logout3').css('display', 'none');
            $('#upic3').attr('src', '');
            $('#uname').html('');
         });
     }
      
      window.fbAsyncInit = function() {
          FB.init({
            appId      : '2452881354805198',
            cookie     : true,
            xfbml      : true,
            version    : 'v4.0'
          });

          FB.getLoginStatus(function(response) {
             console.log(response);
            statusChangeCallback(response);
          });
        };

        // Load the SDK asynchronously
        (function(d, s, id) {
          var js, fjs = d.getElementsByTagName(s)[0];
          if (d.getElementById(id)) return;
          js = d.createElement(s); js.id = id;
          js.src = "https://connect.facebook.net/en_US/sdk.js";
          fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));

        function statusChangeCallback(response) {
          if (response.status === 'connected') {
            getINFO();
          } else {
            $('#login3').css('display', 'block');
            $('#logout3').css('display', 'none');
            $('#upic3').attr('src', '');
            $('#uname').html('');
          }
        }
           
        function fbLogin () {
          FB.login(function(response){
            statusChangeCallback(response);
          }, {scope: 'public_profile, email'});
        }

        function fbLogout () {
          FB.logout(function(response) {
            statusChangeCallback(response);
          });
        }

        function getINFO() {
          FB.api('/me?fields=id,name,picture.width(100).height(100).as(picture_small)', function(response) {
          console.log(response);
          console.log(response.id);
         var pname = response.name;
         var pid = response.id;
         $.ajax({
           data: {id:pid,name:pname},
           url: 'login.sns',
           type: 'post',
           dataType: 'text',
           success: function (json) {
              var result = JSON.parse(json);
              if(result.code == "success"){
                 /* alert(result.desc); */
                 fbLogout();
                 window.location.replace("../main/main2.member");
              }else{
                 alert(result.desc);
              }
           }
        });
            $('#login3').css('display', 'none');
            $('#logout3').css('display', 'block');
            $('#upic3').attr('src', response.picture_small.data.url );
            $('#uname').html('[ ' + response.name + ' ]');
          });
        }
        
        function onSignIn(googleUser) {
            var profile = googleUser.getBasicProfile();
            console.log(profile);
            console.log(profile.getId());
            var pname = profile.getName();
            var pid = profile.getId();
            $.ajax({
              data: {id:pid,name:pname},
              url: 'login.sns',
              type: 'post',
              dataType: 'text',
              success: function (json) {
                 var result = JSON.parse(json);
                 if(result.code == "success"){
                    /* alert(result.desc); */
                    signOut4();
                    $('#login4').css('display', 'none');
                    $('#logout4').css('display', 'block');
                    $('#upic4').attr('src', profile.getImageUrl());
                    $('#uname').html('[ ' +profile.getName() + ' ]');
                    window.location.replace("../main/main2.member");
                 }else{            	   
              	  signOut4();
                    alert(result.desc);
                    
                 }
              }
           });
        }
        
        function onFailure(error) {
      	  
        }
        function signOut4() {
            var auth2 = gapi.auth2.getAuthInstance();
            auth2.signOut().then(function () {
               $('#login4').css('display', 'block');
               $('#logout4').css('display', 'none');
               $('#upic4').attr('src', '');
               $('#uname').html('');
            });
        }
         function renderButton() {
             gapi.signin2.render('login4', {
                'scope': 'profile email',
                'width': 'border-box',
                'height': 34,
                'longtitle': true,
                'theme': 'dark',
                'onsuccess': onSignIn,
                'onfailure': onFailure
             });
         }
         $(document).ready(function() {
            
         });
        
        
        
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
              <h1>Login</h1>
              <span class="subheading">로그인 페이지입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
	
	
	<div class="container">

  	<div class="col-lg-auto">

  <!-- 점보트론 -->

   <div class="jumbotron" style="padding-top: 20px;">

   <!-- 로그인 정보를 숨기면서 전송post -->

   <form id= "Login">

    	<h3 style="text-align: center;"> 로그인화면 </h3>

    	<div class="form-group">

     	<input type="text" class="form-control" placeholder="아이디" id="id" name="id" maxlength="20">

   		 </div>

   		 <div class="form-group">

     	<input type="password" class="form-control" placeholder="비밀번호" id="pw" name="pw" maxlength="20">

    	</div>

    <input type="button" class="btn btn-primary form-control" value="로그인" onclick="form_check();">
    
   	<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'" class="btn btn-primary form-control" value="회원가입.">
	
	<div id="naverIdLogin">
        <div class="btnArea type1">
            <a id="naverIdLogin_loginButton" href="#" role="button"class="btnNaver " onclick="MemberAction.snsLogin('naver', '%2Findex.html')"><span>네이버 로그인</span></a>
          	</div>
            		<!-- <img src="https://static.nid.naver.com/oauth/big_g.PNG" width=320></a> -->
    </div>
    
    <div id="login2" style="display: block">
          			<div class="btnArea type1">
             			<a id="custom-login-btn" href="javascript:loginWithKakao()" class="btnKakao " onclick="MemberAction.snsLogin('kakao', '%2Findex.html')"><span>카카오계정 로그인</span></a>
          			</div>
     </div>
      
      <div id="logout2" style="display: none;">
          <input type="button" class="btn btn-success" onclick="signOut2();" value="로그아웃" /><br>      
          <img id="upic2" src=""><br>
          <span id="uname"></span>
      </div>
      
      <div id="login3" style="display: block;">
     		<div class="btnArea type1">
          		<a href="#none" class="btnFacebook " onclick="fbLogin();"><span>Facebook 로그인</span></a>
      		</div>
      </div>      			
	<div id="logout3" style="display: none;">
          	<input type="button" class="btn btn-success" onclick="fbLogout();" value="로그아웃" /><br>      
          	<img id="upic3" src=""><br>
         	<span id="uname"></span>
      </div>
      
      <div id="login4"></div>
          <div id="logout4" style="display: none;">
          <input type="button" class="btn btn-success" onclick="signOut4();" value="로그아웃" /><br>      
         	<img id="upic4" src=""><br>
          	<span id="uname"></span>
          </div> 
   		<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
   </form>

  </div>

 </div>

</div>
	<%@ include file="../title/footer.jsp" %>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="naveridlogin_js_sdk_2.0.0.js"></script>
	
	  <script>
      var naverLogin = new naver.LoginWithNaverId(
         {
            clientId: "SIwAO68TQiuH0ujXBRad",
            callbackUrl: "http://localhost:8081/Project/main/login.jsp",
            isPopup: false,
         }
      );
      naverLogin.init();
      

      $("#gnbLogin").attr("href", naverLogin.generateAuthorizeUrl());

      window.addEventListener('load', function () {
         naverLogin.getLoginStatus(function (status) {
            if (status) {
               setLoginStatus();
            }
         });
      });

      function setLoginStatus() {
         console.log(naverLogin.user);
         console.log(naverLogin.user.getId());
         var pname = naverLogin.user.getName();
         var pid = naverLogin.user.getId();
          $.ajax({
            data: {id:pid,name:pname},
            url: 'login.sns',
            type: 'post',
            dataType: 'text',
            success: function (json) {
               var result = JSON.parse(json);
               if(result.code == "success"){
                  /* alert(result.desc); */
                  naverLogin.logout();
                  window.location.replace("../main/main2.member");
               }else{
                  alert(result.desc);
               }
            }
         });
         var uid = naverLogin.user.getId();
         var profileImage = naverLogin.user.getProfileImage();
         var uName = naverLogin.user.getName();
         var NickName = naverLogin.user.getNickName();
         var eMail = naverLogin.user.getEmail();
         $("#naverIdLogin_loginButton").html(
               '<br><br><img src="' + profileImage + 
               '" height=50 /> <p>' + uid + "-" + uName + '님 반갑습니다.</p>');
         $("#gnbLogin").html("Logout");
         $("#gnbLogin").attr("href", "#");
         /* (7) 로그아웃 버튼을 설정하고 동작을 정의합니다. */
         $("#gnbLogin").click(function () {
            naverLogin.logout();
            //location.reload();
            location.href="http://localhost:8081/main/main2.member";
         });
      }
   	</script>
  
  
</body>
</html>