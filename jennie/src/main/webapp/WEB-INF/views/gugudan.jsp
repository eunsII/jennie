
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단</title>
<link rel="stylesheet" type="text/css" href="/whistle/css/w3.css">
<link rel="stylesheet" type="text/css" href="/whistle/css/user.css">
</head>
<body>
	<div class="w3-content mxw750 w3-center">
		<h1 class="w3-pink w3-margin-bottom w3-padding w3-card-4">구구단</h1>
<c:forEach var="dan" begin="2" end="9" varStatus="st">
		<div class="inblock w3-card-4 w3-margin w150">
			<div class="w3-col ${COLOR[st.count - 1]}">
				<h3>${dan} 단</h3>
			</div>
			<div class="w3-col w3-padding">
	<c:forEach var="gop" begin="1" end="9"> 
				<p>${dan} x ${gop} = ${dan * gop }</p>
	</c:forEach>
			</div>
		</div>
</c:forEach>
	</div>
</body>
</html>