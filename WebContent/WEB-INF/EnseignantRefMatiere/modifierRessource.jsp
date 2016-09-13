<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Modification des ressources d'une matiere</title>
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
	<!-- Script -->
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<!-- Css -->
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/animate.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/fonts/css/font-awesome.min.css"/>" rel="stylesheet">
	
	<!-- Liste déroulante avec recherche -->
	<link href="<c:url value="/resources/css/select/select2.min.css"/>" rel="stylesheet">
	<script src="<c:url value="/resources/js/select/select2.full.js"/>"></script>
	
	
</head>

<body class="nav-md">

	<div class="container body">
		<div class="main_container">

			<jsp:include page="../sidebar-gauche.jsp" />
			<jsp:include page="../topbar-navigation.jsp" />

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="x_panel tile">
							<div class="x_title">
							
								<h2>Modifier des ressources</h2>
								<ul class="nav navbar-right panel_toolbox"
									style="min-width: 0px;">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>
									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix"></div>
								
							</div>
							
							<!-- Début x_content -->
							<div class="x_content">

								<c:if test="${empty ressources}">		
									<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
										<c:out value="Aucune ressource n'est actuellement dans la base de données" />
									</div>		
								</c:if>
								
								<c:if test="${form.resultat == 'Succès de la suppression de la ressource.'}">	
									<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
										<c:out value="${form.resultat}" />
									</div>		
								</c:if>
									
								<c:if test="${form.resultat == 'Échec de la mise à jour de la ressource.'}">	
									<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
										<c:out value="${form.resultat}" />
									</div>		
								</c:if>
								
								<c:if test="${form.erreurs['choixRessource'] != null}">	
									<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
										<c:out value="${form.erreurs['choixRessource']}" />
									</div>		
								</c:if>
									
								<c:if test="${form.erreurs['nom'] != null}">	
									<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
										<c:out value="${form.erreurs['nom']}" />
									</div>		
								</c:if>
								
								<c:if test="${not empty ressources}">
										
									<label>Merci de choisir une ressource à modifier : </label>
									
									<div class="form-group">
										<select class="form-control select2" name="choixRessource" id="choixRessource" form="modifierForm"required>
											<c:forEach var="ressource" items="${ressources}">
												<option value="<c:out value="${ressource.nom}.${ressource.type}"/>">
												<c:out value="${ressource.nom}.${ressource.type} [${ressource.matiere.nom}]" /></option>
											</c:forEach>
										</select>
									</div>
										
									<form id="modifierForm" method="post" action="ModifierRessource">
								
										<label>Nouveau nom : </label>
										<div class="form-group">
											<input class="form-control" type="text" id="nomRessource" name="nomRessource" value="<c:out value=""/>" size="20" required/>
										</div>
										<button type="submit" class="btn btn-success btn-block">Valider</button>
									
									</form>		
								
									<form id="supprimerForm" method="post" action="SupprimerRessource">
										<input type='hidden' id='hiddenRessource' name='hiddenRessource' value='' required>
										<button type="submit" class="btn btn-danger btn-block">Supprimer la ressource</button>
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
							<!-- Fin x_content -->
							
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

	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/custom.js"/>"></script>
	
	<!-- Init liste déroulante avec recherche -->
	<script src="<c:url value="/resources/js/select/select2.full.js"/>"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			var x = $($('#choixRessource')).val();
			$('#hiddenRessource').val(x);
		});
	</script>

	<script type='text/javascript'>
		$(function() {
			$('#choixRessource').change(function() {
				var x = $(this).val();
				$('#hiddenRessource').val(x);
			});
		});
	</script>

	<script>
	$(document).ready(function() {
		  $(".select2").select2();
		});
	</script>

</body>

</html>