<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="wrapper">
		<h1>Dane rejsu</h1>

		<div class="row header">
			<div class="cell">Dane rejsu</div>
			<div class="cell"></div>
		</div>
		<div class="row">
			<div class="cell">ID</div>
			<div class="cell">${param.cruiseId}</div>
		</div>
		<div class="row">
			<div class="cell">Kapitan</div>
			<div class="cell">${cruise.captainName}</div>
		</div>
		<div class="row">
			<div class="cell">Miejsce</div>
			<div class="cell">${cruise.location}</div>
		</div>
		<div class="row">
			<div class="cell">Yacht</div>
			<div class="cell">${cruise.yachtName}</div>
		</div>
		<div class="row">
			<div class="cell">Przepłynięte mile</div>
			<div class="cell">${cruise.distance}</div>
		</div>
		<div class="row">
			<div class="cell">Data startu</div>
			<div class="cell">${cruise.startDate}</div>
		</div>
		<div class="row">
			<div class="cell">Data zakończenia</div>
			<div class="cell">${cruise.endDate}</div>
		</div>


		<div class="row header">
			<div class="cell">Lista załogi</div>
			<div class="cell"></div>
		</div>

		<c:forEach var="tempCrew" items="${cruise.crew}" varStatus="loop">
			<div class="row">
				<div class="cell">Załogant : ${loop.index}</div>
				<div class="cell">${tempCrew}</div>
			</div>

		</c:forEach>
		<br>
		<form action="CruisesController" method="get">
			<input type="hidden" name="task" value="editCruise" /> <input
				type="hidden" name="cruiseId" value="${param.cruiseId}" /> <input
				type="submit" value="Edytuj" />
		</form>
		<br>
		<form action="CruisesController" method="get">
			<input type="hidden" name="task" value="deleteCruise" /> <input
				type="hidden" name="cruiseId" value="${param.cruiseId}" /> <input
				type="submit" value="Usuń" />
		</form>
		<br>
		<form action="CruisesController">
			<input type="submit" value="Powrót do listy" />
		</form>

	</div>

</body>
</html>