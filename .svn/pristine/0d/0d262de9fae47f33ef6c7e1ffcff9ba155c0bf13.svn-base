<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Afficher l'historique des auto évaluations</title>
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
<link href="<c:url value="/resources/css/bootstrap-select.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/form/bootstrap-formhelpers.css"/>"
	rel="stylesheet">

<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/nprogress.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-select.js"/>"></script>
<script
	src="<c:url value="/resources/js/form/bootstrap-formhelpers.js"/>"></script>


</head>

<body class="nav-md">

	<div class="container body">
		<div class="main_container">

			<!-- sidebar gauche -->
			<jsp:include page="../sidebar-gauche.jsp" />
             <!-- Barre du haut -->
            <jsp:include page="../topbar-navigation.jsp" />
            
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="x_panel tile">
							<div class="x_title">

								<h2>Afficher l'historique des auto évaluations via ce
									formulaire</h2>
								<ul class="nav navbar-right panel_toolbox"
									style="min-width: 0px;">
									<li>
										<a class="collapse-link">
											<i class="fa fa-chevron-up"></i>
										</a>
									</li>
									<li>
										<a class="close-link">
											<i class="fa fa-close"></i>
										</a>
									</li>
								</ul>
								<div class="clearfix"></div>

							</div>

							<div class="x_content">
								<br>
								<form method="post" action="HistoriqueAutoEvaluation">

									<c:if test="${empty examens}">

										<div class="alert alert-danger alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out
												value="Aucun examen n'est actuellement enregistrée sur la base de donnée." />
										</div>
									</c:if>
									<c:if test="${not empty quantite}">
										<div class="alert alert-info alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:if test="${not empty dateDebut && not empty dateFin}">
												<c:out
													value="L'auto évaluation : ${examen.nom} à été réalisée ${quantite} fois sur la période"/> <fmt:formatDate type="date" dateStyle="short" value="${dateDebut}" /> à <fmt:formatDate type="date" dateStyle="short" value="${dateFin}" />.
											</c:if>
											<c:if test="${empty dateDebut && not empty dateFin}">
												<c:out
													value="L'auto évaluation : ${examen.nom} à été réalisée ${quantite} fois jusqu'à la date"/> <fmt:formatDate type="date" dateStyle="short" value="${dateFin}" />.
											</c:if>
											<c:if test="${not empty dateDebut && empty dateFin}">
												<c:out
													value="L'auto évaluation : ${examen.nom} à été réalisée ${quantite} fois à partir de la date"/> <fmt:formatDate type="date" dateStyle="short" value="${dateDebut}" />.
											</c:if>
											<c:if test="${empty dateDebut &&  empty dateFin}">
												<c:out
													value="L'auto évaluation : ${examen.nom} à été réalisée ${quantite} fois." />
											</c:if>
										</div>
									</c:if>
									<c:if test="${not empty examens}">
										<div class="form-group">
											<div style="font-size: 14px; margin-bottom: 12px;">
												Merci de choisir l'auto évaluation</div>
											<select class="form-control" data-live-search="true"
												name="choixExamen" id="choixExamen" required>
												<c:forEach var="examen" items="${examens}">
													<option value="<c:out value="${examen.nom}"/>">
														<c:out value="${examen.nom} [${examen.matiere.nom}]" />
													</option>
												</c:forEach>
											</select>
										</div>
									</c:if>

									<div class="form-group">
										<label for="dateDebut">Début de la période : </label>
										<input type="date" id="dateDebut" name="dateDebut">
									</div>

									<div class="form-group">
										<label for="dateDebut">Fin de la période : </label>
										<input type="date" id="dateFin" name="dateFin">
									</div>

									<div class="text-center">
										<button type="submit" class="btn btn-success btn-block ">Afficher
											l'historique</button>
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
				</div>

				<div class="clearfix"></div>
				<!-- footer content -->
				<footer>
					<div class="pull-right">Atribus - Projet GL - Groupe n°2 -
						2016</div>
					<div class="clearfix"></div>
				</footer>
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
	<script src="<c:url value="/resources/js/bootstrap-select.js"/>"></script>
</body>

</html>