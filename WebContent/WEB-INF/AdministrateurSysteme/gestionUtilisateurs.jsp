<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Gestion des utilisateurs</title>
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
      <jsp:include page="../sidebar-gauche.jsp" />
             <!-- Barre du haut -->
            <jsp:include page="../topbar-navigation.jsp" />
            
      <!-- page content -->
      <div class="right_col" role="main">

        <!-- top tiles -->
        <div class="row tile_count">
          <div class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
            <div class="left"></div>
            <div class="right">
              <span class="count_top"><i class="fa fa-user"></i> Utilisateurs</span>
              <div class="count"><c:out value="${requestScope.nbUtilisateur}"></c:out></div>
            </div>
          </div>
          <div class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
            <div class="left"></div>
            <div class="right">
              <span class="count_top"><i class="fa fa-user"></i> Eleves</span>
              <div class="count"><c:out value="${requestScope.nbEleve}"></c:out></div>
            </div>
          </div>
          <div class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
            <div class="left"></div>
            <div class="right">
              <span class="count_top"><i class="fa fa-user"></i> Enseignants</span>
              <div class="count">${requestScope.nbEnseignant}</div>
            </div>
          </div>
          <div class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
            <div class="left"></div>
            <div class="right">
              <span class="count_top"><i class="fa fa-user"></i> Administrateurs</span>
              <div class="count"><c:out value="${requestScope.nbAdminSyst}"></c:out></div>
            </div>
          </div>
        </div>
        <!-- /top tiles -->

        <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel">
                <div class="x_title">
                  <h2>Gestion des utilisateurs</h2>
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
								<div class="col-md-4 col-sm-4 col-xs-4">
									<div >
										<treecontrol class="tree-classic"
													 tree-model="treedata"
													 options="opts"
													 selected-node="selected"
													 on-selection="eventSlection(node, selected)">
											{{node.roleName}} {{node.promotion}} {{node.nom}} {{node.prenom}} {{node.separator}} {{node.login}}
										</treecontrol>
									</div>
                </div>
               	<!-- /Arbre choix utilisateurs --> 
               	
               	<!-- Formaulaire modification utilisateur -->                	
               	<div class="col-md-8 col-sm-8 col-xs-8">
               		<form method="POST" action="GestionUtilisateurs" class="form-horizontal form-label-left">
               			<div class="form-group">
               			
	               			<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
	                      <input type="text" class="form-control has-feedback-left" id="nom" name="nom" placeholder="Nom">
	                      <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
	                    </div>
	
	                    <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
	                      <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prénom">
	                      <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
	                    </div>
	                    
	                    <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
	                      <input type="text" class="form-control has-feedback-left" id="username" name="login" placeholder="Login" required>
	                      <span class="fa fa-lock form-control-feedback left" aria-hidden="true"></span>
	                    </div>
	                    
	                    <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
	                      <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
	                      <span class="fa fa-key form-control-feedback right" aria-hidden="true"></span>
	                    </div>
	                    
	                    <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
	                      <input type="text" class="form-control has-feedback-left" id="email" name="email" placeholder="Email" >
	                      <span class="fa fa-envelope form-control-feedback left" aria-hidden="true"></span>
	                    </div>
                    	          
                    	<div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback">
	                    	<label >Groupes :</label> 
	                    	<br/>                     
	                    	<div class="col-md-4 col-sm-4 col-xs-12 form-group has-feedback">
		                      <input type="checkbox" name="cbAS" value="cbAS" class="flat"/> Administrateur
		                      <br />
		                      <input type="checkbox" name="cbDE" value="cbDE" class="flat" /> Directeur des études
		                      <br />
		                      <input type="checkbox" name="cbDP" value="cbDP" class="flat" /> Directeur des programmes
		                      <br />
	                    	</div>
	                    	<div class="col-md-4 col-sm-4 col-xs-12 form-group has-feedback">
		                      <input type="checkbox" name="cbEN" value="cbEN" class="flat" /> Enseignant
		                      <br />
		                      <input type="checkbox" name="cbERUE" value="cbERUE" class="flat" /> Enseignant réf UE
		                      <br />
		                      <input type="checkbox" name="cbERM" value="cbERM" class="flat" /> Enseignant réf mat.
		                      <br />
	                    	</div>
	                    	<div class="col-md-4 col-sm-4 col-xs-12 form-group has-feedback">
		                      <input type="checkbox" name="cbEL" id="cbEL"  value="cbEL" class="flat" /> Élève
		                      <br />
	                    	</div>

                    	</div>      
                    </div>
                    <div class="ln_solid"></div>
                    <div class="form-group">
	                    <div class="col-md-4 col-sm-4 col-xs-12">
	                    	<p>Sélection: <code>{{selected.nom}} {{selected.prenom}}</code></p>
	                    	<c:out value="${requestScope.form.erreurs['login']}" />
	                    	<c:out value="${requestScope.form.erreurs['password']}" />
	                    	<c:out value="${requestScope.form.erreurs['nom']}" />
	                    	<c:out value="${requestScope.form.erreurs['prenom']}" />
	                    	<c:out value="${requestScope.form.erreurs['email']}" />
	                    </div>
                      <div id="btns" class="col-md-8 col-sm-8 col-xs-12">
                        <button id="btn-annuler" type="button" class="btn btn-primary" ng-click="clearSelected()" onclick="uncheckAll()" >Annuler</button>
                        <button id="btn-ajouter" type="submit" name="ajouter" value="" class="btn btn-success">Ajouter</button>
                        <button id="btn-supprimer" type="submit" name="supprimer" value="" class="btn btn-danger" style="visibility: hidden;">Supprimer</button>
                      </div>
                    </div>
               		</form>
               	</div>
                <!-- /Formaulaire modification utilisateur -->  
              </div>
              </div>
            </div>
        </div>
        <br />

        <!-- footer content -->
      	<jsp:include page="../footer.jsp" />

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
    	arbre = ${requestScope.arbre}
    	$scope.treedata = arbre;
    	
    	$scope.opts = {
				dirSelectable: false
			};
    	
    	$scope.clearSelected = function() {
        $scope.selected = undefined;
      }
					
		  $scope.eventSlection = function(node, selected) {   	  
			  if(selected){
				  uncheckAll();
				  $scope.selected = node;
				  
				  document.getElementById('btn-supprimer').style.visibility = "";
				  document.getElementById('btn-supprimer').value = node.login;
	    	  document.getElementById('btn-ajouter').innerHTML = "Modifier";
	    	  document.getElementById('btn-ajouter').value = node.login;
	    	  document.getElementById('btn-ajouter').name = "modifier";
	    		document.getElementById("password").required = false;
	    	  document.getElementById('nom').value = node.nom;
	    	  document.getElementById('prenom').value = node.prenom;
	    	  document.getElementById('username').value = node.login;
	    	  document.getElementById('password').value = "";
	    	  document.getElementById('email').value = node.email;
	
	    	  var groupes = ["cbAS", "cbDE", "cbDP", "cbERUE", "cbERM", "cbEN", "cbEL"];
	    	  
	    	  // On parcourt le tabeau pour activer les checkbox
	    	  for(i=0; i<groupes.length; i++){
	    		  var taille = arbre[i].children.length;
	    		  if(groupes[i]=='cbEL'){	// Dans eleves il y a des promos donc...
	    			  for(var j= 0; j < taille; j++)
					    	  {
					    		  var nbElevesDansLaPromo = arbre[i].children[j].children.length;
					    		  for(var k=0; k < nbElevesDansLaPromo; k++){
						    		  var login = arbre[i].children[j].children[k].login;
					    			  if(node.login == login){
						    			  check(groupes[i]);
											}
					    		  }
					    	  }
	    		  } else {
	    			  for(var j= 0; j < taille; j++)
			    	  {								    			  
			    		  var login = arbre[i].children[j].login;
			    		  if(node.login == login){
			    			  check(groupes[i]);
								}
			    	  }
	    		  }
	    	  }
			  
			  } else {
				  $scope.selected = null;
				  uncheckAll();
			  }
		  };
    });
		  	
   	function check(id) {
			$('input[name='+id+']').prop('checked', true);
			$('input[name='+id+']').parent().addClass('checked'); 
		}
		function uncheckAll() {
			$("input:checkbox").prop('checked', false); 
			$("input:checkbox").parent().removeClass('checked');
			$("input:text").val('');
			$("input:password").val('');
			document.getElementById('btn-supprimer').style.visibility = "hidden";
			document.getElementById('btn-supprimer').value = "";
			document.getElementById('btn-ajouter').innerHTML = "Ajouter";
  	  document.getElementById('btn-ajouter').value = "";
  	  document.getElementById('btn-ajouter').name = "ajouter";
  		document.getElementById("password").required = true;
		}
		
		
	
		  
		  
	// 	  var tailleAdmin = ${sessionScope.test}[0].children.length;
	// 	  for(var i= 0; i < tailleAdmin; i++)
	// 	  {
	// 		  var login = ${sessionScope.test}[0].children[i].login;
	// 		  if(sel.login == login){
	// 			  check('cbAS');
	//					}
	// 	  }
		  
	// 	  var nbDirecteurEtudes = ${sessionScope.test}[1].children.length;
	// 	  for(var i= 0; i < nbDirecteurEtudes; i++)
	// 	  {
	// 		  var login = ${sessionScope.test}[1].children[i].login;
	// 		  if(sel.login == login){
	// 			  check('cbDE');
	//					}
	// 	  }
	
	// 	  var nbPromos = ${sessionScope.test}[6].children.length;
	// 	  for(var i= 0; i < nbPromos; i++)
	// 	  {
	// 		  var nbElevesDansLaPromo = ${sessionScope.test}[6].children[i].children.length;
	// 		  for(var j= 0; j < nbElevesDansLaPromo; j++){
	//	    		  var login = ${sessionScope.test}[6].children[i].children[j].login;
	// 			  if(sel.login == login){
	//	    			  check('cbEL');
	//						}
	// 		  }
	// 	  }

</script>
<c:if test="${not empty requestScope.form}">
	<script >
	var permanotice, tooltip, _alert;
	$(function() {
			new PNotify({
	  title: '<c:out value="${requestScope.form.resultat}" />',
	  type: 'error',
	  text: '<c:out value="${requestScope.form.erreurs[\'promotion\']}" />  <c:out value="${requestScope.form.erreurs[\'login\']}" /> ',
	  nonblock: {
	      nonblock: true,
	      nonblock_opacity: .2
	       }
	   });
	});
	</script>
</c:if>
</body>

</html>
