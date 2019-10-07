<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>혼합 로그인하기 4</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="http://code.jquery.com/jquery.js"></script>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<meta name="google-signin-client_id" content="402062581041-0mm7h0ilbhk7q0fe877840tquusi4kf1.apps.googleusercontent.com">
	
	<!-- 구글 -->
	<script>
	
    function onSignIn(googleUser) {
    	var profile = googleUser.getBasicProfile();
		$('#my-signin2').css('display', 'none');
    	$('#logout').css('display', 'block');
    	$('#upic').attr('src', profile.getImageUrl());
    	$('#uname').html('[ ' +profile.getName() + ' ]');
    }
    function onFailure(error) {
    }
	function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	    	$('#my-signin2').css('display', 'block');
	    	$('#logout').css('display', 'none');
	    	$('#upic').attr('src', '');
	    	$('#uname').html('');
	    });
	}
    function renderButton() {
        gapi.signin2.render('my-signin2', {
	        'scope': 'profile email',
	        'width': 240,
	        'height': 50,
	        'longtitle': true,
	        'theme': 'dark',
	        'onsuccess': onSignIn,
	        'onfailure': onFailure
        });

    }
    $(document).ready(function() {
    	
    });
	</script>
	<!-- 페이스북 -->
	<script>
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
      $('#flogin').css('display', 'block');
      $('#flogout').css('display', 'none');
      $('#fupic').attr('src', '');
      $('#funame').html('');
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
      $('#flogin').css('display', 'none');
      $('#flogout').css('display', 'block');
      $('#fupic').attr('src', response.picture_small.data.url );
      $('#funame').html('[ ' + response.name + ' ]');
    });
  }

	</script>
	
	<!-- 카카오 -->
	
	<script type='text/javascript'>
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
                //console.log(res);
                console.log(res.id);
                $('#klogin').css('display', 'none');
               	$('#klogout').css('display', 'block');
                $('#kupic').attr('src', res.properties.thumbnail_image );
               	$('#kuname').html('[ ' + res.properties.nickname + ' ]');
             }
         })
	}

    function ksignOut() {
	    Kakao.Auth.logout(function () {
	    	$('#klogin').css('display', 'block');
	    	$('#klogout').css('display', 'none');
	    	$('#kupic').attr('src', '');
	    	$('#kuname').html('');
	    });
	}
    
    
</script>

</head>
<body>
    <div id="my-signin2"></div>


    <div id="logout" style="display: none;">
    <input type="button" onclick="signOut();" value="로그아웃" /><br>

    <img id="upic" src=""><br>
    <span id="uname"></span>
    </div>
  
    <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
    
    <hr>
    

	<div id="flogin" style="display: block;">
    <input type="button" onclick="fbLogin();" value="로그인" /><br>
	</div>
	
	<div id="flogout" style="display: none;">
    	<input type="button" onclick="fbLogout();" value="로그아웃" /><br>

    	<img id="fupic" src=""><br>
    	<span id="funame"></span>
	</div>
	
	<hr>
	<div id="klogin" style="display: block">
    <a id="custom-login-btn" href="javascript:loginWithKakao()">
    <img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
    </a>
	</div>

	
	<div id="klogout" style="display: none;">
    <input type="button" class="btn btn-success" onclick="ksignOut();" value="로그아웃" /><br>

    <img id="kupic" src=""><br>
   	<span id="kuname"></span>
	</div>
	
	<hr>
	
	<!-- 네이버 -->
	
		<!-- Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	 crossorigin="anonymous">
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	 crossorigin="anonymous">

	<style type="text/css">
	.header,body{padding-bottom:20px}.header,.jumbotron{border-bottom:1px solid #e5e5e5}body{padding-top:20px}.footer,.header,.marketing{padding-right:15px;padding-left:15px}.header h3{margin-top:0;margin-bottom:0;line-height:40px}.footer{padding-top:19px;color:#777;border-top:1px solid #e5e5e5}@media (min-width:768px){.container{max-width:730px}}.container-narrow>hr{margin:30px 0}.jumbotron{text-align:center}.jumbotron .btn{padding:14px 24px;font-size:21px}.marketing{margin:40px 0}.marketing p+h4{margin-top:28px}@media screen and (min-width:768px){.footer,.header,.marketing{padding-right:0;padding-left:0}.header{margin-bottom:30px}.jumbotron{border-bottom:0}}
	</style>
	
	<div class="container">
		<div class="header clearfix">
			<nav>
				<ul class="nav nav-pills pull-right">
					<li role="presentation" class="active"><a href="#">Home</a></li>
					<li role="presentation"><a id="gnbLogin" href="#">Login</a></li>
				</ul>
			</nav>
			<h3 class="text-muted">Login With NaverID Javascript SDK</h3>
		</div>

			<div id="naverIdLogin">
			<a id="naverIdLogin_loginButton" href="#" role="button"><img src="https://static.nid.naver.com/oauth/big_g.PNG" width=320></a>
			</div>

	</div>
	
	<!-- /container -->
	<script src="https://code.jquery.com/jquery-1.12.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<!-- (2) LoginWithNaverId Javscript SDK -->
	<script src="naveridlogin_js_sdk_2.0.0.js"></script>

	<!-- (3) LoginWithNaverId Javscript 설정 정보 및 초기화 -->
	<script>
		
		var naverLogin = new naver.LoginWithNaverId(
			{

				clientId: "SIwAO68TQiuH0ujXBRad",
				callbackUrl: "http://localhost:8081/MixLogin/MixLogin4.jsp",
				isPopup: false,
				loginButton: {color: "green", type: 3, height: 60}
			}
		);
		/* (4) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
		naverLogin.init();
		
		/* (4-1) 임의의 링크를 설정해줄 필요가 있는 경우 */
		$("#gnbLogin").attr("href", naverLogin.generateAuthorizeUrl());

		/* (5) 현재 로그인 상태를 확인 */
		window.addEventListener('load', function () {
			naverLogin.getLoginStatus(function (status) {
				if (status) {
					/* (6) 로그인 상태가 "true" 인 경우 로그인 버튼을 없애고
					   사용자 정보를 출력합니다. */
					setLoginStatus();
				}
			});
		});

		/* (6) 로그인 상태가 "true" 인 경우 로그인 버튼을 없애고
		   사용자 정보를 출력합니다. */
		function setLoginStatus() {
			console.log(naverLogin.user);
			var uid = naverLogin.user.getId();
			var profileImage = naverLogin.user.getProfileImage();
			var uName = naverLogin.user.getName();
			var nickName = naverLogin.user.getNickName();
			var eMail = naverLogin.user.getEmail();
			$("#naverIdLogin_loginButton").html(
					'<br><br><img src="' + profileImage + 
					'" height=50 /> <p>' + uid + "-" + uName + '님 반갑습니다.</p>');
			$("#gnbLogin").html("Logout");
			$("#gnbLogin").attr("href", "#");
			/* (7) 로그아웃 버튼을 설정하고 동작을 정의합니다. */
			$("#gnbLogin").click(function () {
				naverLogin.logout();
				location.reload();
			});
		}
	</script>
	

</body>
</html>



