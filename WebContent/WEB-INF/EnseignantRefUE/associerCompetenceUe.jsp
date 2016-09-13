<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Association compétence Ue</title>
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
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="x_panel tile">
							<div class="x_title">
							
								<h2>Association d'une compétence et d'un niveau à un UE</h2>
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
								<br>
								<form method="post" action="AssocierCompetenceUE">

									<c:choose>
										<c:when test="${form.resultat == 'erreurDao'}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="Une erreur est survenue, cette association existe déjà."/>
											</div>
										</c:when>
										<c:when test="${form.resultat == 'success'}">
											<div class="alert alert-success alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="Succès de l'association d'une compétence et d'un niveau à un UE."/>
											</div>
										</c:when>
									</c:choose>
									
									<c:if test="${form.erreurs['nomUe'] != null}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="${form.erreurs['nomUe']}" />
											</div>
									</c:if>
									
									<c:if test="${form.erreurs['nomCompetence'] != null}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="${form.erreurs['nomCompetence']}" />
											</div>
									</c:if>
									
									<c:if test="${form.erreurs['niveau'] != null}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="${form.erreurs['niveau']}" />
											</div>
									</c:if>
									
									<div style="font-size: 14px; margin-bottom: 8px; margin-top: 12px;">
										Veuillez sélectionner l'UE : 
									</div>
									
									<c:if test="${not empty ues}">
										<select class="form-control select2" name="listeUe" id="listeUe">
											<c:forEach var="ue" items="${ues}">
												<option value="<c:out value="${ue.nom}"/>">
													<c:out value="${ue.nom}" /></option>
											</c:forEach>
										</select>
									</c:if>
									
									<c:if test="${form.erreurs['competence'] != null}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="${form.erreurs['competence']}" />
											</div>
									</c:if>
									
									<div style="font-size: 14px; margin-bottom: 8px; margin-top: 12px;">
										Veuillez sélectionner la compétence à associer : 
									</div>
									
									<c:if test="${not empty competences}">
										<select class="form-control select2" name="listeCompetence" id="listeCompetence">
											<c:forEach var="competence" items="${competences}">
												<option value="<c:out value="${competence.nom}"/>"><c:out
														value="${competence.nom}" /></option>
											</c:forEach>
										</select>
									</c:if>
									
									<c:if test="${form.erreurs['niveau'] != null}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="${form.erreurs['niveau']}" />
											</div>
									</c:if>
									
									<div style="font-size: 14px; margin-bottom: 8px; margin-top: 12px;">
										Veuillez inscrire le niveau attendu pour cette compétence :
									</div>
									
									<input class="form-control bfh-number" type="text" id="niveau" name="niveau" value="0" data-min="0" data-max="5" data-wrap="true" required> 
									<br>

									<div class="form-group row">
											
										<div class="col-md-6">
											<button type="submit" class="btn btn-success btn-block">Ajouter l'association</button>
										</div>
										</form>
										<div class="col-md-6">
											<form method="get" action="ModifierAssocierCompetenceUE">
												<button type="submit" class="btn btn-danger btn-block">Modifier une association</button>
											</form>
										</div>
										
									</div>
									
									<ul class="nav nav-list">
										<li class="divider"></li>
									</ul>
									
									<div class="text-center">
										<span class="glyphicon glyphicon-home"></span> <a
											href="<c:url value="/"/>"> Revenir à l'index</a>
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