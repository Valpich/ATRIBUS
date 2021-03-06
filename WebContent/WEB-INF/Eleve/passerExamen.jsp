<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Passer un examen</title>
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
	<!-- Css -->
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/animate.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/fonts/css/font-awesome.min.css"/>" rel="stylesheet">
	
	<!-- Input de type number -->
	<link href="<c:url value="/resources/css/form/bootstrap-formhelpers.css"/>" rel="stylesheet">
	<script src="<c:url value="/resources/js/form/bootstrap-formhelpers.js"/>"></script>
	
	<!-- Liste déroulante avec recherche -->
	<link href="<c:url value="/resources/css/select/select2.min.css"/>" rel="stylesheet">
	<script src="<c:url value="/resources/js/select/select2.full.js"/>"></script>
	
	<!-- Script -->
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	
</head>

<body class="nav-md">

	<div class="container body">
		<div class="main_container">

			<jsp:include page="../sidebar-gauche.jsp" />
			<jsp:include page="../topbar-navigation.jsp" />

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel tile">
							<div class="x_title">
							
								<c:if test="${not empty examen}">
									<h2>Examen : ${examen.nom}</h2>
								</c:if>
								
								<c:if test="${empty examen}">
									<h2>Passer un examen</h2>
								</c:if>
								<ul class="nav navbar-right panel_toolbox"
									style="min-width: 0px;">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>
									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix"></div>
								
							</div>
							
							<div class="x_content">
								
								<c:if test="${empty form}">
								
									<c:if test="${empty examens}">
										<c:if test="${ empty examen}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="Aucun examen n'est actuellement dans la base de données" />
											</div>
										</c:if>
									</c:if>
									
									<c:if test="${empty examen}">
									
										<form method="post" action="PasserExamen">
											<label>Merci de choisir l'examen à passer</label>
											<div class="form-group">
												<select class="form-control select2" name="choixExamen" id="choixExamen" required >
													<c:forEach var="examen" items="${ examens}">
														<option value="<c:out value="${examen.nom}"/>">
														<c:out value="${examen.nom} [${examen.matiere.nom}]" /></option>
													</c:forEach>
												</select>
											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-success btn-block">Passer cet examen</button>
											</div>
										</form>
										
									</c:if>
									
									<c:if test="${not empty examen}">
										
									<c:if test="${empty examen.exercices}">
										<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="L'examen ne comporte pas d'exercices" />
										</div>
									</c:if>
									
										<c:if test="${not empty examen.exercices}">
											<form action="ValiderExamen" method="post">
											
												<input required type='hidden' id='choixExamen' name='choixExamen' value='<c:out value="${examen.nom}" />'><br>
												<c:forEach var="exercice" items="${examen.exercices}" varStatus="loop">
													<p class="bg-success">Question N°${loop.index+1} </p>
													<label for="question${loop.index+1}">Question : </label>
													<c:out value="${exercice.question}" />
													<br />
													<label for="reponses">Reponse : </label>
													<input required class="form-control" type="text" id="reponses" name="reponses" value="<c:out value=""/>" size="200" maxlength="2000" />
													<br>
												</c:forEach>
												<button type="submit" class="btn btn-success btn-block">Valider les réponses</button>
							
											</form>
										</c:if>
									</c:if>
								</c:if>
								
								<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

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
				<jsp:include page="../footer.jsp" />
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
	<script src="<c:url value="/resources/js/custom.js"/>"></script>
	
	<!-- Init liste déroulante avec recherche -->
	<script src="<c:url value="/resources/js/select/select2.full.js"/>"></script>
	
	<script src="<c:url value="/resources/js/form/bootstrap-formhelpers.js"/>"></script>
	
	<script>
	$(document).ready(function() {
		  $(".select2").select2();
		});
	</script>

</body>

</html>