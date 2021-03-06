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

<title>Ajouter une compétence</title>
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
			<!-- /sidebar gauche -->


			<!-- top navigation -->
			<jsp:include page="../topbar-navigation.jsp" />
			<!-- /top navigation -->
			
			
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel tile">
						
							<div class="x_title">
								<h2>Ajouter une compétence </h2>
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

							
							<!-- Contenu du formulaire-->
							<label class="">Merci de compléter le formulaire suivant : </label>
			                <div class="x_content">
			                  <br/>
			                  <form class="form-horizontal form-label-left" method="post" action="AjouterCompetence">
			                    <div class="form-group">
			                      <label class="control-label col-md-3 col-sm-3 col-xs-12">Intitulé</label>
			                      <div class="col-md-6 col-sm-6 col-xs-9">
									<input class="form-control" type="text" id="nomCompetence" name="nomCompetence" placeholder="Nom de la compétence" required>
									<div class="uk-text-danger" style="font-size: 12px;">
										${form.erreurs['nom']}
									</div>
			                      </div>
			                    </div>
								<div class="form-group">
			                      <label class="control-label col-md-3 col-sm-3 col-xs-12">ID du parent</label>
			                      <div class="col-md-9 col-sm-9 col-xs-12">
									<input class="form-control" type="number" id="parentId" value="0" step="1" min="0" max="20" name="parentId" style="width :150px;"required>
									<div class="uk-text-danger" style="font-size: 12px;">
										${form.erreurs['parentId']}
									</div>
			                      </div>
			                    </div>					
			                    <div class="form-group">
			                      <label class="control-label col-md-3 col-sm-3 col-xs-12">Profondeur</label>
			                      <div class="col-md-9 col-sm-9 col-xs-12">
									<input class="form-control" type="number" id="profondeur" value="0" step="1" min="0" max="4" name="profondeur" style="width :150px;" required>
									<div class="uk-text-danger" style="font-size: 12px;">
										${form.erreurs['profondeur']}
									</div>
			                      </div>
			                    </div>
								<div class="form-group">
									<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
										<button type="submit" class="btn btn-primary">Annuler</button>
										<button type="submit" class="btn btn-success">Ajouter la compétence</button>
									</div>
									<c:choose>
			    						<c:when test="${form.resultat == 'echec'}">
											<span class="label label-danger"> Échec de l'ajout d'une compétence.</span>
			   							</c:when>
			   							<c:when test="${form.resultat == 'echecDao'}">
			    							<span class="label label-warning"> Échec de l'ajout d'une compétence : une erreur imprévue est survenue, merci de réessayer dans quelques instants.</span>
			   							</c:when>   
			   							<c:when test="${form.resultat == 'success'}">
			    							<span class="label label-success"> Succès dans l'ajout d'une compétence.</span>
			   							</c:when>   
									</c:choose>
								</div>
								<div class="ln_solid"></div> <!--Trait léger-->
								<div class="form-group">
									<a href="<c:url value="/"/>"> Revenir à l'index</a>
								</div>
			                  </form>
							</div>
							<!-- /Contenu du formulaire-->
				
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

	<!-- PNotify -->
	<script src="<c:url value="/resources/js/notify/pnotify.core.js"/>"></script>
	<script src="<c:url value="/resources/js/notify/pnotify.buttons.js"/>"></script>
	<script src="<c:url value="/resources/js/notify/pnotify.nonblock.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-select.js"/>"></script>

</body>

</html>