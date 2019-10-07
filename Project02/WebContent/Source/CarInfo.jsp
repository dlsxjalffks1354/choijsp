<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

  <head>
  	<%@ include file="../include/Head.jsp" %>

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
              <h1>MyCar</h1>
              <span class="subheading">현재 소유중인 자동차의 정보 및 정비내역을 한눈에 볼 수 있습니다.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-1 col-lg-11 mx-auto">
        
         <form name="CarInfo" novalidate>
         	<h2>내 차량 정보</h2>
         	<!-- 차량정보 출력하기 위한 테이블 -->
         	<table class="table table-striped table-bordered">
         		<colgroup>
						<col width="15%" />
						<col width="30%" />
						<col width="10%" />
						<col width="15%" />
						<col width="15%" />
						<col width="15%" />
				</colgroup>
         		<thead>
         		<tr>
         			<th class="text-center">브랜드</th>
         			<th class="text-center">차량명</th>
         			<th class="text-center">차종</th>
         			<th class="text-center">연식</th>
         			<th class="text-center">주행거리</th>
         			<th class="text-center">변속기</th>
         		</tr>
         		</thead>
         		<!-- DB에서 가져오기(반복) end="0" -->
         		<tbody>
         			<c:choose>
         				<c:when test="${empty lists }">
         				<tr>
         					<td colspan="6" style="text-align: center;">등록된 차량 정보가 없습니다.</td>
         				</tr>
         				</c:when>
         				<c:otherwise>
         					<c:forEach items="${lists }" var="data" varStatus="loop">
         					<tr>
	         					<td class="text-center">${data.brand }</td>
			         			<td class="text-center">
				         			<a href="<c:url value='/Source/CarInfoModify?id=${sessionScope.USER_ID }&name=${data.car_name }'/>">
				         				${data.car_name }
				         			</a>
			         			</td>
			         			<td class="text-center">${data.car_type }</td>
			         			<td class="text-center">${data.year }</td>
			         			<td class="text-center">
			         				<fmt:formatNumber value="${data.km }" pattern="###,###,###" />km
			         			</td>
		         				<td class="text-center">${data.transmission }</td>
		         			</tr>
	         				</c:forEach>
         				</c:otherwise>
         			</c:choose>
         		</tbody>
         	</table>
            <hr/>
            
            <h2>내 차량 정비내역(2018년)</h2>
         	<!-- 정비내역 출력하기 위한 테이블 -->
         	<table class="table table-striped table-bordered">
         		<colgroup>
						<col width="5%" />
						<col width="17%" />
						<col width="10%" />
						<col width="9%" />
						<col width="9%" />
						<col width="9%" />
						<col width="9%" />
						<col width="16%" />
						<col width="6%" />
						<col width="10%" />
				</colgroup>
         		<thead>
         		<tr>
         			<th class="text-center">날짜</th>
         			<th class="text-center">부품명</th>
         			<th class="text-center">수량(개/L)</th>
         			<th class="text-center">가격</th>
         			<th class="text-center">공임비</th>
         			<th class="text-center">부가세</th>
         			<th class="text-center">총금액</th>
         			<th class="text-center">업체명</th>
         			<th class="text-center">결제</th>
         			<th class="text-center">비고</th>
         		</tr>
         		</thead>
         		<!-- DB에서 가져오기(반복) -->
         		<tbody>
         			<c:choose>
         				<c:when test="${empty listsFix }">
         				<tr>
         					<td colspan="10" style="text-align: center;">등록된 수리내역이 없습니다.</td>
         				</tr>
         				</c:when>
         				<c:otherwise>
         					<c:forEach items="${listsFix }" var="fix" varStatus="loop">
         					<tr>
	         					<td class="text-center">${fix.day }</td>
			         			<td class="text-center">
			         				<a href="<c:url value='/Source/CarFixModify?id=${sessionScope.USER_ID }&name=${fix.parts_name }'/>">
			         					${fix.parts_name }
			         				</a>
			         			</td>
			         			<td class="text-center">${fix.parts_cnt }</td>
			         			<td class="text-center">
			         				₩<fmt:formatNumber value="${fix.parts_money }" pattern="###,###,###" />
			         			</td>
			         			<td class="text-center">
			         				₩<fmt:formatNumber value="${fix.fix_money }" pattern="###,###,###" />
			         			</td>
		         				<td class="text-center">
		         					₩<fmt:formatNumber value="${fix.tax }" pattern="###,###,###" />
		         				</td>
		         				<td class="text-center">
		         					₩<fmt:formatNumber value="${fix.money }" pattern="###,###,###" />
		         				</td>
		         				<td class="text-center">${fix.shop_name }</td>
		         				<td class="text-center">${fix.pay }</td>
		         				<td class="text-center">${fix.gita }</td>
		         			</tr>
	         				</c:forEach>
         				</c:otherwise>
         			</c:choose>
         		
         		</tbody>
         	</table>
            
            <br>
            <div id="success"></div>
            <div class="form-group">
              <button type="button" class="btn btn-primary"
              	onclick="location.href='CarInfoInput.jsp'">차량정보입력</button>
              <button type="button" class="btn btn-primary"
              	onclick="location.href='CarFixInput.jsp'">정비내역입력</button>
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
