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

<title>Convoquer un étudiant</title>
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

<!-- Date -->
<script
	src="<c:url value="/resources/js/moment/moment-with-locales.js"/>"></script>
<link
	href="<c:url value="/resources/css/datetimepicker/bootstrap-datetimepicker.min.css"/>"
	rel="stylesheet">
<script
	src="<c:url value="/resources/js/datetimepicker/bootstrap-datetimepicker.min.js"/>"></script>

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

								<h2>Convoquer un étudiant via ce formulaire</h2>
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
								<br>
								<br>
								<br>
								<form method="post" action="ConvoquerEtudiant">

									<c:choose>
										<c:when test="${empty eleves}">
											<div class="alert alert-danger alert-dismissible fade in"
												style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out
													value="Aucun étudiant n'est actuellement enregistré sur la base de donnée." />
											</div>
										</c:when>
									</c:choose>

									<c:if test="${form.resultat == 'success'}">
										<div class="alert alert-success alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="Succès de la convocation de l'étudiant" />
										</div>
									</c:if>
									<c:if test="${form.resultat == 'error'}">
										<div class="alert alert-danger alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out
												value="La convocation de l'étudiant ne s'est pas effectuée" />
										</div>
									</c:if>
									<c:if test="${form.resultat == 'errorMail'}">
										<div class="alert alert-danger alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out
												value="Échec lors de l'envoi du mail : une erreur imprévue est survenue, merci de réessayer dans quelques instants" />
										</div>
									</c:if>

									<c:if test="${form.erreurs['eleve'] != null}">
										<div class="alert alert-danger alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${form.erreurs['eleve']}" />
										</div>
									</c:if>

									<div class="form-group">
										<label>Choisir un étudiant :</label>
										<c:if test="${not empty eleves}">
											<select class="selectpicker show-tick form-control"
												data-live-search="true" name="choixEleve" id="choixEleve"
												required>
												<c:forEach var="eleve" items="${eleves}">
													<option value="<c:out value="${eleve.idEleve}"/>"><c:out
															value="${eleve.nom} ${eleve.prenom} [ID : ${eleve.idEleve}]" /></option>
												</c:forEach>
											</select>
										</c:if>
									</div>

									<c:if test="${form.erreurs['date'] != null}">
										<div class="alert alert-danger alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${form.erreurs['date']}" />
										</div>
									</c:if>

									<div class="form-group">
										<label>Saisir la date de la convocation :</label>
										<div class='input-group' id='datetimepicker1'>
											<input type='text' id="date" name="date" class="form-control" />
											<span class="input-group-addon">
												<span class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>

									<br>

									<div class="text-center">
										<button type="submit" class="btn btn-success btn-block">Convoquer
											l'étudiant</button>
									</div>
									<br>
									<br>
									<br>
									<br>
									<br>
									<br>
									<br>
									<br>
									<br>
									<br>
									<br>

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

	<!-- Date -->
	<script
		src="<c:url value="/resources/js/moment/moment-with-locales.js"/>"></script>
	<script
		src="<c:url value="/resources/js/datetimepicker/bootstrap-datetimepicker.min.js"/>"></script>

	<script type="text/javascript">
		var today = new Date();

		$(function() {
			$('#datetimepicker1').datetimepicker({
				locale : 'fr',
				minDate : 1,
				daysOfWeekDisabled : [ 0, 6 ],
				format : 'DD/MM/YYYY HH:mm'
			});
		});
	</script>

</body>

</html>