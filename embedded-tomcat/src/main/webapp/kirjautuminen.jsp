<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Kirjautuminen</title>
		<script src="/js/app.js"></script>
	</head>
	<body>
		<form action="kirjautuminen" method="post">
		Syötä salasana päästäksesi PRO-sivulle 
		<br><br>
    		<input name="salasana" type="password"/> 
    		<input type="submit" class ="btn btn success" value="Syötä salasana"/>
    	<br><br>
    	Vinkki salasanaan paljon on 11 + 11
    	</form>
	</body>