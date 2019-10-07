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
		
		if(f.day.value=="")
		{
			alert("수리 날짜를 입력하세요.");
			f.day.focus();
			
			return false;
		}
		
		if(f.parts_cnt.value=="")
		{
			alert("수리한 부품 개수를 입력하세요.");
			f.parts_cnt.focus();
			
			return false;
		}
		else
		{
			var bool = numCheck(f.parts_cnt);
			if(bool!=true)
			{
				return false;
			}
		}
		
		if(f.parts_money.value=="")
		{
			alert("부품의 금액를 입력하세요.");
			f.parts_money.focus();
			
			return false;
		}
		else
		{
			var bool = numCheck(f.parts_money);
			if(bool!=true)
			{
				return false;
			}
		}
		
		if(f.fix_money.value!="")
		{
			var bool = numCheck(f.fix_money);
			if(bool!=true)
			{
				return false;
			}
		}
		
		if(f.money.value=="")
		{
			alert("총 수리금액을 입력하세요.");
			f.money.focus();
			
			return false;
		}
		else
		{
			var bool = numCheck(f.money);
			if(bool!=true)
			{
				return false;
			}
		}
		if(f.day.value=="")
		{
			alert("수리 날짜를 입력하세요.");
			f.day.focus();
			
			return false;
		}
		if(f.shop_name.value=="")
		{
			alert("정비소 이름을 입력하세요.");
			f.shop_name.focus();
			
			return false;
		}
		if(f.pay.value=="")
		{
			alert("결제방식을 입력하세요.");
			f.pay.focus();
			
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
		location.href = "<c:url value='/Source/CarFixDelete?name=${dto.parts_name}&id=${sessionScope.USER_ID }'/>";
	}
}

function numCheck(f)
{
	// 숫자만 있는지 검사
	var valid = "1234567890"
	var ok = "yes";
	var temp;
	for (var i=0; i<f.value.length; i++)
	{
		temp = "" + f.value.substring(i, i+1);
		if (valid.indexOf(temp) == "-1") ok = "no";
	}
	if (ok == "no")
	{
		alert("금액은 숫자만 입력할 수 있습니다");
		f.value='';
		f.focus();
		
		return false;
   }
	
	return true;
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
              <h1>내 차 정비내역 수정</h1>
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
         	action="<c:url value="/Source/CarFixModify?id=${sessionScope.USER_ID }"/>"
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
         			<th class="text-center" style="vertical-align: middle;">날짜</th>
         			<td>
         				<input type="text" class="form-control" placeholder="날짜" name="day" value="${dto.day }"/>
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">부품명</th>
         			<td>
         				<input type="text" class="form-control" placeholder="부품명" 
         					name="parts_name" value="${dto.parts_name }" readonly />
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">수량(개/L)</th>
         			<td>
         				<input type="text" class="form-control" placeholder="수량" name="parts_cnt" value="${dto.parts_cnt }"/>
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">부품가격</th>
         			<td>
         				 <input type="text" class="form-control" placeholder="가격" name="parts_money" value="${dto.parts_money }">
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">공임비</th>
         			<td>
         				<input type="text" class="form-control" placeholder="공임비" name="fix_money" value="${dto.fix_money }">
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">부가세</th>
         			<td>
         				<input type="text" class="form-control" placeholder="부가세" name="tax" value="${dto.tax }">
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">총금액</th>
         			<td>
         				<input type="text" class="form-control" placeholder="총금액" name="money" value="${dto.money }">
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">업체명</th>
         			<td>
         				<input type="text" class="form-control" placeholder="업체명" name="shop_name" value="${dto.shop_name }">
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">결제</th>
         			<td>
         				<input type="text" class="form-control" placeholder="결제" name="pay" value="${dto.pay }">
         			</td>
         		</tr>
         		<tr>
         			<th class="text-center" style="vertical-align: middle;">비고</th>
         			<td>
         				<input type="text" class="form-control" placeholder="비고" name="gita" value="${dto.gita }">
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
