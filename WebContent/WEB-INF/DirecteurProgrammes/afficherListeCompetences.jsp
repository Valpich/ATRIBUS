<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="apple-touch-icon" sizes="57x57"
	href="<c:url value="/resources/images/apple-icon-57x57.png"/>">
<link rel="apple-touch-icon" sizes="60x60"
	href="<c:url value="/resources/images/apple-icon-60x60.png"/>">
<link rel="apple-touch-icon" sizes="72x72"
	href="<c:url value="/resources/images/apple-icon-72x72.png"/>">
<link rel="apple-touch-icon" sizes="76x76"
	href="<c:url value="/resources/images/apple-icon-76x76.png"/>">
<link rel="apple-touch-icon" sizes="114x114"
	href="<c:url value="/resources/images/apple-icon-114x114.png"/>">
<link rel="apple-touch-icon" sizes="120x120"
	href="<c:url value="/resources/images/apple-icon-120x120.png"/>">
<link rel="apple-touch-icon" sizes="144x144"
	href="<c:url value="/resources/images/apple-icon-144x144.png"/>">
<link rel="apple-touch-icon" sizes="152x152"
	href="<c:url value="/resources/images/apple-icon-152x152.png"/>">
<link rel="apple-touch-icon" sizes="180x180"
	href="<c:url value="/resources/images/apple-icon-180x180.png"/>">
<link rel="icon" type="image/png" sizes="192x192"
	href="<c:url value="/resources/images/android-icon-192x192.png"/>">
<link rel="icon" type="image/png" sizes="32x32"
	href="<c:url value="/resources/images/favicon-32x32.png"/>">
<link rel="icon" type="image/png" sizes="96x96"
	href="<c:url value="/resources/images/favicon-96x96.png"/>">
<link rel="icon" type="image/png" sizes="16x16"
	href="<c:url value="/resources/images/favicon-16x16.png"/>">
<link rel="manifest"
	href="<c:url value="/resources/images/manifest.json"/>">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
<title>Liste des competences</title>

</head>
<body>
	<h1>Liste des competences</h1>


	<c:if test="${not empty referentiel}">
		<!-- Ceci ne récupère rien ! -->

		<p>Liste reférentiel</p>
		<c:forEach items="${referentiel}" var="competence">
			<form name="modifierCompetence" method="post"
			action=" ModifierCompetence">
			
					<input name ="competenceProfondeur" value ="<c:out value = "${competence.profondeur }"/>"/>
				
					<input name ="competenceNom" value ="<c:out value = "${competence.nom }"/>"/>
				
					<input type = "hidden" name = "competenceId" value = "<c:out value = "${competence.id }"/>"/>
							
					<input type="hidden" name = "nomForm" value = "formEtat1"/>
					<input type="submit" value="Modifier">
			</form>
		</c:forEach>
		<br />
		<br />
	</c:if>
	<div class="text-center">
		<span class="glyphicon glyphicon-home"></span> <a
			href="<c:url value="/"/>"> Revenir à l'index</a>
	</div>
</body>
</html>