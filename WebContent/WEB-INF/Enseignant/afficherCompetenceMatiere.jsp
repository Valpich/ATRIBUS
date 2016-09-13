<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Afficher compétences matière</title>
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
	
	<!-- Tableau -->	
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
					<div class="col-md-12 col-sm-12 col-xs-6">
						<div class="x_panel tile">
							<div class="x_title">
							
								<h2>Afficher les compétences liées à une matière</h2>
								<ul class="nav navbar-right panel_toolbox"
									style="min-width: 0px;">
									<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
									<li><a class="close-link"><i class="fa fa-close"></i></a></li>
								</ul>
								<div class="clearfix"></div>
								
							</div>
							
							<div class="x_content">
								
								<c:if test="${not empty matieres}">
									
									<form method="post" action="AfficherCompetenceMatiere">
									
										<div style="font-size: 14px; margin-bottom: 8px; margin-top: 12px;">
											Veuillez sélectionner la matière : 
										</div>
									
										<div class="form-group">
											<c:if test="${not empty matieres}">
												<select class="form-control select2" name="nomMatiere" id="nomMatiere" required>
													<c:forEach var="matiere" items="${matieres}">
														<option value="<c:out value="${matiere.nom}"/>">
														<c:out value="${matiere.nom}" /></option>
													</c:forEach>
												</select>
											</c:if>
										</div>
									
										<div class="form-group">
											<button type="submit" class="btn btn-success btn-block">Afficher les compétences liées à cette matière</button>
										</div>
										
									</form>
								</c:if>
								
								<c:if test="${empty matieres}">
									<c:if test="${empty listeCompetence}">
									<div class="alert alert-danger alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
										<c:out value="Cette maitère ne contient aucune compétence" />
									</div>
																		<div class="form-group">
										<form method="get" action="AfficherCompetenceMatiere">
											<button type="submit" class="btn btn-primary btn-block" required>Revenir à la liste des matières</button>
										</form>
									</div>
									</c:if>
								</c:if>							
								<c:if test="${not empty listeCompetence}">
								
									<table id="tableCompetences" class="table table-striped responsive-utilities jambo_table bulk_action">
										<thead>
											<tr>
												<th>ID</th>
												<th>Nom</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="competence" items="${listeCompetence}">
												<tr>
													<td><c:out value="${competence.id}"/></td>
													<td><c:out value="${competence.nom}"/></td>
												</tr>	
											</c:forEach>
										</tbody>
									</table>
									
									<div class="form-group">
										<form method="get" action="AfficherCompetenceMatiere">
											<button type="submit" class="btn btn-primary btn-block">Revenir à la liste des matières</button>
										</form>
									</div>
									
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
	<script src="<c:url value="/resources/js/custom.js"/>"></script>
	
	<!-- Init liste déroulante avec recherche -->
	<script src="<c:url value="/resources/js/select/select2.full.js"/>"></script>
		
	<script src="<c:url value="/resources/js/datatables/jquery.dataTables.min.js"/>"></script>
    <script src="<c:url value="/resources/js/datatables/dataTables.bootstrap.min.js"/>"></script>	
	
	<script>
	$(document).ready(function() {
		  $(".select2").select2();
		});
	</script>
	
	<script type="text/javascript">
												
    $(document).ready(function() {
		  $('#tableCompetences').DataTable({
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
   
    </script>

</body>

</html>