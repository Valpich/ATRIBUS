<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Correction des examens</title>
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
<script src="<c:url value="/resources/js/form/bootstrap-formhelpers.js"/>"></script>

	<!-- Liste déroulante avec recherche -->
	<link href="<c:url value="/resources/css/select/select2.min.css"/>" rel="stylesheet">

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
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel tile">
							<div class="x_title">

								<c:if test="${not empty examen.exercices}">
									<h2>Correction de l'examen : <c:out value="${examen.nom}" /></h2>
								</c:if>
								<c:if test="${empty examen.exercices}">
									<h2>Correction d'un examen</h2>
								</c:if>
								<ul class="nav navbar-right panel_toolbox" style="min-width: 0px;">
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
								<c:if test="${empty resultatModifications}">
								
										<c:if test="${empty examens}">
											<c:if test="${empty examen}">
												<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
													<c:out value = "Aucun examen n'est actuellement présent dans la base de données"/>
												</div>
											</c:if>
										</c:if>
										
										<c:if test="${empty examen}">
											<c:if test="${not empty examens}">
												<div class="form-group">
												<form method="post" action="CorrigerExamen">
													<p>Choisir un exercice à corriger :</p>
													
													<div class="form-group">
														<select class="form-control select2" name="choixExamen" id="choixExamen" required>
															<c:forEach var="examen" items="${ examens}">
																<option value="<c:out value="${examen.nom}"/>">
																	<c:out value="${examen.nom} [${examen.matiere.nom}]" />
																</option>
															</c:forEach>
														</select>
													</div>
													
													<button type="submit" class="btn btn-success btn-block">Corriger cette examen</button>
													
												</form>
												</div>
											</c:if>
										</c:if>
										<c:if test="${not empty examen}">
											<c:if test="${not empty examen.exercices}">
											<div class="form-group">
												<form action="ValiderCorrigerExamen" method="post">

													<input type='hidden' id='choixExamen' name='choixExamen' value='<c:out value="${choixExamen}" />' required>
													<c:forEach var="exercice" items="${examen.exercices}" varStatus="loop">
														<p class="bg-success">Question N°${loop.index+1}</p>
														<label class="text-success">Question : </label><c:out value=" ${exercice.question}" />
														<br/>
														<label class="text-success">Reponse de l'enseignant : </label><c:out value=" ${exercice.reponse}"/>
														<br/>
														<br>
														<c:forEach var="evaluation" items="${evaluations}">
															<c:if test="${evaluation.exercice.id == exercice.id}">
																<label>Reponse de l'eleve <c:out value="${evaluation.eleve.nom} ${evaluation.eleve.prenom}" /> : </label><c:out value=" ${evaluation.reponse}"/>
																<br>
																<label for="points">Points : </label> (Maximum : <c:out value="${exercice.nbPoints}" />)
																<br>
																<input class="form-control" type="number" id="points" name="points" value="<c:out value="${evaluation.note}" />" size="20" maxlength="30" step="0.01" min="0" max="<c:out value="${exercice.nbPoints}" />" required/>
																<br />
															
															</c:if>
														</c:forEach>
													</c:forEach>
													<br />
													<button type="submit" class="btn btn-success btn-block">Valider la correction</button>
												</form>
												</div>
											</c:if>
											<c:if test="${empty examen.exercices}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												L'examen ne comporte pas d'exercices ...
											</div>
										</c:if>
										</c:if>
						
								</c:if>
								<c:if test="${not empty resultatModifications}">
									<p class="bg-primary">Récapitulatif de la correction</p>
									<br>
									<c:forEach var="evaluation" items="${resultatModifications}">
										Reponse de l'eleve <label class="text-primary"><c:out value="${evaluation.eleve.nom} ${evaluation.eleve.prenom}" /></label> :
										<c:out value="${evaluation.reponse}" />
										<br/>
										<label for="points">Points : </label>
										<c:out value="${evaluation.note}"/>/<c:out value="${evaluation.exercice.nbPoints}" /> !
										<br/>
										<br/>
									</c:forEach>
									<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
									<form method="get" action="CorrigerExamen">
										<button type="submit" class="btn btn-primary btn-block">Corriger un autre examen</button>
									</form>
								</c:if>

									<div class="text-center" id="autoEval" style="display: none">
										<a class="btn btn-primary btn-block" href="<c:url value="/EnseignantRefMatiere/AjouterAutoEvaluation"/>">Ajouter un exercice d'auto-evaluation</a>
									</div>
									<ul class="nav nav-list">
										<li class="divider"></li>
									</ul>
									<div class="text-center">
										<span class="glyphicon glyphicon-home"></span>
										<a href="<c:url value="/"/>"> Revenir à l'index</a>
									</div>

			
							</div>
						</div>
					</div>
				</div>

				<div class="clearfix"></div>
				<!-- footer content -->
				<footer>
					<div class="pull-right">Atribus - Projet GL - Groupe n°2 - 2016</div>
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

	<!-- Init liste déroulante avec recherche -->
	<script src="<c:url value="/resources/js/select/select2.full.js"/>"></script>
	
	<script>
	$(document).ready(function() {
		  $(".select2").select2();
		});
	</script>

</body>

</html>
