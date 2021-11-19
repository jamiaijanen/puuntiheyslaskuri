<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Puun tiheyslaskuri</title>
		<script src="/js/app.js"></script>
	</head>
	<h1>Puun tiheyslaskuri</h1>
	<body>
	<form action="/balsalaskuri">
		<table border="2">
		
				<tr>
					<td></td>
					<td>Arvo</td>
				</tr>
				<tr>
					<td><label for="korkeus">Korkeus (mm)</label></td>
					<td><input type="number" name="korkeus" value="<%out.print(request.getParameter("korkeus"));%>" step="any"></td>
				</tr>
				
				<tr>
					<td><label for="pituus">Pituus (mm)</label></td>
					<td><input type="number" name="pituus" value="<%out.print(request.getParameter("pituus"));%>" step="any"></td>
				</tr>
				
				<tr>
					<td><label for="leveys">Leveys (mm)</label></td>
					<td><input type="number" name="leveys" value="<%out.print(request.getParameter("leveys"));%>" step="any"></td>
				</tr>
				
				<tr>
					<td><label for="paino">Paino (g)</label></td>
					<td><input type="number" name="paino" value="<%out.print(request.getParameter("paino"));%>" step="any"></td>
				</tr>
				
				<tr>
					<td></td>
					<td><input class="btn btn success" type ="submit" name="action" value="Laske">
					<button><a href="kirjautuminen.jsp">PRO versioon</a></button></td>
				</tr>
				
				<tr>
					<td>Tulos (kg/m3)</td>
					<td>${ tulos }</td>
				</tr>
				
				<tr>
					<td>Tulos (lb/ft3)</td>
					<td>${ toinenTulos }</td>
				</tr>
		</table>
	</form>
	</body>
</html>