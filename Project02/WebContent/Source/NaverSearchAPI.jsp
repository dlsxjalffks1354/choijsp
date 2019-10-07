<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/Head.jsp" %>
<script src="../jquery/jquery-3.2.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#searchBtn').click(function(){
			
			$.ajax({
				url : "../Source/NaverSearchAPI",
				type : "get",
				dataType : "json",
				data : {
					searchKeyword : $('#searchKeyword').val(),
				},
				success : sucFunc,
				error : errFunc
			});
		});
	});
	
	function sucFunc(d)
	{
		//var json = d;
		
		var str = "";
		$.each(d.items, function(index, item){
			str += "<ul>";
			str += "<li>" + item.title + "</li>";
			str += "<li><a href='" + item.link + "' target='_blank' >" + item.link + "</a></li>";
			str += "<li>" + item.description + "</li>";
			str += "<li>" + item.bloggername + "</li>";
			str += "<li>" + item.postdate + "</li>";
			str += "</ul>";
		});
		
		$('#searchResult').html(str);
	}
	function errFunc(e)
	{
		alert("에러발생 : " + e.status + " : " + e.statusText);
	}
</script>
</head>

  <body>

    <%@ include file="../include/navigation.jsp" %>
    
    <!-- Page Header -->
    <header class="masthead" style="background-image: url('../Resources/img/main_bg.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>Search</h1>
              <span class="subheading">검색을 위한 페이지입니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>
    
<div class="container">
	<form class="navbar-form navbar-left" id="searchFrm">
		<table class="table table-bordered">
			<tr>
				<td>검색어</td>
				<td>
					<input type="text" class="form-control" id="searchKeyword" placeholder="검색할단어를입력하세요" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button class="btn btn-default" type="button" id="searchBtn">
				        <i class="glyphicon glyphicon-search">검색</i>
				    </button>
				</td>
			</tr>
		</table>
	</form>
	<div class="row" id="searchResult">
		여기에 정보가 노출됩니다.
	</div>
</div>	
 <hr>

    <%@ include file="../include/Footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="../Resources/vendor/jquery/jquery.min.js"></script>
    <script src="../Resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Custom scripts for this template -->
    <script src="../Resources/js/clean-blog.min.js"></script>

  </body>

</html>
