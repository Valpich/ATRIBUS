<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-3 left_col">
	<div class="left_col scroll-view">
    	<div class="navbar nav_title" style="border: 0;">
            <a href="<c:url value="/"/>" class="site_title"><i class="fa fa-graduation-cap"></i> <span>ATRIBUS</span></a>
        </div>
        <div class="clearfix"></div>

	<!-- menu prile quick info -->
	<div class="profile">
		<div class="profile_pic">
			<img src="<c:url value="/resources/images/imgUser.jpg"/>" class="img-circle profile_img">
		</div>
		<div class="profile_info">
			<span style="font-size: 14px;">Bonjour</span>
			<h2>
				<c:out value="${sessionScope.sessionUtilisateur.get(0).getPrenom()} " />
				<c:out value="${sessionScope.sessionUtilisateur.get(0).getNom()}" />
			</h2>
		</div>
	</div>
	<!-- /menu prile quick info -->

	<br />

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
	
	<!-- sidebar menu -->
	<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
		<div class="menu_section">
			<br><br><br>
			<ul class="nav side-menu">
			
			<!-- Administration -->
				<c:if test="${droitAdminSyst}">
						<li><a><i class="fa fa-dashboard"></i> Administration <span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu" style="display: none">
								<li><a href="<c:url value="/AdministrateurSysteme/GestionUtilisateurs"/>">Gestion des utilisateurs</a></li>
								<li><a href="<c:url value="/AdministrateurSysteme/Simuler"/>">Simuler des droits</a></li>
								<li><a href="<c:url value="/AdministrateurSysteme/AuthentificationLDAP"/>">Authentification LDAP</a></li>
								<li><a href="<c:url value="/AdministrateurSysteme/AfficherLogs"/>">Consulter les logs</a></li>
							</ul>
						</li>
				</c:if>
				
				<!-- Etudiants -->
					<c:if test="${droitDirecteurEtudes}">
						<li><a><i class="fa fa-user"></i> Etudiants <span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu" style="display: none">
								<li><a href="<c:url value="/DirecteurEtudes/ConsulterProgres"/>">Consulter les progrès</a></li>
								<li><a href="<c:url value="/DirecteurEtudes/ConvoquerEtudiant"/>">Convoquer un étudiant</a></li>
								<li><a href="<c:url value="/DirecteurEtudes/ExtraireToutesLesNotes"/>">Télécharger toutes les notes</a></li>
							</ul>
						</li>
					</c:if>
					
					<!-- Promotion -->
					<c:if test="${droitDirecteurEtudes}">
						<li><a><i class="fa fa-users"></i> Promotions <span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu" style="display: none">
								<li><a href="<c:url value="/DirecteurEtudes/ExtraireNotes"/>">Télécharger les notes</a></li>
							</ul>
						</li>
					</c:if>	
					
					<!-- Compétences -->
					<c:if test="${droitDirecteurProgrammes || droitDirecteurEtudes || droitEleve || droitEnseignant || droitEnseignantRefMatiere || droitEnseignantRefUe}">
						<li><a><i class="fa fa-list-ul"></i> Compétences <span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu" style="display: none">
	
							<c:if test="${droitDirecteurProgrammes}">
								<li><a href="<c:url value="/DirecteurProgrammes/GererCompetences"/>">Gestion du référentiel de compétences</a></li>
								<li><a href="<c:url value="/DirecteurProgrammes/ValiderCompetences"/>">Valider des compétences</a></li>
								</c:if>
								<c:if test="${droitEleve}">
								<li><a href="<c:url value="/AfficherCompetences"/>">Afficher le référentiel</a></li>
								<li><a href="<c:url value="/Eleve/AfficherMesCompetences"/>">Afficher mes compétences</a></li>
								<li><a href="<c:url value="/Eleve/ConsulterCompetencesExamen"/>">Afficher les compétences d'un examen</a></li>
								<li><a href="<c:url value="/Eleve/SuggererCompetence"/>">Suggérer compétence (Eleve)</a></li>
								</c:if>
								<c:if test="${droitEnseignant}">
								<li><a href="<c:url value="/Enseignant/SuggererCompetence"/>">Suggérer compétence (Enseignant)</a></li>
								</c:if>
								<c:if test="${droitEnseignantRefMatiere}">
								<li><a href="<c:url value="/AfficherCompetences"/>">Afficher le référentiel de compétences</a></li>
								<li><a href="<c:url value="/EnseignantRefMatiere/SuggererCompetence"/>">Suggérer compétence (ERM)</a></li>
								</c:if>
								<c:if test="${droitEnseignantRefUe}">
								<li><a href="<c:url value="/AfficherCompetences"/>">Afficher le référentiel de compétences</a></li>
								<li><a href="<c:url value="/EnseignantRefUE/SuggererCompetence"/>">Suggérer compétence (ERUE)</a></li>
								</c:if>
							</ul>
						</li>
						</c:if>
				 <!-- Unité enseignement -->	
					<c:if test="${droitEnseignantRefUe || droitEnseignantRefMatiere || droitDirecteurProgrammes || droitEnseignant}">

						<li><a><i class="fa fa-mortar-board"></i> Unités d'enseignement<span
								class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu" style="display: none">
							<c:if test="${droitDirecteurProgrammes}">
								<li><a href="<c:url value="/DirecteurProgrammes/AjouterUE"/>">Ajouter une unité d'enseignement</a></li>
								<li><a href="<c:url value="/DirecteurProgrammes/ModifierUE"/>">Modifier une unité d'enseignement</a></li>
								<li><a href="<c:url value="/DirecteurProgrammes/ModifierAssocierCompetenceUE"/>">Modifier les compétences d'une unité d'enseignement</a></li>
								<li><a href="<c:url value="/EnseignantRefUE/ModifierMatiereUE"/>">Modifier les matières d'une unité d'enseignement</a></li>
							</c:if>
							<c:if test="${droitEnseignantRefUe}">
								<li><a href="<c:url value="/EnseignantRefUE/AssocierCompetenceUE"/>">Associer des compétences d'une unité d'enseignement</a></li>
								<li><a href="<c:url value="/EnseignantRefUE/ModifierAssocierCompetenceUE"/>">Modifier les compétences d'une unité d'enseignement</a></li>
								<li><a href="<c:url value="/EnseignantRefUE/ModifierMatiereUE"/>">Modifier les matières d'une unité d'enseignement</a></li>
							</c:if>
							<c:if test="${droitEnseignantRefMatiere}">
								<li><a href="<c:url value="/EnseignantRefMatiere/AfficherCompetenceUE"/>">Afficher les compétences d'une unité d'enseignement</a></li>
							</c:if>
							<c:if test="${droitEnseignant}">
								<li><a href="<c:url value="/Enseignant/AfficherCompetenceUE"/>">Afficher les compétences d'une unité d'enseignement</a></li>
								<li><a href="<c:url value="/Enseignant/AfficherCompetenceMatiere"/>">Afficher les compétences d'une matière</a></li>
							</c:if>
							</ul>
						</li>
					</c:if>
					
				<!-- Matiere -->
				<c:if test="${droitEnseignantRefMatiere || droitEnseignant}">
				<li><a><i class="fa fa-book"></i> Matières <span class="fa fa-chevron-down"></span></a>
					
					<c:if test="${droitEnseignantRefUe}">
							<ul class="nav child_menu" style="display: none">
								<li><a href="<c:url value="/EnseignantRefUE/AjouterMatiere"/>"> Ajouter une matière </a></li>
								<li><a href="<c:url value="/EnseignantRefUE/ModifierMatiereUE"/>"> Modifier une matière </a></li>
	           			 	</ul>
            			</c:if>
            			<c:if test="${droitEnseignant}">
							<ul class="nav child_menu" style="display: none">
								<li><a href="<c:url value="/Enseignant/AfficherCompetenceMatiere"/>"> Afficher les compétences d'une matière </a></li>
	           			 	</ul>
            			</c:if>
            		</li>
            			
            		</c:if>
            		 <!-- Ressources -->
            		<c:if test="${droitEnseignantRefMatiere || droitEleve || droitEnseignantRefUe}">
						<li><a><i class="fa fa-upload"></i> Ressources <span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu" style="display: none">
							    <c:if test="${droitEnseignantRefMatiere}">
								<li><a href="<c:url value="/EnseignantRefMatiere/EnvoyerRessource"/>">Ajouter une ressource</a></li>
								<li><a href="<c:url value="/EnseignantRefMatiere/ModifierRessource"/>">Modifier une ressource</a></li>
								</c:if>
								<c:if test="${droitEleve}">
								<li><a href="<c:url value="/Eleve/ConsulterRessource"/>">Télécharger une ressource</a></li>
								</c:if>
							<c:if test="${droitEnseignantRefUe}">
								<li><a href="<c:url value="/EnseignantRefUE/UtilisationRessources"/>">Consulter l'historique d'utilisation des ressources</a></li>
								</c:if>
							</ul>
						</li>
						</c:if>
					
					<!-- Examen -->
					<c:if test="${droitEnseignantRefMatiere || droitEleve || droitEnseignant}">
						<li><a><i class="fa fa-edit"></i> Evaluations <span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu" style="display: none">
							<c:if test="${droitEnseignantRefMatiere }">
								<li><a href="<c:url value="/EnseignantRefMatiere/AjouterExamen"/>">Ajouter un examen</a></li>
								<li><a href="<c:url value="/EnseignantRefMatiere/ModifierExamen"/>">Modifier un examen</a></li>
								<li><a href="<c:url value="/EnseignantRefMatiere/AjouterExercice"/>">Ajouter un exercice</a></li>
								<li><a href="<c:url value="/EnseignantRefMatiere/AjouterAutoEvaluation"/>">Ajouter un exercice d'auto-évaluation</a></li>
								<li><a href="<c:url value="/EnseignantRefMatiere/ModifierExercice"/>">Modifier un exercice</a></li>
								<li><a href="<c:url value="/EnseignantRefMatiere/HistoriqueAutoEvaluation"/>">Consulter l'historique des auto-évaluations</a></li>
							</c:if>
							<c:if test="${droitEnseignant }">
								<li><a href="<c:url value="/Enseignant/CorrigerExamen"/>">Corriger un examen</a></li>
							</c:if>
							<c:if test="${droitEleve}">
								<li><a href="<c:url value="/Eleve/ConsulterAutoEvaluation"/>">Consulter mes auto-évaluations</a></li>
								<li><a href="<c:url value="/Eleve/ConsulterNotes"/>">Consulter mes notes</a></li>
								<li><a href="<c:url value="/Eleve/PasserExamen"/>">Passer un examen</a></li>
								<li><a href="<c:url value="/Eleve/PasserAutoEvaluation"/>">Passer une auto-évaluation</a></li>
							</c:if>
							</ul>
						</li>
				</c:if>
				
					
			</ul>
		</div>
	</div>
	<!-- /sidebar menu -->
	
		<!-- /menu footer buttons -->
          <div class="sidebar-footer hidden-small">
          	<a href="<c:url value="/"/>" data-toggle="tooltip" data-placement="top" title="Revenir à l'index">
              <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
            </a>
            <a href="https://outlook.office365.com/" data-toggle="tooltip" data-placement="top" title="Boite mail">
              <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
            </a>
            <a href="<c:url value="/ContacterAdministrateur"/>" data-toggle="tooltip" data-placement="top" title="Contacter l'administrateur">
              <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
            </a>
            <a href="<c:url value="/deconnexion"/>" data-toggle="tooltip" data-placement="top" title="Se déconnecter">
              <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
            </a>
          </div>
          <!-- /menu footer buttons -->
	</div>
</div>