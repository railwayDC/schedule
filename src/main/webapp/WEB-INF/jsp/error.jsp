<%@ page errorPage="true" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
	<title>Ошибка</title>
	<style>
		<%@ include file='../../css/error.css'%>	
	</style>
</head>
<body>
	<div class="wrap">
	   <div class="logo">
	   <h1>${pageContext.errorData.statusCode}</h1>
	    <p>Произошла ошибка!</p>
  	      <div class="sub">
	        <p><a href="../index.jsp">Назад</a></p>
	      </div>
        </div>
	</div>
</body>