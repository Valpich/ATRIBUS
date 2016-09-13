<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Modifier association Ue</title>

	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/fonts/css/font-awesome.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/animate.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/icheck/flat/green.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/maps/jquery-jvectormap-2.0.3.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/floatexamples.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap-select.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/form/bootstrap-formhelpers.css"/>" rel="stylesheet">

	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/nprogress.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-select.js"/>"></script>
	<script src="<c:url value="/resources/js/form/bootstrap-formhelpers.js"/>"></script>

  	<!-- Liste déroulante avec recherche -->
	<link href="<c:url value="/resources/css/select/select2.min.css"/>" rel="stylesheet">
	
	<!-- Tableau -->
	<link href="<c:url value="/resources/css/datatables/dataTables.bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/datatables/dataTables.responsive.css"/>" rel="stylesheet">
	
</head>

<body class="nav-md">

	<div class="container body">
		<div class="main_container">

			<!-- sidebar gauche -->
			<jsp:include page="../sidebar-gauche.jsp" />
			
			<jsp:include page="../topbar-navigation.jsp" />

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel tile">
						
							<div class="x_title">
								<h2>Modifier les matières composant une UE </h2>
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
								
								<c:if test="${empty listeUesMatieres && empty listeDesMatieres && empty matiereAModifier}">
									<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
										<c:out value="Les matières présentent ne sont associées à aucune unité d'enseignement."/>
									</div>
								</c:if>
								
								<c:if test="${not empty listeUesMatieres}">
								
									<c:if test="${form != null}">
										<c:if test="${form.resultat == 'succes'}">
											<div class="alert alert-success alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="La matière a été modifiée avec succès."/>
											</div>
										</c:if>
										
										<c:if test="${form.resultat == 'erreur'}">
											<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="La matière n'a pas été modifiée suite à une erreur imprévue, veuillez réesayer."/>
											</div>
										</c:if>
									</c:if>
								
									<form method="post" action="ModifierMatiereUE">
										
										<div class="form-group">
											<label>Sélectionner une unité d'enseignement :</label>
											<select class="form-control select2" name="ue" id="ue" required>
												<c:forEach var="ue" items="${listeUesMatieres}">
													<option value="<c:out value="${ue.id}"/>">
														<c:out value="${ue.nom}" />
													</option>
												</c:forEach>
											</select>
										</div>
										
										<div class="form-group">
											<button class="btn btn-success btn-block" name="choixUe" type="submit">Modifier les matières de cette unité d'enseignement</button>		
										</div>
	
									</form>
									
								</c:if>
								
								<c:if test="${not empty listeDesMatieres}">
									
									<c:if test="${not empty succesSupprimer}">
										<div class="alert alert-success alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${succesSupprimer}"/>
										</div>
									</c:if>
									
									<c:if test="${not empty echecSupprimer}">
										<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${echecSupprimer}"/>
										</div>
									</c:if>
								
									<table id="tableMatieres" class="table table-striped responsive-utilities jambo_table bulk_action">
										<thead>
											<tr>
												<th>ID</th>
												<th>Nom</th>
												<th>Coefficient</th>
												<th>Enseignant référant</th>
												<th></th>
											</tr>
										</thead>
											<tbody>
											
												<c:forEach var="matiere" items="${listeDesMatieres}">
													<tr>
														<form method="post" action="ModifierMatiereUE">
															<input class="form-control" name="idUe" type="hidden" value="<c:out value="${idUe}"/>"/>
															<td><input class="form-control" name="idMatiere" type="hidden" value="<c:out value="${matiere.id}"/>"/><c:out value="${matiere.id}"/></td>
															<td><input class="form-control" name="nomMatiere" type="hidden" value="<c:out value="${matiere.nom}"/>"/><c:out value="${matiere.nom}"/></td>
															<td><input class="form-control" name="coefMatiere" type="hidden" value="<c:out value="${matiere.coefficient}"/>"/><c:out value="${matiere.coefficient}"/></td>
															<td>
																<c:forEach var="erm" items="${erms}">
																	<c:if test="${erm.matiere.id == matiere.id}">
																		<input class="form-control" name="enseignantMatiere" type="hidden" value="<c:out value="${erm.id}"/>"/><c:out value="${erm.nom} ${erm.prenom}"/>
																	</c:if>
																</c:forEach>
															</td>
															<td class="text-center">		
																<button type="submit" class="btn btn-warning btn-xs" name="modifier" data-toggle="tooltip" data-placement="top" title="Modifier">
																	<span class="glyphicon glyphicon-edit"></span>
																</button>
																<button type="submit" class="btn btn-danger btn-xs" name="supprimer" data-toggle="tooltip" data-placement="top" title="Supprimer">
																	<span class="glyphicon glyphicon-trash"></span>
																</button>
															</td>		
														</form>
													</tr>
													
												</c:forEach>
											
											</tbody>
										</table>
										
										<br>
										
										<form method="get" action="ModifierMatiereUE">
											<button class="btn btn-success btn-block" type="submit">Choisir une autre unité d'enseignement</button>		
										</form>
									
								</c:if>
								
								<c:if test="${not empty matiereAModifier}">
								
									<c:if test="${form != null}">
									<c:if test="${form.resultat = 'erreur'}">
										<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="La modification de la matière n'a pas été effectuée"/>
										</div>
									</c:if>
								
									<c:if test="${form.erreurs['matiere'] != null}">
										<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${form.erreurs['matiere']}"/>
										</div>
									</c:if>
									
									<c:if test="${form.erreurs['coefficiant'] != null}">
										<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${form.erreurs['coefficiant']}"/>
										</div>
									</c:if>
									
									<c:if test="${form.erreurs['enseignant'] != null}">
										<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${form.erreurs['enseignant']}"/>
										</div>
									</c:if>
								
									<c:if test="${form.erreurs['ue'] != null}">
										<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<c:out value="${form.erreurs['ue']}"/>
										</div>
									</c:if>
									</c:if>
									
									<form method="post" action="ModifierMatiereUE">
										<input class="form-control" name="idMatiereAModifier" type="hidden" value="<c:out value="${matiereAModifier.id}"/>" required/>
										<label>Saisir le nouveau nom :</label>
										<div class="form-group">
											<input class="form-control" type="text" name="nomNouvelleMatiere" id="nomNouvelleMatiere" value="<c:out value="${matiereAModifier.nom}"/>" required/>
										</div>
										<div class="form-group col-md-6 col-sm-6 col-xs-6" style="margin-left: -10px;">
											<label>Sélectionner le nouvelle enseignant associé :</label>
											<input class="form-control" type="hidden" name="idAncienneEnseignant" value="${ancienneUe.enseignantRefUe.idEnseignantRefUe}" required/>
											<select class="form-control" name="idEnseignantNouvelleMatiere" id="idEnseignantNouvelleMatiere" required>
												<c:forEach var="enseignant" items="${listeEnseignants}">
													<option value="<c:out value="${enseignant.id}"/>">
														<c:out value="${enseignant.prenom} ${enseignant.nom}" />
													</option>
												</c:forEach>
											</select>
										</div>	
										<div class="form-group col-md-6 col-sm-6 col-xs-6" style="margin-left: -10px;">
											<label>Sélectionner l'unité d'enseignement associée :</label>
											<select class="form-control" name="idNouvelleUe" id="idNouvelleUe" required>
												<c:forEach var="ue" items="${listeUes}">
													<option value="<c:out value="${ue.id}"/>">
														<c:out value="${ue.nom}" />
													</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group col-md-12 col-sm-12 col-xs-12" style="margin-left: -10px;">
											<label>Saisir le nouveau coefficiant :</label>
											<input class="form-control" type="number" name="coefficient" id="coefficient"  value="<c:out value="${matiereAModifier.coefficient}"/>" required/>
										</div>
										<div class="form-group">	
											<br>
											<br>
											<button type="submit" class="btn btn-success btn-block" name="validerModifierMatiere">Valider la modification de cette matière</button>
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
					<jsp:include page="../footer.jsp"/>
					<!-- /footer content -->
				</div>
				<!-- /page content -->
			</div>
		</div>


		<div id="custom_notifications" class="custom-notifications dsp_none">
			<ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group"></ul>
		<div class="clearfix"></div>
		<div id="notif-group" class="tabbed_notifications"></div>
	</div>

	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/custom.js"/>"></script>
	<script src="<c:url value="/resources/js/select/select2.full.js"/>"></script>	
	<script src="<c:url value="/resources/js/datatables/jquery.dataTables.min.js"/>"></script>
    <script src="<c:url value="/resources/js/datatables/dataTables.bootstrap.min.js"/>"></script>	
	
	<script>

	$(document).ready(function() {
		$(".select2").select2();
	});
	
    $(document).ready(function() {
        $('#tableMatieres').DataTable ({
        	"language": {
        		"sProcessing":     "Traitement en cours...",
        		"sSearch":         "Rechercher&nbsp;: ",
        	    "sLengthMenu":     "Afficher _MENU_ &eacute;l&eacute;ments",
        		"sInfo":           "Affichage de l'&eacute;lement _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
        		"sInfoEmpty":      "Affichage de l'&eacute;lement 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
        		"sInfoFiltered":   "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
        		"sInfoPostFix":    "",
        		"sLoadingRecords": "Chargement en cours...",
        	    "sZeroRecords":    "Aucun &eacute;l&eacute;ment &agrave; afficher",
        		"sEmptyTable":     "Aucune donn&eacute;e disponible dans le tableau",
        		"oPaginate": {
        			"sFirst":      "Premier",
        			"sPrevious":   "Pr&eacute;c&eacute;dent",
        			"sNext":       "Suivant",
        			"sLast":       "Dernier"
        		},
        		"oAria": {
        			"sSortAscending":  ": activer pour trier la colonne par ordre croissant",
        			"sSortDescending": ": activer pour trier la colonne par ordre d&eacute;croissant"
        		}
			},
            responsive: true  
        });
    });
   
    $('#tableMatieres').on( 'click', 'tbody td:not(:first-child)', function (e) {
        editor.inline( this );
    } );

	</script>

</body>

</html>