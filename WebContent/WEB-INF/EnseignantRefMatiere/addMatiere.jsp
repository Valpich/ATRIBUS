<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Ajouter une matière</title>
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
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/fonts/css/font-awesome.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/animate.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">

	<link href="<c:url value="/resources/css/bootstrap-select.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/form/bootstrap-formhelpers.css"/>" rel="stylesheet">

	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/nprogress.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-select.js"/>"></script>
	<script src="<c:url value="/resources/js/form/bootstrap-formhelpers.js"/>"></script>
	
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
								<h2>Ajouter une matière </h2>
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
			                <div class="x_content">
			                  <br/>
			                  <form class="form-horizontal form-label-left" method="post" action="AjouterMatiere">
			                    <div class="form-group">
			                    
			                    	<c:choose>
										<c:when test="${form.resultat == 'success'}">
											<div class="alert alert-success alert-dismissible fade in"
												style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="La matière a été ajoutée avec succès" />
												</div>
											</c:when>
											<c:when test="${form.resultat == 'echec'}">
											<div class="alert alert-danger alert-dismissible fade in"
												style="text-shadow: 0 0px 0 #fff; margin-left: 34%; margin-right: 34%;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="La matière n'a pas été ajoutée" />
												</div>
											</c:when>
									</c:choose>
			         
			         					<c:if test="${not empty form.erreurs['nom']}">
			                      			<div class="col-md-offset-4" style="margin-left: 36%;">
												<span class="label label-danger"> <c:out value="${form.erreurs['nom']}"/> </span>
											</div>
										</c:if>
			                      <label class="control-label col-md-4 col-sm-4 col-xs-12" for="nom">Intitulé de la matière : </label>
			                      <div class="col-md-4 col-sm-4 col-xs-4 ">
			                      	
										
										<input class="form-control" type="text" id="nom" name="nom" value="" required/>
			                      </div>
			                    </div>
								
								<div class="form-group">
			                      <label class="control-label col-md-4 col-sm-4 col-xs-4" for="semestre">Semestre :</label>
			                      <div class="col-md-4 col-sm-4 col-xs-4">
									<div>
										<c:if test="${not empty semestres}">
											<select name="listeSemestre" id="listeSemestre" class="form-control" required>
												<c:forEach var="semestre" items="${semestres}">
													<option value="<c:out value="${semestre.numeroSemestre}"/>">
														<c:out value="${semestre.numeroSemestre}" />
													</option>
												</c:forEach>
											</select>
										</c:if>
									</div>
									<c:if test="${not empty form.erreurs['semestre']}">
										<div class="uk-text-danger" style="font-size: 12px;">
											<span class="label label-danger"><c:out value="${form.erreurs['semestre']}"></c:out></span>
										</div>
									</c:if>
			                      </div>
			                    </div>	
								
			                    <div class="form-group">
			                      <label class="control-label col-md-4 col-sm-4 col-xs-4" for="coefficient_matiere">Coefficient : </label>
			                      <div class="col-md-4 col-sm-4 col-xs-4">
									<input class="form-control" type="number" step="0.5" min="0" max="10" id="coefficient_matiere" name="coefficient_matiere" value="1.0" required/>
									<c:if test="${not empty form.erreurs['coefficient_matiere']}">
										<div class="uk-text-danger" style="font-size: 12px;">
											<span class="label label-danger"><c:out value="${form.erreurs['coefficient_matiere']}"></c:out></span>
										</div>
									</c:if>
			                      </div>
			                    </div>
								
								<div class="form-group">
			                      <label class="control-label col-md-4 col-sm-4 col-xs-4" for="semestre" for="enseignant">Enseignant <span class="requis">*</span> </label>
			                      <div class="col-md-4 col-sm-4 col-xs-4">
									<div>
										<c:if test="${not empty enseignant}">
											<select name="listeEnseignantRefMatiere" id="listeEnseignantRefMatiere"  class="form-control col-md-7 col-xs-12" required>
												<c:forEach var="enseignant" items="${enseignant}">
													<option value="<c:out value="${enseignant.nom}"/>">
														<c:out value="${enseignant.nom}" />
													</option>
												</c:forEach>
											</select>
										</c:if>
									</div>
									<c:if test="${not empty form.erreurs['enseignant']}">
										<div class="uk-text-danger" style="font-size: 12px;">
											<span class="erreur">${form.erreurs['enseignant']}</span>
										</div>
									</c:if>
			                      </div>
			                    </div>
								
								<div class="form-group">
			                      <label class="control-label col-md-4 col-sm-4 col-xs-4" for="semestre" for="ue">UE référence : </label>
			                      <div class="col-md-4 col-sm-4 col-xs-4">
									<div>
										<c:if test="${not empty ues}">
											<select name="listeUe" id="listeUe"  class="form-control" required>
												<c:forEach var="ue" items="${ues}">
													<option value="<c:out value="${ue.nom}"/>">
														<c:out value="${ue.nom}" />
													</option>
												</c:forEach>
											</select>
										</c:if>
									</div>
									<c:if test="${not empty form.erreurs['ue']}">
										<div class="uk-text-danger" style="font-size: 12px;">
											<span class="label label-danger"><c:out value="${form.erreurs['ue']}"></c:out></span>
										</div>	
									</c:if>
			                      </div>
			                    </div>
							
								<div class="form-group">
									<div class="col-md-4 col-sm-4 col-xs-4 col-md-offset-4">
										<button type="submit" class="btn btn-success btn-block">Ajouter la matière</button>
									</div>
								</div>
			                  </form>
			                  
			                  	<ul class="nav nav-list">
									<li class="divider"></li>
								</ul>

								<div class="text-center">
									<span class="glyphicon glyphicon-home"></span> 
									<a href="<c:url value="/"/>"> Revenir à l'index</a>
								</div>
			                  
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