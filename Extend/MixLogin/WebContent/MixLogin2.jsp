<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>합쳐서 아이디로 로그인</title>
    <script src="http://code.jquery.com/jquery.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

	<script src="https://apis.google.com/js/api:client.js"></script>
	<script>
	var googleUser = {};
	var startApp = function() {
		gapi.load('auth2', function() {
			// Retrieve the singleton for the GoogleAuth library and set up the client.
			auth2 = gapi.auth2.init({
				client_id: '402062581041-0mm7h0ilbhk7q0fe877840tquusi4kf1.apps.googleusercontent.com',
				cookiepolicy: 'single_host_origin',
        		// Request scopes in addition to 'profile' and 'email'
        		//scope: 'additional_scope'
			});
			
			attachSignin(document.getElementById('login'));
		});
	};

	function attachSignin(element) {
		auth2.attachClickHandler(element, {},
			function(googleUser) {
				var profile = googleUser.getBasicProfile();
				$('#login').css('display', 'none');
		    	$('#logout').css('display', 'block');
		    	$('#upic').attr('src', profile.getImageUrl());
		    	$('#uname').html('[ ' +profile.getName() + ' ]');
			}, function(error) {
				alert(JSON.stringify(error, undefined, 2));
			});
	}
	function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	    	$('#login').css('display', 'block');
	    	$('#logout').css('display', 'none');
	    	$('#upic').attr('src', '');
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
	      $('#login').css('display', 'block');
	      $('#logout').css('display', 'none');
	      $('#upic').attr('src', '');
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
	      $('#login').css('display', 'none');
	      $('#logout').css('display', 'block');
	      $('#upic').attr('src', response.picture_small.data.url );
	      $('#uname').html('[ ' + response.name + ' ]');
	    });
	  }
	</script>
</head>
<body>

	<div id="login" class="btn btn-primary col-md-1"> Google </div>

    <div id="logout" style="display: none;">
    	<input type="button" class="btn btn-success col-md-1" onclick="signOut();" value="로그아웃" /><br>

    	<img id="upic" src=""><br>
    	<span id="uname"></span>
    </div>
    
    
    


	<script>startApp();</script>
	<hr>
	
	<div id="login" style="display: block;">
    <input type="button" onclick="fbLogin();" value="로그인" /><br>
	</div>

	<div id="logout" style="display: none;">
    <input type="button" onclick="fbLogout();" value="로그아웃" /><br>

    <img id="upic" src=""><br>
    <span id="uname"></span>
	</div>
	
  

    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>

<!-- <!DOCTYPE>
<html>
<head>
    <title>Insert title here</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="http://code.jquery.com/jquery.js"></script>

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
      $('#login').css('display', 'block');
      $('#logout').css('display', 'none');
      $('#upic').attr('src', '');
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
      $('#login').css('display', 'none');
      $('#logout').css('display', 'block');
      $('#upic').attr('src', response.picture_small.data.url );
      $('#uname').html('[ ' + response.name + ' ]');
    });
  }

</script>

</head>
<body>

<div id="login" style="display: block;">
    <input type="button" onclick="fbLogin();" value="로그인" /><br>
</div>

<div id="logout" style="display: none;">
    <input type="button" onclick="fbLogout();" value="로그아웃" /><br>

    <img id="upic" src=""><br>
    <span id="uname"></span>
</div>





</body>
</html>

 -->
