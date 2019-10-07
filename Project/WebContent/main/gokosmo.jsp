<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<%@ include file="../title/header.jsp" %>

<meta charset="UTF-8">
<title>찾아오시는 길</title>
  	
  	<style>
      
  	.site-heading,.msg,.map{
  	text-align:center;
  	}
  .fr {
  float: right;
	}
  .fl {
  float: left;
	}
	.fc
	{
	float:center;
	text-align:center;
	width:300px;
	}	
  </style>
</head>
<body>
<%@ include file="../title/navigation.jsp"  %>
<header class="masthead" style="background-image: url('../img/img2.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
         <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>Location</h1>
              <span class="subheading" >찾아오시는 길입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
<hr>
<div id="map" style="height:400px"></div>
<script>
/*
if(!navigator.geolocation)
	alert("지원하지 않음");
else // found() 콜백 함수 등록
	navigator.geolocation.getCurrentPosition(found);
*/

// 위치 파악 시 found() 호출.
// 위치 정보 들어 있는 position 객체가 매개 변수로 넘어온다.
function found(position) {
	var now = new Date(position.timestamp);
	var lat = position.coords.latitude; // 위도
	var lon = position.coords.longitude; // 경도
	var acc = position.coords.accuracy; // 정확도

	// 위도와 경도의 소수점 이하 자리가 너무 길어 유효 숫자 6자리로 짜름
	lat = lat.toPrecision(6); lon = lon.toPrecision(6);

	var text = "현재 시간 " + now.toUTCString() + "<br>";
	text += "현재 위치 (위도 " + lat + "°, 경도 " + lon + "°)<br>";
	text += "정확도 " + acc + "m<br>";

	document.getElementById("msg").innerHTML = text;
}

var map, infoWindow;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 37.479118, lng: 126.878942},
        zoom: 16
    });
    infoWindow = new google.maps.InfoWindow;

    // Try HTML5 geolocation.
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
                lat: 37.479118,
                lng: 126.878942
            };

            infoWindow.setPosition(pos);
            infoWindow.setContent('한국소프트웨어 인재개발원.');
            infoWindow.open(map);
            map.setCenter(pos);
            
            found(position);
        }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
    }
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
                          'Error: The Geolocation service failed.' :
                          'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
}

</script>
	<div>
	<ul class="direction">
	<li class="fl" >
	<strong class="sub_tit">대중교통으로 방문하시는 경우</strong>
	<ul class="bul_dot">
	<li>
	<strong>지하철</strong>
	<p>
	6번출구 나오셔서 좌측횡단보도 건너 좌측으로 10m 가시면 삼거리가 나옵니다.
	<br>
	삼거리에서 우측 방향으로 직진하셔서 사거리 대각선방향에 우리은행 건물
	<br>
	410호입니다.
	</p>
	</li>
	<li>
	<strong>버스</strong>
	<p>21, 571, 652, 금천 05[디지털3단지월드벤쳐센터] 정류장에서 하차</p>
	</li>
	</ul>
	</li>
	<li class="fr">
	<strong class="sub_tit">자가용으로 방문하시는 경우</strong>
	<ul class="bul_dot">
	<li>서부간선도로를 타고 오다가 광명교를 타고 좌회전 후 첫 사거리에서 우회전</li>
	<li>가리봉5거리에서 철산방향 수출의 다리를 넘어 첫 사거리(한진사거리)에서 우회전</li>
	<li>남부순황도로 구로IC로 나와 좌회전</li>
	</ul>
	</li>
	</ul>
	</div>

	<hr>
	<br>
	<br>
	<div>
	<li class="fc">
	<%@ include file="../title/footer.jsp" %>
	</li>

	</div>

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDBZfo_Pcq_61ZIcusersOrjTEMWyluwEc&callback=initMap" async defer></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>

</html>