<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Mon profil</title>

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
<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/fonts/css/font-awesome.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/animate.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/icheck/flat/green.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/maps/jquery-jvectormap-2.0.3.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/floatexamples.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/select/select2.min.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/form/bootstrap-formhelpers.css"/>"
	rel="stylesheet">

<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/nprogress.js"/>"></script>
<script
	src="<c:url value="/resources/js/form/bootstrap-formhelpers.js"/>"></script>
</head>


<body class="nav-md">
	<div class="container body">
		<div class="main_container">

			<!-- sidebar gauche -->
			<jsp:include page="sidebar-gauche.jsp" />
			<!-- Barre du haut -->
			<jsp:include page="topbar-navigation.jsp" />
			<!-- Tests sur les droits -->
			<c:forEach items="${sessionScope.sessionUtilisateur}"
				var="utilisateur">
				<c:if
					test="${utilisateur.getClass().getSimpleName() == 'Utilisateur' }">
					<c:set var="user" value="${utilisateur}" scope="request" />
				</c:if>
				<c:if
					test="${utilisateur.getClass().getSimpleName() == 'AdminSyst' }">
					<c:set var="droitAdminSyst" value="true" scope="request" />
				</c:if>
				<c:if
					test="${utilisateur.getClass().getSimpleName() == 'EnseignantRefMatiere' }">
					<c:set var="droitEnseignantRefMatiere" value="true" scope="request" />
					<c:set var="ERM" value="${utilisateur}" scope="request" />
				</c:if>
				<c:if
					test="${utilisateur.getClass().getSimpleName() == 'DirecteurEtudes' }">
					<c:set var="droitDirecteurEtudes" value="true" scope="request" />
				</c:if>
				<c:if test="${utilisateur.getClass().getSimpleName() == 'Eleve' }">
					<c:set var="droitEleve" value="true" scope="request" />
					<c:set var="eleve" value="${utilisateur}" scope="request" />
				</c:if>
				<c:if
					test="${utilisateur.getClass().getSimpleName() == 'Enseignant' }">
					<c:set var="droitEnseignant" value="true" scope="request" />
					<c:set var="enseignant" value="${utilisateur}" scope="request" />
				</c:if>
				<c:if
					test="${utilisateur.getClass().getSimpleName() == 'DirecteurProgrammes' }">
					<c:set var="droitDirecteurProgrammes" value="true" scope="request" />
				</c:if>
				<c:if
					test="${utilisateur.getClass().getSimpleName() == 'EnseignantRefUe' }">
					<c:set var="droitEnseignantRefUe" value="true" scope="request" />
					<c:set var="ERUE" value="${utilisateur}" scope="request" />
				</c:if>
			</c:forEach>
			<!-- page content -->
			<div class="right_col" role="main">


				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<h2>Mon profil</h2>
							<div class="x_title"></div>
							<c:if test="${form.erreurs['erreur']}">
	   							<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
	                  				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	                  					<span aria-hidden="true">×</span>
	                  				</button>
	                  				<c:out value="${form.erreurs['erreur']}"/>
	                			</div>
							</c:if>
							<c:if test="${form.erreurs['success']}">
	   							<div class="alert alert-info alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
	                  				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	                  					<span aria-hidden="true">×</span>
	                  				</button>
	                  				<c:out value="${form.erreurs['success']}"/>
	                			</div>
							</c:if>
							<form method="post" action="MonProfil">
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="form-group">

										<div title="Nom"
											class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
											<input type="text" class="form-control has-feedback-left"
												id="nom" name="nom" readonly="readonly" placeholder="Nom"
												value="<c:out value="${sessionUtilisateur.get(0).nom}"/>">
											<span class="fa fa-user form-control-feedback left"
												aria-hidden="true"></span>
										</div>

										<div title="Prenom"
											class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
											<input type="text" class="form-control" id="prenom"
												name="prenom" readonly="readonly" placeholder="Prénom"
												value="<c:out value="${sessionUtilisateur.get(0).prenom}"/>">
											<span class="fa fa-user form-control-feedback right"
												aria-hidden="true"></span>
										</div>

										<div title="Login"
											class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
											<input type="text" class="form-control has-feedback-left"
												id="username" name="login" readonly="readonly"
												placeholder="Login"
												value="<c:out value="${sessionUtilisateur.get(0).login}"/>"
												required>
											<span class="fa fa-lock form-control-feedback left"
												aria-hidden="true"></span>
										</div>

										<div title="Mot de passe"
											class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
											<input type="password" class="form-control" id="password"
												name="password" readonly="readonly" placeholder="Password"
												value="********">
											<span class="fa fa-key form-control-feedback right"
												aria-hidden="true"></span>
										</div>

										<div title="Email"
											class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
											<input type="text" class="form-control has-feedback-left"
												id="email" readonly="readonly" name="email"
												placeholder="Email"
												value="<c:out value="${sessionUtilisateur.get(0).email}"/>">
											<span class="fa fa-envelope form-control-feedback left"
												aria-hidden="true"></span>
										</div>
										<c:if test="${not empty droitEleve}">
											<div title="Promotion"
												class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<input type="text" class="form-control has-feedback-right"
													id="promotion" readonly="readonly" name="promotion"
													placeholder="Promotion"
													value="<c:out value="${eleve.promotion.nom}"/>">
												<span class="form-control-feedback right fa fa-users"
													aria-hidden="true"> </span>
											</div>
										</c:if>
										<c:if test="${not empty droitEnseignantRefUe}">
											<div title="Référent de l'unité d'enseignement"
												class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<input type="text" class="form-control has-feedback-right"
													id="refUe" readonly="readonly" name="refUe"
													placeholder="Ue ref" value="<c:out value="${ue.nom}"/>">
												<span
													class="fa fa-graduation-cap form-control-feedback right"
													aria-hidden="true"></span>
											</div>
										</c:if>
										<c:if test="${not empty droitEnseignantRefMatiere}">
											<div title="Référent de la maitère"
												class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<input type="text" class="form-control has-feedback-left"
													id="matiereRef" readonly="readonly" name="matiereRef"
													placeholder="Matiere ref"
													value="<c:out value="${ERM.matiere.nom}"/>">
												<span class="form-control-feedback left fa fa-book"
													aria-hidden="true"> </span>
											</div>
										</c:if>
										<c:if test="${not empty droitEnseignant}">
											<c:if test="${not empty enseignant.enseigneMatiere}">
												<div class="form-group" title="Matières enseignée(s)">
													<div class="col-md-6 col-sm-6 col-xs-12 form-group">
														<select class="select2_single form-control" tabindex="-1">
															<option></option>
															<c:forEach var="matiere"
																items="${enseignant.enseigneMatiere}">
																<c:out value="${matiere.nom}" />
																<option value="<c:out value="${matiere.nom}"/>"><c:out
																		value="${matiere.nom}" /></option>
															</c:forEach>
														</select>
													</div>
												</div>
											</c:if>
										</c:if>
										<div
											class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback">
											<label>Groupes :</label>
											<br />
											<div
												class="col-md-4 col-sm-4 col-xs-12 form-group has-feedback">
												<c:if test="${not empty droitAdminSyst}">
													<input type="checkbox" disabled="disabled" name="cbAS"
														value="cbAS" class="flat" checked="checked" />
											Administrateur
											</c:if>
												<c:if test="${empty droitAdminSyst}">
													<input type="checkbox" disabled="disabled" name="cbAS"
														value="cbAS" class="flat" />
											Administrateur
											</c:if>
												<br />
												<c:if test="${not empty droitDirecteurEtudes}">
													<input type="checkbox" disabled="disabled" name="cbDE"
														value="cbDE" class="flat" checked="checked" />
											Directeur des études
											</c:if>
												<c:if test="${empty droitDirecteurEtudes}">
													<input type="checkbox" name="cbDE" value="cbDE"
														class="flat" disabled="disabled" />
											Directeur des études
											</c:if>
												<br />
												<c:if test="${not empty droitDirecteurProgrammes}">
													<input type="checkbox" disabled="disabled" name="cbDP"
														value="cbDP" class="flat" checked="checked" />
											Directeur des programmes
											</c:if>
												<c:if test="${empty droitDirecteurProgrammes}">
													<input type="checkbox" disabled="disabled" name="cbDP"
														value="cbDP" class="flat" />
											Directeur des programmes
											</c:if>
												<br />
											</div>
											<div
												class="col-md-4 col-sm-4 col-xs-12 form-group has-feedback">
												<c:if test="${not empty droitEnseignant}">
													<input type="checkbox" disabled="disabled" name="cbEN"
														value="cbEN" class="flat" checked="checked" />
											Enseignant
											</c:if>
												<c:if test="${empty droitEnseignant}">
													<input type="checkbox" disabled="disabled" name="cbEN"
														value="cbEN" class="flat" />
											Enseignant
											</c:if>
												<br />
												<c:if test="${not empty droitEnseignantRefUe}">
													<input type="checkbox" disabled="disabled" name="cbERUE"
														value="cbERUE" class="flat" checked="checked" />
											Enseignant réf UE
											</c:if>
												<c:if test="${empty droitEnseignantRefUe}">
													<input type="checkbox" disabled="disabled" name="cbERUE"
														value="cbERUE" class="flat" />
											Enseignant réf UE
											</c:if>
												<br />
												<c:if test="${not empty droitEnseignantRefMatiere}">
													<input type="checkbox" disabled="disabled" name="cbERM"
														value="cbERM" class="flat" checked="checked" />
											Enseignant réf mat.
											</c:if>
												<c:if test="${empty droitEnseignantRefMatiere}">
													<input type="checkbox" disabled="disabled" name="cbERM"
														value="cbERM" class="flat" />
											Enseignant réf mat.
											</c:if>
												<br />
											</div>
											<div
												class="col-md-4 col-sm-4 col-xs-12 form-group has-feedback">
												<c:if test="${not empty droitEleve}">
													<input type="checkbox" disabled="disabled" name="cbEL"
														id="cbEL" value="cbEL" class="flat" checked="checked" />
											Élève
											</c:if>
												<c:if test="${empty droitEleve}">
													<input type="checkbox" disabled="disabled" name="cbEL"
														id="cbEL" value="cbEL" class="flat" />
											Élève
											</c:if>
												<br />
											</div>
										</div>
									</div>
									<div
										class="col-md-12 col-sm-12 col-xs-12 item form-group has-feedback"
										title="Actif si les mails sont desactivés" >
										<p class="text-muted">Fréquence de conversion automatique
											des notifications en email :</p>
										<c:if test="${user.frequenceNotificationMail == 0}">
											<div class="btn-group" data-toggle="buttons" >
												<label class="btn btn-default">
													<input type="radio" name="options" value="heure">
													Toutes les heures
												</label>
												<label class="btn btn-default">
													<input type="radio" name="options" value="jour">
													Tous les jours
												</label>
												<label class="btn btn-default">
													<input type="radio" name="options" value="semaine">
													Toutes les semaines
												</label>
												<label class="btn btn-default active">
													<input type="radio" name="options" value="jamais">
													Jamais
												</label>
											</div>

										</c:if>
										<c:if test="${user.frequenceNotificationMail == 1}">
											<div class="btn-group" data-toggle="buttons">
												<label class="btn btn-default active">
													<input type="radio" name="options" value="heure">
													Toutes les heures
												</label>
												<label class="btn btn-default">
													<input type="radio" name="options" value="jour">
													Tous les jours
												</label>
												<label class="btn btn-default">
													<input type="radio" name="options" value="semaine">
													Toutes les semaines
												</label>
												<label class="btn btn-default">
													<input type="radio" name="options" value="jamais">
													Jamais
												</label>
											</div>
										</c:if>
										<c:if test="${user.frequenceNotificationMail == 2}">
											<div class="btn-group" data-toggle="buttons">
												<label class="btn btn-default">
													<input type="radio" name="options" value="heure">
													Toutes les heures
												</label>
												<label class="btn btn-default active">
													<input type="radio" name="options" value="jour">
													Tous les jours
												</label>
												<label class="btn btn-default">
													<input type="radio" name="options" value="semaine">
													Toutes les semaines
												</label>
												<label class="btn btn-default">
													<input type="radio" name="options" value="jamais">
													Jamais
												</label>
											</div>

										</c:if>
										<c:if test="${user.frequenceNotificationMail == 3}">
											<div class="btn-group" data-toggle="buttons">
												<label class="btn btn-default ">
													<input type="radio" name="options" value="heure">
													Toutes les heures
												</label>
												<label class="btn btn-default">
													<input type="radio" name="options" value="jour">
													Tous les jours
												</label>
												<label class="btn btn-default active">
													<input type="radio" name="options" value="semaine">
													Toutes les semaines
												</label>
												<label class="btn btn-default">
													<input type="radio" name="options" value="jamais">
													Jamais
												</label>
											</div>
										</c:if>
									</div>
								</div>
								<div
									class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback">
									<c:if test="${user.notificationActive}">
										<div class="checkbox">
											<label>
												<input name="notificationActif" type="checkbox" class="flat" checked="checked">
												Notifications activées
											</label>
										</div>
									</c:if>
									<c:if test="${!user.notificationActive}">
										<div class="checkbox">
											<label>
												<input name="notificationActif" type="checkbox" class="flat">
												Notifications activées
											</label>
										</div>
									</c:if>
									<c:if test="${user.notificationMail}">
										<div class="checkbox">
											<label>
												<input name="mailActif" type="checkbox" class="flat" checked="checked">
												Mails activés
											</label>
										</div>
									</c:if>
									<c:if test="${!user.notificationMail}">
										<div class="checkbox">
											<label>
												<input name="mailActif" type="checkbox" class="flat">
												Mails activés
											</label>
										</div>
									</c:if>
								</div>
								<div class="text-center">
									<button type="submit" class="btn btn-success btn-lg">Modifier
										votre profil</button>
								</div>

								<ul class="nav nav-list">
									<li class="divider"></li>
								</ul>

								<div class="text-center">
									<span class="glyphicon glyphicon-home"></span>
									<a href="<c:url value="/"/>"> Revenir à l'index</a>
								</div>

							</form>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<!-- footer content -->
				<jsp:include page="footer.jsp" />
				<!-- /footer content -->
			</div>
			<!-- /page content -->
		</div>
	</div>
	<div id="custom_notifications" class="custom-notifications dsp_none">
		<ul class="list-unstyled notifications clearfix"
			data-tabbed_notifications="notif-group">
		</ul>
		<div class="clearfix"></div>
		<div id="notif-group" class="tabbed_notifications"></div>
	</div>


	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script
		src="<c:url value="/resources/js/progressbar/bootstrap-progressbar.min.js"/>"></script>
	<script src="<c:url value="/resources/js/icheck/icheck.min.js"/>"></script>
	<script src="<c:url value="/resources/js/custom.js"/>"></script>
	<!-- PNotify -->
	<script src="<c:url value="/resources/js/notify/pnotify.core.js"/>"></script>
	<script src="<c:url value="/resources/js/notify/pnotify.buttons.js"/>"></script>
	<script src="<c:url value="/resources/js/notify/pnotify.nonblock.js"/>"></script>
	<script src="<c:url value="/resources/js/select/select2.full.js"/>"></script>

	<script>
		$(document).ready(function() {
			$(".select2_single").select2({
				placeholder : "Matière(s) enseignée(s)",
				allowClear : true
			});
		});
	</script>
</body>

</html>
