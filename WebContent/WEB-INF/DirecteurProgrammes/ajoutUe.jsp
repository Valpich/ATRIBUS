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

<title>Ajouter une unité d'enseignement</title>
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
			<jsp:include page="/WEB-INF/sidebar-gauche.jsp" />
             <!-- Barre du haut -->
            <jsp:include page="/WEB-INF/topbar-navigation.jsp" />
            
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="x_panel tile">
							<div class="x_title">

								<h2>Ajouter une unité d'enseignement</h2>
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
							
								<form method="post" action="AjouterUE">
								
									<c:if test="${form.resultat == 'succes'}">
											<div class="alert alert-success alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="L'ajout de l'unité d'enseignement a été effectuée avec succès"/>
										</div>
									</c:if>
										
									<c:if test="${form.resultat == 'erreur'}">
										<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="Une erreur est survenue lors de l'ajout, veuillez réessayer"/>
										</div>
									</c:if>
										
									<c:if test="${form.resultat == 'erreurDao'}">
										<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="Une erreur lors de l'ajout en base de donnée est survenu"/>
										</div>
									</c:if>
									
									<c:if test="${form.erreurs['nom'] != null}">
										<div class="alert alert-danger alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${form.erreurs['nom']}" />  <br /> 
										</div>
									</c:if>
							
									<label for="nomUe">Nom de l'UE :</label>
									<div class="form-group">
										<input class="form-control" type="text" id="nomUe" name="nomUe"
											value="<c:out value="${ue.nom}"/>" size="20" maxlength="60" required/> 
									</div>	
									
									<c:if test="${form.erreurs['ects'] != null}">
										<div class="alert alert-danger alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${form.erreurs['ects']}" />  <br /> 
										</div>
									</c:if>
														
									<label for="nbCreditsEcts">Nombre de crédits ECTS attribués à l'UE :</label> <br /> 
									<div class="form-group">
										<input class="form-control" type="text" id="nbCreditsEcts" name="nbCreditsEcts"
											value="<c:out value="${ue.nbCreditsEcts}"/>" size="20" maxlength="60" required/>
									</div>
									
									<c:if test="${form.erreurs['nbHeures'] != null}">
										<div class="alert alert-danger alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${form.erreurs['nbHeures']}" />  <br /> 
										</div>
									</c:if>
									
									<label for="nbHeures">Nombre d'heures attribuées à l'UE :</label> <br /> 
									<div class="form-group">
										<input class="form-control" type="text" id="nbHeures" name="nbHeures"
											value="<c:out value="${ue.nbHeures}"/>" size="20" maxlength="20" required/>
									</div>
									
									<c:if test="${empty listeEnseignants}">
										Aucun enseignant n'est actuellement enregistré sur la base de données ... 
									</c:if>
									
									<c:if test="${form.erreurs['enseignant'] != null}">
										<div class="alert alert-danger alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${form.erreurs['enseignant']}" />  <br /> 
										</div>
									</c:if>
						
									<c:if test="${not empty listeEnseignants}">
										<label>Enseignant référent :</label><br />
										<select class="form-control" name="choixIdEnseignant" id="choixIdEnseignant" required>
											<c:forEach var="enseignant" items="${listeEnseignants}">
												<option value="<c:out value="${enseignant.id}"/>">
													<c:out value="${enseignant.nom} ${enseignant.prenom}" />
												</option>
											</c:forEach>
										</select>
									</c:if>
									<br />
									
									<c:if test="${form.erreurs['semestre'] != null}">
										<div class="alert alert-danger alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${form.erreurs['semestre']}" />  <br /> 
										</div>
									</c:if>
						
									<c:if test="${not empty listeSemestres}">
										<label>Sélectionner le semestre associé :</label>
										<select class="form-control" name="semestre" id="semestre" required>
											<c:forEach var="semestre" items="${listeSemestres}">
												<option value="<c:out value="${semestre.numeroSemestre}"/>">
													<c:out value="${semestre.numeroSemestre}" />
												</option>
											</c:forEach>
										</select>
									</c:if>
									<br />						
													
								<br>
							
								<button class="btn btn-success btn-block" type="submit">Ajouter l'unité d'enseignement</button>		
															
							</form>
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

	<script src="<c:url value="/resources/js/custom.js"/>"></script>
	
	<!-- Init liste déroulante avec recherche -->
	<script src="<c:url value="/resources/js/select/select2.full.js"/>"></script>

	<!-- PNotify -->
	<script src="<c:url value="/resources/js/notify/pnotify.core.js"/>"></script>
	<script src="<c:url value="/resources/js/notify/pnotify.buttons.js"/>"></script>
	<script src="<c:url value="/resources/js/notify/pnotify.nonblock.js"/>"></script>
	
	<script>
	$(document).ready(function() {
		  $(".select2").select2();
		});
	</script>
	
</body>
</html>