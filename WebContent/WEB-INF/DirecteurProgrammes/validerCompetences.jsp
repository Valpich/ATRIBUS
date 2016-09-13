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

<title>Valider des compétences</title>
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
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel tile">
							<div class="x_title">

								<h2>Validation des compétences</h2>
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
							<c:if test="${not empty error}">
								<div class="alert alert-danger alert-dismissible fade in"
									style="text-shadow: 0 0px 0 #fff;" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<c:out
										value="Echec lors de la mise à jour de certaines compétences" />
								</div>
							</c:if>
							<c:if test="${not empty succes}">
								<div class="alert alert-success alert-dismissible fade in"
									style="text-shadow: 0 0px 0 #fff;" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<c:out value="Succès de la mise à jour des compétences" />
								</div>
							</c:if>
							<c:if test="${not empty vide}">
								<div class="alert alert-warning alert-dismissible fade in"
									style="text-shadow: 0 0px 0 #fff;" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<c:out value="Vous n'aviez coché aucune compétences" />
								</div>
							</c:if>

							<div class="x_content">
								<c:if test="${(not empty ues )||( not empty matieres)}">
									<form method="post" action="ValiderCompetences">
									
										<div class="input-group">
											<span class="input-group-addon">Recherche</span>
											<input id="filter" type="text" class="form-control">
										</div>
										
										<table class="table table-striped responsive-utilities jambo_table bulk_action">
										
											<thead>
												<tr>
													<th><input type="checkbox" id="check-all" class="flat"></th>
													<th class="column-title" style="display: table-cell;">ID</th>
													<th class="column-title" style="display: table-cell;">Compétence</th>
													<th class="column-title" style="display: table-cell;">Matiere</th>
													<th class="column-title" style="display: table-cell;">Unité d'enseignement</th>
													<th class="bulk-actions" style="display: none;">ID</th>
													<th class="bulk-actions" style="display: none;">Compétence</th>
													<th class="bulk-actions" style="display: none;">Matiere</th>
													<th class="bulk-actions" style="display: none;">Unité d'enseignement</th>
												</tr>
											</thead>

											<tbody class="recherche">
												<c:set var="compteur" value="0" scope="page" />
												<c:forEach items="${ues }" var="ue">
													<c:forEach items="${ue.competences}" var="competence">
														<c:if test="${(compteur)%2 eq 0}">
															<c:set var="pair" value="even pointer" scope="page" />
														</c:if>
														<c:if test="${(compteur)%2 ne 0}">
															<c:set var="pair" value="odd pointer" scope="page" />
														</c:if>
														<c:if test="${competence.value.valide == false}">
															<tr class="searchable ${pair}">
																<c:set var="compteur" value="${compteur+1}" scope="page" />
																<td class="a-center ">
																	<input type="checkbox" class="flat" name="table_records" value="${competence.value.uniqueId}">
																</td>
																<td><c:out value="${competence.value.id}" /></td>
																<td><c:out value="${competence.value.nom}" /></td>
																<td>Ne cible pas une matière.</td>
																<td><c:out value="${ue.nom}" /></td>
															</tr>
														</c:if>
													</c:forEach>
												</c:forEach>
												<c:forEach items="${matieres }" var="matiere">
													<c:forEach items="${matiere.competences}" var="competence">
														<c:if test="${(compteur)%2 eq 0}">
															<c:set var="pair" value="even pointer" scope="page" />
														</c:if>
														<c:if test="${(compteur)%2 ne 0}">
															<c:set var="pair" value="odd pointer" scope="page" />
														</c:if>
														<c:if test="${competence.valide == false}">
															<tr class="${pair}">
																<c:set var="compteur" value="${compteur+1}" scope="page" />
																<td class="a-center">
																	<input type="checkbox" class="flat" name="table_records" value="${competence.uniqueId}">
																</td>
																<td><c:out value="${competence.id}"/></td>
																<td><c:out value="${competence.nom}"/></td>
																<td><c:out value="${matiere.nom}" /></td>
																<td><c:out value="${matiere.ue.nom}" /></td>
															</tr>
														</c:if>
													</c:forEach>
												</c:forEach>
											</tbody>
										</table>
										
										<div class="row">
											<div class="col-md-6">
												<button type="submit" class="btn btn-success btn-block">Valider</button>								
											</div>
											<div class="col-md-6">
												<a class="btn btn-danger btn-block" type="button" href="<c:url value="ModifierAssocierCompetenceUE"/>">Revenir aux associations</a>								
											</div>											
										</div>
										
									</form>
								</c:if>
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
	
	<script>

		$(document).ready(function () {
		    (function ($) {
		        $('#filter').keyup(function () {
		            var rex = new RegExp($(this).val(), 'i');
		            $('.recherche tr').hide();
		            $('.recherche tr').filter(function () {
		                return rex.test($(this).text());
		            }).show();
		        })
		    }(jQuery));
		});
		
	</script>

</body>

</html>