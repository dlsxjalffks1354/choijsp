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
		var f = document.fixForm;
		
		if(f.day.value=="")
		{
			alert("차량 수리 날짜를 입력하세요.");
			f.day.focus();
			
			return false;
		}
		
		if(f.parts_name.value=="")
		{
			alert("차량 부품명을 입력하세요.");
			f.parts_name.focus();
			
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
		
		if(!confirm("부품명은 수정이 불가능합니다. 입력 완료하시겠습니까?"))
		{
			return false;
		}
	}
</script>
<script type="text/javascript">
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
              <h1>자동차 수리 내역 입력</h1>
              <span class="subheading">현재 소유중인 자동차의 정비내역을 입력하세요</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
        
         <form name="fixForm" method="post" action="<c:url value="/Source/CarFix?id=${sessionScope.USER_ID }" />"
         	onsubmit="return formCheck();" novalidate>
         	<div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>날짜</label>
                <input type="text" class="form-control" placeholder="날짜" name="day"/>
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>부품명</label>
                <input type="text" class="form-control" placeholder="부품명" name="parts_name"/>
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>수량</label>
                <input type="text" class="form-control" placeholder="수량" name="parts_cnt"/>
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>부품가격</label>
                <input type="text" class="form-control" placeholder="부품가격" name="parts_money">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group col-xs-12 floating-label-form-group controls">
                <label>공임비</label>
                <input type="text" class="form-control" placeholder="공임비" name="fix_money">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group col-xs-12 floating-label-form-group controls">
                <label>부가세</label>
                <input type="text" class="form-control" placeholder="부가세" name="tax">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group col-xs-12 floating-label-form-group controls">
                <label>총금액</label>
                <input type="text" class="form-control" placeholder="총금액" name="money">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group col-xs-12 floating-label-form-group controls">
                <label>업체명</label>
                <input type="text" class="form-control" placeholder="업체명" name="shop_name">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group col-xs-12 floating-label-form-group controls">
                <label>결제</label>
                <input type="text" class="form-control" placeholder="결제방식" name="pay">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group col-xs-12 floating-label-form-group controls">
                <label>비고</label>
                <input type="text" class="form-control" placeholder="비고" name="gita">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            
            <br>
            
            <div id="success"></div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary" id="sendMessageButton">Send</button>
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
