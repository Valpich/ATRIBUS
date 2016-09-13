<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">

	<title>Modification d'une unité d'enseignement</title>
	<link rel="apple-touch-icon" sizes="57x57" href="<c:url value="/resources/images/apple-icon-57x57.png"/>">
	<link rel="apple-touch-icon" sizes="60x60" href="<c:url value="/resources/images/apple-icon-60x60.png"/>">
	<link rel="apple-touch-icon" sizes="72x72" href="<c:url value="/resources/images/apple-icon-72x72.png"/>">
	<link rel="apple-touch-icon" sizes="76x76" href="<c:url value="/resources/images/apple-icon-76x76.png"/>">
	<link rel="apple-touch-icon" sizes="114x114" href="<c:url value="/resources/images/apple-icon-114x114.png"/>">
	<link rel="apple-touch-icon" sizes="120x120" href="<c:url value="/resources/images/apple-icon-120x120.png"/>">
	<link rel="apple-touch-icon" sizes="144x144" href="<c:url value="/resources/images/apple-icon-144x144.png"/>">
	<link rel="apple-touch-icon" sizes="152x152" href="<c:url value="/resources/images/apple-icon-152x152.png"/>">
	<link rel="apple-touch-icon" sizes="180x180" href="<c:url value="/resources/images/apple-icon-180x180.png"/>">
	<link rel="icon" type="image/png" sizes="192x192" href="<c:url value="/resources/images/android-icon-192x192.png"/>">
	<link rel="icon" type="image/png" sizes="32x32" href="<c:url value="/resources/images/favicon-32x32.png"/>">
	<link rel="icon" type="image/png" sizes="96x96" href="<c:url value="/resources/images/favicon-96x96.png"/>">
	<link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/resources/images/favicon-16x16.png"/>">
	<link rel="manifest" href="<c:url value="/resources/images/manifest.json"/>">
	
  	<!-- Bootstrap core CSS -->
  	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
  	<link href="<c:url value="/resources/fonts/css/font-awesome.min.css"/>" rel="stylesheet">
  	<link href="<c:url value="/resources/css/animate.min.css"/>" rel="stylesheet">
  	<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
  	<link href="<c:url value="/resources/css/icheck/flat/green.css"/>" rel="stylesheet">
	
	<!-- jquery + nprogress scripts -->
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
  	<script src="<c:url value="/resources/js/nprogress.js"/>"></script>
  	
  	<!-- Liste déroulante avec recherche -->
	<link href="<c:url value="/resources/css/select/select2.min.css"/>" rel="stylesheet">
	
	<!-- Datatable -->
	<link href="<c:url value="/resources/css/datatables/dataTables.bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/datatables/dataTables.responsive.css"/>" rel="stylesheet">
	
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
							
								<c:if test="${not empty listeUe}">
									<h2>Modifier une unité d'enseignement</h2>
								</c:if>
								<c:if test="${not empty ancienneUe}">
									<h2>Modification de l'unité d'enseignement : <c:out value="${ancienneUe.nom}"></c:out></h2>
								</c:if>
								
								<ul class="nav navbar-right panel_toolbox" style="min-width: 0px;">
									<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix"></div>
								
							</div>
							
							<div class="x_content">
					
								<c:if test="${empty listeUe && empty ancienneUe}">
								
										<c:if test="${form.erreurs['ue'] != null}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="${form.erreurs['ue']}" />
											</div>
										</c:if>
										
										<c:if test="${form.erreurs['pointEcts'] != null}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="${form.erreurs['pointEcts']}" />
											</div>
										</c:if>
										
										<c:if test="${form.erreurs['nbHeures'] != null}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="${form.erreurs['nbHeures']}" />
											</div>
										</c:if>
										
										<c:if test="${form.erreurs['semestre'] != null}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="${form.erreurs['semestre']}" />
											</div>
										</c:if>
										
										<c:if test="${form.erreurs['enseignant'] != null}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="${form.erreurs['enseignant']}" />
											</div>
										</c:if>						
									
										<c:if test="${form.resultat == 'success'}">
											<div class="alert alert-success alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="La modification de l'unité d'enseignement a été effectuée avec succès"/>
											</div>
										</c:if>
										
										<c:if test="${form.resultat == 'erreur'}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="Une erreur est survenue lors de la modification, veuillez réessayer"/>
											</div>
										</c:if>
										
										<c:if test="${form.resultat == 'erreurDao'}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="Une erreur lors de la modification en base de donnée est survenu"/>
											</div>
										</c:if>
										
									<form method="get" action="ModifierUE">
										<div class="form-group">
											<button type="submit" class="btn btn-success btn-block">Modifier une autre unité d'enseignement</button>
										</div>
									</form>
								</c:if>
								
								<c:if test="${not empty listeUe}">
									<label>Veuillez sélectionner l'unité d'enseignement à modifier :</label>
									<form method="post" action="ModifierUE">
										<div class="form-group">
											<select class="form-control select2" name="idUe" id="idUe" required>
												<c:forEach var="ue" items="${listeUe}">
													<option value="<c:out value="${ue.id}"/>">
														<c:out value="${ue.nom}" />
													</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<button type="submit" class="btn btn-success btn-block" name="choixUe">Modifier cette unité d'enseignement</button>
										</div>
									</form>
								</c:if>
								
								<c:if test="${not empty ancienneUe}">
									<form method="post" action="ModifierUE">
										
										<label>Saisir le nouveau nom :</label>
										<div class="form-group">
											<input class="form-control" type="hidden" name="idUe" value="<c:out value="${ancienneUe.id}"/>" />
											<input class="form-control" type="text" name="nomNouvelleUe" id="nomNouvelleUe" value="<c:out value="${ancienneUe.nom}"/>" required/>
										</div>
										
										<div class="form-group col-md-6 col-sm-6 col-xs-6" style="margin-left: -10px;">
											<label>Saisir le nouveau nombre de crédit ECTS :</label>
											<input class="form-control" type="number" name="ectsNouvelleUe" id="ectsNouvelleUe"  value="<c:out value="${ancienneUe.nbCreditsEcts}"/>"/>
										</div>
										<div class="form-group col-md-6 col-sm-6 col-xs-6" style="margin-left: 10px;">
											<label>Saisir le nouveau nombre d'heures :</label>
											<input class="form-control" type="number" name="nbHeuresNouvelleUe" id="nbHeuresNouvelleUe" value="<c:out value="${ancienneUe.nbHeures}"/>"required/>
										</div>
										<div class="form-group col-md-6 col-sm-6 col-xs-6" style="margin-left: -10px;">
											<label>Sélectionner le nouveau semestre associé :</label>
											<select class="form-control" name="semestreNouvelleUe" id="semestreNouvelleUe" required>
												<c:forEach var="semestre" items="${listeSemestres}">
													<option value="<c:out value="${semestre.numeroSemestre}"/>">
														<c:out value="${semestre.numeroSemestre}" />
													</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group col-md-6 col-sm-6 col-xs-6" style="margin-left: 10px;">
											<label>Sélectionner le nouvelle enseignant associé :</label>
											<input class="form-control" type="hidden" name="nomAncienneEnseignant" value="${ancienneUe.enseignantRefUe.idEnseignantRefUe}" />
											<select class="form-control" name="enseignantNouvelleUe" id="enseignantNouvelleUe" required>
												<c:forEach var="enseignant" items="${listeEnseignants}">
													<option value="<c:out value="${enseignant.id}"/>">
														<c:out value="${enseignant.prenom} ${enseignant.nom}" />
													</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">	
											<br>
											<br>
											<button type="submit" class="btn btn-success btn-block" name="validerModifierUe">Valider la modification de cette unité d'enseignement</button>
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
	<script src="<c:url value="/resources/js/icheck/icheck.min.js"/>"></script>
	<script src="<c:url value="/resources/js/custom.js"/>"></script>
	
	<!-- Init liste déroulante avec recherche -->
	<script src="<c:url value="/resources/js/select/select2.full.js"/>"></script>
	
	<!-- Datatable -->
	<script src="<c:url value="/resources/js/datatables/jquery.dataTables.min.js"/>"></script>
    <script src="<c:url value="/resources/js/datatables/dataTables.bootstrap.min.js"/>"></script>
	
	<script>
	$(document).ready(function() {
		  $(".select2").select2();
		});
	</script>

</body>

</html>