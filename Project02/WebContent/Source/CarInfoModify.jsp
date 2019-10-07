<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/Head.jsp" %>
	<script type="text/javascript">
	function formCheck()
	{
		var f = document.car_info;
		
		if(f.brand.value=="")
		{
			alert("차량 브랜드 명을 입력하세요.");
			f.brand.focus();
			
			return false;
		}
		
		if(f.car_name.value=="")
		{
			alert("차량 이름과 등급을 입력하세요.");
			f.car_name.focus();
			
			return false;
		}
		
		if(f.car_type.value=="")
		{
			alert("차량 종류(크기)을 입력하세요.");
			f.car_type.focus();
			
			return false;
		}
		
		if(f.year.value=="")
		{
			alert("차량 연식을 입력하세요.");
			f.year.focus();
			
			return false;
		}
		
		if(f.transmission.value=="")
		{
			alert("차량 변속기를 입력하세요.");
			f.transmission.focus();
			
			return false;
		}
		
		if(f.km.value=="")
		{
			alert("차량 주행거리를 입력하세요.");
			f.km.focus();
			
			return false;
		}
		
		// 주행거리에 숫자만 있는지 검사
		var valid = "1234567890"
		var ok = "yes";
		var temp;
		for (var i=0; i<f.km.value.length; i++)
		{
			temp = "" + f.km.value.substring(i, i+1);
			if (valid.indexOf(temp) == "-1") ok = "no";
		}
		if (ok == "no")
		{
			alert("자동차 주행거리는 숫자만 입력할 수 있습니다");
			f.km.value='';
			f.km.focus();
			
			return false;
	   }
		
		if(f.name.value=="")
		{
			alert("차량 차량 소유주 및 관리자 이름을 입력하세요.");
			f.name.focus();
			
			return false;
		}
		
		if(!confirm("수정 완료 하시겠습니까??"))
		{
			return false;
		}
	}
</script>
<script type="text/javascript">
function deleteCheck()
{
	if(!confirm("삭제하면 복구가 불가능합니다. 그래도 삭제하시겠습니까??"))
	{
		return false;
	}
	else
	{
		location.href = "<c:url value='/Source/CarInfoDelete?name=${dto.car_name }&id=${sessionScope.USER_ID }'/>";
	}
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
              <h1>내 차 정보 수정</h1>
              <span class="subheading">현재 소유중인 자동차의 정보를 수정하세요.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">

         <form name="car_info" method="post" 
         	action="<c:url value="/Source/CarInfoModify?id=${sessionScope.USER_ID }"/>"
         	onsubmit="return formCheck();" novalidate>
         	<table class="table table-striped table-bordered">
         		<colgroup>
						<col width="20%" />
						<col width="80%" />
				</colgroup>
         	<c:choose>
         		<c:when test="${empty dto }">
         			<div class="control-group">
		              <div class="form-group floating-label-form-group controls">
		               <h2>데이터가 없습니다.</h2>
		              </div>
		            </div>
         		</c:when>
         		<c:otherwise>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">브랜드</th>
         			<td>
         				<input type="text" class="form-control" placeholder="브랜드" 
         					name="brand" value="${dto.brand }" readonly/>
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">차량명</th>
         			<td>
         				<input type="text" class="form-control" placeholder="차량명" 
         					name="car_name" value="${dto.car_name }" readonly />
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">차종</th>
         			<td>
         				<input type="text" class="form-control" placeholder="차종" name="car_type" value="${dto.car_type }"/>
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">연식</th>
         			<td>
         				 <input type="text" class="form-control" placeholder="연식" name="year" value="${dto.year }">
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">주행거리</th>
         			<td>
         				<input type="text" class="form-control" placeholder="주행거리" name="km" value="${dto.km }">
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">변속기</th>
         			<td>
         				<input type="text" class="form-control" placeholder="변속기" name="transmission" value="${dto.transmission }">
         			</td>
         		</tr>
         		</c:otherwise>
         	</c:choose>
         	
         	</table>
            <br>

            <div id="success"></div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary">Modify</button>
              <button type="button" class="btn btn-primary"
              	onclick="javascript:deleteCheck();">
              		Delete
              </button>
              <button type="button" class="btn btn-primary" 
              	onclick="location.href='<c:url value="/Source/CarInfo?id=${sessionScope.USER_ID }"/>'">
              		Back
              </button>
            </div>
          </form>

        </div>
      </div>
    </div>

    <hr>

    <!-- Footer -->
    <%@ include file="../include/Footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="../Resources/vendor/jquery/jquery.min.js"></script>
    <script src="../Resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Custom scripts for this template -->
    <script src="../Resources/js/clean-blog.min.js"></script>

  </body>

</html>
