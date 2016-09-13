<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Affichage du référentiel de compétence</title>
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
  <link href="<c:url value="/resources/css/maps/jquery-jvectormap-2.0.3.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/css/floatexamples.css"/>" rel="stylesheet">
  
	<!-- tree-control CSS (AngularJS) -->
	<link href="<c:url value="/resources/angularjs/tree-control/css/tree-control.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/angularjs/tree-control/css/tree-control-attribute.css"/>" rel="stylesheet">
	
	<!-- AngularJS + tree-control scripts -->
	<script src="<c:url value="/resources/angularjs/tree-control/angular.1.2.29.js"/>"></script>
	<script src="<c:url value="/resources/angularjs/tree-control/angular-tree-control.js"/>"></script>
	
	<!-- jquery + nprogress scripts -->
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
  <script src="<c:url value="/resources/js/nprogress.js"/>"></script>
    
</head>


<body class="nav-md">
  <div class="container body">
    <div class="main_container">

			<!-- sidebar gauche -->
			<jsp:include page="sidebar-gauche.jsp" />
			<!-- Barre du haut -->
			<jsp:include page="topbar-navigation.jsp" />
			
            
      <!-- page content -->
      <div class="right_col" role="main">

        <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel">
                <div class="x_title">
                  <h2>Référentiel de compétences</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li>
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Settings 1</a>
                        </li>
                        <li><a href="#">Settings 2</a>
                        </li>
                      </ul>
                    </li>
                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                    </li>
                  </ul>
                  <div class="clearfix"></div>
                </div>

                <div class="x_content" ng-app="arbreUtilisateurs" ng-controller="DirSelection" id="test">
                <br />
                <!-- Arbre choix utilisateurs --> 
								<div class="col-md-7 col-sm-7 col-xs-7">
									
										<c:if test="${form.resultat == 'succes'}">
											<div class="alert alert-success alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="La modification de la compétence a été effectuée avec succès"/>
											</div>
										</c:if>
										<c:if test="${form.resultat == 'echec'}">
											<div class="alert alert-success alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="La modification de la compétence a échoué, veuillez réessayer"/>
											</div>
										</c:if>
										
										<c:if test="${not empty form.erreurs['nom']}">
											<div class="alert alert-success alert-dismissible fade in" style="text-shadow: 0 0px 0 #fff;" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<c:out value="${form.erreurs['nom']}"/>
											</div>
										</c:if>
										
									
								
									<div >
										<treecontrol class="tree-classic"
													 tree-model="treedata"
													 options="opts"
													 selected-node="selected"
													 on-selection="eventSlection(node, selected)">
											{{node.numerotation}} - {{node.nomCompetence}}
										</treecontrol>
									</div>
                </div>
               	<!-- /Arbre choix utilisateurs --> 
               	
               							<!-- Tests sur les droits -->
	<c:forEach items="${sessionScope.sessionUtilisateur}" var="utilisateur">
		<c:if test="${utilisateur.getClass().getSimpleName() == 'AdminSyst' }">
			<c:set var="droitAdminSyst" value="true" scope="request" />
		</c:if>
		<c:if test="${utilisateur.getClass().getSimpleName() == 'EnseignantRefMatiere' }">
			<c:set var="droitEnseignantRefMatiere" value="true" scope="request" />
		</c:if>
		<c:if test="${utilisateur.getClass().getSimpleName() == 'DirecteurEtudes' }">
			<c:set var="droitDirecteurEtudes" value="true" scope="request" />
		</c:if>
		<c:if test="${utilisateur.getClass().getSimpleName() == 'Eleve' }">
			<c:set var="droitEleve" value="true" scope="request" />
		</c:if>
		<c:if test="${utilisateur.getClass().getSimpleName() == 'Enseignant' }">
			<c:set var="droitEnseignant" value="true" scope="request" />
		</c:if>
		<c:if test="${utilisateur.getClass().getSimpleName() == 'DirecteurProgrammes' }">
			<c:set var="droitDirecteurProgrammes" value="true" scope="request" />
		</c:if>
		<c:if test="${utilisateur.getClass().getSimpleName() == 'EnseignantRefUe' }">
			<c:set var="droitEnseignantRefUe" value="true" scope="request" />
		</c:if>
	</c:forEach>
               	
               	<!-- Formaulaire modification utilisateur -->                	
               	<div class="col-md-5 col-sm-5 col-xs-5">
               		<form method="POST" action="GererCompetences" class="form-horizontal form-label-left">
               			<div class="form-group">
	               			<div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-left: -3px;">
	               			
	                      <input type="text" class="form-control " id="nomCompetence" name="nomCompetence" placeholder="Nom">
	                    </div>
	
                    	          
      
                    </div>
	
		
				<c:if test="${droitDirecteurProgrammes}">			
                    <div class="form-group">
                      <div id="btns" class="col-md-12 col-sm-12 col-xs-12">
                        <input type="hidden" id="idCompetence" name="idCompetence" value="" />
                        <input type="hidden" id="parentId" name="parentId" value="0" />
                        <input type="hidden" id="profondeur" name="profondeur" value="1" />

                        <button id="btn-annuler" type="button" class="btn btn-primary" ng-click="clearSelected()" onclick="uncheckAll()" style="width: 99px">Annuler</button>
                        <button id="btn-modifier" type="submit" name="modifier" value="" class="btn btn-warning" style="width: 99px">Modifier</button>
                        <button id="btn-supprimer" type="submit" name="supprimer" value="" class="btn btn-danger" style="width: 99px">Supprimer</button>
                        <button id="btn-ajouter" type="submit" name="ajouter" value="" class="btn btn-success" style="width: 99px">Ajouter</button>
                        
                      </div>
                    </div>
                    </c:if>
               		</form>
               	</div>
               	
                <!-- /Formaulaire modification utilisateur -->  
              </div>
              </div>
            </div>
        </div>
        <br />

        <!-- footer content -->
      	<jsp:include page="footer.jsp" />

      </div>
      <!-- /page content -->
    </div>

  </div>

  <div id="custom_notifications" class="custom-notifications dsp_none">
    <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
    </ul>
    <div class="clearfix"></div>
    <div id="notif-group" class="tabbed_notifications"></div>
  </div>


	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/progressbar/bootstrap-progressbar.min.js"/>"></script>
	<script src="<c:url value="/resources/js/icheck/icheck.min.js"/>"></script>
	<script src="<c:url value="/resources/js/custom.js"/>"></script>
	
	<!-- PNotify -->
	<script src="<c:url value="/resources/js/notify/pnotify.core.js"/>"></script>
	<script src="<c:url value="/resources/js/notify/pnotify.buttons.js"/>"></script>
	<script src="<c:url value="/resources/js/notify/pnotify.nonblock.js"/>"></script>
	
  
  <script>
    var arbreUtilisateurs = angular.module("arbreUtilisateurs", ["treeControl"]);
    
    arbreUtilisateurs.controller('DirSelection', function($scope) {
    	var arbre = ${requestScope.referentiel};
//     	var arbre = [
//     	 			{ "nomComp" : "Comp1", "children" : [
//     	 			  { "nomComp" : "Comp1.1", "children" : []},
//     	 			  { "nomComp" : "Comp1.2", "children" : []},
//     	 			  { "nomComp" : "Comp1.3", "children" : [
//     	 					{ "nomComp" : "Comp1.3.1", "children" : []}
//     	 				]}
//     	 			]},
//     	 			{ "nomComp" : "Comp2", "children" : [
//     	 			  { "nomComp" : "Comp2.1", "children" : []},
//     	 			  { "nomComp" : "Comp2.2", "children" : []},
//     	 			  { "nomComp" : "Comp2.3", "children" : [
//     	 					{ "nomComp" : "Comp1.3.1", "children" : []}
//     	 				]}
//     	 			]}
//     	 		  ];
    	$scope.treedata = arbre;
    	
    	$scope.opts = {
			dirSelectable: true
		};
    	
    	$scope.clearSelected = function() {
        	$scope.selected = undefined;
      	}
    	
    	
		
		$scope.eventSlection = function(node, selected) {
			if(selected){
	    	  document.getElementById('nomCompetence').value = node.nomCompetence;
	    	  document.getElementById('idCompetence').value = node.id;
	    	  document.getElementById('parentId').value = node.id;
	    	  document.getElementById('profondeur').value = node.profondeur + 1;


			}

		};
    });
    
	function uncheckAll() {
		$("input:text").val('');
		document.getElementById('nomCompetence').value = "";
  	  	document.getElementById('idCompetence').value = "";
  	  	document.getElementById('parentId').value = "0";
  	  	document.getElementById('profondeur').value = "1";
// 		document.getElementById('btn-supprimer').style.visibility = "hidden";
// 		document.getElementById('btn-supprimer').value = "";
// 		document.getElementById('btn-ajouter').innerHTML = "Ajouter";
// 	  	document.getElementById('btn-ajouter').value = "";
// 	  	document.getElementById('btn-ajouter').name = "ajouter";
// 		document.getElementById("password").required = true;
	}

</script>
</body>

</html>
