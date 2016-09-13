<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Consultation des auto-évaluations</title>
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
									<h2>Consulter une auto-évaluation</h2>
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
								
								<c:if test="${ empty examens}">
									<c:if test="${ empty examen}">
										<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="Aucune auto evaluation n'est actuellement dans la base de données" />
										</div>
									</c:if>
								</c:if>
								<c:if test="${empty examen}">
									<c:if test="${not empty examens}">
										<form method="post" action="ConsulterAutoEvaluation">
											<p>Merci de choisir une auto evaluation à afficher</p>
											<div class="form-group">
											<select class="form-control select2" name="choixExamen" id="choixExamen">
												<c:forEach var="examen" items="${ examens}">
													<option value="<c:out value="${examen.nom}"/>">
													<c:out value="${examen.nom} [${examen.matiere.nom}]" /></option>
												</c:forEach>
											</select>
											</div>
											<div class="form-group">
											<button type="submit" class="btn btn-success btn-block">Consulter cette auto-evaluation</button>
											</div>
										</form>
									</c:if>
								</c:if>
								
								<c:if test="${not empty examen}">
									<c:if test="${not empty examen.exercices}">
										<label>Une bonne réponse de votre part est en vert, votre réponse saisie est cochée ou non.</label>
	
										<input type='hidden' id='choixExamen' name='choixExamen' value='<c:out value="${choixExamen}" />'>
										<c:forEach var="exercice" items="${ examen.exercices}" varStatus="loop">
										<p class="bg-success" >Question N°${loop.index+1}</p>
										<c:set var="reponseEleve" value="${reponsesEleve[exercice]}" />
										<label for="question${loop.index+1}">Question : </label>
										<c:out value="${exercice.question}" />
										<br />
											<c:forEach var="entry" items="${reponsesQCM}">
												<c:if test="${entry.key == exercice.question}">
													<c:forEach var="rep" items="${entry.value}" varStatus="loop">
														<c:set var="questKey" value="${exercice.question}" />
														<c:set var="contains" value="false" />
														<c:forEach var="item" items="${reponsesValides[questKey]}">
														<c:if test="${item eq (loop.index+1)}">
															<c:set var="contains" value="true" />
														</c:if>
													</c:forEach>
													<c:set var="containsEleve" value="false" />
													<c:forEach var="item" items="${reponseEleve}">
													<c:if test="${item eq (loop.index+1)}">
														<c:set var="containsEleve" value="true" />
													</c:if>
													</c:forEach>
													<c:if test="${contains == true}">
														<c:if test="${containsEleve == true}">
															<font color="green">
																<input class="flat" checked="checked" disabled="disabled" type="checkbox" id="checkboxrep" name="checkboxrep" value="<c:out value="${entry.key}" />/////<c:out value="${loop.index+1}" />">
																<label for="checkboxrep">
																	<c:out value="${rep}" />
																</label>
															</font>
														</c:if>
														<c:if test="${containsEleve == false}">
															<font color="red">
																<input class="flat" disabled="disabled" type="checkbox" id="checkboxrep" name="checkboxrep" value="<c:out value="${entry.key}" />/////<c:out value="${loop.index+1}" />">
																<label for="checkboxrep">
																	<c:out value="${rep}" />
																</label>
															</font>
														</c:if>
													</c:if>
													<c:if test="${contains == false}">
														<c:if test="${containsEleve == false}">
															<font color="green">
																<input class="flat" disabled="disabled" type="checkbox" id="checkboxrep" name="checkboxrep" value="<c:out value="${entry.key}" />/////<c:out value="${loop.index+1}" />">
																<label for="checkboxrep">
																	<c:out value="${rep}" />
																</label>
															</font>
														</c:if>
													</c:if>
													<c:if test="${contains == false}">
														<c:if test="${containsEleve == true}">
															<font color="red">
																<input class="flat" checked="checked" disabled="disabled" type="checkbox" id="checkboxrep" name="checkboxrep" value="<c:out value="${entry.key}" />/////<c:out value="${loop.index+1}" />">
																<label for="checkboxrep">
																	<c:out value="${rep}" />
																</label>
															</font>
														</c:if>
													</c:if>
													<br/>
											</c:forEach>
										</c:if>
								</c:forEach>
								<br />
							</c:forEach>
						</c:if>
						<c:if test="${empty examen.exercices}">
							<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
								<c:out value="L'examen ne comporte pas d'exercices" />
							</div>
						</c:if>
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
	
	<script>
	$(document).ready(function() {
		  $(".select2").select2();
		});
	</script>

</body>

</html>