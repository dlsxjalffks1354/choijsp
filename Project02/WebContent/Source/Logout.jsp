<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- BbsLogout.jsp --%>
<%
	// 로그아웃 처리
	session.invalidate();
	response.sendRedirect("../Source/Main.jsp");
%>
