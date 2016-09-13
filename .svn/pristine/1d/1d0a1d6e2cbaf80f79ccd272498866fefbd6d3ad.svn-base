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
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

<title>Modification d'un exercice</title>
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

<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/fonts/css/font-awesome.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/animate.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/icheck/flat/green.css"/>"
	rel="stylesheet">

<!-- jquery + nprogress scripts -->
<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/nprogress.js"/>"></script>

<!-- Liste déroulante avec recherche -->
<link href="<c:url value="/resources/css/select/select2.min.css"/>"
	rel="stylesheet">

<script>
var comp = 1;
function ajouter_competence(loopIndex,loopIndexComp) {
	comp++;
	var objTo = document.getElementById('champ_competences'+loopIndex.toString());
	var divtest = document.createElement("div");
	divtest.id='champ_competences'+loopIndex.toString()+'_'+(loopIndexComp+1).toString()+'';
	divtest.innerHTML ='<div class="col-md-8 col-sm-8 col-xs-8"><select style="margin-left: -10px;" class="form-control" name="choixCompetence'+loopIndex.toString()+'_'+(loopIndexComp+1).toString()+'" id="choixCompetence'+loopIndex.toString()+'_'+(loopIndexComp+1).toString()+'"><c:if test="${not empty competences }"><c:forEach var="competence" items="${ competences}"><option value="<c:out value="${competence.nom}"/>"><c:out value="${competence.nom}" /></option></c:forEach></c:if></select></div><div class="col-md-4 col-sm-4 col-xs-4 input-group"><input class="form-control" type="number" value="0" id="pourcentage'+loopIndex.toString()+'_'+(loopIndexComp+1).toString()+'" name="pourcentage'+loopIndex.toString()+'_'+(loopIndexComp+1).toString()+'" value="<c:out value="${exercice.pourcentages['+(loopIndexComp+1).toString()+']}"/>" size="20" maxlength="30" step="1" min="0" max="100" /><div class="input-group-addon">%</div></div></div><c:set var="loopIndex" value="'+loopIndex.toString()+'" /><c:set var="loopIndexComp" value="'+(loopIndexComp+2).toString()+'" />'
	objTo.appendChild(divtest)
	var objToDeux = document.getElementById('plus_comp_'+loopIndex);
	var tmp = loopIndexComp+1;
	objToDeux.onclick=function(){
		ajouter_competence(loopIndex,tmp);
	};
	var objToTrois = document.getElementById('moins_comp_'+loopIndex);
	console.log(tmp);
	objToTrois.onclick=function(){
		supprimer_competence(loopIndex,tmp);
	};
}
function supprimer_competence(loopIndex,loopIndexComp) {
	if(loopIndexComp>1){
		document.getElementById('choixCompetence'+loopIndex.toString()+'_'+loopIndexComp.toString()+'').parentElement.parentElement
				.remove();
		var objToDeux = document.getElementById('plus_comp_'+loopIndex);
		var tmp = loopIndexComp-1;
		objToDeux.onclick=function(){
			ajouter_competence(loopIndex,tmp);
		};
		var objToTrois = document.getElementById('moins_comp_'+loopIndex);
		console.log(tmp);
		objToTrois.onclick=function(){
			supprimer_competence(loopIndex,tmp);
		};
		comp--;
	}
}
Element.prototype.remove = function() {
	this.parentElement.removeChild(this);
}
NodeList.prototype.remove = HTMLCollection.prototype.remove = function() {
	for (var i = this.length - 1; i >= 0; i--) {
		if (this[i] && this[i].parentElement) {
			this[i].parentElement.removeChild(this[i]);
		}
	}
}
</script>

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

								<h2>Modifier les exercices d'un examen</h2>
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

								<c:if test="${not empty message}">
									<div class="alert alert-success alert-dismissible fade in"
										style="text-shadow: 0 0px 0 #fff;" role="alert">
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
										<c:out
											value="Succès de la mise à jour de l'exercice" />
									</div>
								</c:if>

								<c:if test="${empty sessionScope.examen}">

									<form method="post" action="ModifierExercice">

										<label>Sélectionner l'examen à modifier : </label>
										<div class="form-group">
											<select class="form-control select2" name="choixExamen"
												id="choixExamen" required>
												<c:forEach var="examen" items="${ examens}">
													<option value="<c:out value="${examen.id}"/>"><c:out
															value="${examen.nom} [${examen.matiere.nom}]" /></option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<button type="submit" class="btn btn-success btn-block">Modifier
												cette examen</button>
										</div>
										<br />
									</form>
								</c:if>

								<c:if test="${not empty sessionScope.examen}">

									<c:if test="${empty exercices}">
										<div class="alert alert-danger alert-dismissible fade in"
											style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out
												value="Aucun exercice n'est actuellement dans la base de données" />
										</div>
									</c:if>
									<c:if test="${not empty exercices}">

										<form method="post" action="ModifierExercice">
											<c:forEach var="exercice" items="${ exercices}"
												varStatus="loop">
												<p class="bg-success">Exercice N°${loop.index+1}</p>
												<c:if test="${not empty exercice.competences}">
													<div id="champ_competences${loop.index+1}">
														<c:forEach var="competenceExo"
															items="${exercice.competences.values() }"
															varStatus="loopComp">
															<label>Compétence(s) associées à cette exercice :
															</label>
															<div
																id="champ_competences${loop.index+1}_${loopComp.index+1}">

																<div class="col-md-8 col-sm-8 col-xs-8">

																	<select style="margin-left: -10px;"
																		class="form-control"
																		name="choixCompetence${loop.index+1}_${loopComp.index+1}"
																		id="choixCompetence${loop.index+1}_${loopComp.index+1}" required>
																		<option value="<c:out value="${competenceExo.nom}"/>">
																			<c:out value="${competenceExo.nom}" /></option>
																		<c:if test="${not empty competences }">
																			<c:forEach var="competence" items="${ competences}">
																				<c:if test="${ competence.nom != competenceExo.nom}">
																					<option value="<c:out value="${competence.nom}"/>">
																						<c:out value="${competence.nom}" /></option>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</select>

																</div>
																<div class="col-md-4 col-sm-4 col-xs-4 input-group">

																	<input class="form-control" type="number"
																		id="pourcentage${loop.index+1}_${loopComp.index+1}"
																		name="pourcentage${loop.index+1}_${loopComp.index+1}"
																		value="<c:out value="${exercice.pourcentages[loopComp.index+1]}"/>"
																		size="20" maxlength="30" step="1" min="0" max="100" required />
																	<div class="input-group-addon">%</div>
																</div>
															</div>
															<c:set var="loopIndex" value="${loop.index+1}" />
															<c:set var="loopIndexComp" value="${loopComp.index+1}" />
														</c:forEach>
													</div>

													<div class="input-group">
														<button class="btn btn-success" type="button"
															id="plus_comp_${loopIndex}"
															onclick="ajouter_competence(${loopIndex},${loopIndexComp});"
															value="Ajouter une competence">
															<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
														</button>
														<button class="btn btn-danger" type="button"
															id="moins_comp_${loopIndex}"
															onclick="supprimer_competence(${loopIndex},${loopIndexComp});"
															value="Supprimer une competence">
															<span class="glyphicon glyphicon-minus"
																aria-hidden="true"></span>
														</button>
													</div>

												</c:if>

												<c:if test="${empty competences}">
													<div class="alert alert-danger alert-dismissible fade in"
														style="text-shadow: 0 0px 0 #fff;" role="alert">
														<button type="button" class="close" data-dismiss="alert"
															aria-label="Close">
															<span aria-hidden="true">×</span>
														</button>
														<c:out
															value="Aucune compétence n'est actuellement dans la base de données" />
													</div>
												</c:if>

												<label>Question :</label>
												<input class="form-control" type="text"
													id="question${loop.index+1}" name="question${loop.index+1}"
													value="<c:out value="${exercice.question}"/>" size="200"
													maxlength="2000" required/>

												<span class="erreur">${form.erreurs['question${loop.index+1}']}</span>

												<br>

												<label>Réponse : </label>
												<input class="form-control" type="text"
													id="reponse${loop.index+1}" name="reponse${loop.index+1}"
													value="<c:out value="${exercice.reponse}"/>" size="200"
													maxlength="2000" required/>

												<span class="erreur">${form.erreurs['reponse${loop.index+1}']}</span>

												<br>

												<label>Nombre de points : </label>
												<div class="col-md-2 col-sm-2 col-xs-2 input-group">
													<input type="number" class="form-control"
														id="points${loop.index+1}" name="points${loop.index+1}"
														value="<c:out value="${exercice.nbPoints}"/>" size="20"
														maxlength="30" step="0.01" min="0" />
													<div class="input-group-addon">Points</div>
												</div>
												<span class="erreur">${form.erreurs['points${loop.index+1}']}</span>
											</c:forEach>
											<br />
											<button type="submit" class="btn btn-success btn-block">Valider
												les modifications</button>
										</form>
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