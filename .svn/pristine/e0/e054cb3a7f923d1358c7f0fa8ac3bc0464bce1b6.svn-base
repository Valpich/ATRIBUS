<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Ajouter un utilisateur</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/css/form.css"/>" />
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
</head>
<body>
	<form method="post" action="adduser">
		<fieldset>
			<legend>Ajouter un utilisateur</legend>
			<p>Vous pouvez ajouter un utilisateur via ce formulaire.</p>

			<label for="login">
				Login
				<span class="requis">*</span>
			</label>
			<input type="text" id="login" name="login"
				value="<c:out value="${utilisateur.login}"/>" size="20"
				maxlength="60" />
			<span class="erreur">${form.erreurs['login']}</span>
			<br />

			<label for="password">
				Mot de passe
				<span class="requis">*</span>
			</label>
			<input type="password" id="password" name="password" value=""
				size="20" maxlength="20" />
			<span class="erreur">${form.erreurs['password']}</span>
			<br />

			<label for="nom">Nom </label>
			<input type="text" id="nom" name="nom"
				value="<c:out value="${utilisateur.nom}"/>" size="20" maxlength="20" />
			<br />

			<label for="prenom">Prenom </label>
			<input type="text" id="prenom" name="prenom"
				value="<c:out value="${utilisateur.prenom}"/>" size="20"
				maxlength="20" />
			<br />

			<label for="email">Email </label>
			<input type="text" id="email" name="email"
				value="<c:out value="${utilisateur.email}"/>" size="20"
				maxlength="50" />
			<span class="erreur">${form.erreurs['email']}</span>
			<br />

			<label for="droits">
				Groupe
				<span class="requis">*</span>
			</label>
			<input type="checkbox" name="cbAS" value="cbAS">
			Administrateur système
			<input type="checkbox" name="cbDE" value="cbDE">
			Directeur des études
			<input type="checkbox" name="cbDP" value="cbDB">
			Directeur des programmes
			<input type="checkbox" name="cbEN" value="cbEN">
			Enseignant
			<input type="checkbox" name="cbEL" value="cbEL">
			Elève
			<br />

			<label for="promotion">
				Promotion
				<span class="requis">* (si élève)</span>
			</label>

			<input type="text" id="promotion" name="promotion" maxlength="20" />
			<span class="erreur">${form.erreurs['promotion']}</span>
			<br />

			<input type="submit" value="Inscription" class="sansLabel" />
			<br />

			<p>${utilisateur}</p>

			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
		</fieldset>
	</form>
	<a href="<c:url value="/"/>">Cliquer ici pour revenir à l'index</a>

</body>
</html>