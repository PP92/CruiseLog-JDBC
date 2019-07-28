<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/FormStyle.css" type="text/css">
<title>CruiseForm</title>
</head>
<body>

	<div class="form-style-5">




		<form action="CruisesController" method="get">
			<h1>Dodaj nowy rejs:</h1>
			<input type="hidden" name="task" value="addCruise" /> <br> Imię
			kapitana: <input type="text" name="captainName" required /> <br>
			Miejsce: <input type="text" name="location" required /> <br>
			Nazwa jachtu: <input type="text" name="yachtName" required /> <br>
			Przepłynięte mile <input type="number" name="distance" /> <br>
			Data rozpoczęcia: <input type="date" name="startDate"
				min="1900-01-01" max="2100-12-31" /> <br> Data zakończenia: <input
				type="date" name="endDate" min="1900-01-01" max="2100-12-31" /> <br>
			<h3>Lista załogi</h3>
			<br>

			<c:set var="crewMax" value="${11}" />
			<c:forEach var="i" begin="0" end="${crewMax}">
  <!--        Załogant  --><c:out value="${i}" />: 
         <input type="text" name="crew${i}" />
				<br>
			</c:forEach>

			<input type="submit" value="Utwórz!" />
		</form>

		<br>
		<form action="CruisesController">
			<input type="hidden" name="task" value="listCruises" /> <input
				type="submit" value="Powrót do listy" />
		</form>

	</div>
</body>
</html>