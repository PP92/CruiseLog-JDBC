<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Cruise</title>
<link rel="stylesheet" href="css/FormStyle.css" type="text/css">
</head>
<body>

	<div class="form-style-5">
		<form action="CruisesController" method="get">

			<h1>Zaktualizuj dane rejsu</h1>
		<input type="hidden" name="task" value="updateCruise" /> 
		<input	type="hidden" name="cruiseId" value="${param.cruiseId}" /> 
		<br>Imię kapitana: <input type="text" name="captainName" value="${cruiseToUpdate.captainName}" required/>
		<br>Miejsce: <input type="text" name="location" value="${cruiseToUpdate.location}" required/>
		<br> Nazwa jachtu: <input type="text" name="yachtName" value="${cruiseToUpdate.yachtName}" required/> 
		<br>Przepłynięte mile: <input type="number" name="distance" value="${cruiseToUpdate.distance}" /> 
		<br> Data rozpoczęcia: <input type="date" name="startDate" min="1900-01-01" max="2100-12-31" value="${cruiseToUpdate.startDate}" /> 
		<br>Data zakończenia: <input type="date" name="endDate" min="1900-01-01" max="2100-12-31" value="${cruiseToUpdate.endDate}" />
		<br> 
		<h3>Lista załogi</h3>

		<c:set var="crewMax" value="${11}" />
		<input type="hidden" name="crewMax" value="${crewMax}" />
		<c:forEach var="i" begin="0" end="${crewMax}">
         Załogant 
         <c:out value="${i}" />: <input type="text"
				name="crew${i}" value="${cruiseToUpdate.crew[i]}" />
			<br>
		</c:forEach>


			<input type="submit" value="Zapisz!" />
		</form>

		<br>


		<form action="CruisesController">
			<input type="hidden" name="task" value="listCruises" /> <input
				type="submit" value="Powrót do listy" />
		</form>
	</div>
</body>
</html>