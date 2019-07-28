<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Lista rejsów</title>
</head>
<body>
	<div></div>
	<div class="wrapper">
		<h1>Lista rejsów</h1>
		<div class="table">

			<div class="row header">
				<div class="cell">Lp.</div>
				<div class="cell">ID</div>
				<div class="cell">Kapitan</div>
				<div class="cell">Miejsce</div>
				<div class="cell">Data startu</div>
				<div class="cell"></div>
		</div> <c:forEach var="entry" items="${cruisesList}" varStatus="loop">

				<div class="row">
					<div class="cell">${loop.index}</div>
					<div class="cell">${entry.id}</div>
					<div class="cell">${entry.captainName}</div>
					<div class="cell">${entry.location}</div>
					<div class="cell">${entry.startDate}</div>
					<div class="cell">
						<form action="CruisesController" method="get">
							<input type="hidden" name="task" value="showCruise" />
							<input type="hidden" name="cruiseId" value="${entry.id}" />
							<input type="submit" value="Szczegóły" />
						</form>
				</div>
				</div>
			</c:forEach>

	</div>

		<form action="cruise-form.jsp">
			<input type="submit" value="Dodaj nowy rejs" />
		</form> <br>
		<form action="CruisesController" method="get">
			<input type="hidden" name="task" value="addRandomCruise" />
			<input type="submit" value="Generuj losowy rejs!" />
		</form> <br>

	</div>

</body>
</html>