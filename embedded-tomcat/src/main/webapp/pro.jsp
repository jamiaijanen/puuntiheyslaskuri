<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Pro sivu</title>
	</head>
	<h1>Puun tiheyslaskuri PRO versio</h1>
	<body>
	<form method="post" action="prosivu">
		<table border="2">
		
				<tr>
					<td></td>
					<td>Arvo</td>
				</tr>
				<tr>
					<td><label for="korkeus">Korkeus (mm)</label></td>
					<td><input type="number" name="korkeus" step="any" placeholder= ${ korkeus }></td>
				</tr>
				
				<tr>
					<td><label for="pituus">Pituus (mm)</label></td>
					<td><input type="number" name="pituus" step="any" placeholder= ${ pituus }></td>
				</tr>
				
				<tr>
					<td><label for="leveys">Leveys (mm)</label></td>
					<td><input type="number" name="leveys" step="any" placeholder= ${ leveys }></td>
				</tr>
				
				<tr>
					<td><label for="paino">Paino (g)</label></td>
					<td><input type="number" name="paino" step="any" placeholder= ${ paino }></td>
				</tr>
				
				<tr>
					<td><label for="grain">Grain</label></td>
					<td><input type="text" name="grain" placeholder= ${ grain }></td>
				</tr>
				
				<tr>
					<td></td>
					<td><input class="btn btn success" type ="submit" name="action" value="Laske"></td>
				</tr>
				
				<tr>
					<td>Tulos (kg/m3)</td>
					<td>${ tulos }</td>
				</tr>
		</table>
	</form>
		<br>
		<br>
		<table border="1">
			<tr>
				<td>ID</td>
				<td>tiheys</td>
				<td>korkeus</td>
				<td>leveys</td>
				<td>paino</td>
				<td>pituus</td>
				<td>grain</td>
				<td>DELETE</td>
			</tr>
	      		<c:forEach items = "${ tuotteet }" var = "BalsaItem">
	      			<tr id="BalsaItem-${ BalsaItem.getId() }">
         				<td><c:out value = "${BalsaItem.getId()}"/></td>
         				<td><c:out value = "${BalsaItem.getTiheys()}"/></td>
         				<td><c:out value = "${BalsaItem.getKorkeus()}"/></td>
         				<td><c:out value = "${BalsaItem.getLeveys()}"/></td>
         				<td><c:out value = "${BalsaItem.getPaino()}"/></td>
         				<td><c:out value = "${BalsaItem.getPituus()}"/></td>
         				<td><c:out value = "${BalsaItem.getGrain()}"/></td>
         				<td><button type="button" onclick="removeProduct(${BalsaItem.getId()});">DELETE</button></td>
         			</tr>
      			</c:forEach>
      	</table>
      	<script type="text/javascript">
      	async function removeProduct(id) {
			let response = await fetch("/prosivu?id=" + id , { method: "DELETE"});

			if (response.status === 200) {
				let element = document.getElementById("BalsaItem-" + id);
				element.remove();
			} else {
				alert("Something went wrong");
			}
		}
		</script>
	</body>
</html>